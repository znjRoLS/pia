/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Conference;
import Model.HibernateHelper;
import Model.Location;
import Model.ModeratorConference;
import Model.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author rols
 */
@ManagedBean(name="conference")
@javax.faces.bean.RequestScoped
public class ConferenceController {
    
     private String conferenceId;
    
    private Conference conference;
    private List<User> moderators;
    private List<String> moderatorNames;
    private String[] selectedModeratorNames;
    
    private List<Location> locations;
    private List<String> locationNames;
    private String selectedLocationName;

    public ConferenceController() {
        conference = new Conference();
        
        moderators = User.getModerators();
        
        moderatorNames = new ArrayList<String>();
        for (User u : moderators) {
            moderatorNames.add(u.getUsername());
        }
        
        locations = Location.getAll();
        
        locationNames = new ArrayList<String>();
        for (Location u : locations) {
            locationNames.add(u.getName());
        }
        
    }
    
//    public void showMusicEvent() {
//        Session session = HibernateHelper.getFactory().openSession();
//      Transaction tx = null;
//      try{
//        tx = session.beginTransaction();
//         
//        musicEvent = (MusicEvent) session.get(MusicEvent.class, musicEventId);
//        ticketTypes = musicEvent.getTicketTypes();
//        performers = musicEvent.getPerformers();
//        socialNetworks = musicEvent.getSocialNetworks();
//  
//        tx.commit();
//      }catch (HibernateException e) {
//         if (tx!=null) tx.rollback();
//         e.printStackTrace(); 
//      }finally {
//         session.close(); 
//      }
//    }
    
    public String addConference() {
        Session session = HibernateHelper.getFactory().openSession();
      Transaction tx = null;
      Integer userID = null;
      try{
        tx = session.beginTransaction();
         
//        HashSet<User> moderatorsPersist = new HashSet<User>();
//        
//        for (User u : moderators) {
//            if (Arrays.asList(selectedModeratorNames).contains(u.getUsername())) {
//                moderatorsPersist.add(u);
//            }
//        }
        
        //conference.setModerators(moderatorsPersist);
        
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

    
    
    
    
}
