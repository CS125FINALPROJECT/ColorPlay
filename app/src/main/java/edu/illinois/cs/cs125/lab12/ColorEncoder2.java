package edu.illinois.cs.cs125.lab12;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.Palette.Builder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ColorEncoder2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_encoder2);
        init();

    }

    private void init() {
        ImageView imgShow = (ImageView) findViewById(R.id.photoView);
        Bitmap bitmap = edu.illinois.cs.cs125.lab12.ColorEncoder.getBitmap();
        imgShow.setImageBitmap(bitmap);
        Palette palette = createPaletteSync(bitmap);
        Builder builder = new Builder(bitmap);
        builder.maximumColorCount(32);
        ImageView color1 = findViewById(R.id.color1);
        ImageView color2 = findViewById(R.id.color2);
        ImageView color3 = findViewById(R.id.color3);
        ImageView color4 = findViewById(R.id.color4);
        ImageView color5 = findViewById(R.id.color5);

        int lightVibrant = palette.getLightVibrantColor(0);
        int vibrant = palette.getVibrantColor(0);
        int darkVibrant = palette.getDarkVibrantColor(0);
        int lightMuted = palette.getLightMutedColor(0);
        int darkMuted = palette.getDarkMutedColor(0);

        color1.setBackgroundColor(lightVibrant);
        color2.setBackgroundColor(vibrant);
        color3.setBackgroundColor(darkVibrant);
        color4.setBackgroundColor(lightMuted);
        color5.setBackgroundColor(darkMuted);

        Button back = (Button) findViewById(R.id.encodeAgain);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    public Palette createPaletteSync(Bitmap bitmap) {
        Palette p = Palette.from(bitmap).generate();
        return p;
    }

    public int[] colorArray(ImageView image, int color) {
        int[] array = new int[image.getMaxWidth() * image.getMaxHeight()];
        for (int pixel : array) {
            pixel = color;
        }
        return array;
    }

    public void goBack() {
        Intent back = new Intent(this, edu.illinois.cs.cs125.lab12.ColorEncoder.class);
        startActivity(back);
    }

}
