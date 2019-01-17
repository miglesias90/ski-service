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

    private String customeremail;

    private String customerphone;

    private Priority priority;

    private Service service;

    private State state;

    private Date startdate;

    private Date enddate;

    public Serviceorder(String customername, String customeremail, String customerphone, Priority priority, Service service) {
        this.customername = customername;
        this.customeremail = customeremail;
        this.customerphone = customerphone;
        this.priority = priority;
        this.service = service;
    }

    public String getCustomername() {
        return customername;
    }

    public String getCustomeremail() {
        return customeremail;
    }

    public String getCustomerphone() {
        return customerphone;
    }

    public Priority getPriority() {
        return priority;
    }

    public Service getService() {
        return service;
    }

    public State getState() {
        return state;
    }

    public Date getStartdate() {
        return startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public void setCustomeremail(String customeremail) {
        this.customeremail = customeremail;
    }

    public void setCustomerphone(String customerphone) {
        this.customerphone = customerphone;
    }

    public void setState(State state) {
        this.state = state;
    }
}
