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
import Model.ModeratorConference;
import Model.Presentation;
import Model.Room;
import Model.SessionConf;
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
@javax.faces.bean.SessionScoped
public class ConferenceController {
    
     private String conferenceId;
    
    private Conference conference;
    private List<User> moderators;
    private List<String> moderatorNames;
    private String[] selectedModeratorNames;
    
    private List<Location> locations;
    private List<String> locationNames;
    private String selectedLocationName;
    
    private Conference selectedConference;
    
    private String searchText;
    
    private List<Conference> allConferences;
    private List<Conference> filteredConferences;
    
    private List<SessionConf> sessions;
    
    private List<Room> rooms;
    private List<String> roomNames;
    
    private List<Presentation> presentations = new ArrayList<>();

    public ConferenceController() {
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
        
        allConferences = Conference.getAll();
        
        rooms = Room.getAll();
        roomNames = new ArrayList<>();
        
        rooms.forEach((room) -> {
            roomNames.add(room.getName());
         });
        
        
        
    }
    
    public void addPresentation() {
        presentations.add(new Presentation());
    }
    
    public void addSession() {
        sessions.add(new SessionConf());
    }
    
    public String viewConference(User currentUser, Conference conf) {
     
        selectedConference = conf;
        
        if (currentUser == null) {
            return "login";
        }
        
        sessions = new ArrayList<>();
        for (SessionConf ses : conf.getSessions()) {
            sessions.add(ses);
        }
        
        return "conference_show";
    }

    public String updateConference() {
        
        
        Session session = HibernateHelper.getFactory().openSession();
      Transaction tx = null;
      Integer userID = null;
      try{
        tx = session.beginTransaction();
         
        for (Presentation presentation : presentations) {
                
            for (SessionConf sessionconf : sessions) {
                if (sessionconf.getName().equals(presentation.getSessionName())) {
                    sessionconf.getPresentations().add(presentation);
                    presentation.setSession(sessionconf);
                    break;
                }
            }
            
            session.save(presentation);
            
            for (User author : presentation.getAuthorNames()) {
                User user = User.FindByNameSurname(author.getFirst_name(), author.getLast_name());

                if (user == null) {
                    user = author;
                    user.setType(User.UserType.USER.ordinal());
                    session.save(user);
                }

                AuthorPresentation authPres = new AuthorPresentation();
                authPres.setAuthor(user);
                authPres.setPresentation(presentation);

                session.save(authPres);
                presentation.getAuthors().add(authPres);
            }

        }
        
        for (SessionConf sessionConf : sessions ) {
            for (Room room : rooms) {
                if (room.getName().equals(sessionConf.getRoomName())) {
                    sessionConf.setRoom(room);
                }
            }
            
            sessionConf.setConference(selectedConference);
            
            session.save(sessionConf);
        }
        
        session.save(selectedConference);
        
        
        tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      
      return "index";
    }
    
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

    
    public void searchConferences() {
        filteredConferences = new ArrayList<>();
        
        if (searchText != null) {
            for (Conference conf : allConferences) {
                if (conf.getName().contains(searchText)) {
                    filteredConferences.add(conf);
                }
            }
        }
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

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
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

    public List<SessionConf> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionConf> sessions) {
        this.sessions = sessions;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<String> getRoomNames() {
        return roomNames;
    }

    public void setRoomNames(List<String> roomNames) {
        this.roomNames = roomNames;
    }

    public List<Presentation> getPresentations() {
        return presentations;
    }

    public void setPresentations(List<Presentation> presentations) {
        this.presentations = presentations;
    }

    
    
}
