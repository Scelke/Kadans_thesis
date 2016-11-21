package com.example.elke.thesis;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Camera extends Activity {

    private int REQUEST_CAMERA = 0;
    private ImageView ivImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ivImage = (ImageView) findViewById(R.id.ivImage);
        cameraIntent();
    }


    private void cameraIntent()
    {
        //implicit intent:  allows a component from another app to handle it
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //This method will give a result back. In this case, it will give an image capture by camera.
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    //We need to handle the result we have received by calling startActivityForResult() method
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CAMERA)
            onCaptureImageResult(data);
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
/*
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        Intent intent = new Intent(this, PhotoReceiver.class);
        intent.putExtra("picture", thumbnail);
        startActivity(intent);
    }
}