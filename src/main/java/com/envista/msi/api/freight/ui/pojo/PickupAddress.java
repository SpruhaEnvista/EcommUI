/**
 * 
 */
package com.envista.msi.api.freight.ui.pojo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author SANKER
 *
 */
public class PickupAddress {
	private long id;
	private long stopNumber;
	private String name;
	private String line1;
	private String line2;
	private String city;
	private String state;
	private String postal;
	private String country;
	private String airportCode;
	private String bol;
	private String po;
	private boolean startPoint;
	private boolean endPoint;
	private List<InBound> inBounds;
	private List<OutBound> outBounds;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 */
	public PickupAddress() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param line1
	 * @param line2
	 * @param city
	 * @param state
	 * @param postal
	 * @param country
	 * @param airportCode
	 * @param bol
	 * @param po
	 * @param startPoint
	 * @param endPoint
	 * @param inBounds
	 * @param outBounds
	 * @param additionalProperties
	 */
	public PickupAddress(String name, String line1, String line2, String city, String state, String postal,
			String country, String airportCode, String bol, String po, boolean startPoint, boolean endPoint,
			List<InBound> inBounds, List<OutBound> outBounds, Map<String, Object> additionalProperties) {
		super();
		this.name = name;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.state = state;
		this.postal = postal;
		this.country = country;
		this.airportCode = airportCode;
		this.bol = bol;
		this.po = po;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.inBounds = inBounds;
		this.outBounds = outBounds;
		this.additionalProperties = additionalProperties;
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
	 * @return the stopNumber
	 */
	public long getStopNumber() {
		return stopNumber;
	}

	/**
	 * @param stopNumber the stopNumber to set
	 */
	public void setStopNumber(long stopNumber) {
		this.stopNumber = stopNumber;
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
	 * @return the line1
	 */
	public String getLine1() {
		return line1;
	}

	/**
	 * @param line1
	 *            the line1 to set
	 */
	public void setLine1(String line1) {
		this.line1 = line1;
	}

	/**
	 * @return the line2
	 */
	public String getLine2() {
		return line2;
	}

	/**
	 * @param line2
	 *            the line2 to set
	 */
	public void setLine2(String line2) {
		this.line2 = line2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the postal
	 */
	public String getPostal() {
		return postal;
	}

	/**
	 * @param postal
	 *            the postal to set
	 */
	public void setPostal(String postal) {
		this.postal = postal;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the airportCode
	 */
	public String getAirportCode() {
		return airportCode;
	}

	/**
	 * @param airportCode
	 *            the airportCode to set
	 */
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	/**
	 * @return the bol
	 */
	public String getBol() {
		return bol;
	}

	/**
	 * @param bol
	 *            the bol to set
	 */
	public void setBol(String bol) {
		this.bol = bol;
	}

	/**
	 * @return the po
	 */
	public String getPo() {
		return po;
	}

	/**
	 * @param po
	 *            the po to set
	 */
	public void setPo(String po) {
		this.po = po;
	}

	/**
	 * @return the startPoint
	 */
	public boolean isStartPoint() {
		return startPoint;
	}

	/**
	 * @param startPoint
	 *            the startPoint to set
	 */
	public void setStartPoint(boolean startPoint) {
		this.startPoint = startPoint;
	}

	/**
	 * @return the endPoint
	 */
	public boolean isEndPoint() {
		return endPoint;
	}

	/**
	 * @param endPoint
	 *            the endPoint to set
	 */
	public void setEndPoint(boolean endPoint) {
		this.endPoint = endPoint;
	}

	/**
	 * @return the inBounds
	 */
	public List<InBound> getInBounds() {
		return inBounds;
	}

	/**
	 * @param inBounds
	 *            the inBounds to set
	 */
	public void setInBounds(List<InBound> inBounds) {
		this.inBounds = inBounds;
	}

	/**
	 * @return the outBounds
	 */
	public List<OutBound> getOutBounds() {
		return outBounds;
	}

	/**
	 * @param outBounds
	 *            the outBounds to set
	 */
	public void setOutBounds(List<OutBound> outBounds) {
		this.outBounds = outBounds;
	}

	/**
	 * @return the additionalProperties
	 */
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	/**
	 * @param additionalProperties
	 *            the additionalProperties to set
	 */
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 10;
		return "PickupAddress [id=" + id + ", " + (name != null ? "name=" + name + ", " : "")
				+ (line1 != null ? "line1=" + line1 + ", " : "") + (line2 != null ? "line2=" + line2 + ", " : "")
				+ (city != null ? "city=" + city + ", " : "") + (state != null ? "state=" + state + ", " : "")
				+ (postal != null ? "postal=" + postal + ", " : "")
				+ (country != null ? "country=" + country + ", " : "")
				+ (airportCode != null ? "airportCode=" + airportCode + ", " : "")
				+ (bol != null ? "bol=" + bol + ", " : "") + (po != null ? "po=" + po + ", " : "") + "startPoint="
				+ startPoint + ", endPoint=" + endPoint + ", "
				+ (inBounds != null ? "inBounds=" + toString(inBounds, maxLen) + ", " : "")
				+ (outBounds != null ? "outBounds=" + toString(outBounds, maxLen) + ", " : "")
				+ (additionalProperties != null
						? "additionalProperties=" + toString(additionalProperties.entrySet(), maxLen) : "")
				+ "]";
	}

	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}

}
