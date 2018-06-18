package com.envista.msi.api.web.rest.dto.rtr;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 6/13/2018.
 */
@XmlRootElement(name = "Results")
@XmlAccessorType(XmlAccessType.NONE)
public class RateSetResponse implements Serializable {

    @XmlElement(name = "StatusCode")
    private String statusCode;

    @XmlElement(name = "StatusMessage")
    private String statusMessage;

    @XmlElementWrapper(name = "RateSets")
    @XmlElement(name = "RateSet", required = true)
    private List<RateSetResponse.RateSet> rateSets = new ArrayList<RateSetResponse.RateSet>();


    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public List<RateSet> getRateSets() {
        return rateSets;
    }

    public void setRateSets(List<RateSet> rateSets) {
        this.rateSets = rateSets;
    }

    @Override
    public String toString() {
        return "RateSetResponse{" +
                "rateSets=" + rateSets +
                '}';
    }

    @XmlAccessorType(XmlAccessType.NONE)
    //@XmlAccessorType(XmlAccessType.FIELD)
    public static class RateSet implements Serializable {

        @XmlElement(name = "Name")
        private String name;

        @XmlElement(name = "Domain")
        private String domain;

        @XmlElement(name = "CustomerCode")
        private String customerCode;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getCustomerCode() {
            return customerCode;
        }

        public void setCustomerCode(String customerCode) {
            this.customerCode = customerCode;
        }

        @Override
        public String toString() {
            return "RateSet{" +
                    "name='" + name + '\'' +
                    ", domain='" + domain + '\'' +
                    ", customerCode='" + customerCode + '\'' +
                    '}';
        }
    }
}
