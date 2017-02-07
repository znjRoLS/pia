/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author rols
 */
@ManagedBean(name="login")
@SessionScoped
public class LoginController {
    private String username;
    private String password;
    private String message;
    private boolean loggedIn = false;
    private boolean allValid = false;
    private User.UserType userType;
    
    public void ValidateUsername(FacesContext fc, UIComponent c, Object value) {
        
         allValid = User.FindByUsername((String) value) != null;
        if (!allValid) {
             throw new ValidatorException(
	         new FacesMessage("Username not recognized!"));
        }
    }
    
    public String TryLogin() {
        User user = User.FindByUsername(username);
        if (user == null) {
             message = "User with username " + username + " not found!";
             return "login";
        }
        if (!user.getPassword().equals(password)) {
             message = "Wrong password!";
             return "login";
        }
        
        loggedIn = true;
        switch(user.getUserType()) {
            case ADMIN:
                return "home_admin";
            case USER:
                return "home_user";
        }
        
        return "login";
    }
    
    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        return "index";
    }

    public void lockForAdmin() throws IOException {
        if (userType != User.UserType.ADMIN) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
        }
    }
    
    
    public boolean isAllValid() {
        return allValid;
    }

    public void setAllValid(boolean allValid) {
        this.allValid = allValid;
    }

    
    
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User.UserType getUserType() {
        return userType;
    }

    public void setUserType(User.UserType userType) {
        this.userType = userType;
    }
    
    
    
}
