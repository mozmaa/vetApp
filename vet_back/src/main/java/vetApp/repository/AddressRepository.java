package vetApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vetApp.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
