/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author rols
 */
public class UserSession {
    private int userSessionId;
    private User user;
    private SessionConf session;

    
    public static void addNew(User user, SessionConf sessionConf){
        UserSession userConf = new UserSession();
        userConf.setSession(sessionConf);
        userConf.setUser(user);
        
        Session session = HibernateHelper.getFactory().openSession();
      Transaction tx = null;
      try{
        tx = session.beginTransaction();
        session.save(userConf);
         
        tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
    }
    
    public int getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(int userSessionId) {
        this.userSessionId = userSessionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SessionConf getSession() {
        return session;
    }

    public void setSession(SessionConf session) {
        this.session = session;
    }
    
    
}
