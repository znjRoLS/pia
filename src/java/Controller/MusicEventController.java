/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MusicEvent;
import Model.Performer;
import Model.SocialNetwork;
import Model.TicketType;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author rols
 */
@ManagedBean(name="festival")
@ViewScoped
public class MusicEventController {
    private MusicEvent musicEvent;
    private List<TicketType> ticketTypes;
    private List<SocialNetwork> socialNetworks;
    private List<Performer> performers;

    public MusicEventController() {
        musicEvent = new MusicEvent();
        ticketTypes = new ArrayList<>();
        socialNetworks = new ArrayList<>();
        performers = new ArrayList<>();
    }
    
    public void addTicketType() {
        System.out.println("yep, here");
        System.out.println("num " + ticketTypes.size());
        
        ticketTypes.add(new TicketType());
        System.out.println("num " + ticketTypes.size());
    }
    
    public void addSocialNetwork() {
        socialNetworks.add(new SocialNetwork());
    }
    
    public void addPerformer() {
        performers.add(new Performer());
    }
    
    public MusicEvent getMusicEvent() {
        return musicEvent;
    }

    public void setMusicEvent(MusicEvent musicEvent) {
        this.musicEvent = musicEvent;
    }

    public List<TicketType> getTicketTypes() {
        return ticketTypes;
    }

    public void setTicketTypes(List<TicketType> ticketTypes) {
        this.ticketTypes = ticketTypes;
    }

    public List<SocialNetwork> getSocialNetworks() {
        return socialNetworks;
    }

    public void setSocialNetworks(List<SocialNetwork> socialNetworks) {
        this.socialNetworks = socialNetworks;
    }

    public List<Performer> getPerformers() {
        return performers;
    }

    public void setPerformers(List<Performer> performers) {
        this.performers = performers;
    }
    
    
}
