/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
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
    private boolean logged_in = false;
    private boolean all_valid = false;
    
    public void ValidateUsername(FacesContext fc, UIComponent c, Object value) {
        
         all_valid = User.FindByUsername((String) value) != null;
        if (!all_valid) {
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
        
        logged_in = true;
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

    public boolean isAll_valid() {
        return all_valid;
    }

    public void setAll_valid(boolean all_valid) {
        this.all_valid = all_valid;
    }

    
    
    public boolean isLogged_in() {
        return logged_in;
    }

    public void setLogged_in(boolean logged_in) {
        this.logged_in = logged_in;
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
    
    
}
