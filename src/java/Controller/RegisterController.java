/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author rols
 */
@ManagedBean(name="register")
@SessionScoped
public class RegisterController {
    
    
    public void ValidateUsername(FacesContext fc, UIComponent c, Object value) {
        username_valid = User.FindByUsername((String) value) == null;
        updateAllValid();
        if (!username_valid) {
             throw new ValidatorException(
	         new FacesMessage("Username in use!"));
        }
    }
    
    public void ValidateEmail(FacesContext fc, UIComponent c, Object value) {
        email_valid = User.FindByEmail((String) value) == null;
        updateAllValid();
        if (!email_valid) {
             throw new ValidatorException(
	         new FacesMessage("Email in use!"));
        }
    }
    
    public void ValidatePassword(FacesContext fc, UIComponent c, Object value) {
                
        password_valid = true;
        
        if (password.length() < 8) {
            password_valid = false;
            throw new ValidatorException(new FacesMessage("Password week! Need at least 8 characters"));
        }
        if (password.length() > 12) {
            password_valid = false;
            throw new ValidatorException(new FacesMessage("Password too long! Need at most 12 characters"));
        }
        {
            int small = 0, big = 0, num = 0, other = 0;
            for (char i : password.toCharArray()) {
                if (i >= 'a' && i <= 'z') small ++;
                else if (i >= 'A' && i <= 'Z') big ++;
                else if (i >= '0' && i <= '9') num ++;
                else other ++;
                
            }
            if (small < 3 || big < 1 || num < 1 || other < 1) {
                password_valid = false;
                throw new ValidatorException(new FacesMessage("Password week! Need at least 3 small letters, 1 capital, 1 number and 1 special char"));
            }
        }
        
        String password = (String) value;
        String password_repeat = (String) c.getAttributes().get("passwordRepeat");

//        System.out.println("repeat " + password_repeat + " pass " + password);
        
        if (password == null || password_repeat == null) {
            password_valid = true;            
        } else if (!password.equals(password_repeat)) {
            password_valid = false;
            updateAllValid();
            throw new ValidatorException(new FacesMessage("Passwords do not match!"));
        }
        
        updateAllValid();
    }
    
    private void updateAllValid() {
        System.out.println("updateing all valid " + all_valid + "| email " + email_valid + "| username " + username_valid + "| pass " + password_valid);
        all_valid = email_valid && username_valid && password_valid;
    }
    
    public String TryRegister() {
        User user = new User(
                0, 
                first_name, 
                last_name, 
                username, 
                password, 
                phone, 
                email, 
                new Date(), 
                User.UserType.USER, 
                false
        );
        
        User.addUser(user);
        
        return "home_user";
    }
    
    
    private String first_name;
    private String last_name;
    private String username;
    private String password;
//    private String password_repeat;
    private String phone;
    private String email;
    
    private boolean email_valid = false;
    private boolean password_valid = false;
    private boolean username_valid = false;
    private boolean all_valid = false;

    public boolean isAll_valid() {
        return all_valid;
    }

    public void setAll_valid(boolean all_valid) {
        this.all_valid = all_valid;
    }

    
    
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

//    public String getPassword_repeat() {
//        return password_repeat;
//    }
//
//    public void setPassword_repeat(String password_repeat) {
//        this.password_repeat = password_repeat;
//    }
    
    

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }    
    
}
