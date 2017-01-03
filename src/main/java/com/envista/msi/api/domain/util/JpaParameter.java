package com.envista.msi.api.domain.util;

public class JpaParameter<T> {
	private final String name;
	private final Integer position;
	private final Class<T> javaType;
	
	public JpaParameter(String name) {
		this(name, null, null);
	}
	
	public JpaParameter(String name,  Class<T> javaType) {
		this(name, null, javaType);
	}
	
	public JpaParameter(Integer position) {
		this(null, position, null);
	}

	
	public JpaParameter(Integer position, Class<T> javaType) {
		this(null, position, javaType);
	}

	public JpaParameter(String name, Integer position, Class<T> javaType) {
		super();
		this.name = name;
		this.position = position;
		this.javaType = javaType;
	}

	public String getName() {
		return name;
	}

	public Integer getPosition() {
		return position;
	}

	public Class<T> getJavaType() {
		return javaType;
	}
}
