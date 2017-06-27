/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Controller;

import Model.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.validator.ValidatorException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import sun.misc.IOUtils;

/**
 *
 * @author rols
 */
@ManagedBean(name="register")
@SessionScoped
public class RegisterController {
    
    private String first_name;
    private String last_name;
    private String username;
    private String password;
//    private String password_repeat;
    private String phone;
    private String email;
    private String institution;
    private String shirt_size;
    private String linkedin;
    private String sex;
    private String[] shirt_sizes = {"S", "M", "L", "XL", "XXL"};
    private String profile_pic;
    private String[] sexs = {"M", "F"};
    
    private boolean email_valid = false;
    private boolean password_valid = false;
    private boolean username_valid = false;
    private boolean all_valid = false;
    
    public final String UPLOADDIR = "C:\\workspace\\UMRI\\pia\\";
    public final String PROFILEPIC = "uploads\\profilepics\\";
    
    
    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        try{
            if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
                // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
                return new DefaultStreamedContent();
            }
            else {
                // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
                String filename = context.getExternalContext().getRequestParameterMap().get("filename");
                File file = new File(UPLOADDIR + PROFILEPIC, profile_pic);
                FileInputStream fs = new FileInputStream(file);
                return new DefaultStreamedContent(fs);
            }
        } catch(Exception e) {
            return null;
        }
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
    
    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile fileUploaded = event.getFile();
        
        String fileName = fileUploaded.getFileName();
        profile_pic = fileName;
        Path folder = Paths.get(UPLOADDIR + PROFILEPIC);
        Path filePath  = Paths.get(UPLOADDIR + PROFILEPIC + fileName);
        String extension = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
        String filename = fileName.substring(0,fileName.lastIndexOf('.'));
        try {
            // Make sure the directories exist
            Files.createDirectories(folder);
            
            try (InputStream in = new ByteArrayInputStream(fileUploaded.getContents())) {
                Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ValidateUsername(FacesContext fc, UIComponent c, Object value) {
        username_valid = User.FindByUsername((String) value) == null;
        updateAllValid();
        if (!username_valid) {
            throw new ValidatorException(
                    new FacesMessage("Username in use!"));
        }
    }
    
    public void ValidateEmail(FacesContext fc, UIComponent c, Object value) {
        email_valid = User.FindByEmail((String) value) == null;
        updateAllValid();
        if (!email_valid) {
            throw new ValidatorException(
                    new FacesMessage("Email in use!"));
        }
    }
    
    public void ValidatePassword(FacesContext fc, UIComponent c, Object value) {
        String password = (String) value;
        String password_repeat = (String) c.getAttributes().get("passwordRepeat");
        
        if (password == null || password_repeat == null) {
            password_valid = true;
            return;
        }
        
        password_valid = true;
        
        if (password.length() < 8) {
            password_valid = false;
            throw new ValidatorException(new FacesMessage("Password week! Need at least 8 characters"));
        }
        if (password.length() > 12) {
            password_valid = false;
            throw new ValidatorException(new FacesMessage("Password too long! Need at most 12 characters"));
        }
        {
            int small = 0, big = 0, num = 0, other = 0;
            for (char i : password.toCharArray()) {
                if (i >= 'a' && i <= 'z') small ++;
                else if (i >= 'A' && i <= 'Z') big ++;
                else if (i >= '0' && i <= '9') num ++;
                else other ++;
                
            }
            if (small < 3 || big < 1 || num < 1 || other < 1) {
                password_valid = false;
                throw new ValidatorException(new FacesMessage("Password week! Need at least 3 small letters, 1 capital, 1 number and 1 special char"));
            }
        }
        
        
        
//        System.out.println("repeat " + password_repeat + " pass " + password);

if (password == null || password_repeat == null) {
    password_valid = true;
} else if (!password.equals(password_repeat)) {
    password_valid = false;
    updateAllValid();
    throw new ValidatorException(new FacesMessage("Passwords do not match!"));
}

updateAllValid();
    }
    
    private void updateAllValid() {
        System.out.println("updateing all valid " + all_valid + "| email " + email_valid + "| username " + username_valid + "| pass " + password_valid);
        all_valid = email_valid && username_valid && password_valid;
    }
    
    public String TryRegister() {
        User user = new User(
                first_name,
                last_name,
                username,
                password,
                phone,
                email,
                new Date(),
                User.UserType.USER,
                false,
                sex.equals("M"),
                linkedin,
                shirt_size,
                institution,
                profile_pic
        );
        
        User.addUser(user);
        
        return "home_user";
    }
    
    
    
    public boolean isAll_valid() {
        return all_valid;
    }
    
    public void setAll_valid(boolean all_valid) {
        this.all_valid = all_valid;
    }
    
    
    
    public String getFirst_name() {
        return first_name;
    }
    
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    
    public String getLast_name() {
        return last_name;
    }
    
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
//    public String getPassword_repeat() {
//        return password_repeat;
//    }
//
//    public void setPassword_repeat(String password_repeat) {
//        this.password_repeat = password_repeat;
//    }
    
    
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getInstitution() {
        return institution;
    }
    
    public void setInstitution(String institution) {
        this.institution = institution;
    }
    
    public String getShirt_size() {
        return shirt_size;
    }
    
    public void setShirt_size(String shirt_size) {
        this.shirt_size = shirt_size;
    }
    
    public String getLinkedin() {
        return linkedin;
    }
    
    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
    
    public String getSex() {
        return sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public String[] getShirt_sizes() {
        return shirt_sizes;
    }
    
    public void setShirt_sizes(String[] shirt_sizes) {
        this.shirt_sizes = shirt_sizes;
    }
    
    public String[] getSexs() {
        return sexs;
    }
    
    public void setSexs(String[] sexs) {
        this.sexs = sexs;
    }
    
    public String getWhole_profile_pic() {
        return UPLOADDIR + PROFILEPIC + profile_pic;
    }
    
    public void setWhole_profile_pic(String whole_profile_pic) {
        
    }
    
    
}
