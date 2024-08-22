package com.sh.pettopia.ncpTest;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j // 홍지민 추가 - 로그 찍어보려고 추가했습니다.
public class FileService {

    private final AmazonS3Client amazonS3Client;

    @Value("${application.bucket.name}")
    private String bucketName;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String getUuidFileName(String fileName) {
        String ext = fileName.substring(fileName.indexOf(".") + 1);
        return UUID.randomUUID().toString() + "." + ext;
    }

    // NCP 버킷에 업로드할 폴더명 바꿀 사람은 여기서 바꾸기
    public List<FileDto> uploadFilesSample(List<MultipartFile> multipartFiles){

        return uploadFiles(multipartFiles, "sample-folder");  // ncp 버킷에 filePath 경로의 디렉토리에 올라감(없으면 생성함)
    }

    //NOTICE: filePath의 맨 앞에 /는 안붙여도됨. ex) history/images
    public List<FileDto> uploadFiles(List<MultipartFile> multipartFiles, String filePath) {
        log.info("FileService / uploadFiles");
        List<FileDto> s3files = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {

            String originalFileName = multipartFile.getOriginalFilename();
            String uploadFileName = getUuidFileName(originalFileName);
            String uploadFileUrl = "";

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(multipartFile.getSize());
            objectMetadata.setContentType(multipartFile.getContentType());

            try (InputStream inputStream = multipartFile.getInputStream()) {

                String keyName = filePath + "/" + uploadFileName;

                // S3에 폴더 및 파일 업로드
                amazonS3Client.putObject(
                        new PutObjectRequest(bucketName, keyName, inputStream, objectMetadata)
                                .withCannedAcl(CannedAccessControlList.PublicRead));


                // S3에 업로드한 폴더 및 파일 URL
                uploadFileUrl = "https://kr.object.ncloudstorage.com/" + bucketName + "/" + keyName;

            } catch (IOException e) {
                e.printStackTrace();
            }

            s3files.add(
                    FileDto.builder()
                            .originalFileName(originalFileName)
                            .uploadFileName(uploadFileName)
                            .uploadFilePath(filePath)
                            .uploadFileUrl(uploadFileUrl)
                            .build());

        }
        return s3files;
    }

    // NCP 해당 폴더에 올라온 파일 모두 불러오기

    public List<FileDto> listFiles(String filePath) {
        List<FileDto> files = new ArrayList<>();
        ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName).withPrefix(filePath + "/");
        ListObjectsV2Result result;

        do {
            result = amazonS3Client.listObjectsV2(req);

            for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                String key = objectSummary.getKey();
                String fileName = key.substring(key.lastIndexOf("/") + 1);

                files.add(FileDto.builder()
                        .originalFileName(fileName)
                        .uploadFileName(fileName)
                        .uploadFilePath(filePath) // 현재 지정한 폴더만 가져옴
                        .uploadFileUrl("https://kr.object.ncloudstorage.com/" + bucketName + "/" + key)
                        .build());
            }
            req.setContinuationToken(result.getNextContinuationToken());
        } while (result.isTruncated());

        return files;
    }

    public List<FileDto> sitterUpFile(List<MultipartFile> multipartFiles,String petSitterEmail,String directory){

        String filePath="petsitter/"+petSitterEmail+"/"+directory; // 파일에 저장되는 경로는 petsitter/email/directory 안에 파일이 저장된다
        System.out.println(filePath);
        return uploadFiles(multipartFiles,filePath);  // ncp 버킷에 filePath 경로의 디렉토리에 올라감(없으면 생성함)

    }


    public void deleteImage(String petSitterEmail, String directory, String fileName)
    {
        String path="petsitter/"+petSitterEmail+"/"+directory+"/";
        System.out.println("service path = " + path);

        amazonS3Client.deleteObject(bucketName,path+fileName);

    }

    // NCP 해당 폴더에 올라온 파일 모두 불러오기
    public List<FileDto> sitterDownImg(String petSitterEmail,String directory) {
        String path="petsitter/"+petSitterEmail+"/"+directory+"/";
        System.out.println("service path = " + path);
        List<FileDto> files = new ArrayList<>();
        ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName).withPrefix(path);
        ListObjectsV2Result result;

        do {
            result = amazonS3Client.listObjectsV2(req);

            for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                String key = objectSummary.getKey();
                String fileName = key.substring(key.lastIndexOf("/") + 1);


                files.add(FileDto.builder()
                        .originalFileName(fileName)
                        .uploadFileName(fileName)
                        .uploadFilePath(path) // 현재 지정한 폴더만 가져옴
                        .uploadFileUrl("https://kr.object.ncloudstorage.com/" + bucketName + "/" + key)
                        .build());
            }
            req.setContinuationToken(result.getNextContinuationToken());
        } while (result.isTruncated());

        return files;
    }


    // 홍지민 - 펫 프로필 사진 업로드하는 메소드입니다.
    public List<FileDto> petProfileUpload(List<MultipartFile> files, String petName) {
        String filePath = "pet/" + petName;
        log.debug(filePath);
        return uploadFiles(files, filePath);
    }

    // 홍지민 - 회원 사진 업로드 하는 메소드입니다.
    public List<FileDto> memberProfileUpload(List<MultipartFile> files, String memberName) {
        String filePath = "member/" + memberName;
        log.debug(filePath);
        return uploadFiles(files, filePath);
    }
}