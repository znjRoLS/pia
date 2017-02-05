/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MusicEvent;
import java.util.ArrayList;
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
    private String search_text;
    private ArrayList<MusicEvent> filtered_events;

    public String getSearch_text() {
        return search_text;
    }

    public void setSearch_text(String search_text) {
        this.search_text = search_text;
    }

    public ArrayList<MusicEvent> getFiltered_events() {
        return filtered_events;
    }

    public void setFiltered_events(ArrayList<MusicEvent> filtered_events) {
        this.filtered_events = filtered_events;
    }
    
    
}
