package ru.crudger.crudger.aspect.logger.model;

import org.hibernate.Hibernate;
import ru.crudger.crudger.aspect.logger.model.json.ActionDomainEnum;
import ru.crudger.crudger.aspect.logger.model.json.ActionEnum;
import ru.crudger.crudger.aspect.logger.model.json.HttpMethodEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * The {@code LogEntity} class represents a log entity in the system.
 *
 * <p>It is annotated with {@code @Entity} and {@code @Table} annotations to specify the database table mapping.</p>
 *
 * <p>Properties:</p>
 * <ul>
 *     <li>{@link #id}: The unique identifier of the log entity</li>
 *     <li>{@link #userId}: The user ID associated with the log</li>
 *     <li>{@link #userLogin}: The login name of the user</li>
 *     <li>{@link #clientRequestIp}: The IP address of the client making the request</li>
 *     <li>{@link #httpMethodEnum}: The HTTP method used in the request</li>
 *     <li>{@link #url}: The URL of the request</li>
 *     <li>{@link #action}: The action performed</li>
 *     <li>{@link #actionDomain}: The domain of the action</li>
 *     <li>{@link #requestDataChange}: The request data change information</li>
 *     <li>{@link #responseDataAfterChange}: The response data after a change operation</li>
 *     <li>{@link #actionStatus}: The status of the action</li>
 *     <li>{@link #responseStatus}: The status of the response</li>
 *     <li>{@link #dtCreate}: The timestamp of when the log was created</li>
 *     <li>{@link #baseException}: The base exception associated with the log</li>
 *     <li>{@link #stackTraceOnError}: The stack trace on error</li>
 * </ul>
 *
 * @author ogbozoyan
 * @date 17.03.2023
 */
@Entity
@Table(name = "system_f_log", schema = "public")
public class LogEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_login")
    private String userLogin;
    @Column(name = "client_request_source_ip")
    private String clientRequestIp;
    @Column(name = "http_method")
    @Enumerated(EnumType.STRING)
    private HttpMethodEnum httpMethodEnum;
    @Column(name = "url")
    private String url;
    @Column(name = "action")
    @Enumerated(EnumType.STRING)
    private ActionEnum action;
    @Column(name = "action_domain")
    @Enumerated(EnumType.STRING)
    private ActionDomainEnum actionDomain;
    @Column(name = "request_data_change", columnDefinition = "varchar")
    private String requestDataChange;
    @Column(name = "response_data_after_change", columnDefinition = "varchar")
    private String responseDataAfterChange;
    @Column(name = "action_status")
    private String actionStatus;
    @Column(name = "response_status")
    private String responseStatus;
    @Column(name = "dt_create")
    private Timestamp dtCreate;
    @Column(name = "base_exception")
    private String baseException;
    @Column(name = "stack_trace_on_error")
    private String stackTraceOnError;

    public LogEntity() {
    }

    public LogEntity(Long id, String userId, String userLogin, String clientRequestIp, HttpMethodEnum httpMethodEnum, String url, ActionEnum action, ActionDomainEnum actionDomain, String requestDataChange, String responseDataAfterChange, String actionStatus, String responseStatus, Timestamp dtCreate, String baseException, String stackTraceOnError) {
        this.id = id;
        this.userId = userId;
        this.userLogin = userLogin;
        this.clientRequestIp = clientRequestIp;
        this.httpMethodEnum = httpMethodEnum;
        this.url = url;
        this.action = action;
        this.actionDomain = actionDomain;
        this.requestDataChange = requestDataChange;
        this.responseDataAfterChange = responseDataAfterChange;
        this.actionStatus = actionStatus;
        this.responseStatus = responseStatus;
        this.dtCreate = dtCreate;
        this.baseException = baseException;
        this.stackTraceOnError = stackTraceOnError;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LogEntity logEntity = (LogEntity) o;
        return getId() != null && Objects.equals(getId(), logEntity.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Long getId() {
        return this.id;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getUserLogin() {
        return this.userLogin;
    }

    public String getClientRequestIp() {
        return this.clientRequestIp;
    }

    public HttpMethodEnum getHttpMethodEnum() {
        return this.httpMethodEnum;
    }

    public String getUrl() {
        return this.url;
    }

    public ActionEnum getAction() {
        return this.action;
    }

    public ActionDomainEnum getActionDomain() {
        return this.actionDomain;
    }

    public String getRequestDataChange() {
        return this.requestDataChange;
    }

    public String getResponseDataAfterChange() {
        return this.responseDataAfterChange;
    }

    public String getActionStatus() {
        return this.actionStatus;
    }

    public String getResponseStatus() {
        return this.responseStatus;
    }

    public Timestamp getDtCreate() {
        return this.dtCreate;
    }

    public String getBaseException() {
        return this.baseException;
    }

    public String getStackTraceOnError() {
        return this.stackTraceOnError;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setClientRequestIp(String clientRequestIp) {
        this.clientRequestIp = clientRequestIp;
    }

    public void setHttpMethodEnum(HttpMethodEnum httpMethodEnum) {
        this.httpMethodEnum = httpMethodEnum;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAction(ActionEnum action) {
        this.action = action;
    }

    public void setActionDomain(ActionDomainEnum actionDomain) {
        this.actionDomain = actionDomain;
    }

    public void setRequestDataChange(String requestDataChange) {
        this.requestDataChange = requestDataChange;
    }

    public void setResponseDataAfterChange(String responseDataAfterChange) {
        this.responseDataAfterChange = responseDataAfterChange;
    }

    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public void setDtCreate(Timestamp dtCreate) {
        this.dtCreate = dtCreate;
    }

    public void setBaseException(String baseException) {
        this.baseException = baseException;
    }

    public void setStackTraceOnError(String stackTraceOnError) {
        this.stackTraceOnError = stackTraceOnError;
    }

    public String toString() {
        return "LogEntity(id=" + this.getId() + ", userId=" + this.getUserId() + ", userLogin=" + this.getUserLogin() + ", clientRequestIp=" + this.getClientRequestIp() + ", httpMethodEnum=" + this.getHttpMethodEnum() + ", url=" + this.getUrl() + ", action=" + this.getAction() + ", actionDomain=" + this.getActionDomain() + ", requestDataChange=" + this.getRequestDataChange() + ", responseDataAfterChange=" + this.getResponseDataAfterChange() + ", actionStatus=" + this.getActionStatus() + ", responseStatus=" + this.getResponseStatus() + ", dtCreate=" + this.getDtCreate() + ", baseException=" + this.getBaseException() + ", stackTraceOnError=" + this.getStackTraceOnError() + ")";
    }
}
