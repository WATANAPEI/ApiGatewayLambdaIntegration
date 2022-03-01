package dev.wpei.infra;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.UUID;

public class GetBinaryStream {
    public GetBinaryStream(){}

    public static byte[] getFileByteArray(String url) {
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try(Response response = client.newCall(request).execute()) {
            return response.body().bytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void UploadS3(byte[] is, String bucketName) {
        Region region = Region.AP_NORTHEAST_1;
        String objectKey = UUID.randomUUID().toString();
        try(S3Client s3Client = S3Client.builder()
                .region(region)
                .build();) {

            try {
                PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(objectKey)
                        .build();

                //PutObjectRequest putObjectResponse = S3Client.putObject(putObjectRequest, RequestBody.fromBytes(is, contentLength));
            } catch(S3Exception e) {
                throw new IOException(e);
            }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
    }

}
