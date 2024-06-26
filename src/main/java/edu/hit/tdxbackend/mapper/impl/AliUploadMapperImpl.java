package edu.hit.tdxbackend.mapper.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import edu.hit.tdxbackend.mapper.UploadMapper;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class AliUploadMapperImpl implements UploadMapper {
    private final String endpoint = "oss-cn-beijing.aliyuncs.com";
    // 使用环境变量中获取的RAM用户的访问密钥配置访问凭证。
    private final EnvironmentVariableCredentialsProvider credentialsProvider =
            CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
    private final OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

    public AliUploadMapperImpl() throws com.aliyuncs.exceptions.ClientException {
    }

    @Override
    public String uploadImage(String imageName, InputStream input, String type) {
//        System.out.println("uploadImage------" + type);
        try {
            switch (type) {
                case "type_single" -> imageName = "productSingle/" + imageName;
                case "type_detail" -> imageName = "productDetail/" + imageName;
                case "category" -> imageName = "category/" + imageName;
                case "type_single_middle" -> imageName = "productSingleMiddle/" + imageName;
                case "type_single_small" -> imageName = "productSingleSmall/" + imageName;
                case null, default -> {
                    return null;
                }
            }
            // 创建PutObjectRequest对象。
            String bucketName = "online-store-wenruyv";
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, imageName, input);
            // 创建PutObject请求。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
//            System.out.println(result.getETag());
            return "https://" + bucketName + "." + endpoint + "/" + imageName;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        }
        return null;
    }

    @Override
    public void destroy() {
        if (ossClient != null) {
            ossClient.shutdown();
        }
    }
}
