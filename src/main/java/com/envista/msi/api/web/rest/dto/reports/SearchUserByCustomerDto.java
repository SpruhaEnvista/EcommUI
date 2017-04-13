package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Siddhant on 11/04/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "SearchUserByCustomer.getReportUserCustomers", procedureName = "shp_rpt_sel_user_custmr_proc",
                resultSetMappings = "SelectUserCustomer",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "get_sel_user_customer", type = Void.class)
        }),
        @NamedStoredProcedureQuery(name = "SearchUserByCustomer.getReportSearchUsers", procedureName = "shp_rpt_sched_srch_user_proc",
                resultSetMappings = "SearchUsers",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_customer_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_full_name", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_email", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_is_user_only", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "get_search_results", type = Void.class)
        })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SelectUserCustomer", classes = {
                @ConstructorResult(
                targetClass = SearchUserByCustomerDto.class,
                columns = {
                        @ColumnResult(name = "customer_id", type = Long.class),
                        @ColumnResult(name = "customer_name", type = String.class),
                })
        }),
        @SqlResultSetMapping(name = "SearchUsers", classes = {
                @ConstructorResult(
                        targetClass = SearchUserByCustomerDto.class,
                        columns = {
                                @ColumnResult(name = "user_id", type = Long.class),
                                @ColumnResult(name = "user_name", type = String.class),
                                @ColumnResult(name = "fullname", type = String.class),
                                @ColumnResult(name = "email", type = String.class)
                        })
        })
})
@Entity
public class SearchUserByCustomerDto implements Serializable {
    @Id
    private Long id;

    @Column(name="customer_id")
    private Long customerId;

    @Column(name="customer_name")
    private String customerName;

    @Column(name="user_id")
    private Long userId;

    @Column(name="user_name")
    private String userName;

    @Column(name="fullname")
    private String fullName;

    @Column(name="email")
    private String email;


    public SearchUserByCustomerDto() { }

    public SearchUserByCustomerDto(Long customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public SearchUserByCustomerDto( Long userId, String userName, String fullName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
    }

    public Long getId() { return id; }

    public void setId(Long id) {this.id = id;}

    public Long getCustomerId() {return customerId;}

    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getCustomerName() {return customerName; }

    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public Long getUserId() {return userId; }

    public void setUserId(Long userId) {this.userId = userId; }

    public String getUserName() {return userName;}

    public void setUserName(String userName) {this.userName = userName;}

    public String getFullName() {return fullName;}

    public void setFullName(String fullName) {this.fullName = fullName; }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}
}
