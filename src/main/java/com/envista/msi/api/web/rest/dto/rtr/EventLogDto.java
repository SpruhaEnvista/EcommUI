package com.envista.msi.api.web.rest.dto.rtr;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "EventLogDto.getEventLogs", procedureName = "get_event_logs",
                resultSetMappings = "EventLogDto.getEventLogs",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "job_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "refcur_event_log",
                                type = Void.class)
                })})
@SqlResultSetMappings(
        @SqlResultSetMapping(
                name = "EventLogDto.getEventLogs",
                classes = {
                        @ConstructorResult(
                                targetClass = EventLogDto.class,
                                columns = {
                                        @ColumnResult(name = "event_log_id", type = Long.class),
                                        @ColumnResult(name = "created", type = String.class),
                                        @ColumnResult(name = "user_id", type = Long.class),
                                        @ColumnResult(name = "log_level", type = String.class),
                                        @ColumnResult(name = "rating_component", type = String.class),
                                        @ColumnResult(name = "job_id", type = Long.class),
                                        @ColumnResult(name = "entry_content", type = String.class),
                                        @ColumnResult(name = "username", type = String.class),
                                        @ColumnResult(name = "rating_version", type = Long.class),
                                        @ColumnResult(name = "customer_code", type = String.class)
                                }
                        )
                }
        )
)

@Entity
//@Table(name = "event_log")
public class EventLogDto implements Serializable {

    @Id
    @Column(name = "event_log_id")
    private Long eventLogid;
    @Column(name = "created")
    private String created;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "log_level")
    private String logLevel;
    @Column(name = "rating_component")
    private String ratingComponent;
    @Column(name = "job_id")
    private Long jobId;
    @Column(name = "entry_content")
    private String entryContent;
    @Column(name = "username")
    private String userName;
    @Column(name = "rating_version")
    private Long ratingVersion;
    @Column(name = "customer_code")
    private String customerCode;

    public Long getEventLogid() {
        return eventLogid;
    }

    public void setEventLogid(Long eventLogid) {
        this.eventLogid = eventLogid;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getRatingComponent() {
        return ratingComponent;
    }

    public void setRatingComponent(String ratingComponent) {
        this.ratingComponent = ratingComponent;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getEntryContent() {
        return entryContent;
    }

    public void setEntryContent(String entryContent) {
        this.entryContent = entryContent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getRatingVersion() {
        return ratingVersion;
    }

    public void setRatingVersion(Long ratingVersion) {
        this.ratingVersion = ratingVersion;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public EventLogDto(Long eventLogid, String created, Long userId, String logLevel, String ratingComponent, Long jobId, String entryContent, String userName, Long ratingVersion, String customerCode) {
        this.eventLogid = eventLogid;
        this.created = created;
        this.userId = userId;
        this.logLevel = logLevel;
        this.ratingComponent = ratingComponent;
        this.jobId = jobId;
        this.entryContent = entryContent;
        this.userName = userName;
        this.ratingVersion = ratingVersion;
        this.customerCode = customerCode;
    }

    public EventLogDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventLogDto)) return false;
        EventLogDto that = (EventLogDto) o;
        return Objects.equals(getEventLogid(), that.getEventLogid()) &&
                Objects.equals(getCreated(), that.getCreated()) &&
                Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getLogLevel(), that.getLogLevel()) &&
                Objects.equals(getRatingComponent(), that.getRatingComponent()) &&
                Objects.equals(getJobId(), that.getJobId()) &&
                Objects.equals(getEntryContent(), that.getEntryContent()) &&
                Objects.equals(getUserName(), that.getUserName()) &&
                Objects.equals(getRatingVersion(), that.getRatingVersion()) &&
                Objects.equals(getCustomerCode(), that.getCustomerCode());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getEventLogid(), getCreated(), getUserId(), getLogLevel(), getRatingComponent(), getJobId(), getEntryContent(), getUserName(), getRatingVersion(), getCustomerCode());
    }


}
