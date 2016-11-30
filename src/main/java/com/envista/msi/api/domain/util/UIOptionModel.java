/**
 * 
 */
package com.envista.msi.api.domain.util;

import java.io.Serializable;

/**
 * 
 * <p>
 * A simple class encapsulating a ui option values.
 * 
 * disabled disabled Specifies that an option should be disabled label text
 * Specifies a shorter label for an option selected selected Specifies that
 * an option should be pre-selected when the page loads value
 * </p>
 * 
 * 
 * @author SANKER
 *
 */
public class UIOptionModel implements Serializable {

	/**
	* disabled disabled Specifies that an option should be disabled label text
	* Specifies a shorter label for an option selected selected Specifies that
	* an option should be pre-selected when the page loads value
	*/
	private static final long serialVersionUID = 1L;

	/**
	 * Name.
	 */
	private String label;

	/**
	 * Value.
	 */
	private String value;

	/**
	 * disabled.
	 */
	private boolean disabled;

	/**
	 * selected.
	 */
	private boolean selected;

	/**
	 * @param label
	 * @param value
	 * @param disabled
	 * @param selected
	 */
	public UIOptionModel(String label, String value, boolean disabled, boolean selected) {
		super();
		this.label = label;
		this.value = value;
		this.disabled = disabled;
		this.selected = selected;
	}

	/**
	 * @param label
	 * @param value
	 * @param disabled
	 */
	public UIOptionModel(String label, String value, boolean disabled) {
		this(label, value, false, false);
	}

	/**
	 * @param label
	 * @param value
	 */
	public UIOptionModel(String label, String value) {
		this(label, value, false);
	}

	/**
	 * 
	 */
	public UIOptionModel() {
		super();
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the disabled
	 */
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (disabled ? 1231 : 1237);
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + (selected ? 1231 : 1237);
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UIOptionModel other = (UIOptionModel) obj;
		if (disabled != other.disabled)
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (selected != other.selected)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UIOptionModel [" + (label != null ? "label=" + label + ", " : "")
				+ (value != null ? "value=" + value + ", " : "") + "disabled=" + disabled + ", selected=" + selected
				+ "]";
	}
}
