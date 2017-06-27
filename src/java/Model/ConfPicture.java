/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rols
 */
public class ConfPicture {
    private int confPictureId;
    private String path;
    private Conference conference;

    
    public final String UPLOADDIR = "C:\\workspace\\UMRI\\pia\\";
    public final String CONFPICTURE = "uploads\\conferencePictures\\";
    
    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        try{
            
            String filename = context.getExternalContext().getRequestParameterMap().get("filename");
            File file = new File(UPLOADDIR + CONFPICTURE + conference.getConferenceId() + "\\", getPath());
            FileInputStream fs = new FileInputStream(file);
            return new DefaultStreamedContent(fs);
        } catch(Exception e) {
            return null;
        }
    }
    
    public static ConfPicture add(Conference conf, String path) {
        ConfPicture picture = new ConfPicture();
        picture.setConference(conf);
        picture.setPath(path);
        
        Session session = HibernateHelper.getFactory().openSession();
        Transaction tx = null;
        try{
          tx = session.beginTransaction();

          session.save(picture);

          tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
        
        return picture;
    }
    
    public int getConfPictureId() {
        return confPictureId;
    }

    public void setConfPictureId(int confPictureId) {
        this.confPictureId = confPictureId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }
    
    
}
