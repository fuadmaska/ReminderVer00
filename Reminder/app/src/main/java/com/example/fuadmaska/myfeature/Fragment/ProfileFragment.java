package com.example.fuadmaska.myfeature.Fragment;


import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fuadmaska.myfeature.Model.DataReminder;
import com.example.fuadmaska.myfeature.PopUpDetailReminder;
import com.example.fuadmaska.myfeature.PopUpLogout;
import com.example.fuadmaska.myfeature.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    TextView logout;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_profile, container, false);
       logout = v.findViewById(R.id.logout);
       logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getActivity(), PopUpDetailReminder.class);
               Dialog dialog = new PopUpLogout(getActivity(), intent);
//               startActivityForResult(intent,2);
               dialog.show();
           }
       });


       return v;

    }

}
