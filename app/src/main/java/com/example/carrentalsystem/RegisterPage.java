package com.example.carrentalsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterPage extends AppCompatActivity {

    EditText et_name,et_mail,et_password,et_phone;
    Button registerbtn;
    TextView tv_already_acc;

    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_register_page);

        et_name = findViewById(R.id.et_regname);
        et_mail = findViewById(R.id.et_regmail);
        et_password = findViewById(R.id.et_regpassword);
        et_phone = findViewById(R.id.et_regphone);
        registerbtn = findViewById(R.id.registerbtn);
        tv_already_acc = findViewById(R.id.tv_already_acc);


        tv_already_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterPage.this,LoginPage.class));
            }
        });

        mAuth = FirebaseAuth.getInstance();
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
                //startActivity(new Intent(RegisterPage.this,LoginPage.class));
            }
        });

    }
    String timestamp = ""+System.currentTimeMillis();
    public void createUser(){
        String name = et_name.getText().toString().trim();
        String mail = et_mail.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String phone = et_phone.getText().toString().trim();



        if (TextUtils.isEmpty(mail)){
            et_mail.setError("Mail cannot be empty");
            et_mail.requestFocus();
        }else if (TextUtils.isEmpty(password)) {
            et_password.setError("Password cannot be empty");
            et_password.requestFocus();
        }else {
            mAuth.createUserWithEmailAndPassword(mail,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    savedetails();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterPage.this, "Failed1", Toast.LENGTH_SHORT).show();
                }
            });
        }



    }

    private void savedetails() {

        String name = et_name.getText().toString().trim();
        String mail = et_mail.getText().toString().trim();
        String phone = et_phone.getText().toString().trim();

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("Name",""+name);
        hashMap.put("E-Mail",""+mail);
        hashMap.put("Mobile No",""+phone);
        hashMap.put("User ID",""+timestamp);



        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(mAuth.getUid()).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(RegisterPage.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterPage.this,LoginPage.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterPage.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

}

