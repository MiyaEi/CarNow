package com.example.carnow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    private TextView tvname;
    private TextView tvemail;
    private TextView tvphone;
    private TextView tvlesen;
    private TextView tvDOB;

    Button btnlogout;
    FirebaseDatabase database =FirebaseDatabase.getInstance("https://carnow-9c803-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference myRef = database.getReference("User");

    private FirebaseAuth mFirebaseAuth;

    private FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        tvname = findViewById(R.id.name_user);
        tvemail = findViewById(R.id.email_user);
        tvphone = findViewById(R.id.phone_user);
        tvDOB = findViewById(R.id.date_birth_user);
        tvlesen=findViewById(R.id.license_user);
        btnlogout = findViewById(R.id.btn_logout);


        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        tvemail.setText(mFirebaseUser.getEmail());

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                finish();
                Toast.makeText(Profile.this, "Signed Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Profile.this, LoginActivity.class));
            }
        });
        Query checkUser = myRef.orderByChild("email").equalTo(mFirebaseUser.getEmail());

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1: snapshot.getChildren()) {
                    tvname.setText(snapshot1.child("name").getValue().toString());
                    tvlesen.setText(snapshot1.child("lesen").getValue().toString());
                    tvphone.setText(snapshot1.child("phone").getValue().toString());
                    tvDOB.setText(snapshot1.child("date_birth").getValue().toString());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}

