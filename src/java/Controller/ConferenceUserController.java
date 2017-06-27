/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Conference;
import Model.HibernateHelper;
import Model.Message;
import Model.User;
import Model.UserConference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author rols
 */
@ManagedBean(name="conference_user")
@javax.faces.bean.SessionScoped
public class ConferenceUserController {
    private List<Conference> allConferences;
    private List<Conference> filteredConferences;
    
    private List<Conference> registredConferences;
    
    private String searchText;
    private Date startDate;
    private Date endDate;
    
    private String message_body;
    private String messageTo;
    
    
    private List<Message> unreadMsgs;
    private List<Message> readMsgs;
    
    public ConferenceUserController() {
        allConferences = Conference.getAll();
        filteredConferences = new ArrayList<>();
        
    }
    
    public void sendMessage(User currUser) {
        for (User user : User.getAll()) {
            if (user.getUsername() == null) continue;
            if (user.getUsername().equals(messageTo)) {
                Message.addMessage(currUser, user, message_body);
            }
        }
        
    }
    
    public void onload(User user) {
        registredConferences = Conference.getByRegistredUser(user);
    }
    
    public void searchConferences(User user) {
        filteredConferences = new ArrayList<>();
        
        Date today = new Date();
        
        if (searchText != null) {
            for (Conference conf : allConferences) {
               
                if (conf.getEndDate().before(today)) {
                    continue;
                }
                
                if (searchText != null && !conf.getName().contains(searchText)) {
                    continue;
                }
                if (startDate != null && conf.getStartDate().before(startDate)) {
                    continue;
                }
                if (endDate != null && conf.getEndDate().after(endDate)) {
                    continue;
                }
                
                
                filteredConferences.add(conf);
                
                if (user != null) {
                    conf.checkIfRegistered(user);
                }
                
            }
        }
        
        
    }
    
    public void readAllMessages(User user) {
        
        readMsgs = user.getReadMsgs();
        unreadMsgs = user.getUnreadMsgs();
        
        Session session = HibernateHelper.getFactory().openSession();
        Transaction tx = null;
        try{
          tx = session.beginTransaction();

          for (Message message : user.getMessages()) {
            message.setRead(1);
            session.update(message);
        }

          tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
    }
    
    public void registerForConference(User user, Conference conference) {
        UserConference.addNew(user, conference);
        conference.setRegistred(true);
    }

    public List<Conference> getAllConferences() {
        return allConferences;
    }

    public void setAllConferences(List<Conference> allConferences) {
        this.allConferences = allConferences;
    }

    public List<Conference> getFilteredConferences() {
        return filteredConferences;
    }

    public void setFilteredConferences(List<Conference> filteredConferences) {
        this.filteredConferences = filteredConferences;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public List<Conference> getRegistredConferences() {
        return registredConferences;
    }

    public void setRegistredConferences(List<Conference> registredConferences) {
        this.registredConferences = registredConferences;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getMessage_body() {
        return message_body;
    }

    public void setMessage_body(String message_body) {
        this.message_body = message_body;
    }

    public String getMessageTo() {
        return messageTo;
    }

    public void setMessageTo(String messageTo) {
        this.messageTo = messageTo;
    }

    public List<Message> getUnreadMsgs() {
        return unreadMsgs;
    }

    public void setUnreadMsgs(List<Message> unreadMsgs) {
        this.unreadMsgs = unreadMsgs;
    }

    public List<Message> getReadMsgs() {
        return readMsgs;
    }

    public void setReadMsgs(List<Message> readMsgs) {
        this.readMsgs = readMsgs;
    }
    
    
}
