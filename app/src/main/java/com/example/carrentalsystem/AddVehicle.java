package com.example.carrentalsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddVehicle extends AppCompatActivity {

    EditText seat,mil,fuel,rent,name;
    Button addcar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);



        seat = findViewById(R.id.seat);
        mil = findViewById(R.id.mil);
        name = findViewById(R.id.name);
        fuel = findViewById(R.id.fuel);
        rent = findViewById(R.id.rent);

        addcar = findViewById(R.id.addveh);

        addcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Inputdata();
            }
        });




    }

    private void Inputdata() {

        String nametv,seattv,miltv,fueltv,renttv;
        final String time = ""+System.currentTimeMillis();

        nametv = name.getText().toString().trim();
        seattv = seat.getText().toString().trim();
        miltv = mil.getText().toString().trim();
        fueltv = fuel.getText().toString().trim();
        renttv = rent.getText().toString().trim();

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("carname",""+nametv);
        hashMap.put("noofseat",""+seattv);
        hashMap.put("mileage",""+miltv);
        hashMap.put("fueltype",""+fueltv);
        hashMap.put("rent",""+renttv);
        hashMap.put("carid",""+time);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("com.example.carrentalsystem.Cars");

            databaseReference.child(""+time).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(AddVehicle.this, "Car added......", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddVehicle.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });


    }
}