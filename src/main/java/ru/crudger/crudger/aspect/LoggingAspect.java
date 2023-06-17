package ru.crudger.crudger.aspect;

import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import ru.crudger.crudger.aspect.logger.model.LogEntity;
import ru.crudger.crudger.aspect.logger.model.json.ActionStatusEnum;
import ru.crudger.crudger.aspect.logger.model.json.Params;
import ru.crudger.crudger.aspect.logger.model.json.RequestDataChange;
import ru.crudger.crudger.aspect.logger.model.json.ResponseDataAfterChange;
import ru.crudger.crudger.aspect.logger.service.LogEntityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

import static ru.crudger.crudger.configuration.GeneralCrudgerAutoconfiguration.REGEX_FOR_LOGGER;


/**
 * The LoggingAspect class is an aspect that provides logging functionality for annotated methods.
 * It captures the request and response data, logs the method execution details, and saves the log entity to the database.
 *
 * @author ogbozoyan
 * @date 06.04.2023
 */

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private HttpServletRequest httpRequest;
    @Autowired
    private HttpServletResponse httpResponse;
    @Autowired
    private LogEntityService logEntityService;
    /*
    @Autowired
    private UserService userService;
    */
    @Autowired
    private ObjectWriter objectWriter;
    private ContentCachingRequestWrapper requestWrapper;
    private ContentCachingResponseWrapper responseWrapper;
    private LogEntity logEntity;
    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Pointcut definition for methods annotated with @ToLogger.
     */
    @Pointcut("@annotation(ru.crudger.crudger.aspect.ToLogger)")
    public void callLogger() {
    }

    /**
     * Advice executed before the annotated method execution.
     */
    @Before("@annotation(ru.crudger.crudger.aspect.ToLogger)")
    public void before(JoinPoint joinPoint) {
        try {
            logEntity = new LogEntity();
            initRequest();

            ToLogger toLogger = getLogger(joinPoint);
            /*
            logEntity.setUserId(userService.getCurrentUserId());
            logEntity.setUserLogin(userService.getCurrentUserName());
            */
            logEntity.setClientRequestIp(httpRequest.getRemoteAddr());
            logEntity.setHttpMethodEnum(toLogger.httpMethod());
            logEntity.setUrl(httpRequest.getRequestURL().toString());
            logEntity.setActionDomain(toLogger.actionDomain());
            logEntity.setAction(toLogger.action());

            RequestDataChange requestDataChange = new RequestDataChange();
            List<Params> paramsList = new ArrayList<>();

            Enumeration<String> paramNames = httpRequest.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String name = paramNames.nextElement();
                String[] values = httpRequest.getParameterValues(name);
                paramsList.add(new Params(name, values));
            }
            requestDataChange.setParams(paramsList);
            requestDataChange.setBody(getRequestBody(requestWrapper));

            logEntity.setRequestDataChange(objectWriter.writeValueAsString(requestDataChange).replaceAll(REGEX_FOR_LOGGER, ""));

            logEntity.setDtCreate(null);
            logEntity.setActionStatus(null);
            logEntity.setResponseStatus(null);
            logEntity.setBaseException(null);
            logEntity.setStackTraceOnError(null);
            logEntity.setResponseDataAfterChange(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Advice executed after the annotated method execution.
     */
    @AfterReturning(value = "@annotation(ru.crudger.crudger.aspect.ToLogger)", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
        try {
            ToLogger toLogger = getLogger(joinPoint);
            initResponse();
            initRequest();
            ResponseDataAfterChange responseDataAfterChange = new ResponseDataAfterChange();

            if (toLogger.returnResponse()) {
                responseDataAfterChange.setBody(getResponseBody(returnValue));
            }

            logEntity.setResponseDataAfterChange(objectWriter.writeValueAsString(responseDataAfterChange).replaceAll(REGEX_FOR_LOGGER, ""));

            logEntity.setResponseStatus(String.valueOf(httpResponse != null ? httpResponse.getStatus() : 0));
            logEntity.setActionStatus(String.valueOf(ActionStatusEnum.SUCCESSFULLY));
            logEntity.setDtCreate(Timestamp.valueOf(LocalDateTime.now()));

            if (toLogger.isSaveInDataBase()) {
                logEntityService.save(logEntity);
            }
            logger.info(logEntity.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Advice executed after the annotated method throws an exception.
     */
    @AfterThrowing(value = "@annotation(ru.crudger.crudger.aspect.ToLogger)", throwing = "exception")
    public void afterError(JoinPoint joinPoint, Throwable exception) {
        try {
            ToLogger toLogger = getLogger(joinPoint);
            initResponse();
            initRequest();

            logEntity.setResponseDataAfterChange(null);
            logEntity.setResponseStatus(String.valueOf(500));
            logEntity.setActionStatus(String.valueOf(ActionStatusEnum.ERROR));
            logEntity.setBaseException(exception.getClass().getSimpleName());
            logEntity.setStackTraceOnError(ExceptionUtils.getMessage(exception));
            logEntity.setDtCreate(Timestamp.valueOf(LocalDateTime.now()));


            logEntityService.save(logEntity);
            logger.info(logEntity.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the request body from the given request wrapper.
     */
    private String getRequestBody(ContentCachingRequestWrapper request) {
        try {
            String bodyRequest = new String(request.getContentAsByteArray(), StandardCharsets.UTF_8);
            bodyRequest = bodyRequest.replaceAll(REGEX_FOR_LOGGER, "");
            return bodyRequest;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves the response body from the given response object.
     */
    private String getResponseBody(Object response) {
        try {
            if (response == null)
                return null;
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Initializes the current request.
     */
    private void initRequest() {
        this.httpRequest = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        this.requestWrapper = (ContentCachingRequestWrapper) httpRequest;

    }

    /**
     * Initializes the current request.
     */
    private void initResponse() {
        this.httpResponse = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
        this.responseWrapper = (ContentCachingResponseWrapper) httpResponse;
    }

    /**
     * Retrieves the @ToLogger annotation from the given join point.
     */
    private ToLogger getLogger(JoinPoint joinPoint) {
        /*================Extract information from the annotation===============*/
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getAnnotation(ToLogger.class);
        /*=====================================================================*/
    }
}

