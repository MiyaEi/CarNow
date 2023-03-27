package com.example.carnow;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

public class BookingActivity extends AppCompatActivity {
    TextView tvmodel,tvplate,tvseat,tvprice,tvcontact,tvday;
    EditText etdate;
    Button btnbook,btnadd,btnminus;
    ImageView imgcar;
    String total;
    FirebaseDatabase database =FirebaseDatabase.getInstance("https://carnow-9c803-default-rtdb.asia-southeast1.firebasedatabase.app/");


    FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
    Booking booking;
    int a, b=0,c=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        tvmodel = findViewById(R.id.tv_model_booking);
        tvplate = findViewById(R.id.tv_plate_booking);
        tvcontact = findViewById(R.id.tv_phone_booking);
        tvprice = findViewById(R.id.tv_price_booking);
        tvseat = findViewById(R.id.tv_seater_booking);
        btnbook = findViewById(R.id.btn_booking);
        btnadd = findViewById(R.id.btnadd);
        btnminus =findViewById(R.id.btnminus);
        imgcar =findViewById(R.id.img_booking);
        tvday =findViewById(R.id.tv_day_booking);
        etdate = findViewById(R.id.et_date_pickup);



        tvmodel.setText(getIntent().getStringExtra("model"));
        tvplate.setText(getIntent().getStringExtra("plate"));
        tvseat.setText(getIntent().getStringExtra("seater"));
        tvcontact.setText(getIntent().getStringExtra("phone"));
        tvprice.setText(getIntent().getStringExtra("price"));


        String model = tvmodel.getText().toString().trim();
        String plate = tvplate.getText().toString().trim();
        String seater = tvseat.getText().toString().trim();
        String contact = tvcontact.getText().toString().trim();
        String price = tvprice.getText().toString().trim();
        String digits = price.replaceAll("RM ", "");

        int a = Integer.valueOf(digits);
        //int a = Integer.parseInt(digits);



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

        //Query checkUser = myRef.orderByChild("email").equalTo(mFirebaseUser.getEmail());
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b++;
                tvday.setText(""+b);
                int c=a*b;
                total = Integer.toString(c);

            }
        });
        btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b>0) {
                    b--;
                    tvday.setText(""+b);
                    int c = a * b;
                    total = Integer.toString(c);
                }
                else if(b<=0) {
                    b = 0;
                    tvday.setText("" + b);
                    int c = a * b;
                    total = Integer.toString(c);
                }
            }
        });
        btnbook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AlertDialog. Builder builder1= new AlertDialog. Builder (BookingActivity.this);
                builder1.setMessage ("Are you sure"). setTitle("Confirmation");

                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (tvday.getText()=="0" || etdate.getText()==null) {
                            //remind user to key in the data
                            AlertDialog. Builder builder = new AlertDialog. Builder (BookingActivity. this);
                            builder.setMessage ("Please enter an usage day and pickup day"). setTitle("Warning"). setPositiveButton ("OK", null);
                            AlertDialog dialog = builder.create();
                            dialog. show ();
                        }
                        else{

                            String day = tvday.getText().toString().trim();
                            String date = etdate.getText().toString();

                            String finaltotal = "RM "+ total;


                            DatabaseReference myRef = database.getReference("Booking");
                            booking = new Booking(model,plate,seater,price,contact,date,day,finaltotal);
                            myRef.push().setValue(booking);

                            Intent intent = new Intent(BookingActivity.this,BookingDetail.class);
                            intent.putExtra("model", model);
                            intent.putExtra("plate",plate);
                            intent.putExtra("price",price);
                            intent.putExtra("day",day);
                            intent.putExtra("date",date);
                            intent.putExtra("total",finaltotal);

                            Toast.makeText(BookingActivity.this, "Booking confirmed", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                    }
                });
                builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog1 = builder1.create();
                dialog1. show ();

            }
        });

    }
}
