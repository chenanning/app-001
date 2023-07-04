package icu.huajuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class App001FileApplication {

	public static void main(String[] args) {
		SpringApplication.run(App001FileApplication.class, args);
	}

}
