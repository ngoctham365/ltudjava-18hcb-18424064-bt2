package ltudjava.hcb.bt2.dto;
// Generated Aug 18, 2019 10:56:04 AM by Hibernate Tools 4.3.1


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TimeTable generated by hbm2java
 */
@Entity
@Table(name="time_table"
    ,catalog="scoremanagement"
)
public class TimeTable  implements java.io.Serializable {


     private TimeTableId id;
     private String room;

    public TimeTable() {
    }

	
    public TimeTable(TimeTableId id) {
        this.id = id;
    }
    public TimeTable(TimeTableId id, String room) {
       this.id = id;
       this.room = room;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="subjectCode", column=@Column(name="subject_code", nullable=false, length=8) ), 
        @AttributeOverride(name="grade", column=@Column(name="grade", nullable=false) ) } )
    public TimeTableId getId() {
        return this.id;
    }
    
    public void setId(TimeTableId id) {
        this.id = id;
    }

    
    @Column(name="room", length=8)
    public String getRoom() {
        return this.room;
    }
    
    public void setRoom(String room) {
        this.room = room;
    }




}


