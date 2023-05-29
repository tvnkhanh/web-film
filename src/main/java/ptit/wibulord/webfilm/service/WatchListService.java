package ptit.wibulord.webfilm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptit.wibulord.webfilm.model.WatchList;
import ptit.wibulord.webfilm.repository.WatchListRepository;

@Service
public class WatchListService {
    @Autowired
    WatchListRepository watchListRepository;

    public void addWatchList(WatchList watchList){
        watchListRepository.save(watchList);
    }
    public WatchList findByID(int id){
        return watchListRepository.getById(id);
    }
}
