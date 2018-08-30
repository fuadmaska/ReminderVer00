package com.example.fuadmaska.myfeature.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fuadmaska.myfeature.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClaimFragment extends Fragment {
    TabLayout tabatas;
    ViewPager pageratas;
    Unbinder unbinder;


    public ClaimFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_claim, container, false);
        unbinder = ButterKnife.bind(this, view);
        tabatas = view.findViewById(R.id.tabclaim);
        pageratas = view.findViewById(R.id.pagerclaim);
        tabatas.addTab(tabatas.newTab().setText("Search ID"));
        tabatas.addTab(tabatas.newTab().setText("Claim History"));
        tabatas.setTabTextColors(Color.parseColor("#757575"), Color.parseColor("#B71C1C"));

        tabatas.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabatas.setTabMode(TabLayout.MODE_FIXED);

        Custom adapter = new Custom(getActivity().getSupportFragmentManager());
        pageratas.setAdapter(adapter);
        pageratas.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabatas));
        tabatas.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pageratas.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;

    }


    private class Custom extends FragmentStatePagerAdapter {
        public Custom(FragmentManager tabCount) {
            super(tabCount);

        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new ClaimSearchIdFragment();
            } else {
                return new ClaimHistoryFragment();

            }

        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
