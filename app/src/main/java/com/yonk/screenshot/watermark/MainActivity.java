package com.yonk.screenshot.watermark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nmd.screenshot.Screenshot;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private TextView txt;
    private Screenshot screenshot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        (findViewById(R.id.takePic)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Process process;
                try {
                    process = Runtime.getRuntime().exec("screencap -p " + "/sdcard/01.png");
                    try {
                        process.waitFor();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        initialize();
    }
    private void initialize() {
        txt = (TextView)findViewById(R.id.editText);
        screenshot = new Screenshot(this.context);
        screenshot.notificationBigStyle();
    }
    public void take() {
        screenshot.setFileName(System.currentTimeMillis()+".png");
        screenshot.notificationTitle("My screenshot title");
        screenshot.setCallback(new Screenshot.OnResultListener() {
            @Override
            public void result(boolean success, String filePath, Bitmap bitmap) {
                txt.setText(filePath);
                txt.setTextColor(success ? Color.GREEN : Color.RED);
                // if success is true then set text color to green, else red
            }
        });
        //After you have done your settings let's take the screenshot
        screenshot.takeScreenshot();
    }
}
