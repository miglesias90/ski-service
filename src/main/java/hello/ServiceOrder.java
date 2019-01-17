package hello;

import javafx.scene.layout.Priority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ServiceOrder {

	@Id
	@GeneratedValue
	private Long id;

	private String customerName;

	private String customerEmail;

	private String customerPhone;

	private Priority priority;

	protected ServiceOrder() {
	}

	public ServiceOrder(String customerName, String customerEmail, String customerPhone, Priority priority) {
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
		this.priority = priority;
	}

	public Long getId() {
		return id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return String.format("ServiceOrder[id=%d, customerName='%s', customerEmail='%s']", id,
				customerName, customerEmail);
	}

}
