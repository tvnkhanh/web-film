package ptit.wibulord.webfilm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ptit.wibulord.webfilm.model.Role;
import ptit.wibulord.webfilm.service.RoleService;

import java.util.List;

@SpringBootTest
public class RoleTest {
    @Autowired
    private RoleService roleService;
    @Test
    public void getRoles(){
        List<Role> roleList = roleService.listRole();
        System.out.println(roleList);
    }
}
