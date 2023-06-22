package iuc.huajuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class App001GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(App001GatewayApplication.class, args);
    }

}
