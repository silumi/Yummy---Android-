package com.example.yummy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button BtnSignUp,BtnSignIn;
    TextView textslogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BtnSignIn=(Button)findViewById(R.id.BtnSignIn);
        BtnSignUp=(Button)findViewById(R.id.BtnSignUp);

        textslogan=(TextView)findViewById(R.id.txtslogan);

        Typeface face= Typeface.createFromAsset(getAssets(),"Fonts/Nabila.ttf");

        BtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        BtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup=new Intent(MainActivity.this, Signup.class);
                startActivity(signup);
            }
        });
        BtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login=new Intent(MainActivity.this, Login.class);
                startActivity(login);
            }
        });


    }
}
