package ltudjava.hcb.bt2.dto;
// Generated Aug 15, 2019 10:19:42 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Score generated by hbm2java
 */
@Entity
@Table(name="score"
    ,catalog="scoremanagement"
    , uniqueConstraints = @UniqueConstraint(columnNames={"student_id", "subject_id", "grade_id"}) 
)
public class Score  implements java.io.Serializable {


     private Integer id;
     private Student student;
     private TimeTable timeTable;
     private Float scodeHaft;
     private Float scoreFull;
     private Float scoreAnother;
     private Float scoreSummary;
     private Remarking remarking;

    public Score() {
    }

	
    public Score(Student student, TimeTable timeTable) {
        this.student = student;
        this.timeTable = timeTable;
    }
    public Score(Student student, TimeTable timeTable, Float scodeHaft, Float scoreFull, Float scoreAnother, Float scoreSummary, Remarking remarking) {
       this.student = student;
       this.timeTable = timeTable;
       this.scodeHaft = scodeHaft;
       this.scoreFull = scoreFull;
       this.scoreAnother = scoreAnother;
       this.scoreSummary = scoreSummary;
       this.remarking = remarking;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="student_id", nullable=false)
    public Student getStudent() {
        return this.student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( { 
        @JoinColumn(name="grade_id", referencedColumnName="subject_code", nullable=false), 
        @JoinColumn(name="subject_id", referencedColumnName="grade", nullable=false) } )
    public TimeTable getTimeTable() {
        return this.timeTable;
    }
    
    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }

    
    @Column(name="scode_haft", precision=12, scale=0)
    public Float getScodeHaft() {
        return this.scodeHaft;
    }
    
    public void setScodeHaft(Float scodeHaft) {
        this.scodeHaft = scodeHaft;
    }

    
    @Column(name="score_full", precision=12, scale=0)
    public Float getScoreFull() {
        return this.scoreFull;
    }
    
    public void setScoreFull(Float scoreFull) {
        this.scoreFull = scoreFull;
    }

    
    @Column(name="score_another", precision=12, scale=0)
    public Float getScoreAnother() {
        return this.scoreAnother;
    }
    
    public void setScoreAnother(Float scoreAnother) {
        this.scoreAnother = scoreAnother;
    }

    
    @Column(name="score_summary", precision=12, scale=0)
    public Float getScoreSummary() {
        return this.scoreSummary;
    }
    
    public void setScoreSummary(Float scoreSummary) {
        this.scoreSummary = scoreSummary;
    }

@OneToOne(fetch=FetchType.LAZY, mappedBy="score")
    public Remarking getRemarking() {
        return this.remarking;
    }
    
    public void setRemarking(Remarking remarking) {
        this.remarking = remarking;
    }




}


