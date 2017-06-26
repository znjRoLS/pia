/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author rols
 */
public class UserConference {
    private int userConferenceId;
    private User user;
    private Conference conference;

    
    public static void addNew(User user, Conference conference){
        UserConference userConf = new UserConference();
        userConf.setConference(conference);
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
    
    public int getUserConferenceId() {
        return userConferenceId;
    }

    public void setUserConferenceId(int userConferenceId) {
        this.userConferenceId = userConferenceId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }
    
    
}
