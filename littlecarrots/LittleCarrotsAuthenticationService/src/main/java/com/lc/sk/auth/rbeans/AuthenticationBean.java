/**
 * 
 */
package com.lc.sk.auth.rbeans;

import java.io.Serializable;

/**
 * @author Mastanvali Shaik LittleCarrotsAuthenticationService 2020
 */
public class AuthenticationBean implements Serializable {

	private String username;
	private long tokenid;
	private String email;
	private String rolename;
	private int responsecode;
	private long timestamp;
	private boolean status;

	/**
	 * 
	 */
	public AuthenticationBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	/**
	 * @param username
	 * @param tokenid
	 * @param email
	 * @param rolename
	 * @param responsecode
	 * @param timestamp
	 * @param status
	 */
	public AuthenticationBean(String username, long tokenid, String email, String rolename, int responsecode,
			long timestamp, boolean status) {
		super();
		this.username = username;
		this.tokenid = tokenid;
		this.email = email;
		this.rolename = rolename;
		this.responsecode = responsecode;
		this.timestamp = timestamp;
		this.status = status;
	}



	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the tokenid
	 */
	public long getTokenid() {
		return tokenid;
	}

	/**
	 * @param tokenid the tokenid to set
	 */
	public void setTokenid(long tokenid) {
		this.tokenid = tokenid;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the rolename
	 */
	public String getRolename() {
		return rolename;
	}

	/**
	 * @param rolename the rolename to set
	 */
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	/**
	 * @return the responsecode
	 */
	public int getResponsecode() {
		return responsecode;
	}

	/**
	 * @param responsecode the responsecode to set
	 */
	public void setResponsecode(int responsecode) {
		this.responsecode = responsecode;
	}

	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}



	/**
	 * @return the status
	 */
	public boolean getStatus() {
		return status;
	}



	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "AuthenticationBean [username=" + username + ", tokenid=" + tokenid + ", email=" + email + ", rolename="
				+ rolename + ", responsecode=" + responsecode + ", timestamp=" + timestamp + ", status=" + status + "]";
	}

	
}
