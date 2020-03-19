package com.longrou.jcamera.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.longrou.jcamera.JCamera;

public class MainActivity2 extends AppCompatActivity {
    int PHOTO_OR_VIDEO_FOR_CAMERA = 0x3701;

    TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button btnCamera = (Button) findViewById(R.id.btn_camera);
        tvShow = (TextView) findViewById(R.id.tv_show);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JCamera.get()
                        //.openPreCamera()// 是否打开为前置摄像头
                        .allowPhoto(true)// 是否允许拍照 默认允许
                        .allowRecord(true)// 是否允许录像 默认允许
                        .setMaxRecordTime(3)//最长录像时间 秒
                        .start(MainActivity2.this,PHOTO_OR_VIDEO_FOR_CAMERA);//PHOTO_OR_VIDEO_FOR_CAMERA 请求码 回调时可用
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK &&  requestCode == PHOTO_OR_VIDEO_FOR_CAMERA){
            if (data != null) {
                if (JCamera.resultIsImg(data)){
                    tvShow.setText( "Image Path：\n"+JCamera.getResultPath(data));
                }else{
                    tvShow.setText( "Video Path：\n"+JCamera.getResultPath(data));
                }
            }else{
                tvShow.setText( "data is null");

            }
        }
    }
}