package com.envista.msi.api.web.rest.dto.invoicing;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by KRISHNAREDDYM on 4/28/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "InvoicingRuleDto.findById", procedureName = "SHP_INV_GET_RULES_BY_ID_PRO",
                resultClasses = InvoicingRuleDetailsDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_INVOICING_RULE_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_RULES_INFO", type = Void.class)
                })
})

@Entity
public class InvoicingRuleDetailsDto implements Serializable {

    @Id
    @Column(name = "Invoicing_Rule_Det_Id")
    private Long invoicingRuleDetId;

    @Column(name = "INVOICING_RULE_ID")
    private Long id;

    @Column(name = "RULE_NAME")
    private String ruleName;

    @Column(name = "IS_ACTIVE")
    boolean active;

    @Column(name = "EFFECTIVE_DATE")
    private String effectiveDate;

    @Column(name = "EXPIRY_DATE")
    private String expiryDate;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "WHERE_CLAUSE")
    private String whereClause;

    @Column(name = "CUSTOMER_CODE")
    private String customerCode;

    @Column(name = "CODE_VALUE")
    private String codeValue;

    @Column(name = "CRIT_OPERATOR")
    private String critOperator;

    @Column(name = "AND_OR_OPERATOR")
    private String andOrOperator;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "ISMATCH")
    private boolean matchCase;

  /*  public InvoicingRuleDto() {
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCritOperator() {
        return critOperator;
    }

    public void setCritOperator(String critOperator) {
        this.critOperator = critOperator;
    }

    public String getAndOrOperator() {
        return andOrOperator;
    }

    public void setAndOrOperator(String andOrOperator) {
        this.andOrOperator = andOrOperator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isMatchCase() {
        return matchCase;
    }

    public void setMatchCase(boolean matchCase) {
        this.matchCase = matchCase;
    }
}
