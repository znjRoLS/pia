/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author rols
 */
public class Conference {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    private int conferenceId;
    private String name;
    private String place;
    private Date startDate;
    private Date endDate;
    private Location location;
    private Date startTime;
    private Date endTime;
    
    private boolean canRegister;
    private boolean registred;
    
    @ElementCollection(targetClass=Integer.class)
    private Set<ModeratorConference> moderators = new HashSet<ModeratorConference>(); //moderators
    private Set<SessionConf> sessions = new HashSet<>(); //sessions
    private Set<UserConference> users = new HashSet<>(); //reg users
    
    public void checkIfRegistered(User user) {
        Session session = HibernateHelper.getFactory().openSession();
        Transaction tx = null;
        try{
          tx = session.beginTransaction();

          String cmd = "FROM UserConference C WHERE C.user = :user AND C.conference = :conference";
          Query query = session.createQuery(cmd);
          query.setParameter("user", user);
          query.setParameter("conference", this);
          List<Object> res = query.list();
          registred = res.size() > 0;

          tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
    }
    
    
    public Conference() {
        
        try{
        
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            cal.add(Calendar.DATE, -3);
            Date before3days = cal.getTime(); 

            Date today = new Date();

            canRegister = !startDate.before(today) && !before3days.after(today);
        } catch (Exception e) {
            canRegister = false;
        }
    
    }

    
    
    public static List<Conference> getAll() {
        List<Conference> conferences = null;
        
        Session session = HibernateHelper.getFactory().openSession();
        Transaction tx = null;
        Integer userID = null;
        try{
          tx = session.beginTransaction();

          String cmd = "FROM Conference C ";
          Query query = session.createQuery(cmd);
          conferences = query.list();

          tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
        
        return conferences;
    }
    
    public static List<User> getRegistredUsers(Conference conf) {
        if (conf == null) return null;
        
        List<User> users = new ArrayList<>();
        List<Object[]> results = null;
        
        Session session = HibernateHelper.getFactory().openSession();
        Transaction tx = null;
        Integer userID = null;
        try{
          tx = session.beginTransaction();

          String cmd = "FROM User U JOIN U.conferencesUser M WHERE M.conference = :conference";
          Query query = session.createQuery(cmd);
          query.setParameter("conference", conf);
          results = query.list();

          tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
        
        for (Object[] result: results) {
            users.add((User) result[0]);
        }
        
        return users;
    }
    
    public static List<Conference> getByUser(User user) {
        if (user == null) return null;
        
        List<Conference> conferences = new ArrayList<Conference>();
        List<Object[]> results = null;
        
        Session session = HibernateHelper.getFactory().openSession();
        Transaction tx = null;
        Integer userID = null;
        try{
          tx = session.beginTransaction();

          String cmd = "FROM Conference C JOIN C.moderators M WHERE M.user_id = :user_id";
          Query query = session.createQuery(cmd);
          query.setParameter("user_id", user);
          results = query.list();

          tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
        
        for (Object[] result: results) {
            conferences.add((Conference) result[0]);
        }
        
        return conferences;
    }
    
    public static List<Conference> getByRegistredUser(User user) {
        if (user == null) return null;
        
        List<Conference> conferences = new ArrayList<Conference>();
        List<Object[]> results = null;
        
        Session session = HibernateHelper.getFactory().openSession();
        Transaction tx = null;
        Integer userID = null;
        try{
          tx = session.beginTransaction();

          String cmd = "FROM Conference C JOIN C.users M WHERE M.user = :user";
          Query query = session.createQuery(cmd);
          query.setParameter("user", user);
          results = query.list();

          tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
        
        for (Object[] result: results) {
            conferences.add((Conference) result[0]);
        }
        
        return conferences;
    }
    
    public int getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
        
        try{
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            cal.add(Calendar.DATE, -3);
            Date before3days = cal.getTime(); 

            Date today = new Date();

            canRegister = !startDate.before(today) && !before3days.after(today);
        } catch (Exception e) {
            canRegister = false;
        }
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }  

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<ModeratorConference> getModerators() {
        return moderators;
    }

    public void setModerators(Set<ModeratorConference> moderators) {
        this.moderators = moderators;
    }

    public Set<SessionConf> getSessions() {
        return sessions;
    }

    public void setSessions(Set<SessionConf> sessions) {
        this.sessions = sessions;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Set<UserConference> getUsers() {
        return users;
    }

    public void setUsers(Set<UserConference> users) {
        this.users = users;
    }

    public boolean isCanRegister() {
        return canRegister;
    }

    public void setCanRegister(boolean canRegister) {
        this.canRegister = canRegister;
    }

    public boolean isRegistred() {
        return registred;
    }

    public void setRegistred(boolean registred) {
        this.registred = registred;
    }
    
    
    
}
