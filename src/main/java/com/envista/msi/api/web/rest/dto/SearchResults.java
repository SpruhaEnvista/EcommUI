package com.envista.msi.api.web.rest.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * SearchResults
 * 
 * @author SANKER
 */

public class SearchResults {
	@JsonProperty("invoices")
	private List<InvoiceDetails> invoices = new ArrayList<InvoiceDetails>();

	@JsonProperty("page")
	private Long page = null;

	@JsonProperty("sort")
	private String sort = null;

	public SearchResults invoices(List<InvoiceDetails> invoices) {
		this.invoices = invoices;
		return this;
	}

	public SearchResults addInvoicesItem(InvoiceDetails invoicesItem) {
		this.invoices.add(invoicesItem);
		return this;
	}

	/**
	 * Get invoices
	 * 
	 * @return invoices
	 **/
	public List<InvoiceDetails> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<InvoiceDetails> invoices) {
		this.invoices = invoices;
	}

	public SearchResults page(Long page) {
		this.page = page;
		return this;
	}

	/**
	 * Get page
	 * 
	 * @return page
	 **/
	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}

	public SearchResults sort(String sort) {
		this.sort = sort;
		return this;
	}

	/**
	 * Get sort
	 * 
	 * @return sort
	 **/
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SearchResults searchResults = (SearchResults) o;
		return Objects.equals(this.invoices, searchResults.invoices) && Objects.equals(this.page, searchResults.page)
				&& Objects.equals(this.sort, searchResults.sort);
	}

	@Override
	public int hashCode() {
		return Objects.hash(invoices, page, sort);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("SearchResults {\n");

		sb.append("    invoices: ").append(toIndentedString(invoices)).append("\n");
		sb.append("    page: ").append(toIndentedString(page)).append("\n");
		sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
