package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Activity_Patient_Login extends AppCompatActivity {

    private Button btn_Login;
    private Button btn_Register;
    private Button btn_Forgot;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        btn_Login = findViewById(R.id.B_A_PatientLogin_Login);
        btn_Register = findViewById(R.id.B_A_PatientLogin_Register);
        btn_Forgot = findViewById(R.id.B_A_PatientLogin_Forget);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PatientLogin();
            }
        });
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PatientRegister();
            }
        });
        btn_Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PatientForgot();
            }
        });
    }

    private void PatientLogin() {
        Intent intent = new Intent(this, Activity_Patient.class);
        //get fields
        EditText edtEmail = findViewById(R.id.edtLoginPemail);
        EditText edtPass = findViewById(R.id.edtLoginPpassword);

        String email = edtEmail.getText().toString();
        String pass = edtPass.getText().toString();
        email = "1833200@students.wits.ac.za";
        pass = "1234";

        final Map<String, Object>[] data = new Map[]{new HashMap<>()};
        //check if email exists
        final DocumentReference userRef = db.collection("Patient").document(
                email);
        String finalPass = pass;
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    System.out.println(document.exists());
                    if (document.exists()) {
                        //The user exists...
                        data[0] = document.getData();
                        System.out.println("data size: " + data[0].size());
                        Toast.makeText(Activity_Patient_Login.this, "User exists",
                                Toast.LENGTH_SHORT).show();
                        //check if password matches user
                        System.out.println(data[0].get("Password"));

                        if(finalPass.equals(data[0].get("Password"))){
                            Toast.makeText(Activity_Patient_Login.this, "Password exists",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Activity_Patient_Login.this, "Password not exist",
                                    Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        //The user doesn't exist...
                        Toast.makeText(Activity_Patient_Login.this, "User does not exist",
                                Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

//        Intent intent = new Intent(this, Activity_Patient.class);
//        startActivity(intent);
    }

    private void PatientRegister() {
        Intent intent = new Intent(this, Activity_Patient_Register.class);
        startActivity(intent);
    }

    private void PatientForgot() {
    }


}