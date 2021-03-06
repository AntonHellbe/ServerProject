package org.demo.model.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.demo.config.AuthoritiesConstants;
import org.demo.deserialize.GrantedAuthorityDeserializer;
import org.demo.deserialize.RfidKeyDeserializer;
import org.demo.model.RfidKey;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sebastian Börebäck, Anton Hellbe on 2016-04-22.
 * Account model used to define account
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "Account")
public class Account implements UserDetails ,Serializable{

	@Id
	private String id;

	private String username;
	private String password;
	private String firstName;
	private String lastName;

	@JsonDeserialize(using = RfidKeyDeserializer.class)
	private RfidKey rfidKey;

	@JsonDeserialize(using = GrantedAuthorityDeserializer.class)
	private List<GrantedAuthority> authorities = new ArrayList<>();

	private boolean accountNonExpired = true;
	private boolean isEnabled = true;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	private boolean isAccountNonLocked = true;
	private boolean isCredentialsNonExpired = true;

	public Account(){
	}


	public Account(String firstName, String lastName, String username, String password, List<GrantedAuthority> authorities) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}



	public Account(String firstName, String lastName, RfidKey rfidKey, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.rfidKey = rfidKey;
		this.authorities = AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER);
	}

	public Account(String firstName, String lastName, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.authorities = AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER);
	}

	public RfidKey getRfidKey() {
		return rfidKey;
	}

	public void setRfidKey(RfidKey rfidKey) {
		this.rfidKey = rfidKey;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {

		return authorities;
	}

	public String getPassword()	 {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean enabled) {
		isEnabled = enabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		isAccountNonLocked = accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		isCredentialsNonExpired = credentialsNonExpired;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "Account{" +
				"id='" + id + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", rfidKey=" + rfidKey +
				", authorities=" + authorities +
				", accountNonExpired=" + accountNonExpired +
				", isEnabled=" + isEnabled +
				", isAccountNonLocked=" + isAccountNonLocked +
				", isCredentialsNonExpired=" + isCredentialsNonExpired +
				'}';
	}
}
