package ptit.wibulord.webfilm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptit.wibulord.webfilm.model.DetailPurchase;

public interface DetailPurchaseRepository extends JpaRepository<DetailPurchase, Integer> {
}
