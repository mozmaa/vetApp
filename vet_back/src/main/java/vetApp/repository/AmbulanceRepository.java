package vetApp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vetApp.model.Ambulance;

@Repository
public interface AmbulanceRepository extends JpaRepository<Ambulance, Long> {

	@Query("SELECT a FROM Ambulance a JOIN a.address addr WHERE " +
			"(:name IS NULL OR a.name LIKE %:name%) " + 
			"AND (a.closed = :closed)" +
			"AND (:city IS NULL OR addr.city LIKE %:city%)")
	Page<Ambulance> search(
			@Param("name") String name,
			@Param("city") String city,
			@Param("closed") Boolean closed, Pageable pageable);

}
