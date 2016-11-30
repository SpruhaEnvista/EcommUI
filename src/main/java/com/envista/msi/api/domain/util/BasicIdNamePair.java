/**
 * 
 */
package com.envista.msi.api.domain.util;

import java.io.Serializable;

/**
 * 
 * <p>
 * A simple class encapsulating a name/id pair.
 * </p>
 * 
 * 
 * @author SANKER
 *
 */
public class BasicIdNamePair<V> implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	/**
	 * Value.
	 */
	private V id = null;

	/**
	 * Name.
	 */
	private String name = null;

	/**
	* 
	*/
	public BasicIdNamePair() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 */
	public BasicIdNamePair(V id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * Set the name.
	 *
	 * @param name
	 *            The new name
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
	 * @return the id
	 */
	public V getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(V id) {
		this.id = id;
	}

	/**
	 * Get a String representation of this pair.
	 * 
	 * @return A string representation.
	 */
	public String toString() {
		return ("name=" + name + ", " + "id=" + id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		BasicIdNamePair other = (BasicIdNamePair) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
