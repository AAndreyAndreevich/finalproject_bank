package bankapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"bankapp."})
public class BankAppRunner {
    public static void main(String[] args) {
        SpringApplication.run(BankAppRunner.class, args);
    }
}