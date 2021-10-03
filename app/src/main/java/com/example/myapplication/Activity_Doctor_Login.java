package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Activity_Doctor_Login extends AppCompatActivity {

    private Button btn_Login;
    private Button btn_Register;
    private Button btn_Forgot;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        btn_Login = findViewById(R.id.B_A_DoctorLogin_Login);
        btn_Register = findViewById(R.id.B_A_DoctorLogin_Register);
        btn_Forgot = findViewById(R.id.B_A_DoctorLogin_Forget);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoctorLogin();
            }
        });
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoctorRegister();
            }
        });
        btn_Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoctorForgot();
            }
        });
    }

    private void DoctorLogin() {
        Intent intent = new Intent(this, Activity_Doctor.class);
        //get fields
        EditText edtEmail = findViewById(R.id.edtLoginEmail);
        EditText edtPass = findViewById(R.id.edtLoginPass);

        String email = edtEmail.getText().toString();
        String pass = edtPass.getText().toString();
//        email = "1833200@students.wits.ac.za";
//        pass = "abc";

        final Map<String, Object>[] data = new Map[]{new HashMap<>()};
        //check if email exists
        final DocumentReference userRef = db.collection("Doctor").document(
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
                        Toast.makeText(Activity_Doctor_Login.this, "User exists",
                                Toast.LENGTH_SHORT).show();
                        //check if password matches user
                        System.out.println(data[0].get("Password"));

                        if(finalPass.equals(data[0].get("Password"))){
                            Toast.makeText(Activity_Doctor_Login.this, "Password exists",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Activity_Doctor_Login.this, "Password not exist",
                                    Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        //The user doesn't exist...
                        Toast.makeText(Activity_Doctor_Login.this, "User does not exist",
                                Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

//        Intent intent = new Intent(this, Activity_Doctor.class);
//        startActivity(intent);
    }

    private void DoctorRegister() {
        Intent intent = new Intent(this, Activity_Doctor_Register.class);
        startActivity(intent);
    }

    private void DoctorForgot() {

    }
}