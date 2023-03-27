package com.example.carnow;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carnow.adapter.OwnerAdapter;
import com.example.carnow.fragment.HomeFragment;
import com.example.carnow.fragment.MyAccountFragment;
import com.example.carnow.fragment.PostCarFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeInterface extends AppCompatActivity {

    LinearLayoutManager linearLayoutManager;
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    PostCarFragment postFragment = new PostCarFragment();
    MyAccountFragment accountFragment = new MyAccountFragment();

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://carnow-9c803-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference myRef = database.getReference("Four Seater");

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_interface);

        Toolbar toolbar = findViewById(R.id.toolbar_main);

        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        ArrayList<Owner> ownerArrayList = new ArrayList<Owner>();
        OwnerAdapter ownerAdapter = new OwnerAdapter(HomeInterface.this, ownerArrayList);
        recyclerView.setAdapter(ownerAdapter);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Owner owner = dataSnapshot.getValue(Owner.class);
                    ownerArrayList.add(owner);
                }
                ownerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,homeFragment).commit();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent intent = new Intent();
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        intent = new Intent(HomeInterface.this, HomeInterface.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_post:
                        intent = new Intent(HomeInterface.this, PostCar.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_account:
                        intent = new Intent(HomeInterface.this, Profile.class);
                        startActivity(intent);
                        break;
                }

                return true;
            }
        });

        /*linearLayoutManager = new LinearLayoutManager(HomeInterface.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        CarRecyclerViewAdapter carRecyclerViewAdapter = new CarRecyclerViewAdapter(HomeInterface.this, getAllCarInfor());
        recyclerView.setAdapter(carRecyclerViewAdapter);*/

    }

    /* private void replaceFragement(Fragment fragment){
         FragmentManager fragmentManager=getSupportFragmentManager();
         FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
         fragmentTransaction.replace(R.id.,fragement);
     } */


        public boolean onCreateOptionsMenu(Menu menu){

            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main_menu, menu);
            return super.onCreateOptionsMenu(menu);

        }

        public boolean onOptionsItemSelected (@NonNull MenuItem item){
            Intent intent;
            switch (item.getItemId()) {
                case R.id.menu_noti:
                    Toast.makeText(HomeInterface.this, "Notification", Toast.LENGTH_SHORT).show();
                    intent = new Intent(HomeInterface.this, NotificationActivity.class);
                    startActivity(intent);
                    break;
            }
            return super.onOptionsItemSelected(item);

        }

    }
