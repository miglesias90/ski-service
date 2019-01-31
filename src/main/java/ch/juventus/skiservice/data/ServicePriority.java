package ch.juventus.skiservice.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: Alexandra
 * @since: 31.01.2019
 **/
@Entity
@Table(name = "vServicePrioritaet")
public class ServicePriority {

    @Id
    private String servicepriority;

    public String getServicepriority() {
        return servicepriority;
    }

    public void setServicepriority(String servicepriority) {
        this.servicepriority = servicepriority;
    }

    @Override
    public String toString() {
        return "ServicePriority{" +
                "servicepriority='" + servicepriority + '\'' +
                '}';
    }
}
