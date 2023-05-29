package ptit.wibulord.webfilm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ptit.wibulord.webfilm.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    @Query("select a from Account a where a.username = :username")
    public Account findAccountByUsername(@Param("username") String username);
    @Query("select a from Account a where a.username = :username and a.password = :password")
    public Account findAccountByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
}
