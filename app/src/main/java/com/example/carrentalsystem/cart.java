package com.example.carrentalsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class cart extends AppCompatActivity {

    TextView carnameret,fuelret,seatret,milret,carrentret,addret,rentret,driverret,total,cname,cmobile,cemail,cdl;
    Button paybut;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        carnameret = findViewById(R.id.carnameret);
        fuelret = findViewById(R.id.fuelret);
        seatret = findViewById(R.id.seatret);
        milret = findViewById(R.id.milret);
        carrentret = findViewById(R.id.carrentedret);
        cname = findViewById(R.id.cname);
        cmobile = findViewById(R.id.cmobile);
        cemail = findViewById(R.id.cemail);
        cdl = findViewById(R.id.cdl);

        rentret = findViewById(R.id.rentret);
        driverret = findViewById(R.id.driverret);
        total = findViewById(R.id.total);
        paybut = findViewById(R.id.paybut);

        firebaseAuth = FirebaseAuth.getInstance();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(firebaseAuth.getUid()).child("CarBooked").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String name = snapshot.child("Carname").getValue(String.class);
                String fuel = snapshot.child("Fuel").getValue(String.class);
                String seat = snapshot.child("Noofseat").getValue(String.class);
                String mileage = snapshot.child("Mileage").getValue(String.class);
                String carday = snapshot.child("Days").getValue(String.class);
                String rent = snapshot.child("Rent").getValue(String.class);
                String driver = snapshot.child("Driver").getValue(String.class);
                rentret.setText(rent);
                carnameret.setText(name);
                fuelret.setText(fuel);
                seatret.setText(seat);
                milret.setText(mileage);
                carrentret.setText(carday);




                String yes = "YES";

                if (driver.equals(yes)){
                    driverret.setText("500");
                }else{
                    driverret.setText("0");
                }

                String drent = driverret.getText().toString();

                int a,b,c,t;
                a = Integer.valueOf(carday);
                b = Integer.valueOf(rent);
                c = Integer.valueOf(drent);

                t = (c+b)*a;

                String totalval = Integer.toString(t);
                total.setText(totalval);

                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("Total price",""+totalval);

                databaseReference.child(firebaseAuth.getUid()).child("CarBooked").updateChildren(hashMap);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String cnames = snapshot.child("Name").getValue(String.class);
                String cmobiles = snapshot.child("Mobile No").getValue(String.class);
                String cemails = snapshot.child("E-Mail").getValue(String.class);
                String cdls = snapshot.child("CDL").getValue(String.class);

                cname.setText(cnames);
                cmobile.setText(cmobiles);
                cemail.setText(cemails);
                cdl.setText(cdls);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        paybut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(cart.this,PaymentActivity.class));
                finish();
            }
        });
    }
}