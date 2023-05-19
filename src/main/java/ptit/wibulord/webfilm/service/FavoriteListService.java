package ptit.wibulord.webfilm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptit.wibulord.webfilm.model.FavoriteList;
import ptit.wibulord.webfilm.repository.FavoriteListRepository;
@Service
public class FavoriteListService {
    @Autowired
    FavoriteListRepository favoriteListRepository;

    public void addFavoriteList(FavoriteList favoriteList){
        favoriteListRepository.save(favoriteList);
    }
    public FavoriteList findByID(int id){
        return favoriteListRepository.getById(id);
    }
}
