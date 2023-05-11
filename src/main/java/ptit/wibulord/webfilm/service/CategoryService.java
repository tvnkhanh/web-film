package ptit.wibulord.webfilm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ptit.wibulord.webfilm.model.Category;
import ptit.wibulord.webfilm.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getCategoryList(){
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC,"idCategory"));
    }
    public void saveCategory(Category category){
         categoryRepository.save(category);
    }
    public void deleteCategory(int id){
        categoryRepository.deleteById(id);
    }
    public Category findByName(String name){
        return categoryRepository.findByCategoryName(name);
    }
    public Category findById(int id){
        return categoryRepository.findByCategoryId(id);
    }
}
