package top.annokshon.kungfu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class KungfuApplication {

    public static void main(String[] args) {
        SpringApplication.run(KungfuApplication.class, args);
    }

}
