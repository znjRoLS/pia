/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MusicEvent;
import Model.User;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author rols
 */
@ManagedBean(name="admin")
@RequestScoped
public class AdminController {
    private ArrayList<MusicEvent> fiveMostSold;
    private List<User> verificationAwaitingUsers;

    public AdminController() {
        verificationAwaitingUsers = User.getNotVerified();
    }
    
    public List<User> getVerificationAwaitingUsers() {
        return verificationAwaitingUsers;
    }

    public void setVerificationAwaitingUsers(List<User> verificationAwaitingUsers) {
        this.verificationAwaitingUsers = verificationAwaitingUsers;
    }
    
    public void enable(int userId) {
        
//        System.out.println("num " + verificationAwaitingUsers.size());
        System.out.println("userId : "+ userId);
        
        User user = User.FindById(userId);
        removeUser(userId);
        user.enable();
        
//        System.out.println("num " + verificationAwaitingUsers.size());
        
    }
    
    public void disable(int userId) {
        
//        System.out.println("num " + verificationAwaitingUsers.size());
        System.out.println("userId : "+ userId);
        
        User user = User.FindById(userId);
        removeUser(userId);
        User.deleteUser(user);
//        System.out.println("num " + verificationAwaitingUsers.size());
    }
    
    private void removeUser(int userId) {
        List<User> shallBeDeleted = new ArrayList<>();
        
        for (User user: verificationAwaitingUsers) {
            if (user.getId() == userId) {
                shallBeDeleted.add(user);
            }
        }
        
        for (User user: shallBeDeleted) {
            verificationAwaitingUsers.remove(user);
        }
    }
}
