/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AuthorPresentation;
import Model.Conference;
import Model.HibernateHelper;
import Model.Location;
import Model.Message;
import Model.ModeratorConference;
import Model.Presentation;
import Model.Room;
import Model.SessionConf;
import Model.User;
import Model.UserConference;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author rols
 */
@ManagedBean(name="conference_admin")
@javax.faces.bean.SessionScoped
public class ConferenceAdminController {
     private String conferenceId;
    private List<Conference> allConferences;
    
    private Conference conference;
    private List<User> moderators;
    private List<String> moderatorNames;
    private String[] selectedModeratorNames;
    
    private List<Location> locations;
    private List<String> locationNames;
    private String selectedLocationName;
    
    private Conference selectedConference;
    
    
    
    public ConferenceAdminController() {
        allConferences = Conference.getAll();
        conference = new Conference();
        
        moderators = User.getModerators();
        
        moderatorNames = new ArrayList<>();
        moderators.forEach((u) -> {
            moderatorNames.add(u.getUsername());
         });
        
        locations = Location.getAll();
        
        locationNames = new ArrayList<>();
        locations.forEach((u) -> {
            locationNames.add(u.getName());
         });
        
        
        
        
    }
    
    public String deleteConference(Conference conf) {
        
        List<User> users = Conference.getRegistredUsers(conf);
        
        Session session = HibernateHelper.getFactory().openSession();
      Transaction tx = null;
      try{
        tx = session.beginTransaction();
         
        for (User user : users) {
            Message msg = new Message();
            msg.setRead(0);
            msg.setBody(conf.getName());
            msg.setSubject("Conferention is canceled");
            msg.setTime(new Date());
            msg.setUser(user);
            
            session.save(msg);
            
            UserConference userConf = new UserConference();
            userConf.setUser(user);
            userConf.setConference(conf);
            session.delete(userConf); 
        }
        
        allConferences.remove(conf);
        
        session.delete(conf);
        
        
        tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
        
        return "conference_view_admin";
    }
    
    public String addConference() {
        Session session = HibernateHelper.getFactory().openSession();
      Transaction tx = null;
      Integer userID = null;
      try{
        tx = session.beginTransaction();
         
        
        Location selectedLocation = null;
        for (Location u : locations) {
            if (u.getName().equals(selectedLocationName)) {
                selectedLocation = u;
                break;
            }
        }
        
        conference.setLocation(selectedLocation);
        
        session.save(conference);
        
        for (User u : moderators) {
            if (Arrays.asList(selectedModeratorNames).contains(u.getUsername())) {
                ModeratorConference mc = new ModeratorConference();
                mc.setUser_id(u);
                mc.setConference_id(conference);
                session.save(mc);
            }
        }
        
        tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      
      return "index";
    }

    
    public String getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(String conferenceId) {
        this.conferenceId = conferenceId;
    }
    
    
    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public List<String> getModeratorNames() {
        return moderatorNames;
    }

    public void setModeratorNames(List<String> moderatorNames) {
        this.moderatorNames = moderatorNames;
    }

    public String[] getSelectedModeratorNames() {
        return selectedModeratorNames;
    }

    public void setSelectedModeratorNames(String[] selectedModeratorNames) {
        this.selectedModeratorNames = selectedModeratorNames;
    }

    public List<User> getModerators() {
        return moderators;
    }

    public void setModerators(List<User> moderators) {
        this.moderators = moderators;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<String> getLocationNames() {
        return locationNames;
    }

    public void setLocationNames(List<String> locationNames) {
        this.locationNames = locationNames;
    }

    public String getSelectedLocationName() {
        return selectedLocationName;
    }

    public void setSelectedLocationName(String selectedLocationName) {
        this.selectedLocationName = selectedLocationName;
    }

    public Conference getSelectedConference() {
        return selectedConference;
    }

    public void setSelectedConference(Conference selectedConference) {
        this.selectedConference = selectedConference;
    }

    public List<Conference> getAllConferences() {
        return allConferences;
    }

    public void setAllConferences(List<Conference> allConferences) {
        this.allConferences = allConferences;
    }

}
