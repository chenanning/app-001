package icu.huajuan.config;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Client;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * 七牛云 oss 配置类
 *
 * @author Chen Anning
 **/
@Configuration
@Slf4j
public class CustomQiNiuKodoConfig {
    @Value("${qiniu.accessKey}")
    public String accessKey;

    @Value("${qiniu.secretKey}")
    public String secretKey;

    @Value("${qiniu.region}")
    public String region;

    /**
     * 配置空间的存储区域
     */
    @Bean
    public com.qiniu.storage.Configuration qiNiuConfig() {
        return new com.qiniu.storage.Configuration(Zone.huabei());
    }

    /**
     * 构建一个七牛上传工具实例
     */
    @Bean
    public UploadManager uploadManager() {
        return new UploadManager(qiNiuConfig());
    }

    /**
     * 认证信息实例
     */
    @Bean
    public Auth auth() {
        return Auth.create(accessKey, secretKey);
    }

    /**
     * 构建七牛空间管理实例
     */
    @Bean
    public BucketManager bucketManager() {
        return new BucketManager(auth(), qiNiuConfig());
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

}
