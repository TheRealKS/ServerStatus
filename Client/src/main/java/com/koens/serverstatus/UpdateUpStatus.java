package com.koens.serverstatus;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateUpStatus implements Runnable {

    private int up;
    private ServerStatus plugin;

    public UpdateUpStatus(int i, ServerStatus p) {
        this.up = i;
        this.plugin = p;
    }

    @Override
    public void run() {
        plugin.getLogger().info("Writing server status data to the remote server!");
        String params = "up=" + up + "&players=0&worlds=" + plugin.getWorldsString();
        byte[] postdata = params.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        int postdatalenght = postdata.length;
        try {
            URL uurl = new URL(plugin.getUrl());
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
            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream()), 1);
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >=0;) {
                sb.append((char) c);
            }
            if (!sb.toString().equals("Success!")) {
                plugin.getLogger().severe("COULDN'T WRITE SERVER STATUS DATA TO REMOTE SERVER!");
                plugin.getLogger().severe("REPONSE FROM THE SERVER WAS: " + sb.toString());
            } else {
                plugin.getLogger().info("Successfully wrote server status data to the remote server!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
