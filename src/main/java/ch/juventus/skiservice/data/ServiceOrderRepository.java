package ch.juventus.skiservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {
    @Transactional
    @Procedure(procedureName = "insert_service")
    void insertService(@Param("customername") String customerName, @Param("customeremail") String customerEmail,@Param("customerphone") String customerPhone,@Param("servicetype") String serviceType,@Param("servicepriority") String servicePriority);

    @Transactional
    @Procedure(procedureName = "update_service")
    void updateService(@Param("id") Long id,@Param("customername") String customerName, @Param("customeremail") String customerEmail,@Param("customerphone") String customerPhone,@Param("servicestate") String serviceState);
}
