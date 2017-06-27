/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author rols
 */
public class Message {
    private int message_id;
    private String body;
    private User user;
    private String subject;
    private Date time;
    private int read;
    
    
    public static void addMessage(User user1, User user2, String body) {
        Message message = new Message();
        message.setBody(body);
        message.setUser(user2);
        message.setTime(new Date());
        message.setRead(0);
        message.setSubject("Message from " + user1.getUsername());
        
        Session session = HibernateHelper.getFactory().openSession();
        Transaction tx = null;
        try{
          tx = session.beginTransaction();

          session.save(message);

          tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
    }
    
    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    
    
    
}
