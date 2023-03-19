package ptit.wibulord.webfilm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.wibulord.webfilm.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}
