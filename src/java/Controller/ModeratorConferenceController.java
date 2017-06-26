/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Conference;
import Model.User;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author rols
 */
@ManagedBean(name="moderator_conference")
@javax.faces.bean.SessionScoped
public class ModeratorConferenceController {
    private List<Conference> conferencesForCurrent;
    @ManagedProperty(value="#{login}")
    private LoginController loginController;

    public ModeratorConferenceController() {
    
        //conferencesForCurrent = Conference.getByUser(loginController.getCurrentUser());
    }
    
    public void onload(User u) {
        conferencesForCurrent = Conference.getByUser(u);
    }

    public List<Conference> getConferencesForCurrent() {
        return conferencesForCurrent;
    }

    public void setConferencesForCurrent(List<Conference> conferencesForCurrent) {
        this.conferencesForCurrent = conferencesForCurrent;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
   
    
}
