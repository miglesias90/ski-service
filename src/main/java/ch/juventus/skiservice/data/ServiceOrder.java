package ch.juventus.skiservice.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "vServiceAuftrag")
public class ServiceOrder {

	@Id
	private Long id;

	private String customername;

	private String customeremail;

	private String customerphone;

    private String servicestate;

	private String servicepriority;

	private String servicetype;

	private Date startdate;

	private Date enddate;



	public ServiceOrder() {
	}

    public ServiceOrder(String customername, String customeremail, String customerphone, String servicepriority, String servicestate, String servicetype, Date startdate, Date enddate) {
        this.customername = customername;
        this.customeremail = customeremail;
        this.customerphone = customerphone;
        this.servicepriority = servicepriority;
        this.servicestate = servicestate;
        this.servicetype = servicetype;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public Long getId() {
        return id;
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

    public String getServicepriority() {
        return servicepriority;
    }

    public String getServicestate() {
        return servicestate;
    }

    public String getServicetype() {
        return servicetype;
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

    public void setServicestate(String servicestate) {
        this.servicestate = servicestate;
    }

    public void setServicepriority(String servicepriority) {
        this.servicepriority = servicepriority;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    @Override
	public String toString() {
		return String.format("ServiceOrder[id=%d, customername='%s', customeremail='%s']", id,
                customername, customeremail);
	}

}
