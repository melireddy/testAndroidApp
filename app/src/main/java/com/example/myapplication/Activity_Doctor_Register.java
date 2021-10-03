package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Activity_Doctor_Register extends AppCompatActivity {
    private AutoCompleteTextView tv_Fields;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);

        tv_Fields = findViewById(R.id.TV_A_DoctorRegister_Fields);
        String []doctor_Fields
                = {
                "Practitioner",
                "OB/GYN",
                "Psychiatrist",
                "Dentist",
                "General Surgeon",
                "Dermatologist"};
        ArrayAdapter arrayAdapterDoctorFields
                = new ArrayAdapter(this,R.layout.doctor_feilds_dorpdown_item,doctor_Fields);
        tv_Fields.setAdapter(arrayAdapterDoctorFields);

    }

    public void confirmDetails(View v){
        System.out.println("Hello There");
    //REMEMBER TO CHECK FOR DUPLICATE EMAILS*******
        //get Data from fields
        TextInputEditText edtNumber = findViewById(R.id.edtNumber);
        EditText edtMail = findViewById(R.id.edtEmail);
        EditText edtPassword = findViewById(R.id.edtPass);
        EditText edtFirstName = findViewById(R.id.edtName);
        EditText edtLastName = findViewById(R.id.edtSurname);
        EditText edtID = findViewById(R.id.edtID);
        EditText edtQualifications = findViewById(R.id.edtQual);
        EditText edtExperience = findViewById(R.id.edtExp);
        AutoCompleteTextView actField = findViewById(R.id.TV_A_DoctorRegister_Fields);

        String phoneNumber = edtNumber.getText().toString();
        String email = edtMail.getText().toString();
        String password = edtPassword.getText().toString();
        String firstName = edtFirstName.getText().toString();
        String lastName = edtLastName.getText().toString();
        String id = edtID.getText().toString();
        String qualifications = edtQualifications.getText().toString();
        Integer experience = Integer.parseInt(edtExperience.getText().toString());
        String field = actField.getText().toString();

        //create a doctor with given credentials
        Map<String, Object> doctor = new HashMap<>();
        //doctor.put("Email", email);
        doctor.put("Fields", field);
        doctor.put("First Name", firstName);
        doctor.put("Last Name", lastName);
        doctor.put("ID", id);
        doctor.put("Password", password);
        doctor.put("Phone Number", phoneNumber);
        doctor.put("Qualifications", qualifications);
        doctor.put("Years of Experience", experience);

        //create new document with Email being id
        db.collection("Doctor").document(email)
                .set(doctor)
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


//        // Create a new user with a first and last name
//        Map<String, Object> user = new HashMap<>();
//        user.put("first", "Ada");
//        user.put("last", "Lovelace");
//        user.put("born", 1815);
//
//// Add a new document with a generated ID
//        db.collection("users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });
//
//
//        // Create a new user with a first, middle, and last name
//        Map<String, Object> user2 = new HashMap<>();
//        user2.put("first", "Alan");
//        user2.put("middle", "Mathison");
//        user2.put("last", "Turing");
//        user2.put("born", 1912);

    }


}