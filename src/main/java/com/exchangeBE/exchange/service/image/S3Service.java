package com.exchangeBE.exchange.service.image;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3 amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(byte[] imageBytes, String originalFilename) throws IOException {
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1); // 확장자 명

        String s3FileName = UUID.randomUUID().toString().substring(0, 10) + "_" + originalFilename; // 변경된 파일 명

        ObjectMetadata metadata = new ObjectMetadata(); // metadata 생성
        metadata.setContentType("image/" + extension);
        metadata.setContentLength(imageBytes.length);

        //S3에 요청할 때 사용할 byteInputStream 생성
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);

        try{
            //S3로 putObject 할 때 사용할 요청 객체
            //생성자 : bucket 이름, 파일 명, byteInputStream, metadata
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(bucket, s3FileName, byteArrayInputStream, metadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead);

            //실제로 S3에 이미지 데이터를 넣는 부분이다.
            amazonS3Client.putObject(putObjectRequest); // put image to S3
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            byteArrayInputStream.close();
        }

        return amazonS3Client.getUrl(bucket, s3FileName).toString();
    }

    public void deleteImage(String imageUrl) {
        try {
            imageUrl = imageUrl.split("/")[3];
            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, imageUrl);
            amazonS3Client.deleteObject(deleteObjectRequest);
            System.out.println("Deleted image: " + imageUrl);
        } catch (Exception e) {
            System.err.println("Error deleting image: " + imageUrl);
            e.printStackTrace();
        }
    }
}