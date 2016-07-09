import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

public class PostRequestMaker {
	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		System.out.println("\nWelcome to POST Request Maker!");

		System.out.print("Url: ");
		String url = in.nextLine();

		System.out.print("Parameters: ");
		String parameters = in.nextLine();

		System.out.println("Please, wait...");
		sendPost(url, parameters);
	}


	private static void sendPost(String url, String parameters) throws Exception {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(parameters);
		wr.flush();
		wr.close();

		System.out.println("\n\nURL : " + url);
		System.out.println("Request Method: " + con.getRequestMethod());
		System.out.println("Response Code: " + con.getResponseCode());
		System.out.println("Response Message: " + con.getResponseMessage());

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();

		System.out.println("Response Length: " + response.toString().length());
		File file = new File ("rsp.txt");
		PrintWriter out = new PrintWriter("rsp.txt");
		out.println(response.toString());
		System.out.println("Response copied to rsp.txt.");
	}
}
