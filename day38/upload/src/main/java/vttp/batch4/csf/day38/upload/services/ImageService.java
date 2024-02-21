package vttp.batch4.csf.day38.upload.services;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import vttp.batch4.csf.day38.upload.respositories.ImageRepository;

@Service
public class ImageService {

   @Autowired
   private ImageRepository imageRepo;

   @Autowired
   private AmazonS3 s3;

   public String save(InputStream is, String contentType) {
      String picId = UUID.randomUUID().toString().substring(0, 8);
      imageRepo.save(picId, is, contentType);
      return picId;
   }

   public String saveToS3(InputStream is, String contentType, long length) {
      ObjectMetadata metadata = new ObjectMetadata();
      Map<String, String> mydata = new HashMap<>();

      mydata.put("name", "fred");
      metadata.setContentType(contentType);
      metadata.setContentLength(length);
      metadata.setUserMetadata(mydata);

      String id = UUID.randomUUID().toString().substring(0, 8);

      PutObjectRequest putReq = new PutObjectRequest(
         "vttp-batch4" // bucket name
         , "images/%s".formatted(id),  // key
         is, metadata);
      putReq = putReq.withCannedAcl(CannedAccessControlList.PublicRead);

      // Upload to S3 bucket
      PutObjectResult result = s3.putObject(putReq);
      return id;
   }

   public void getFromS3(String key) {

      GetObjectRequest getReq = new GetObjectRequest("vttp-batch4", "images/%s".formatted(key));

      S3Object obj = s3.getObject(getReq);
      S3ObjectInputStream is = obj.getObjectContent();

   }
}