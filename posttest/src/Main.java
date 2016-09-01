import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        String params = "up=1&players=12&worlds=k:1||d:22";
        byte[] postdata = params.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        int postdatalenght = postdata.length;
        try {
            URL uurl = new URL("http://localhost/ServerStatus/Server/set_db.php");
            HttpURLConnection connection = (HttpURLConnection) uurl.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Lenght", Integer.toString(postdatalenght));
            connection.setUseCaches(false);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.write(postdata);
            System.out.println("Wrote data!");
            System.out.println("Response:");
            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream()), 1);
            for (int c; (c = in.read()) >=0;) {
                System.out.println((char) c);
            }
        } catch (IOException e) {

        }
    }
}
