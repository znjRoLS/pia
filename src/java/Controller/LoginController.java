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
    private String new_password;
    private String new_password_repeat;
    private boolean new_password_valid = false;
    private String message;
    private boolean loggedIn = false;
    private boolean allValid = false;
    private boolean isModerator = false;
    private boolean isAdmin = false;
    private User.UserType userType;
    private User currentUser;
    
    public String ChangePassword() {
        if (!currentUser.getPassword().equals(password)) {
            message = "Wrong old password!";
            return "login_pass";
        }
        
        currentUser.changePassword(new_password);
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        return "index";
    }
    
    public void ValidatePassword(FacesContext fc, UIComponent c, Object value) {
        String new_password = (String) value;
        String new_password_repeat = (String) c.getAttributes().get("passwordRepeat");

//        System.out.println("repeat " + password_repeat + " pass " + password);
        
        if (new_password == null || new_password_repeat == null) {
            new_password_valid = true;            
        } else if (!new_password.equals(new_password_repeat)) {
            new_password_valid = false;
            //updateAllValid();
            throw new ValidatorException(new FacesMessage("Passwords do not match!"));
        }
        new_password_valid = true;
        
        //updateAllValid();
    }
    
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
        currentUser = user;
        
        isAdmin = isModerator = false;
        
        switch(user.getUserType()) {
            case ADMIN:
                isAdmin = true;
            case MODERATOR:
                isModerator = true;
        }

        return "index";
        

//        
//        return "login";
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

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public boolean isNew_password_valid() {
        return new_password_valid;
    }

    public void setNew_password_valid(boolean new_password_valid) {
        this.new_password_valid = new_password_valid;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isIsModerator() {
        return isModerator;
    }

    public void setIsModerator(boolean isModerator) {
        this.isModerator = isModerator;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    
    
}
