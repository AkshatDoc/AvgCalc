package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import com.example.firstapp.MainActivity;


import java.io.FileInputStream;

public class SplashScreen extends AppCompatActivity {
    private static final String fileName = "fileSave.txt";
    public static String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 3000);


            if(readFile(fileName) != null){
                info = readFile(fileName);
            }


    }
    public String readFile(String file){
        String text = "";
        try{
            Toast.makeText(SplashScreen.this,"Loading",Toast.LENGTH_SHORT).show();
            FileInputStream fis = openFileInput(file);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text = new String(buffer);
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(SplashScreen.this, "Error Loading File!", Toast.LENGTH_SHORT).show();
        }

        return text;
    }
}
