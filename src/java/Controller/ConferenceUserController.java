/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConfPicture;
import Model.Conference;
import Model.HibernateHelper;
import Model.Message;
import Model.SessionConf;
import Model.User;
import Model.UserConference;
import Model.UserSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

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
    
    private List<ConfPicture> pictures;
    
    private Conference selectedConference;
    
    public ConferenceUserController() {
        allConferences = Conference.getAll();
        filteredConferences = new ArrayList<>();
        
    }
    
    public void preloadPictures(Conference conference) {
        pictures = new ArrayList<>();
        pictures.addAll(conference.getPictures());
        selectedConference = conference;
    }
    
//    public final String UPLOADDIR = "C:\\workspace\\UMRI\\pia\\";
//    public final String CONFPICTURE = "uploads\\conferencePictures\\";
    public final String UPLOADDIR = "uploads\\";
    public final String CONFPICTURE = "conferencePictures\\";
    
    public void uploadPicture(FileUploadEvent event) {
        UploadedFile fileUploaded = event.getFile();
        
        String fileName = fileUploaded.getFileName();
        
        String relativePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        String conferenceIdFolder = selectedConference.getConferenceId() + "\\";
        String finalDir = relativePath + UPLOADDIR + CONFPICTURE + conferenceIdFolder;
                
        Path folder = Paths.get(finalDir);
        Path filePath  = Paths.get(finalDir + fileName);
        String extension = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
        String filename = fileName.substring(0,fileName.lastIndexOf('.'));
        try {
            // Make sure the directories exist
            Files.createDirectories(folder);
            
            try (InputStream in = new ByteArrayInputStream(fileUploaded.getContents())) {
                Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pictures.add(ConfPicture.add(selectedConference, UPLOADDIR + CONFPICTURE + conferenceIdFolder + fileName));
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
    
    public void registerForSession(User user, SessionConf session) {
        UserSession.addNew(user, session);
        session.setRegistered(true);
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

    public List<ConfPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<ConfPicture> pictures) {
        this.pictures = pictures;
    }

    

    public Conference getSelectedConference() {
        return selectedConference;
    }

    public void setSelectedConference(Conference selectedConference) {
        this.selectedConference = selectedConference;
    }
    
    
}
