package com.crudlogger.crudloggerstarted.aspect.logger.model;

import com.crudlogger.crudloggerstarted.aspect.logger.model.json.ActionDomainEnum;
import com.crudlogger.crudloggerstarted.aspect.logger.model.json.ActionEnum;
import com.crudlogger.crudloggerstarted.aspect.logger.model.json.HttpMethodEnum;
import com.crudlogger.crudloggerstarted.crud.model.bigint.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author ogbozoyan
 * @date 17.03.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "system_f_log")
public class LogEntity extends AbstractEntity implements Serializable {
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
}
