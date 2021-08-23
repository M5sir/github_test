package cn.baizhi.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class File {
    public static void uploadAliyun(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI5tD8ZsindcmbUZp7AuAk";
        String accessKeySecret = "fQQlI78wQ4CaeLg3BLmmWfgkwwvczo";
        //String bucketName = "tongt123456";  //存储空间名
        //String objectName = "photos/aaa.jpg";  //文件名  可以指定文件目录
        //String localFile="C:\\Users\\NANAN\\Pictures\\AliMail\\aaa.jpg";  //本地视频路径

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        byte[] content = new byte[0];
        try {
            content = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建PutObjectRequest对象。 参数：Bucket名字，指定文件名，文件本地路径
        PutObjectRequest putObjectRequest = new PutObjectRequest("tongt123456", file.getOriginalFilename(),new ByteArrayInputStream(content));

        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        // putObjectRequest.setMetadata(metadata);

        // 上传文件。
        ossClient.putObject(putObjectRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    public static void saveVideo(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI5tD8ZsindcmbUZp7AuAk";
        String accessKeySecret = "fQQlI78wQ4CaeLg3BLmmWfgkwwvczo";
        //String bucketName = "tongt123456";  //存储空间名
        //String objectName = "photos/aaa.jpg";  //文件名  可以指定文件目录
        //String localFile="C:\\Users\\NANAN\\Pictures\\AliMail\\aaa.jpg";  //本地视频路径

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        byte[] content = new byte[0];
        try {
            content = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建PutObjectRequest对象。 参数：Bucket名字，指定文件名，文件本地路径
        PutObjectRequest putObjectRequest = new PutObjectRequest("tongt123456", "video/"+file.getOriginalFilename(),new ByteArrayInputStream(content));

        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        // putObjectRequest.setMetadata(metadata);

        // 上传文件。
        ossClient.putObject(putObjectRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}

