package com.example.prashant.bazzarmulya;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public   class MainViewAdapter extends RecyclerView.Adapter<MainViewAdapter.RecyclerViewHolder>{



   // private String[] productName;
    private int lastPosition = -1;
    List<String> location;
    private List<String> productPrice;
    private Context context;
    Activity blocksActivity;



    class RecyclerViewHolder extends RecyclerView.ViewHolder{
       // private TextView productName;
       public TextView location;
        public TextView productPrice;



        public RecyclerViewHolder(final View itemView) {
            super(itemView);
           // this.productName =(TextView) itemView.findViewById(R.id.productName);
            this.location=(TextView) itemView.findViewById(R.id.location);
            this.productPrice=(TextView) itemView.findViewById(R.id.productPrice);


            itemView.setClickable(true);

        }
       // public TextView getProductName(){
          //  return productName;
       // }
       public TextView getProductLocation(){
           return location;
       }
        public TextView getProductPrice(){
            return productPrice;
        }


        /* public TextView getDepartmentDescription(){
             return departmentDescription;
         }*/
        public void clearAnimation()
        {
            itemView.clearAnimation();
        }
    }

    public MainViewAdapter(List<String> location, List<String> productPrice, Context context){
      //  this.productName =productName;
        this.location =location;
        this.productPrice =productPrice;



        this.context=context;



    }
    @Override
    public MainViewAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        RecyclerViewHolder holder = new RecyclerViewHolder(v);

        return holder;

    }



    @Override
    public void onBindViewHolder(MainViewAdapter.RecyclerViewHolder holder, final int position) {
       // holder.getProductName().setText(productName[position]);
        holder.getProductLocation().setText(location.get(position));
        holder.getProductPrice().setText(productPrice.get(position));

        animation(holder.itemView,position);


        holder.itemView.setClickable(true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "chalyo hai chalyo", Toast.LENGTH_SHORT).show();;
            }
        });
        //holder.getDepartmentDescription().setText(departmentDescription[position]);


    }

    @Override
   public int getItemCount() {
        return productPrice.size();
    }




    @Override
    public void onViewDetachedFromWindow(final RecyclerViewHolder holder)
    {
        holder.clearAnimation();
    }


    public void animation(View view, int position){
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            view.startAnimation(animation);
            lastPosition = position;
        }

    }


}

