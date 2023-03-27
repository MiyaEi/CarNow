package com.example.carnow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class BookingDetail extends AppCompatActivity {

    TextView tvmodel,tvplate,tvprice,tvday,tvdate, tvtotal;

    FirebaseDatabase database =FirebaseDatabase.getInstance("https://carnow-9c803-default-rtdb.asia-southeast1.firebasedatabase.app/");


    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

    BottomNavigationView bottomNavigationView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_complete);

        Toolbar toolbar = findViewById(R.id.toolbar_main2);

        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        tvmodel = findViewById(R.id.tv_car_confirm);
        tvplate = findViewById(R.id.tv_plate_confirm);
        tvprice = findViewById(R.id.tv_price_confirm);
        tvday = findViewById(R.id.tv_day);
        tvdate = findViewById(R.id.tv_pickup);
        tvtotal = findViewById(R.id.tv_total_detail);



        tvmodel.setText(getIntent().getStringExtra("model"));
        tvplate.setText(getIntent().getStringExtra("plate"));
        tvday.setText(getIntent().getStringExtra("day"));
        tvdate.setText(getIntent().getStringExtra("date"));
        tvprice.setText(getIntent().getStringExtra("price"));
        tvtotal.setText(getIntent().getStringExtra("total"));


     bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Intent intent = new Intent();
            switch (item.getItemId()) {
                case R.id.nav_home:
                    intent = new Intent(BookingDetail.this, HomeInterface.class);
                    startActivity(intent);
                    break;
                case R.id.nav_post:
                    intent = new Intent(BookingDetail.this, PostCar.class);
                    startActivity(intent);
                    break;
                case R.id.nav_account:
                    intent = new Intent(BookingDetail.this, Profile.class);
                    startActivity(intent);
                    break;
            }

            return true;
        }
        });
    }
}