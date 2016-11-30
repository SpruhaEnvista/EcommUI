/**
 * 
 */
package com.envista.msi.api.domain.util;

import java.io.Serializable;

/**
 * 
 * <p>A simple class encapsulating a name/value pair.</p>
 * 
 * 
 * @author SANKER
 *
 */
public class BasicNameValuePair<V>  implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructor.
     * @param name The name.
     * @param value The value.
     */
    public BasicNameValuePair(String name, V value) {
    	// Make sure the name is not null
    	if (name == null) {
    		throw new NullPointerException("Name should not be null");
    	}
        this.name = name;
        this.value = value;
    }

   /**
     * Name.
     */
    private String name = null;

    /**
     * Value.
     */
    private V value = null;

    /**
     * Set the name.
     *
     * @param name The new name
     * @see #getName()
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Return the name.
     *
     * @return String name The name
     * @see #setName(String)
     */
    public String getName() {
        return name;
    }


    /**
     * Set the value.
     *
     * @param value The new value.
     */
    public void setValue(V value) {
        this.value = value;
    }


    /**
     * Return the current value.
     *
     * @return The current value.
     */
    public V getValue() {
        return value;
    }

    /**
     * Get a String representation of this pair.
     * @return A string representation.
     */
    public String toString() {
        return ("name=" + name + ", " + "value=" + value);
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		BasicNameValuePair other = (BasicNameValuePair) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
