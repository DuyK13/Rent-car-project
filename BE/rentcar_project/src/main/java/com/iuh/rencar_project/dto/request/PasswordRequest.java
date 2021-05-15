/**
 * @author trant
 * @created_date Apr 21, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.dto.request;

public class PasswordRequest {

	private String oldPassword;

	private String newPassword;

	public PasswordRequest(String oldPassword, String newPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public PasswordRequest() {
		super();
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
