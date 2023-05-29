package ptit.wibulord.webfilm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ptit.wibulord.webfilm.model.Role;
import ptit.wibulord.webfilm.service.FilmService;
import ptit.wibulord.webfilm.service.RoleService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RoleTest {
    @Autowired
    RoleService roleService;

    @Autowired
    FilmService filmService;
    @Test
    public void get24FilmTopTier(){
        assertThat(filmService.getPageByType("MOVIE")).isGreaterThan(0);
    }
}
