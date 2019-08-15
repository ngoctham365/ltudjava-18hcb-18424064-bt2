package ltudjava.hcb.bt2.dto;
// Generated Aug 15, 2019 10:19:42 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Remarking generated by hbm2java
 */
@Entity
@Table(name="remarking"
    ,catalog="scoremanagement"
)
public class Remarking  implements java.io.Serializable {


     private int scoreId;
     private Score score;
     private String scoreType;
     private Float scoreOld;
     private Float scoreDesired;
     private String reason;
     private String status;

    public Remarking() {
    }

	
    public Remarking(Score score) {
        this.score = score;
    }
    public Remarking(Score score, String scoreType, Float scoreOld, Float scoreDesired, String reason, String status) {
       this.score = score;
       this.scoreType = scoreType;
       this.scoreOld = scoreOld;
       this.scoreDesired = scoreDesired;
       this.reason = reason;
       this.status = status;
    }
   
     @GenericGenerator(name="generator", strategy="foreign", parameters=@Parameter(name="property", value="score"))@Id @GeneratedValue(generator="generator")

    
    @Column(name="score_id", unique=true, nullable=false)
    public int getScoreId() {
        return this.scoreId;
    }
    
    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

@OneToOne(fetch=FetchType.LAZY)@PrimaryKeyJoinColumn
    public Score getScore() {
        return this.score;
    }
    
    public void setScore(Score score) {
        this.score = score;
    }

    
    @Column(name="score_type", length=16)
    public String getScoreType() {
        return this.scoreType;
    }
    
    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    
    @Column(name="score_old", precision=12, scale=0)
    public Float getScoreOld() {
        return this.scoreOld;
    }
    
    public void setScoreOld(Float scoreOld) {
        this.scoreOld = scoreOld;
    }

    
    @Column(name="score_desired", precision=12, scale=0)
    public Float getScoreDesired() {
        return this.scoreDesired;
    }
    
    public void setScoreDesired(Float scoreDesired) {
        this.scoreDesired = scoreDesired;
    }

    
    @Column(name="reason", length=512)
    public String getReason() {
        return this.reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }

    
    @Column(name="status", length=32)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }




}


