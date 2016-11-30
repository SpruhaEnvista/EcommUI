/**
 * 
 */
package com.envista.msi.api.freight.ui.pojo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author SANKER
 *
 */
public class OutBound {
	private long id;
	private Integer qty;
	private Integer weight;
	private Integer uom;
	private String description;
	private String freightClass;
	private String serviceCode;
	private Integer totalPallets;
	private String equipmentType;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	/**
	 * 
	 */
	public OutBound() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param qty
	 * @param weight
	 * @param uom
	 * @param description
	 * @param freightClass
	 * @param serviceCode
	 * @param totalPallets
	 * @param equipmentType
	 * @param additionalProperties
	 */
	public OutBound(long id, Integer qty, Integer weight, Integer uom, String description, String freightClass,
			String serviceCode, Integer totalPallets, String equipmentType, Map<String, Object> additionalProperties) {
		super();
		this.id = id;
		this.qty = qty;
		this.weight = weight;
		this.uom = uom;
		this.description = description;
		this.freightClass = freightClass;
		this.serviceCode = serviceCode;
		this.totalPallets = totalPallets;
		this.equipmentType = equipmentType;
		this.additionalProperties = additionalProperties;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the qty
	 */
	public Integer getQty() {
		return qty;
	}
	/**
	 * @param qty the qty to set
	 */
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	/**
	 * @return the weight
	 */
	public Integer getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	/**
	 * @return the uom
	 */
	public Integer getUom() {
		return uom;
	}
	/**
	 * @param uom the uom to set
	 */
	public void setUom(Integer uom) {
		this.uom = uom;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the freightClass
	 */
	public String getFreightClass() {
		return freightClass;
	}
	/**
	 * @param freightClass the freightClass to set
	 */
	public void setFreightClass(String freightClass) {
		this.freightClass = freightClass;
	}
	/**
	 * @return the serviceCode
	 */
	public String getServiceCode() {
		return serviceCode;
	}
	/**
	 * @param serviceCode the serviceCode to set
	 */
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	/**
	 * @return the totalPallets
	 */
	public Integer getTotalPallets() {
		return totalPallets;
	}
	/**
	 * @param totalPallets the totalPallets to set
	 */
	public void setTotalPallets(Integer totalPallets) {
		this.totalPallets = totalPallets;
	}
	/**
	 * @return the equipmentType
	 */
	public String getEquipmentType() {
		return equipmentType;
	}
	/**
	 * @param equipmentType the equipmentType to set
	 */
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	/**
	 * @return the additionalProperties
	 */
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	/**
	 * @param additionalProperties the additionalProperties to set
	 */
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 10;
		return "OutBound [id=" + id + ", " + (qty != null ? "qty=" + qty + ", " : "")
				+ (weight != null ? "weight=" + weight + ", " : "") + (uom != null ? "uom=" + uom + ", " : "")
				+ (description != null ? "description=" + description + ", " : "")
				+ (freightClass != null ? "freightClass=" + freightClass + ", " : "")
				+ (serviceCode != null ? "serviceCode=" + serviceCode + ", " : "")
				+ (totalPallets != null ? "totalPallets=" + totalPallets + ", " : "")
				+ (equipmentType != null ? "equipmentType=" + equipmentType + ", " : "") + (additionalProperties != null
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
