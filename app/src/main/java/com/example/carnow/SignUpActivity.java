package com.example.carnow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity  extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://carnow-9c803-default-rtdb.asia-southeast1.firebasedatabase.app/");

    User user = new User();
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText et_email_signup = findViewById(R.id.et_email_signup);
        EditText et_password_signup = findViewById(R.id.et_password_signup);
        EditText et_name_signup = findViewById(R.id.et_name_signup);
        EditText et_date_birth = findViewById(R.id.et_date_birth);
        EditText et_lesen = findViewById(R.id.et_lesen);
        EditText et_phone = findViewById(R.id.et_phone);

        firebaseAuth = FirebaseAuth.getInstance();

        Button btnregister = findViewById(R.id.btn_register);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email_signup.getText().toString();
                String password = et_password_signup.getText().toString();
                String name = et_name_signup.getText().toString();
                String date_birth = et_date_birth.getText().toString();
                String lesen = et_lesen.getText().toString();
                String phone = et_phone.getText().toString();

                email = email.trim();
                password = password.trim();

                if(password.isEmpty() || email.isEmpty() || name.isEmpty() || date_birth.isEmpty() || lesen.isEmpty() || phone.isEmpty()){

                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    builder.setMessage("Please enter all details").setTitle("Warning").setPositiveButton("OK",null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    String lastEmail = email;
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                DatabaseReference myRef = database.getReference("User");
                                user = new User(name, date_birth, lesen, phone, lastEmail);
                                myRef.push().setValue(user);

                                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                Toast.makeText(SignUpActivity.this, " Account has been registered", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(SignUpActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

    }
}