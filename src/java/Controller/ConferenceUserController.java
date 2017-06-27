/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Conference;
import Model.User;
import Model.UserConference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;

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
    
    public ConferenceUserController() {
        allConferences = Conference.getAll();
        filteredConferences = new ArrayList<>();
        
        
        
    }
    
    public void onload(User user) {
        registredConferences = Conference.getByRegistredUser(user);
    }
    
    public void searchConferences() {
        filteredConferences = new ArrayList<>();
        
        if (searchText != null) {
            for (Conference conf : allConferences) {
               
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
            }
        }
    }
    
    public void registerForConference(User user, Conference conference) {
        UserConference.addNew(user, conference);
        
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
    
    
}
