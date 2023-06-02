package ptit.wibulord.webfilm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptit.wibulord.webfilm.model.DetailPurchase;
import ptit.wibulord.webfilm.repository.DetailPurchaseRepository;

@Service
public class DetailPurchaseService {
    @Autowired
    DetailPurchaseRepository repository;
    public void save(DetailPurchase detail){
        repository.save(detail);
    }
}
