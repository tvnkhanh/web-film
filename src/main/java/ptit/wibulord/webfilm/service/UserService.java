package ptit.wibulord.webfilm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptit.wibulord.webfilm.model.User;
import ptit.wibulord.webfilm.repository.UserRepository;

import java.util.List;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    public void addUser(User user){
        userRepository.save(user);
    }


    public List<User> getUserList(){
        return userRepository.findAll();
    }

    public User findUserById(int id){
        return userRepository.findById(id);
    }
    public int findIDMax(){
        return userRepository.findMaxId();
    }
    public User findUserByEmail(String email){return userRepository.findByEmail(email);}
}
