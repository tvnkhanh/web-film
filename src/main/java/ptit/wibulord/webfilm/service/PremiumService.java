package ptit.wibulord.webfilm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ptit.wibulord.webfilm.model.Premium;
import ptit.wibulord.webfilm.repository.PremiumRepository;

import java.util.List;

@Service
public class PremiumService {
    @Autowired
    private PremiumRepository premiumRepository;

    public Premium getPackById(int id){
        return premiumRepository.findById(id).get();
    }
    public void deletePack(int id){
        premiumRepository.deleteById(id);
    }
    public void addPack(Premium premium){
        premiumRepository.save(premium);
    }
    public List<Premium> getPremiumList(){
        return premiumRepository.findAll(Sort.by(Sort.Direction.ASC,"idPackage"));
    }
}
