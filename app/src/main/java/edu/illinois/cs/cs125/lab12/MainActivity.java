package edu.illinois.cs.cs125.lab12;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    // Button navigator setUp. OnClick navigate to activity2.
    private Button button;
    public EditText redInput, greenInput, blueInput;
    public String red,green,blue;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        redInput = (EditText) findViewById(R.id.Red);

        greenInput = (EditText) findViewById(R.id.Green);

        blueInput = (EditText) findViewById(R.id.Blue) ;
        toast=Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);;
        button = (Button) findViewById(R.id.Generate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    openActivity2();
                } catch (Exception e) {
                    toast.cancel();
                    toast=Toast.makeText(getApplicationContext(), "Please input valid rgb values!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

    }


    public void openActivity2() {
        red = redInput.getText().toString();
        green = greenInput.getText().toString();
        blue = blueInput.getText().toString();
        int r = Integer.valueOf(red);
        int g = Integer.valueOf(green);
        int b = Integer.valueOf(blue);
        if (r >= 0 && r <= 255 && g >= 0 && g <= 255 && b >= 0 && b <= 255) {
            Intent navigateGenerate = new Intent(this, Main2Activity.class);
            navigateGenerate.putExtra("redTr", red);
            navigateGenerate.putExtra("greenTr", green);
            navigateGenerate.putExtra("blueTr", blue);
            startActivity(navigateGenerate);
        } else {
            toast.cancel();
            toast=Toast.makeText(getApplicationContext(), "Please input valid rgb values!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

}

