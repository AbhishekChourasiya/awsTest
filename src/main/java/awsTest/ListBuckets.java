package awsTest;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

import java.nio.file.Paths;
import java.util.List;

/**
 * List your Amazon S3 buckets.
 *
 * This code expects that you have AWS credentials set up per:
 * http://docs.aws.amazon.com/java-sdk/latest/developer-guide/setup-credentials.html
 */
public class ListBuckets

{
	
	 public static void upload()
	    {
	        final String USAGE = "\n" +
	            "To run this example, supply the name of an S3 bucket and a file to\n" +
	            "upload to it.\n" +
	            "\n" +
	            "Ex: PutObject <bucketname> <filename>\n";

	       
	        String bucket_name = "learning-mumbai-india";
	        String file_path = "C:\\Users\\abhishekkumar\\Documents\\GeneratingLog\\gclog.log";
	        String key_name = Paths.get(file_path).getFileName().toString();

	        System.out.format("Uploading %s to S3 bucket %s...\n", file_path, bucket_name);

	    	AmazonS3 s3 = AmazonS3ClientBuilder.standard()
	                .withRegion(Regions.US_EAST_1)
	                .withForceGlobalBucketAccessEnabled(true)
	                .build();
	        try {
	            s3.putObject(bucket_name, key_name, file_path);
	        } catch (AmazonServiceException e) {
	            System.err.println(e.getErrorMessage());
	            System.exit(1);
	        }
	        System.out.println("Done!");
	    }
	
    public static void main(String[] args)
    {
 
    	AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withForceGlobalBucketAccessEnabled(true)
                .build();
       
        List<Bucket> buckets = s3.listBuckets();
        System.out.println("Your Amazon S3 buckets are:");
        for (Bucket b : buckets) {
            System.out.println("* " + b.getName());
        }
        upload();
    }
}