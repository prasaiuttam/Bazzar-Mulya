package com.example.prashant.bazzarmulya;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = new LinearLayout(this.getActivity());
        TextView textView = new TextView(this.getActivity());
        TextView textView1=new TextView(this.getActivity());
        LinearLayout.LayoutParams textview1=new LinearLayout.LayoutParams(LinearLayout.VERTICAL,LinearLayout.VERTICAL);
        textView.setText("Hello From The Fragment");
        textView.setId(23);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(textView);
        return layout;
    }
}
