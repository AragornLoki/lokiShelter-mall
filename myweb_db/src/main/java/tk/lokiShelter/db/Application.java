package tk.lokiShelter.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"tk.lokiShelter.db"})
@MapperScan("tk.lokiShelter.db.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(tk.lokiShelter.db.Application.class, args);
    }

}