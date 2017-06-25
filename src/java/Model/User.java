/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.*;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author rols
 */
@Entity
@Table(name= "USER")
public class User {
    
    
    public static enum UserType { ADMIN, MODERATOR, USER };
    
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
    private boolean sex;
    private String linkedin;
    private Integer shirt_size;
    private String institution;
    
    @ElementCollection(targetClass=Integer.class)
    private Set<ModeratorConference> conferences = new HashSet<ModeratorConference>(); // for moderators
    
    
    public static List<User> getModerators() {
        List<User> users = null;
        
      Session session = HibernateHelper.getFactory().openSession();
      Transaction tx = null;
      Integer userID = null;
      try{
        tx = session.beginTransaction();
         
        String cmd = "FROM User E WHERE E.enabled = true AND E.type = :moderator";
        Query query = session.createQuery(cmd);
        query.setParameter("moderator", UserType.MODERATOR.ordinal());
        users = query.list();
        
        tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      
      return users;
    }
    
    public void changePassword(String newPass) {
        Session session = HibernateHelper.getFactory().openSession();
      Transaction tx = null;
      try{
        tx = session.beginTransaction();
         
        String cmd = "UPDATE User E SET E.password = :password WHERE E.username = :username";
        Query query = session.createQuery(cmd);
        query.setParameter("password",newPass);
        query.setParameter("username",username);
        query.executeUpdate();
         
        tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
    }
    
    public static List<User> getNotVerified() {
      List<User> users = null;
        
      Session session = HibernateHelper.getFactory().openSession();
      Transaction tx = null;
      Integer userID = null;
      try{
        tx = session.beginTransaction();
         
        String cmd = "FROM User E WHERE E.enabled <> true";
        Query query = session.createQuery(cmd);
        users = query.list();
  
        tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      
      return users;
    }
    
    public static void deleteUser(int id) {
        Session session = HibernateHelper.getFactory().openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         User user = new User();
         user.setId(id);
         session.delete(user); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
        
    }
    
    public static void deleteUser(User user) {
        Session session = HibernateHelper.getFactory().openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         session.delete(user); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
    }
    
    public static int addUser(User user) {
      Session session = HibernateHelper.getFactory().openSession();
      Transaction tx = null;
      Integer userID = null;
      user.enabled = false;
      try{
         tx = session.beginTransaction();
         userID = (Integer) session.save(user); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      
      return userID;
    }
    
    public void enable(){
      Session session = HibernateHelper.getFactory().openSession();
      Transaction tx = null;
      Integer userID = null;
      
      enabled = true;
      
      try{
         tx = session.beginTransaction();
         session.merge(this); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
    }
    
    public static User FindById(int id) {
        User user = null;
      Session session = HibernateHelper.getFactory().openSession();
      Transaction tx = null;
      try{
        tx = session.beginTransaction();
         
        String cmd = "FROM User E WHERE E.id = :id";
        Query query = session.createQuery(cmd);
        query.setParameter("id",id);
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
    
    public static User FindByEmail(String email) {
        User user = null;
      Session session = HibernateHelper.getFactory().openSession();
      Transaction tx = null;
      try{
        tx = session.beginTransaction();
         
        String cmd = "FROM User E WHERE E.email = :email";
        Query query = session.createQuery(cmd);
        query.setParameter("email",email);
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
    
    public static User FindByUsername(String username) {
        User user = null;
      Session session = HibernateHelper.getFactory().openSession();
      Transaction tx = null;
      try{
        tx = session.beginTransaction();
         
        String cmd = "FROM User E WHERE E.username = :username";
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
    

    public User(int id, String first_name, String last_name, String username, String password, String phone, String email, Date created, UserType type, boolean enabled) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
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

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public Integer getShirt_size() {
        return shirt_size;
    }

    public void setShirt_size(Integer shirt_size) {
        this.shirt_size = shirt_size;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public Set<ModeratorConference> getConferences() {
        return conferences;
    }

    public void setConferences(Set<ModeratorConference> conferences) {
        this.conferences = conferences;
    }

    
    
    
}
