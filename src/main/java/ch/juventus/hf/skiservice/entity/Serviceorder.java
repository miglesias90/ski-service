package ch.juventus.hf.skiservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Serviceorder {

    @Id
    @GeneratedValue
    private Long id;

    private String customername;

    //private String customeremail;

    //private String customerphone;

    /*private Priority priority;

    private Service service;

    private State state;

    private Date startdate;

    private Date enddate;*/

    public Serviceorder(String customername) {
        this.customername = customername;
        /*this.customeremail = customeremail;
        this.customerphone = customerphone;
        this.priority = priority;
        this.service = service;*/
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }
}
