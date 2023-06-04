package ptit.wibulord.webfilm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptit.wibulord.webfilm.model.BuyFilm;
import ptit.wibulord.webfilm.repository.BuyFilmRepository;

@Service
public class BuyFilmService {
    @Autowired
    BuyFilmRepository buyFilmRepository;
    public int checkFilmInMyFilmList(int idFilm, int userID){
        if(buyFilmRepository.checkFilm(idFilm,userID) == null){
            return 0;
        }
        return 1;
    }
    public void save(BuyFilm buy) {
        buyFilmRepository.save(buy);
    }
}
