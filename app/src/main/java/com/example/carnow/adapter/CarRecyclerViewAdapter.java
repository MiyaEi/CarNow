package com.example.carnow.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carnow.Car;
import com.example.carnow.CarDetail;
import com.example.carnow.R;

import java.util.List;

public class CarRecyclerViewAdapter extends RecyclerView.Adapter<CarRecyclerViewAdapter.CarViewHolder>{
    public List<Car> carList;
    private Context context;

    public CarRecyclerViewAdapter(Context context, List<Car> carList) {
        this.context = context;
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View car_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_row,null);
        CarViewHolder carVH = new CarViewHolder(car_row);


        return carVH;
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        holder.imgcartype.setImageResource(carList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imgcartype;

        public CarViewHolder(View itemView) {

            super(itemView);

            imgcartype = itemView.findViewById(R.id.img_car);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Intent intent = new Intent(view.getContext(), CarDetail.class);

            view.getContext().startActivity(intent);
        }
    }
}
