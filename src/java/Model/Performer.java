/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;
//import java.sql.Time;
import javafx.scene.chart.PieChart;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author rols
 */
@Entity
@Table(name= "PERFORMER")
public class Performer {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private Date startTime;
    private Date endTime;
    @ManyToOne(cascade = CascadeType.ALL)
    private MusicEvent musicEvent;

    public MusicEvent getMusicEvent() {
        return musicEvent;
    }

    public void setMusicEvent(MusicEvent musicEvent) {
        this.musicEvent = musicEvent;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    
}
