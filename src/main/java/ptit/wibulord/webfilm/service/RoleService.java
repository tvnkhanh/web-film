package ptit.wibulord.webfilm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptit.wibulord.webfilm.model.Role;
import ptit.wibulord.webfilm.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getRoleList(){
        return roleRepository.findAll();
    }

}
