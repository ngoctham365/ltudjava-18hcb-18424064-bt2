package ltudjava.hcb.bt2.dto;
// Generated Aug 18, 2019 10:56:04 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Grade generated by hbm2java
 */
@Entity
@Table(name="grade"
    ,catalog="scoremanagement"
    , uniqueConstraints = @UniqueConstraint(columnNames="name") 
)
public class Grade  implements java.io.Serializable {


     private Integer id;
     private String name;

    public Grade() {
    }

    public Grade(String name) {
       this.name = name;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="name", unique=true, nullable=false, length=8)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }




}


