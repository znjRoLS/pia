/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.util.Pair;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author rols
 */
public class Presentation {
    private int presentationId;
    private String name;
    private SessionConf session;
    private Set<AuthorPresentation> authors = new HashSet<>();
    private Date startTime;
    
    private List<User> authorNames = new ArrayList<>();
    
    private String sessionName;

    public static List<Presentation> getByConference(Conference conf) {
        List<Presentation> presentations = new ArrayList<>();
        
        List<Object[]> results = null;
        
        Session session = HibernateHelper.getFactory().openSession();
        Transaction tx = null;
        Integer userID = null;
        try{
          tx = session.beginTransaction();

          String cmd = "FROM Presentation P JOIN P.session S WHERE S.conference = :conference";
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
            presentations.add((Presentation) result[0]);
        }
        
        
        return presentations;
    }
    
    public void addAuthor() {
        authorNames.add(new User());
    }
    
    public int getPresentationId() {
        return presentationId;
    }

    public void setPresentationId(int presentationId) {
        this.presentationId = presentationId;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SessionConf getSession() {
        return session;
    }

    public void setSession(SessionConf session) {
        this.session = session;
    }

    public Set<AuthorPresentation> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorPresentation> authors) {
        this.authors = authors;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    


    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public List<User> getAuthorNames() {
        return authorNames;
    }

    public void setAuthorNames(List<User> authorNames) {
        this.authorNames = authorNames;
    }

    

   

   
    
    
}
