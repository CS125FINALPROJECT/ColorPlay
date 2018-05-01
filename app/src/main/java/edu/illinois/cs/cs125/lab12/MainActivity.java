package edu.illinois.cs.cs125.lab12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    // Button navigator setUp. OnClick navigate to activity2.
    private Button button;
    public EditText redInput, greenInput, blueInput;
    public String red,green,blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        redInput = (EditText) findViewById(R.id.Red);

        greenInput = (EditText) findViewById(R.id.Green);

        blueInput = (EditText) findViewById(R.id.Blue) ;

        button = (Button) findViewById(R.id.Generate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }
    public void openActivity2() {
        red = redInput.getText().toString();
        green = greenInput.getText().toString();
        blue = blueInput.getText().toString();
        Intent navigateGenerate  = new Intent(this, Main2Activity.class);
        navigateGenerate.putExtra("redTr",red);
        navigateGenerate.putExtra("greenTr",green);
        navigateGenerate.putExtra("blueTr",blue);
        startActivity(navigateGenerate);
    }

}

