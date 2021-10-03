package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Activity_Patient_Register extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);
    }

    public void confirmDetails(View v){
        TextInputEditText edtNumber = findViewById(R.id.edtPnumber);
        EditText edtMail = findViewById(R.id.edtPmail);
        EditText edtPassword = findViewById(R.id.edtPpassword);
        EditText edtFirstName = findViewById(R.id.edtPname);
        EditText edtLastName = findViewById(R.id.edtPsurname);
        EditText edtID = findViewById(R.id.edtPid);

        String phoneNumber = edtNumber.getText().toString();
        String email = edtMail.getText().toString();
        String password = edtPassword.getText().toString();
        String firstName = edtFirstName.getText().toString();
        String lastName = edtLastName.getText().toString();
        String id = edtID.getText().toString();

        //create a doctor with given credentials
        Map<String, Object> patient = new HashMap<>();
        //patient.put("Email", email);
        patient.put("First Name", firstName);
        patient.put("Last Name", lastName);
        patient.put("ID", id);
        patient.put("Password", password);
        patient.put("Phone Number", phoneNumber);

        //create new document with Email being id
        db.collection("Patient").document(email)
                .set(patient)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

    }
}