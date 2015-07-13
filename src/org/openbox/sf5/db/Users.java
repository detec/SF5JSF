
package org.openbox.sf5.db;	

import java.io.Serializable;
import java.util.Objects;
import java.util.Date;
import java.util.UUID;
import java.sql.Timestamp;
import java.util.Set;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.Session;


@ManagedBean
@Entity
@Table(name="Users")
public class Users implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id  
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "my_entity_seq_gen")
	@SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "catalog_seq")
	private long id;


	public long getId() {

		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name="Name", unique = false, nullable = false, length = 30)
		private String Name;

		public void setName(String Name) {
			this.Name = Name;
		}

		public String getName() {
			return this.Name;
		}


	
	@Override
	public String toString() {
		return Name;
	}	

	@Column(name="Login", unique = false, nullable = false, length = 12)
	private String Login;

	public String getLogin() {
		return this.Login;
	}

 	public void setLogin(String Login) {
		this.Login = Login;
	}

	public Users(String Name, String Login) {

	this.Name= Name;
		this.Login = Login;

	}

	public Users() {
	}

	@Override
	public boolean equals(Object other) {
    if (other == null) return false;
    if (other == this) return true;
    
    if (!(other instanceof Users)) return false;
    Users otherUsers = (Users) other;
		if (otherUsers.Name.equals(this.Name)
	&& otherUsers.Login.equals(this.Login)
) return true;
	else return false;
    
}

}