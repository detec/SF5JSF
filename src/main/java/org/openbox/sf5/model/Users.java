package org.openbox.sf5.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Users")
@XmlRootElement
public class Users extends AbstractDbEntity implements Serializable {

	private static final long serialVersionUID = -4622662328306687049L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	public long getId() {

		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "Name", unique = false, nullable = false, length = 30)
	@NotEmpty
	@JsonProperty("Name")
	private String Name;

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getName() {
		return Name;
	}

	@Override
	public String toString() {
		return Login;
	}

	@Column(name = "Login", unique = false, nullable = false, length = 12)
	@NotEmpty
	@JsonProperty("Login")
	private String Login;

	public String getLogin() {
		return Login;
	}

	public void setLogin(String Login) {
		this.Login = Login;
	}

	public Users(String Name, String Login) {

		this.Name = Name;
		this.Login = Login;

	}

	public Users() {
	}

	public Users(String pLogin) {
		Login = pLogin;
		Name = pLogin;
	}

}