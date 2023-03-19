package ptit.wibulord.webfilm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.wibulord.webfilm.model.Premium;

@Repository
public interface PremiumRepository extends JpaRepository<Premium, Integer> {
}
