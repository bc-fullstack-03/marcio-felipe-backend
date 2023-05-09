package com.sysMapBootCamp.projectSysMapBootCamp.service.fileUpload;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
public class AwsService {

    @Autowired
    private AmazonS3 amazonS3;

    public String upload(MultipartFile multipartFile , String fileName) throws Exception {
        var fileUri="";
        try{

            var  fileConvert = convertMultipartToFile(multipartFile);

            amazonS3.putObject(new PutObjectRequest("sys-bucket", fileName,fileConvert).withCannedAcl(CannedAccessControlList.PublicRead));

            fileUri = "http://s3.localstack.localstack.cloud:4566"+"/"+"sys-bucket"+"/"+fileName;
            fileConvert.delete();

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return fileUri;
    }


    private File convertMultipartToFile(MultipartFile file) throws IOException {
        var convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));

        var fos = new FileOutputStream(convFile);

        fos.write(file.getBytes());
        fos.close();

        return convFile;
    }
}
