package vttp.batch4.csf.day38.articles.repositories;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Repository
public class ImageRepository {

	@Value("${s3.bucket.name}")
	private String bucketName;

	@Autowired
	private AmazonS3 s3;

	public String save(String imageId, String mime, InputStream is, long size) {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(mime);
		metadata.setContentLength(size);

		String key = "images/%s".formatted(imageId);

		PutObjectRequest putReq = new PutObjectRequest(bucketName, key, is, metadata);
		putReq = putReq.withCannedAcl(CannedAccessControlList.PublicRead);

		s3.putObject(putReq);

		return s3.getUrl(bucketName, key).toExternalForm();
	}

}
