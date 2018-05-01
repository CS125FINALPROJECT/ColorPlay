package edu.illinois.cs.cs125.lab12;


import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;


public class ColorEncoder extends AppCompatActivity {
    private final String IMAGE_TYPE = "image/*";
    private final int IMAGE_CODE = 0;
    private Button choose = null;
    private Button encode = null;
    private ImageView imgShow;
    protected static Bitmap bitmap = null;

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
        encode = (Button) findViewById(R.id.encode);
        encode.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorEncoder2();
            }
        });

    }

    private void setImage() {
        Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
        getAlbum.setType(IMAGE_TYPE);
        startActivityForResult(getAlbum, IMAGE_CODE);
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
