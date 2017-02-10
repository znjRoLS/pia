/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MusicEvent;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author rols
 */
@ManagedBean(name="search")
@RequestScoped
public class SearchController {
    private List<MusicEvent> musicEvents;
    private String searchText;
    private List<MusicEvent> filteredEvents;

    public SearchController() {
        musicEvents = MusicEvent.getMusicEvents();
    }
    
    public void filterEvents() {
        
        System.out.println("filter meeeee");
        filteredEvents = new ArrayList<>();
        
        if (searchText != null) {
            for (MusicEvent musicEvent : musicEvents) {
                System.out.println(filteredEvents.size() + " " + searchText);
                if (musicEvent.getName().contains(searchText)) {
                    filteredEvents.add(musicEvent);
                }
        }
        }
        
        System.out.println("e " + musicEvents.size());
    }
    
    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public List<MusicEvent> getFilteredEvents() {
        return filteredEvents;
    }

    public void setFilteredEvents(List<MusicEvent> filteredEvents) {
        this.filteredEvents = filteredEvents;
    }
    
    
}
