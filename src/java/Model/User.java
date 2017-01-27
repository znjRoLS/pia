/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author rols
 */
public class User {
    
    public static User FindByUsername(String username) {
        User user = null;
      Session session = HibernateHelper.getFactory().openSession();
      Transaction tx = null;
      try{
        tx = session.beginTransaction();
         
        String cmd = "FROM USER E WHERE E.username = :username";
        Query query = session.createQuery(cmd);
        query.setParameter("username",username);
        List results = query.list();
        if (results.size() > 0) {
            user = (User) results.get(0);
        }
         
        tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      
      return user;
    }
    
    public static enum UserType { ADMIN, USER };
    
    private int id;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Date created;
    private UserType type;
    private boolean enabled;

    public User(int id, String first_name, String second_name, String username, String password, String phone, String email, Date created, UserType type, boolean enabled) {
        this.id = id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.created = created;
        this.type = type;
        this.enabled = enabled;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getType() {
        return type.ordinal();
    }
    
    public UserType getUserType() {
        return type;
    }

    public void setType(int type) {
        this.type = UserType.values()[type];
    }
    
    
}
