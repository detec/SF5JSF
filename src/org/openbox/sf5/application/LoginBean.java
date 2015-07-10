package org.openbox.sf5.application;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;

@ManagedBean
@SessionScoped
@Table(appliesTo = "Users")
public class LoginBean {
	
    private String name;
    private String password;
	
    @Id  
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "my_entity_seq_gen")
	@SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "catalog_seq")
	private long id;
    private boolean loggedIn;

    @ManagedProperty(value="#{navigationBean}")
    private NavigationBean navigationBean;

	
    public String getName ()
    {
        return name;
    }


    public void setName (final String name)
    {
        this.name = name;
    }


    public String getPassword ()
    {
        return password;
    }


    public void setPassword (final String password)
    {
        this.password = password;
    }
    
    public String doLogin() {

        // Get every user from our sample database :)

        for (String user: users) {

            String dbUsername = user.split(":")[0];

            String dbPassword = user.split(":")[1];

             

            // Successful login

  //          if (dbUsername.equals(name) ) {

                loggedIn = true;

                return navigationBean.redirectToWelcome();

    //        }

        }

         

        // Set login ERROR

        FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");

        msg.setSeverity(FacesMessage.SEVERITY_ERROR);

        FacesContext.getCurrentInstance().addMessage(null, msg);

         

        // To to login page

        return navigationBean.toLogin();

         

    }



}
