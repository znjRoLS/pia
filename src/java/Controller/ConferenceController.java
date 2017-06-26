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
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import org.primefaces.json.*;
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

    private UploadedFile conferenceFile;
    
    private String fileUploadError;

    public UploadedFile getConferenceFile() {
        return conferenceFile;
    }

    public void setConferenceFile(UploadedFile conferenceFile) {
        this.conferenceFile = conferenceFile;
    }
     
    
    // convert InputStream to String
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

                br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                        sb.append(line);
                }

        } catch (IOException e) {
                e.printStackTrace();
        } finally {
                if (br != null) {
                        try {
                                br.close();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }

        return sb.toString();

    }
    
    public void fileUpload(FileUploadEvent event) {
        conferenceFile = event.getFile();
        
        if (conferenceFile == null) {
            return;
        }
        String str = conferenceFile.getFileName();
        String ext = str.substring(str.lastIndexOf('.'), str.length());
        if (ext.equals(".json")) {
            jsonParse();
        }
        if (ext.equals(".csv")) {
            //csvParse();
        }
    }
    
    private void jsonParse() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            InputStream is = new ByteArrayInputStream(conferenceFile.getContents());

            String fileContents = getStringFromInputStream(is);

            JSONObject obj = new JSONObject(fileContents);
            String confTitle = obj.getJSONObject("Conference").getString("Title");

            if (!confTitle.equals(selectedConference.getName())) {
                context.addMessage(null, new FacesMessage("Unsuccessful",  "Conference names don't match!") );
                return;
            }

            Session session = HibernateHelper.getFactory().openSession();
            Transaction tx = null;
            Integer userID = null;
            try{
                tx = session.beginTransaction();

                JSONArray arr = obj.getJSONObject("Conference").getJSONArray("Program");
                for (int i = 0; i < arr.length(); i++)
                {
                    SessionConf sessionConf = new SessionConf();
                    sessionConf.setConference(selectedConference);

                    String sessionName = arr.getJSONObject(i).getString("SessionName");
                    sessionConf.setName(sessionName);

                    String type = arr.getJSONObject(i).getString("Type");
                    sessionConf.setType(type);

                    try {
                        Integer roomId = arr.getJSONObject(i).getInt("Room");
                        //Integer roomId = Integer.parseInt(room);
                        for(Room roomObj : rooms) {
                            if (roomObj.getId() == roomId) {
                                sessionConf.setRoom(roomObj);
                                break;
                            }
                        }
                    } catch (Exception e) {

                    }


                    try {
                        String dateStr = arr.getJSONObject(i).getString("Date");
                        DateFormat df = new SimpleDateFormat("DD/MM/yyyy");
                        Date date = df.parse(dateStr);
                        sessionConf.setDate(date);
                    } catch (Exception e) {

                    }
                    try {
                        String timeStr = arr.getJSONObject(i).getString("StartTime");
                        DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                        Date time = df.parse(timeStr);
                        sessionConf.setStartTime(time);
                    } catch (Exception e) {

                    }
                    try {
                        String timeStr = arr.getJSONObject(i).getString("EndTime");
                        DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                        Date time = df.parse(timeStr);
                        sessionConf.setEndTime(time);
                    } catch (Exception e) {

                    }

                    sessions.add(sessionConf);
                    session.save(sessionConf);
                    
                    if (sessionConf.getRoom() != null) {
                        sessionConf.setRoomName(sessionConf.getRoom().getName());
                    }
        
        
                    
                }

                arr = obj.getJSONObject("Conference").getJSONArray("Presentations");
                for (int i = 0; i < arr.length(); i++)
                {
                    Presentation presentation = new Presentation();


                    String presentationName = arr.getJSONObject(i).getString("PresentationName");
                    presentation.setName(presentationName);

                    String sessionName = arr.getJSONObject(i).getString("SessionName");
                    for (SessionConf sessionConf : sessions) {
                        if (sessionConf.getName().equals(sessionName)) {
                            presentation.setSession(sessionConf);
                            sessionConf.getPresentations().add(presentation);
                            break;
                        }
                    }

                    try {
                        String timeStr = arr.getJSONObject(i).getString("StartTime");
                        DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                        Date time = df.parse(timeStr);
                        presentation.setStartTime(time);
                    } catch (Exception e) {

                    }

                    session.save(presentation);
                    presentations.add(presentation);
                    
                    String authors = arr.getJSONObject(i).getString("Authors");
                    for (String singleAuthor : authors.split(", ")) {
                        User user = User.FindByNameSurname(
                                singleAuthor.split(" ")[0], 
                                singleAuthor.split(" ").length > 1 ? singleAuthor.split(" ")[1] : "");

                        if (user == null) {
                            user = new User();
                            user.setFirst_name(singleAuthor.split(" ")[0]);
                            user.setLast_name(singleAuthor.split(" ").length > 1 ? singleAuthor.split(" ")[1] : "");
                            user.setType(User.UserType.USER.ordinal());
                            session.save(user);
                        }

                        AuthorPresentation authPres = new AuthorPresentation();
                        authPres.setAuthor(user);
                        authPres.setPresentation(presentation);

                        session.save(authPres);
                        presentation.getAuthors().add(authPres);
                    }
                    
                    
                    for (AuthorPresentation authPres : presentation.getAuthors()) {
                        presentation.getAuthorNames().add(authPres.getAuthor());
                    }
                    presentation.setSessionName(presentation.getSession().getName());

                }
                tx.commit();
            }catch (HibernateException e) {
             if (tx!=null) tx.rollback();
             e.printStackTrace();

          }finally {
             session.close(); 
          }
        } catch(Exception e) {
            context.addMessage(null, new FacesMessage("Unsuccessful",  e.getMessage()) );
                return;
        }
        
    }
    
    
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
            if (ses.getRoom() != null) {
                ses.setRoomName(ses.getRoom().getName());
            }
            
        }
        
        presentations = Presentation.getByConference(selectedConference);
        
        for (Presentation presentation : presentations) {
            for (AuthorPresentation authPres : presentation.getAuthors()) {
                presentation.getAuthorNames().add(authPres.getAuthor());
            }
            presentation.setSessionName(presentation.getSession().getName());
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

    public String getFileUploadError() {
        return fileUploadError;
    }

    public void setFileUploadError(String fileUploadError) {
        this.fileUploadError = fileUploadError;
    }

    
    
}
