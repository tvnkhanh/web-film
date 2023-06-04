package ptit.wibulord.webfilm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class WebFilmApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFilmApplication.class, args);
    }


}
