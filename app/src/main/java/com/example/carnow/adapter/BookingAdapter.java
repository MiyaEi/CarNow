package com.example.carnow.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carnow.Booking;
import com.example.carnow.BookingActivity;
import com.example.carnow.CarDetail;
import com.example.carnow.R;

import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyViewHolder> {

    Context context;
    public ArrayList<Booking> bookingArrayList;

    public BookingAdapter(Context context, ArrayList<Booking> bookingArrayList) {
        this.context = context;
        this.bookingArrayList = bookingArrayList;
    }

    @NonNull
    @Override
    public BookingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.booking_detail,parent,false);


        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.MyViewHolder holder, int position) {
        Booking booking = bookingArrayList.get(position);


        holder.tvmodel.setText(booking.getModel());
        holder.tvplate.setText(booking.getPlate());
        holder.tvseater.setText(booking.getContact());
        holder.tvprice.setText(booking.getPrice());
        holder.tvcontact.setText(booking.getContact());
        holder.tvday.setText(booking.getDay());
        holder.tvdate.setText(booking.getDate());
        holder.tvtotal.setText(booking.getTotal());
    }

    @Override
    public int getItemCount() {
        return bookingArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvmodel,tvplate,tvseater,tvprice,tvcontact,tvdate,tvday,tvtotal;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            tvmodel = itemView.findViewById(R.id.tv_seater_detail);
            tvplate = itemView.findViewById(R.id.tv_plateDetail);
            tvseater = itemView.findViewById(R.id.tv_seater_detail);
            tvprice = itemView.findViewById(R.id.tv_price_detail);
            tvcontact = itemView.findViewById(R.id.tv_contact_detail);
            tvdate = itemView.findViewById(R.id.tv_date_detail);
            tvday = itemView.findViewById(R.id.tv_day_detail);



        }

        @Override
        public void onClick(View view) {
            Intent intent =new Intent(view.getContext(), BookingActivity.class);
            intent.putExtra("phone", bookingArrayList.get(getAdapterPosition()).getContact());
            intent.putExtra("model",bookingArrayList.get(getAdapterPosition()).getModel());
            intent.putExtra("seater",bookingArrayList.get(getAdapterPosition()).getSeater());
            intent.putExtra("price",bookingArrayList.get(getAdapterPosition()).getPrice());
            intent.putExtra("plate",bookingArrayList.get(getAdapterPosition()).getPlate());
            view.getContext().startActivity(intent);

        }
    }
}
