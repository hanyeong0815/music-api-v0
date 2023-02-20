package com.self.music.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.self.music.domain.enums.FileType;
import com.self.music.utills.pattern.S3BuketFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwsS3Service {
    private final AmazonS3 amazonS3;


    public String uploadFile(FileType fileType, MultipartFile file) {
        String s3Buket = S3BuketFactory.get(fileType).toString();
        System.out.println(s3Buket);

        String fileName = createFileName(file.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try(InputStream inputStream = file.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(s3Buket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
        }

        return amazonS3.getUrl(s3Buket, fileName).toString();
    }


    public String createFileName(String originalFilename) {
        System.out.printf("\n(AwsS3Service.createFileName) originalFilename: %s\n", originalFilename);
        String see = UUID.randomUUID().toString().concat(getFileExtension(originalFilename));
        System.out.printf("See the string: %s\n\n\n", see);
        return see;
    }

    public String getFileExtension(String originalFilename) {
        try {
            return originalFilename.substring(originalFilename.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + originalFilename +")입니다.");
        }
    }

    public void deleteFile(String fileName, String thumbnailName, String bucket){
        DeleteObjectRequest delFile = new DeleteObjectRequest(bucket, fileName);
        DeleteObjectRequest delThumbnail = new DeleteObjectRequest(bucket, thumbnailName);
        amazonS3.deleteObject(delFile);
        amazonS3.deleteObject(delThumbnail);
    }
}
