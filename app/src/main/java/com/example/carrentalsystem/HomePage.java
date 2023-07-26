package com.example.carrentalsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class HomePage extends AppCompatActivity {

    ImageView alto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_home_page);

        alto = findViewById(R.id.alto);

        alto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BottomSheetFragment altobottomsheet = new BottomSheetFragment();
                altobottomsheet.show(getSupportFragmentManager(),altobottomsheet.getTag());


            }
        });
    }
}