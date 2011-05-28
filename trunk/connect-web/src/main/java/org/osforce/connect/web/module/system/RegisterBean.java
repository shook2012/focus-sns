package org.osforce.connect.web.module.system;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.osforce.connect.entity.system.User;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 27, 2011 - 1:40:47 PM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class RegisterBean {
	@NotBlank @Email
	private String username;
	@NotBlank @Min(6)
	private String password;
	@NotBlank @Min(6)
	private String rePassword;
	@NotBlank
	private String nickname;
	
	public RegisterBean() {
	}
	
	public User getUser() {
		User user = new User(username, password);
		user.setNickname(nickname);
		user.setEmail(username);
		return user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
