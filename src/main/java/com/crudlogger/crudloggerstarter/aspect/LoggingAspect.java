package com.crudlogger.crudloggerstarter.aspect;

import com.crudlogger.crudloggerstarter.aspect.logger.model.LogEntity;
import com.crudlogger.crudloggerstarter.aspect.logger.model.json.ActionStatusEnum;
import com.crudlogger.crudloggerstarter.aspect.logger.model.json.Params;
import com.crudlogger.crudloggerstarter.aspect.logger.model.json.RequestDataChange;
import com.crudlogger.crudloggerstarter.aspect.logger.model.json.ResponseDataAfterChange;
import com.crudlogger.crudloggerstarter.aspect.logger.service.LogEntityService;
import com.crudlogger.crudloggerstarter.crud.controller.advice.PointcutException;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;


/**
 * @author ogbozoyan
 * @date 06.04.2023
 * надо оставить только вывод финальной версии
 */

@Aspect
@Component
@RequiredArgsConstructor
public abstract class LoggingAspect {
    @Autowired
    private HttpServletRequest httpRequest;
    @Autowired
    private HttpServletResponse httpResponse;
    @Autowired
    private LogEntityService logEntityService;
    //    @Autowired
//    private UserService userService;
    @Autowired
    private ObjectWriter objectWriter;
    private ContentCachingRequestWrapper requestWrapper;
    private ContentCachingResponseWrapper responseWrapper;
    private LogEntity logEntity;
    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(com.crudlogger.crudloggerstarter.aspect.ToLogger)")
    public void callLogger() {
    }

    @Before("@annotation(com.crudlogger.crudloggerstarter.aspect.ToLogger)")
    public void before(JoinPoint joinPoint) throws IOException {


        logEntity = new LogEntity();
        initRequest();

        ToLogger toLogger = getLogger(joinPoint);
        //TODO: AbstractUserService
        logEntity.setUserId(null);
        logEntity.setUserLogin(null);
        /*=================================================*/
        logEntity.setClientRequestIp(httpRequest.getRemoteAddr());
        logEntity.setHttpMethodEnum(toLogger.httpMethod());
        logEntity.setUrl(httpRequest.getRequestURL().toString());
        logEntity.setActionDomain(toLogger.actionDomain());
        logEntity.setAction(toLogger.action());

        // Set the request data change
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

        logEntity.setRequestDataChange(objectWriter.writeValueAsString(requestDataChange).replaceAll("[\n\r\t]", ""));

        logEntity.setDtCreate(null);
        logEntity.setActionStatus(null);
        logEntity.setResponseStatus(null);
        logEntity.setBaseException(null);
        logEntity.setStackTraceOnError(null);
        logEntity.setResponseDataAfterChange(null);

    }

    @AfterReturning(value = "@annotation(com.crudlogger.crudloggerstarter.aspect.ToLogger)", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) throws IOException {
        ToLogger toLogger = getLogger(joinPoint);
        initResponse();
        initRequest();
        ResponseDataAfterChange responseDataAfterChange = new ResponseDataAfterChange();

        if (toLogger.returnResponse()) {
            responseDataAfterChange.setBody(getResponseBody(returnValue));
        }

        logEntity.setResponseDataAfterChange(
                objectWriter.writeValueAsString(responseDataAfterChange).replaceAll("[\n\r\t]", "")
        );

        logEntity.setResponseStatus(String.valueOf(httpResponse != null ? httpResponse.getStatus() : 0));
        logEntity.setActionStatus(String.valueOf(ActionStatusEnum.SUCCESSFULLY));
        logEntity.setDtCreate(Timestamp.valueOf(LocalDateTime.now()));

        if (toLogger.isSaveInDataBase()) {
            logEntityService.save(logEntity);
        }
        if (toLogger.isPrintToTerminal()) {
            logger.info(toLogger.printAsJson() ?
                    objectWriter.writeValueAsString(logEntity).replaceAll("[\n\r\t]", "")
                    : logEntity.toString());
        }

    }

    @AfterThrowing(value = "@annotation(com.crudlogger.crudloggerstarter.aspect.ToLogger)", throwing = "exception")
    public void afterError(JoinPoint joinPoint, Throwable exception) throws IOException {
        ToLogger toLogger = getLogger(joinPoint);
        initResponse();
        initRequest();

        logEntity.setResponseDataAfterChange(null);
        logEntity.setResponseStatus(String.valueOf(500));
        logEntity.setActionStatus(String.valueOf(ActionStatusEnum.ERROR));
        logEntity.setBaseException(exception.getClass().getSimpleName());
        logEntity.setStackTraceOnError(ExceptionUtils.getMessage(exception));
        logEntity.setDtCreate(Timestamp.valueOf(LocalDateTime.now()));

        if (toLogger.isSaveInDataBase()) {
            logEntityService.save(logEntity);
        }
        if (toLogger.isPrintToTerminal()) {
            logger.info(toLogger.printAsJson() ?
                    objectWriter.writeValueAsString(logEntity).replaceAll("[\n\r\t]", "")
                    : logEntity.toString());
        }
    }


    private String getRequestBody(ContentCachingRequestWrapper request) {
        try {
            String bodyRequest = new String(request.getContentAsByteArray(), StandardCharsets.UTF_8);
            bodyRequest = bodyRequest.replaceAll("[\n\r\t]", "");
            return bodyRequest;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PointcutException(e.getClass().getSimpleName() + " Error while getting request in pointcut: " + e.getMessage());
        }
    }

    private String getResponseBody(Object response) {
        try {
            return objectWriter.writeValueAsString(response).replaceAll("[\n\r\t]", "");
        } catch (Exception e) {
            e.printStackTrace();
            throw new PointcutException(e.getClass().getSimpleName() + " Error while getting response in pointcut: " + e.getMessage());
        }
    }

    private void initRequest() {
        this.httpRequest = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        this.requestWrapper = (ContentCachingRequestWrapper) httpRequest;

    }

    private void initResponse() {
        this.httpResponse = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
        this.responseWrapper = (ContentCachingResponseWrapper) httpResponse;
    }


    private ToLogger getLogger(JoinPoint joinPoint) {
        /*================Extract information from the annotation===============*/
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getAnnotation(ToLogger.class);
        /*=====================================================================*/
    }
}

