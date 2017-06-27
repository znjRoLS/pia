/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author rols
 */
public class SessionConf {
    private int sessionId;
    private String name;
    private String type;
    private Room room;
    private Date date;
    private Date startTime;
    private Date endTime;
    private Conference conference;
    private Set<Presentation> presentations = new HashSet<>();
    
    private Set<UserSession> users = new HashSet<>();
    
    private String roomName;

    private boolean registered;
    
    public void checkIfRegistered(User user) {
        Session session = HibernateHelper.getFactory().openSession();
        Transaction tx = null;
        try{
          tx = session.beginTransaction();

          String cmd = "FROM UserSession S WHERE S.user = :user AND S.session = :session";
          Query query = session.createQuery(cmd);
          query.setParameter("user", user);
          query.setParameter("session", this);
          List<Object> res = query.list();
          registered = res.size() > 0;

          tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
    }
    
    
    private void addPresentation() {
        presentations.add(new Presentation());
    }
    
    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public boolean getRegistred() {
        return registered;
    }
    
    public void setRegistered(boolean registered) {
        this.registered = registered;
    }
    
    
    public Set<Presentation> getPresentations() {
        return presentations;
    }
    
    
    public List<Presentation> getPresentationsSorted() {
        List<Presentation> presSort = new ArrayList<>();
        
        presSort.addAll(presentations);
        
        
        presSort.sort((t, t1) -> {
            if (t.getStartTime().after(t1.getStartTime()))
                return 1;
            
            if (t.getStartTime().before(t1.getStartTime()))
                return -1;
            return 0;
        });
        
        
        return presSort;
    }

    public void setPresentations(Set<Presentation> presentations) {
        this.presentations = presentations;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Set<UserSession> getUsers() {
        return users;
    }

    public void setUsers(Set<UserSession> users) {
        this.users = users;
    }

    public boolean isRegistered() {
        return registered;
    }

    
    
    
}
