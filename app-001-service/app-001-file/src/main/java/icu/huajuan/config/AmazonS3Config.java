package icu.huajuan.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import icu.huajuan.Properties.MinioProperties;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 *
 * @author Chen Anning
 **/
@Configuration
public class AmazonS3Config {
    @Resource
    private MinioProperties minioProperties;

    @Bean
    public AmazonS3 amazonS3Client() {
        // 设置连接时的参数
        ClientConfiguration config = new ClientConfiguration();
        // 设置连接方式为HTTP,可选参数为HTTP和HTTPS
        config.setProtocol(Protocol.HTTP);
        // 设置网络访问超时时间
        config.setConnectionTimeout(5000);
        /**
         * 启用 HTTP/1.1 协议中的 Expect-Continue 机制
         * 启用 Expect-Continue 机制可以在发送大型请求体时提高性能和效率。
         * 当客户端发送包含大量数据的请求时，启用该机制可以让服务器在开始接收实际请求体之前先进行初步验证，以避免在请求体上传输过程中发生不必要的延迟。
         */
        config.setUseExpectContinue(true);
        // 生成用于身份验证的 AWS 凭证
        BasicAWSCredentials credentials = new BasicAWSCredentials(minioProperties.getAccessKey(), minioProperties.getAccessSecret());
        // 设置Endpoint
        AwsClientBuilder.EndpointConfiguration end_point = new AwsClientBuilder.EndpointConfiguration(minioProperties.getEndpoint(), Regions.US_EAST_1.name());
        // 创建 AmazonS3 客户端实例
        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                // 使用标准配置构建客户端
                .withClientConfiguration(config)
                // 设置用于身份验证的 AWS 凭证
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                // 设置客户端的终端节点配置
                .withEndpointConfiguration(end_point)
                // 启用 S3 桶 URL 的路径样式访问
                .withPathStyleAccessEnabled(true)
                // 构建 AmazonS3 客户端
                .build();
        return amazonS3;
    }
}
