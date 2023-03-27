package com.example.carnow.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.carnow.BookingActivity;
import com.example.carnow.CarDetail;
import com.example.carnow.Owner;
import com.example.carnow.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OwnerAdapter extends RecyclerView.Adapter<OwnerAdapter.MyViewHolder> {


    Context context;
    public ArrayList<Owner> ownerArrayList;
    public ImageView imageView ;
    //TextView tvname,tvmodel;
    FirebaseDatabase database =FirebaseDatabase.getInstance("https://carnow-9c803-default-rtdb.asia-southeast1.firebasedatabase.app/");



    public OwnerAdapter(Context context, ArrayList<Owner> ownerArrayList) {
        this.context = context;
        this.ownerArrayList = ownerArrayList;
    }


    @NonNull
    @Override
    public OwnerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.owner_details,parent,false);



        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnerAdapter.MyViewHolder holder, int position) {
        Owner owner = ownerArrayList.get(position);


//            Picasso.get().load(owner.getLink()).into(holder.imageView);

//        Glide.with(holder.imageView.getContext()).load(owner.getLink()).into(holder.imageView);
        holder.tvname.setText(owner.getPhone());
        holder.tvmodel.setText(owner.getModel());
        holder.tvtype.setText(owner.getSeater());
        holder.tvprice.setText(owner.getPrice());
    }



    @Override
    public int getItemCount() {
        return ownerArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView tvname,tvmodel,tvtype,tvprice;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.book_tv_name);
            tvmodel = itemView.findViewById(R.id.tv_model);
            tvtype = itemView.findViewById(R.id.book_tv_type);
            tvprice = itemView.findViewById(R.id.tv_total);
            imageView = itemView.findViewById(R.id.img_view_car);
            itemView.setOnClickListener((View.OnClickListener) this);

            String model = tvmodel.getText().toString().trim();
            String contact = tvname.getText().toString().trim();

            DatabaseReference myRef1 = database.getReference(model);
            Query checkUser = myRef1.orderByChild("phone").equalTo(contact);

            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot snapshot1: snapshot.getChildren()) {
                        String link = snapshot1.child("link").getValue().toString();
                        Picasso.get().load(link).into(imageView);
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

        public void onClick(View view){
            Intent intent =new Intent(view.getContext(), CarDetail.class);
            intent.putExtra("link", ownerArrayList.get(getAdapterPosition()).getLink());
            intent.putExtra("phone", ownerArrayList.get(getAdapterPosition()).getPhone());
            intent.putExtra("model",ownerArrayList.get(getAdapterPosition()).getModel());
            intent.putExtra("seater",ownerArrayList.get(getAdapterPosition()).getSeater());
            intent.putExtra("price",ownerArrayList.get(getAdapterPosition()).getPrice());
            intent.putExtra("plate",ownerArrayList.get(getAdapterPosition()).getPlate());
            view.getContext().startActivity(intent);
            Toast.makeText(view.getContext(), "Selected", Toast.LENGTH_SHORT).show();

        }


    }



}
