/**
 * 
 */
package com.envista.msi.api.repository.freight;

import java.util.Date;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.util.StringUtils;

import com.envista.msi.api.domain.freight.ShpNspInvoiceDetailsTb;
import com.envista.msi.api.domain.freight.ShpNspInvoiceDetailsTb_;
import com.envista.msi.api.web.rest.dto.SearchCriteria;

/**
 * @author SANKER
 *
 */
public class InvoiceDetailsSpecs {
	
/*	private Date checkDate;
	private Long checkAmount;
	private Long customerId;
	private String invoiceStatus;
	private String checkNumber;
	private String  runNumber;
	private String searchInvoiceId;
	private String bolNumber;
	private String invoiceNumber;
	private String invoiceMode;
	private String dateCriteriaId;
	private String invoiceMethod;
	private String poNumber;
	private String proNumber;
	private Boolean includeExceptionInvoices;
	private String receiverZip;
	private String billToZip;
	private String shipperZip;*/

	public static Specification<ShpNspInvoiceDetailsTb> withCustomerId(Long id) {
		return (root, query, cb) -> {
	         return cb.equal(root.get(ShpNspInvoiceDetailsTb_.shpCustomerProfileTb), id);
	      };
	}
	public static Specification<ShpNspInvoiceDetailsTb> withSearchInvoiceId(Long nspInvoiceDetailsId) {
		return (root, query, cb) -> {
	         return cb.equal(root.get(ShpNspInvoiceDetailsTb_.nspInvoiceDetailsId), nspInvoiceDetailsId);
	      };
	}
	public static Specification<ShpNspInvoiceDetailsTb> hasCheckDate(Date date) {
		return (root, query, cb) -> {
	         return cb.equal(root.get(ShpNspInvoiceDetailsTb_.checkDate), date);
	      };
	}
	public static Specification<ShpNspInvoiceDetailsTb> hasCheckAmount(Long value) {
		return (root, query, cb) -> {
	         return cb.equal(root.get(ShpNspInvoiceDetailsTb_.checkAmount), value);
	      };
	}
	public static Specification<ShpNspInvoiceDetailsTb> hasRunNumber(String runNumber) {
		return (root, query, cb) -> {
	         return cb.equal(root.get(ShpNspInvoiceDetailsTb_.runNo), runNumber);
	      };
	}
	//TODO
	/*public static Specification<ShpNspInvoiceDetailsTb> withCheckNumber(String value) {
		return (root, query, cb) -> {
	         return cb.equal(root.get(ShpNspInvoiceDetailsTb_.checkNumber), value);
	      };
	}*/
		
	public static Specification<ShpNspInvoiceDetailsTb> invoiceStatus(String status) {
		return (root, query, cb) -> {
	         return cb.equal(root.get(ShpNspInvoiceDetailsTb_.shpNspCodeValuesTb1), status);
	      };
	}
	static Specification<ShpNspInvoiceDetailsTb> hasTitle(String title) {
        return (root, query, cb) -> {
            //Create query here
        	return null;
        };
    }
	
	
	// TODO
	public static Specification<ShpNspInvoiceDetailsTb> buildCriteria(SearchCriteria searchCriteria) {
		Specification<ShpNspInvoiceDetailsTb> emptySpecs = (root, query, cb) -> {
			return cb.gt(root.get(ShpNspInvoiceDetailsTb_.nspInvoiceDetailsId), -1);
		};
		Specifications<ShpNspInvoiceDetailsTb> specs = Specifications.where(emptySpecs);
		
		if (searchCriteria.getCustomerId() != null && searchCriteria.getCustomerId() > 0) {
			specs.and(withCustomerId(searchCriteria.getCustomerId()));
		}

		if (!StringUtils.isEmpty(searchCriteria.getSearchInvoiceId())) {
			specs = specs.and(withSearchInvoiceId(Long.valueOf(searchCriteria.getSearchInvoiceId())));
		}
		
		if (!StringUtils.isEmpty(searchCriteria.getInvoiceStatus())) {
			specs = specs.and(invoiceStatus(searchCriteria.getInvoiceStatus()));
		}

		return specs;
	}
}
