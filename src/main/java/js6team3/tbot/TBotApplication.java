package js6team3.tbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TBotApplication.class, args);
        System.out.println("JavaSprint6_Team3 Проект");
    }
}