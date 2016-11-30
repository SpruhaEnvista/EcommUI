/**
 * 
 */
package com.envista.msi.api.web.rest.dto;

import com.envista.msi.api.domain.util.BasicIdNamePair;

/**
 * @author SANKER
 *
 */
public class UserAssignedCustomer extends BasicIdNamePair<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BasicIdNamePair<Long> parent;

	/**
	 * 
	 */
	public UserAssignedCustomer() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 */
	public UserAssignedCustomer(Long id, String name) {
		super(id, name);
	}

	/**
	 * @return the parent
	 */
	public BasicIdNamePair<Long> getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(BasicIdNamePair<Long> parent) {
		this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserAssignedCustomer [" + (parent != null ? "parent=" + parent + ", " : "")
				+ (super.toString() != null ? "toString()=" + super.toString() : "") + "]";
	}

}
