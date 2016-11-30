/**
 * 
 */
package com.envista.msi.api.web.rest.dto;

/**
 * @author SANKER
 *
 */
public class CarrierScacName {

	long id;
	String name;
	String scac;

	/**
	 * 
	 */
	public CarrierScacName() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param name
	 * @param active
	 * @param scac
	 */
	public CarrierScacName(long id, String name, String scac) {
		super();
		this.id = id;
		this.name = name;
		this.scac = scac;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the scac
	 */
	public String getScac() {
		return scac;
	}

	/**
	 * @param scac
	 *            the scac to set
	 */
	public void setScac(String scac) {
		this.scac = scac;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CarrierScacName [id=" + id + ", " + (name != null ? "name=" + name + ", " : "") + ", "
				+ (scac != null ? "scac=" + scac : "") + "]";
	}
}
