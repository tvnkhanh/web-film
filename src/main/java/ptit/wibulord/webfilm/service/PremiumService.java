package ptit.wibulord.webfilm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptit.wibulord.webfilm.model.Premium;
import ptit.wibulord.webfilm.repository.PremiumRepository;

import java.util.List;

@Service
public class PremiumService {
    @Autowired
    private PremiumRepository premiumRepository;

    public void addPack(Premium premium){
        premiumRepository.save(premium);
    }
    public List<Premium> getPremiumList(){
        return premiumRepository.findAll();
    }
}
