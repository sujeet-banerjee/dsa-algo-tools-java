package algo.http.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse.BodySubscriber;
import java.net.http.HttpResponse.ResponseInfo;

public class MyHttpClient {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
		      .uri(URI.create("http://openjdk.org/"))
		      .build();
		
		HttpResponse<String> resp = client.send(
				request, BodyHandlers.ofString());
		
		System.out.println("Resp Code: "+ resp.statusCode());
		System.out.println("Resp Body: "+ resp.body());
		
//		client.sendAsync(request, BodyHandlers.ofString())
//		      .thenApply(HttpResponse::body)
//		      .thenAccept(System.out::println)
//		      .join();
	}
	
}
