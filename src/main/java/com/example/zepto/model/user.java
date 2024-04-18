package com.example.zepto.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "Users")
public class user {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private long id;
	
	@Column(name="firstName",nullable=false)
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="email",nullable=false,unique=true)
//	@Email(message = "{errors.invalid_email}")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@ManyToMany(cascade = CascadeType.MERGE,fetch=FetchType.EAGER )
	@JoinTable(
			name="user_role",
			joinColumns= {@JoinColumn(name= "USER_ID",referencedColumnName="USER_ID")},
			inverseJoinColumns= {@JoinColumn(name="ROLE_ID",referencedColumnName="ROLE_ID")})
	private List<role> roles;
	//user role and store
	
	@Column(name="location")
	private String location;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<role> getRoles() {
		return roles;
	}

	public void setRoles(List<role> roles) {
		this.roles = roles;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public user(user user) {
		super();
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.roles = user.getRoles();
		this.location = user.getLocation();
	}
	public user() {
		
	}


	
	
	
	
	
	
	

}
