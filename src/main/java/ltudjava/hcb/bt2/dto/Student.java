package ltudjava.hcb.bt2.dto;
// Generated Aug 18, 2019 10:56:04 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
     private String fullname;
     private String sex;
     private String personCode;
     private Integer grade;

    public Student() {
    }

	
    public Student(String studentCode) {
        this.studentCode = studentCode;
    }
    public Student(String studentCode, String fullname, String sex, String personCode, Integer grade) {
       this.studentCode = studentCode;
       this.fullname = fullname;
       this.sex = sex;
       this.personCode = personCode;
       this.grade = grade;
    }
   
     @Id 

    
    @Column(name="student_code", unique=true, nullable=false, length=8)
    public String getStudentCode() {
        return this.studentCode;
    }
    
    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
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

    
    @Column(name="grade")
    public Integer getGrade() {
        return this.grade;
    }
    
    public void setGrade(Integer grade) {
        this.grade = grade;
    }




}


