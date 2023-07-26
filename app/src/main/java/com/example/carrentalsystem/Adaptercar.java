package com.example.carrentalsystem;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class Adaptercar extends RecyclerView.Adapter<Adaptercar.MyViewHolder> {


    Context context;
    ArrayList<Cars> list;
    FirebaseAuth firebaseAuth;

    public Adaptercar(Context context, ArrayList<Cars> list) {
        this.context = context;
        this.list = list;
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardetails,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Cars cars = list.get(position);
        holder.rent.setText(cars.getRent());
        holder.carname.setText(cars.getCarname());
        holder.noofseat.setText(cars.getNoofseat());
        holder.mileage.setText(cars.getMileage());
        holder.fueltype.setText(cars.getFueltype());

        String rent = cars.getRent();
        String carname = cars.getCarname();
        String noofseat = cars.getNoofseat();
        String mileage = cars.getMileage();
        String fueltype = cars.getFueltype();



        holder.rentcarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nodays = ((Carlist)context).insert(holder.noday);
                String no = "NO";
                String yes = "YES";

                if (TextUtils.isEmpty(nodays)){
                    Toast.makeText(context, "Please Fill the no of days field......", Toast.LENGTH_SHORT).show();
                }else{

                    if (holder.checkBox.isChecked()){
                        HashMap<String,Object> hashMap = new HashMap<>();
                        hashMap.put("Carname",""+carname);
                        hashMap.put("Rent",""+rent);
                        hashMap.put("Noofseat",""+noofseat);
                        hashMap.put("Mileage",""+mileage);
                        hashMap.put("Fuel",""+fueltype);
                        hashMap.put("Days",""+nodays);
                        hashMap.put("Driver",""+yes);

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                        databaseReference.child(firebaseAuth.getUid()).child("CarBooked").setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(context, "Booking Initiated.....", Toast.LENGTH_SHORT).show();
                                ((Carlist)context).next();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Booking Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
                        HashMap<String,Object> hashMap = new HashMap<>();
                        hashMap.put("Carname",""+carname);
                        hashMap.put("Rent",""+rent);
                        hashMap.put("Noofseat",""+noofseat);
                        hashMap.put("Mileage",""+mileage);
                        hashMap.put("Fuel",""+fueltype);
                        hashMap.put("Days",""+nodays);
                        hashMap.put("Driver",""+no);

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                        databaseReference.child(firebaseAuth.getUid()).child("CarBooked").setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(context, "Booking Initiated.....", Toast.LENGTH_SHORT).show();
                                ((Carlist)context).next();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Booking Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }


                }












            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView carid, carname, fueltype, mileage, noofseat,rent;
        Button rentcarbtn;
        EditText noday;
        CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            carname = itemView.findViewById(R.id.carname);
            fueltype = itemView.findViewById(R.id.fuel);
            mileage = itemView.findViewById(R.id.mileage);
            noofseat = itemView.findViewById(R.id.seats);
            rent = itemView.findViewById(R.id.rentcar);
            rentcarbtn = itemView.findViewById(R.id.rentcarbtn);
            noday = itemView.findViewById(R.id.noday);
            checkBox = itemView.findViewById(R.id.checkBox);

        }
    }
}
