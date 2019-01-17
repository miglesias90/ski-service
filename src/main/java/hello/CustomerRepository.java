package hello;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<ServiceOrder, Long> {

	//List<ServiceOrder> findByLastNameStartsWithIgnoreCase(String lastName);
}
