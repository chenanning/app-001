package icu.huajuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/***
 *
 * @author Chen Anning
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class App001NoteApplication {
    public static void main(String[] args) {
        SpringApplication.run(App001NoteApplication.class, args);
    }
}