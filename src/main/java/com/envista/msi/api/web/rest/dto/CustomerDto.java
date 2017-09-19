package com.envista.msi.api.web.rest.dto;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by user1 on 1/18/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "CustomerTb.getCustomersList", procedureName = "SHP_F_GET_CUST_BY_USER_PROC",
                resultClasses = CustomerDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "custList", type = void.class)
                }),
        @NamedStoredProcedureQuery(name = "CustomerDto.getCustomersById", procedureName = "SHP_GET_CUSTOMER_BY_ID_PROC",
                resultSetMappings = {"CustomerDto.getCustomersByIdMapping"},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_customer_ids", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_customer_list", type = void.class)
                })

})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "CustomerDto.getCustomersByIdMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = CustomerDto.class,
                                columns = {
                                        @ColumnResult(name = "customer_id", type = Long.class),
                                        @ColumnResult(name = "customer_name", type = String.class)
                                }
                        )
                }
        )
})
@Entity
public class CustomerDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "customer_id", unique = true)
    private Long customerId;

    @Column(name="customer_name")
    private String customerName;

    public CustomerDto() {
    }

    public CustomerDto(Long customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


}
