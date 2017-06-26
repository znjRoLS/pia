/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author rols
 */
public class Room {
    
    private int id;
    private String name;
    private Location location;
    private Set<SessionConf> sessions = new HashSet<SessionConf>();
    
    public static List<Room> getAll() {
        List<Room> conferences = null;
        
        Session session = HibernateHelper.getFactory().openSession();
        Transaction tx = null;
        Integer userID = null;
        try{
          tx = session.beginTransaction();

          String cmd = "FROM Room R ";
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
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<SessionConf> getSessions() {
        return sessions;
    }

    public void setSessions(Set<SessionConf> sessions) {
        this.sessions = sessions;
    }
    
    @Override
    public boolean equals(Object other) {
        return this == other;
    }    
    
}
