package ptit.wibulord.webfilm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ptit.wibulord.webfilm.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.idUser = :id")
    public User findById(@Param("id")int id);
    @Query("select u from User u where u.email = :email")
    public User findByEmail(@Param("email")String email);
    @Query("select maxindex() from User")
    public int findMaxId();
}
