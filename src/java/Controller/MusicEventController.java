/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.HibernateHelper;
import Model.MusicEvent;
import Model.Performer;
import Model.SocialNetwork;
import Model.TicketType;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author rols
 */
@ManagedBean(name="festival")
@ViewScoped
public class MusicEventController {
  
    private String musicEventId;
    
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
    
    public void showMusicEvent() {
        Session session = HibernateHelper.getFactory().openSession();
      Transaction tx = null;
      try{
        tx = session.beginTransaction();
         
        musicEvent = (MusicEvent) session.get(MusicEvent.class, musicEventId);
        ticketTypes = musicEvent.getTicketTypes();
        performers = musicEvent.getPerformers();
        socialNetworks = musicEvent.getSocialNetworks();
  
        tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
    }
    
    public String addFestival() {
        Session session = HibernateHelper.getFactory().openSession();
      Transaction tx = null;
      Integer userID = null;
      try{
        tx = session.beginTransaction();
         
        for(TicketType ticketType : ticketTypes) {
            ticketType.setMusicEvent(musicEvent);
            session.save(ticketType);
        }
  
        for(Performer ticketType : performers) {
            ticketType.setMusicEvent(musicEvent);
            session.save(ticketType);
        }
        for(SocialNetwork ticketType : socialNetworks) {
            ticketType.setMusicEvent(musicEvent);
            session.save(ticketType);
        }
        
        musicEvent.setSold(0);
        musicEvent.setViewed(0);
        
        session.save(musicEvent);
        
        
        tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      
      return "index";
    }

    public String getMusicEventId() {
        return musicEventId;
    }

    public void setMusicEventId(String musicEventId) {
        this.musicEventId = musicEventId;
    }
    
    
    
    public void addTicketType() {
        ticketTypes.add(new TicketType());
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
