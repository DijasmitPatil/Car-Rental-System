package com.example.carrentalsystem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;



public class BottomSheetFragment extends BottomSheetDialogFragment {

    Button rentbtn;





    public BottomSheetFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
       rentbtn = v.findViewById(R.id.rentbtn);
       rentbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(getContext(), "Button pressed", Toast.LENGTH_SHORT).show();
           }
       });
       return v;
    }


}