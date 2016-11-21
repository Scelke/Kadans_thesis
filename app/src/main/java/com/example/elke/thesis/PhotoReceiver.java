package com.example.elke.thesis;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class PhotoReceiver extends AppCompatActivity {

    ImageView ReceivedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_receiver);

        ReceivedImage = (ImageView) findViewById(R.id.ReceivedImage);

        Intent intent = getIntent();
        Bitmap bitmap = intent.getParcelableExtra("picture");
        ReceivedImage.setImageBitmap(bitmap);

    }
}
