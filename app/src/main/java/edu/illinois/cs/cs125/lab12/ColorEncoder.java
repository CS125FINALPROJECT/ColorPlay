package edu.illinois.cs.cs125.lab12;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;


public class ColorEncoder extends Activity {
    private final String IMAGE_TYPE = "image/*";
    private final int IMAGE_CODE = 0;
    private Button choose = null;
    private Button camera = null;
    private Button encode = null;
    private ImageView imgShow;
    Toast toast;
    protected static Bitmap bitmap = null;
    static final int REQUEST_IMAGE_CAPTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_encoder);
        init();
    }
    private void init() {
        choose = (Button) findViewById(R.id.openFile);
        imgShow = (ImageView) findViewById(R.id.photoView);
        choose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage();
            }
        });
        camera = (Button) findViewById(R.id.takePhoto);
        camera.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        encode = (Button) findViewById(R.id.encode);
        encode.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    openColorEncoder2();
                } catch (Exception e) {
                    toast.cancel();
                    toast= Toast.makeText(getApplicationContext(), "Please choose a file or take a photo!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }


    private void setImage() {
        Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
        getAlbum.setType(IMAGE_TYPE);
        startActivityForResult(getAlbum, IMAGE_CODE);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bm = null;
        ContentResolver resolver = getContentResolver();
        if (requestCode == IMAGE_CODE) {
            try {
                Uri originalUri = data.getData();
                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                imgShow.setImageBitmap(bm);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgShow.setImageBitmap(imageBitmap);
        }
    }

    public static Bitmap getBitmap() {
        return bitmap;
    }

    public void openColorEncoder2() {
        bitmap = ((BitmapDrawable)imgShow.getDrawable()).getBitmap();
        Intent encoder2 = new Intent(this, edu.illinois.cs.cs125.lab12.ColorEncoder2.class);
        startActivity(encoder2);
    }
}
