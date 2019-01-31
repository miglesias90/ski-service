package ch.juventus.skiservice.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: Alexandra
 * @since: 31.01.2019
 **/
@Entity
@Table(name = "vServiceStatus")
public class ServiceState {

    @Id
    private String servicestate;

    public String getServicestate() {
        return servicestate;
    }

    public void setServicestate(String servicestate) {
        this.servicestate = servicestate;
    }

    @Override
    public String toString() {
        return "ServiceState{" +
                "servicestate='" + servicestate + '\'' +
                '}';
    }
}
