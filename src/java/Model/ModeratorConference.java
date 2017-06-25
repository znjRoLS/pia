/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author rols
 */
public class ModeratorConference {
    
    private int modetator_conference_id;
    private Conference conference_id;
    private User user_id;

    public int getModetator_conference_id() {
        return modetator_conference_id;
    }

    public void setModetator_conference_id(int modetator_conference_id) {
        this.modetator_conference_id = modetator_conference_id;
    }

    
    
    public Conference getConference_id() {
        return conference_id;
    }

    public void setConference_id(Conference conference_id) {
        this.conference_id = conference_id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    

    
    
}
