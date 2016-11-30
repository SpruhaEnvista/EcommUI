package com.envista.msi.api.freight.ui.pojo;

public class Profile {
	private String userName = "Sandy";
	private String userId = "1";
	private String password = "password";
	private String id = "1";
	private String user = "1";
	
	
	/**
	 * @param userName
	 * @param userId
	 */
	public Profile(String userName, String userId) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.id = userId;
		this.user = userName;
	}
	
	
	/**
	 * @param userName
	 * @param userId
	 * @param password
	 */
	public Profile(String userName, String userId, String password) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.id = userId;
		this.user = userName;
		this.password = password;
	}


	/**
	 * 
	 */
	public Profile() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Profile [" + (userName != null ? "userName=" + userName + ", " : "") + (userId != null ? "userId=" + userId : "") + "]";
	}
	
}
