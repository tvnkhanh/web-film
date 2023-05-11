package ptit.wibulord.webfilm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ptit.wibulord.webfilm.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select c from Category c where c.categoryName=:name ")
    public Category findByCategoryName(@Param("name")String name);
    @Query("select c from Category c where c.idCategory=:id ")
    public Category findByCategoryId(@Param("id")int id);

}
