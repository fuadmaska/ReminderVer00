package com.example.fuadmaska.myfeature.Fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fuadmaska.myfeature.AddReminder;
import com.example.fuadmaska.myfeature.Interfaces.callBack;
import com.example.fuadmaska.myfeature.Model.DataReminder;
import com.example.fuadmaska.myfeature.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReminderFragment extends Fragment {
    Button btnsetnewrmndr;
    TabLayout tabbawah;
    private ArrayList<DataReminder> data;


    public ReminderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {


            View view = inflater.inflate(R.layout.fragment_reminder, container, false);
            btnsetnewrmndr = view.findViewById(R.id.btnsetnewreminder);
            btnsetnewrmndr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), AddReminder.class);
                    getActivity().startActivity(intent);

                }
            });
    return view;
    }
    private void loaddata() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("datasave", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("datalist", null);
        Type type = new TypeToken<ArrayList<DataReminder>>() {}.getType();
        data = gson.fromJson(json, type);

        if (data == null) {
            data = new ArrayList<>();
        }
    }

}
