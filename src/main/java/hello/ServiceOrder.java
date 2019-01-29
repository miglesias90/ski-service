package hello;

import javafx.scene.layout.Priority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class ServiceOrder {

	@Id
	@GeneratedValue
	private Long id;

	private String customerName;

	private String customerEmail;

	private String customerPhone;

	private String priority;

	private String status;

	private String serviceType;

	private String startDate;

	private String endDate;



	protected ServiceOrder() {
	}

    public ServiceOrder(String customerName, String customerEmail, String customerPhone, String priority, String status, String serviceType, String startDate, String endDate) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.priority = priority;
        this.status = status;
        this.serviceType = serviceType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
	public String toString() {
		return String.format("ServiceOrder[id=%d, customerName='%s', customerEmail='%s']", id,
				customerName, customerEmail);
	}

}
