package tk.lokiShelter.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"tk.lokiShelter.db", "tk.lokiShelter.manage", "tk.lokiShelter.server"})
@MapperScan(basePackages={"tk.lokiShelter.db.dao","tk.lokiShelter.server.dao"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
