package icu.huajuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "icu.huajuan.user")
public class App001FileApplication {

	public static void main(String[] args) {
		SpringApplication.run(App001FileApplication.class, args);
	}

}
