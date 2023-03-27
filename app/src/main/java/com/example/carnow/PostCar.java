package com.example.carnow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carnow.adapter.OwnerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostCar extends AppCompatActivity {
    private EditText etphone,etplate,etprice, etpicture;
    private Button btnpost;
    String modeltxt,seatertxt;


    FirebaseDatabase database =FirebaseDatabase.getInstance("https://carnow-9c803-default-rtdb.asia-southeast1.firebasedatabase.app/");

    Owner owner = new Owner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_car);

        Spinner model =findViewById(R.id.spinner_model);
        Spinner seater = findViewById(R.id.spinner_seater);

        ArrayAdapter<CharSequence> carAdapter = ArrayAdapter.createFromResource(this,R.array.CarType, android.R.layout.simple_spinner_item);
        carAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        model.setAdapter(carAdapter);
        model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                modeltxt = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(adapterView.getContext(), modeltxt, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> seaterAdapter = ArrayAdapter.createFromResource(this,R.array.SeaterType, android.R.layout.simple_spinner_item);
        seaterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seater.setAdapter(seaterAdapter);
        seater.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                seatertxt = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(adapterView.getContext(), seatertxt, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        etphone = findViewById(R.id.et_phone_post);
        etplate = findViewById(R.id.et_plate);
        etprice = findViewById(R.id.et_price);
        etpicture = findViewById(R.id.et_picture);
        btnpost = findViewById(R.id.btn_post);


        btnpost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DatabaseReference myRefmodel = database.getReference(modeltxt);
                DatabaseReference myRefseat = database.getReference(seatertxt);
                String link = etpicture.getText().toString().trim();
                String phone =etphone.getText().toString().trim();
                String plate = etplate.getText().toString().trim();
                String model = modeltxt.toString().trim();
                String seater = seatertxt.toString().trim();
                String price = "RM "+ etprice.getText().toString().trim();


                owner = new Owner(link,phone,plate,model,seater,price);
                Intent intent = new Intent(PostCar.this, OwnerAdapter.class);
                intent.putExtra("model", model);
                intent.putExtra("plate",plate);
                intent.putExtra("price",price);
                intent.putExtra("link",link);
                intent.putExtra("seater",seater);
                intent.putExtra("phone",phone);

                myRefmodel.push().setValue(owner);
                myRefseat.push().setValue(owner);
                Toast.makeText(PostCar.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                etphone.getText().clear();
                etplate.getText().clear();
                etprice.getText().clear();
                etpicture.getText().clear();


            }

        });

    }

}