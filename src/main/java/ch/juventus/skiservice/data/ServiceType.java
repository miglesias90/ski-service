package ch.juventus.skiservice.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: Alexandra
 * @since: 31.01.2019
 **/
@Entity
@Table(name = "vServiceTyp")
public class ServiceType {

    @Id
    private String servicetype;

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    @Override
    public String toString() {
        return "ServiceType{" +
                "servicetype='" + servicetype + '\'' +
                '}';
    }
}
