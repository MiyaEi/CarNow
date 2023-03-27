package com.example.carnow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.squareup.picasso.Picasso;

public class CarDetail extends AppCompatActivity {
    TextView tvmodel,tvplate,tvseat,tvprice,tvcontact;
    Button btnBooking;
    ImageView imgcar;

    FirebaseDatabase database =FirebaseDatabase.getInstance("https://carnow-9c803-default-rtdb.asia-southeast1.firebasedatabase.app/");

    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        tvmodel = findViewById(R.id.tv_modelDetail);
        tvplate = findViewById(R.id.tv_plateDetail);
        tvseat = findViewById(R.id.tv_seaterDetail);
        tvcontact = findViewById(R.id.tv_phoneNumberDetail);
        tvprice = findViewById(R.id.tv_priceDetail);
        btnBooking = findViewById(R.id.btn_booking);
        imgcar =findViewById(R.id.imgCarDetail);
        //tvyear =findViewById(R.id.tv_yearDetail);



        tvmodel.setText(getIntent().getStringExtra("model"));
        tvplate.setText(getIntent().getStringExtra("plate"));
        tvseat.setText(getIntent().getStringExtra("seater"));
        tvcontact.setText(getIntent().getStringExtra("phone"));
        tvprice.setText(getIntent().getStringExtra("price"));
        //tvyear.setText(getIntent().getStringExtra("year"));

        String model = tvmodel.getText().toString().trim();
        String plate = tvplate.getText().toString().trim();
        String seater = tvseat.getText().toString().trim();
        String contact = tvcontact.getText().toString().trim();
        String price = tvprice.getText().toString().trim();

        DatabaseReference myRef1 = database.getReference(model);
        Query checkUser = myRef1.orderByChild("phone").equalTo(contact);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1: snapshot.getChildren()) {
                    String link = snapshot1.child("link").getValue().toString();
                    Picasso.get().load(link).into(imgcar);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Intent intent = getIntent();

        //  TextView tvName = findViewById(R. id. tv_car_name_detail);
        // tvName.setText(intent.getStringExtra("carName"));
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(CarDetailActivity.this, "Log in Successfully", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(view.getContext(), BookingActivity.class);
                intent.putExtra("phone", contact);
                intent.putExtra("model",model);
                intent.putExtra("seater",seater);
                intent.putExtra("price",price);
                intent.putExtra("plate",plate);
                startActivity(intent);

            }
        });
    }
}