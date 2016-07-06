package com.example.prashant.bazzarmulya;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uttam on 7/2/16.
 */
public class MyLocationFragment2 extends Fragment {

    MainViewAdapter2 recyclerAdapter;
    private String productName, location, price;
    private List<String> products, locations, prices;
    private final String LOG_TAG = MyLocationFragment2.class.getSimpleName();

    public MyLocationFragment2() {
        // Required empty public constructor
//        location;

        products = new ArrayList<String>();
        locations = new ArrayList<String>();
        prices = new ArrayList<String>();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.recyclerfragment, container, false);
        android.support.v7.widget.RecyclerView mRecyclerView = (android.support.v7.widget.RecyclerView) rootView.findViewById(R.id.recyclerView);
        android.support.v7.widget.RecyclerView.LayoutManager recyclerViewLayout= new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(recyclerViewLayout);
        mRecyclerView.setHasFixedSize(true);


//        String productName = getArguments().getString("NAME");
        //Log.v(LOG_TAG, "Product Name = " + productName);

        String location = getArguments().getString("LOCATION");
        Log.v(LOG_TAG, "Location = " + location);

//        String price = getArguments().getString("PRICE");
        //Log.v(LOG_TAG, "Price = " + price);

        String productName;
        String price;

        DatabaseHelper helper = new DatabaseHelper(this.getActivity());
        Cursor cursor = helper.getDataBylocation(location);

        try {
            while (cursor.moveToNext()) {
                Log.v(LOG_TAG, String.valueOf(cursor));
                productName = cursor.getString(cursor.getColumnIndex("NAME"));
                products.add(productName);

                location = cursor.getString(cursor.getColumnIndex("LOCATION"));
                locations.add(location);

                price = cursor.getString(cursor.getColumnIndex("PRICE"));
                prices.add(price);


            }
        } finally {
            cursor.close();
        }

//        products.add(productName);
//        productPrice.add(price);


        recyclerAdapter = new MainViewAdapter2(products, prices, getActivity());


        mRecyclerView.setAdapter(recyclerAdapter);



        return rootView;
    }

}
