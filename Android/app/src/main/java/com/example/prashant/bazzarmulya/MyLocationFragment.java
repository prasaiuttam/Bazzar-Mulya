package com.example.prashant.bazzarmulya;
import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.database.Cursor;
import android.provider.Settings;
import android.support.design.internal.NavigationMenu;
import android.support.v4.app.FragmentActivity;


import android.os.Bundle;
import android.support.design.widget.Snackbar;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import android.support.v4.view.ViewGroupCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by uttam on 6/25/16.
 *
 */

public  class  MyLocationFragment extends ListFragment {

    private final String LOG_TAG = MyListFragment.class.getSimpleName();
    private String productName, location, price;
    private List<String> products, locations, prices;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState)
    {

        products=new ArrayList<String>();
        locations = new ArrayList<String>();
        prices = new ArrayList<String>();
        ViewGroup rootView=(ViewGroup) inflater.inflate(R.layout.mylistfragment,container,false);


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),R.layout.mytextfragment,R.id.txtitem,locations);
        setListAdapter(adapter);
        setRetainInstance(true);


        DatabaseHelper myDB=new DatabaseHelper(this.getActivity());

       //myDB.insertData("Mayos", "Kathmandu", 105);
      // myDB.insertData("Momo", "bhaktapur", 96);

        Cursor cursor = myDB.getDataBylocation();

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




        return rootView;

    }

    public void onListItemClick(ListView i, View view, int position, long id)
    {
        String  Selected1= (String) i.getItemAtPosition(position);
        FragmentManager fragmentManager=getFragmentManager();

        android.app.FragmentTransaction transaction=fragmentManager.beginTransaction();
        MyLocationFragment2 fragment=new MyLocationFragment2();
        Bundle args = new Bundle();
//        args.putString("NAME", products.get(position));
        args.putString("LOCATION", locations.get(position));

//        "SELECT * FROM products WHERE products.location = " + locations.get(position);
//        args.putString("PRICE", prices.get(position));
        fragment.setArguments(args);

        transaction.replace(R.id.content,fragment).commit();

        ViewGroup viewGroup = (ViewGroup) view;

        TextView txt = (TextView) viewGroup.findViewById(R.id.txtitem);
        Toast.makeText(getActivity(), txt.getText().toString(), Toast.LENGTH_LONG).show();
    }



}