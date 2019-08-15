package ltudjava.hcb.bt2.dto;
// Generated Aug 15, 2019 11:16:22 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Student generated by hbm2java
 */
@Entity
@Table(name="student"
    ,catalog="scoremanagement"
)
public class Student  implements java.io.Serializable {


     private String studentCode;
     private Grade grade;
     private String fullname;
     private String sex;
     private String personCode;
     private Set<Score> scores = new HashSet<Score>(0);
     private User user;

    public Student() {
    }

	
    public Student(String studentCode) {
        this.studentCode = studentCode;
    }
    public Student(String studentCode, Grade grade, String fullname, String sex, String personCode, Set<Score> scores, User user) {
       this.studentCode = studentCode;
       this.grade = grade;
       this.fullname = fullname;
       this.sex = sex;
       this.personCode = personCode;
       this.scores = scores;
       this.user = user;
    }
   
     @Id 

    
    @Column(name="student_code", unique=true, nullable=false, length=8)
    public String getStudentCode() {
        return this.studentCode;
    }
    
    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="grade")
    public Grade getGrade() {
        return this.grade;
    }
    
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    
    @Column(name="fullname", length=128)
    public String getFullname() {
        return this.fullname;
    }
    
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    
    @Column(name="sex", length=4)
    public String getSex() {
        return this.sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }

    
    @Column(name="person_code", length=12)
    public String getPersonCode() {
        return this.personCode;
    }
    
    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="student")
    public Set<Score> getScores() {
        return this.scores;
    }
    
    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

@OneToOne(fetch=FetchType.LAZY, mappedBy="student")
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }




}


