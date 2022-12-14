package com.example.main.s3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
    
    @Configuration
    public class MovieS3Config {
    
    //@Value("${cloud.aws.credentials.accessKey}")
    private String awsId = "xx";

    //@Value("${cloud.aws.credentials.secretKey}")
    private String awsKey = "xx";

    //@Value("${cloud.aws.region.static}")
    private String region = "xx";

    
        @Bean
        public AmazonS3 s3client() {
    
            BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsId, awsKey);
            AmazonS3 amazonS3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(Regions.fromName(region))
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                    .build();
    
            return amazonS3Client;
        }
    }
