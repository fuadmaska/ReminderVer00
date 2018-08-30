package com.example.fuadmaska.myfeature;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuadmaska.myfeature.Fragment.ClaimFragment;
import com.example.fuadmaska.myfeature.Fragment.FragmentReminderMain;
import com.example.fuadmaska.myfeature.Fragment.HomeFragment;
import com.example.fuadmaska.myfeature.Fragment.MessageFragment;
import com.example.fuadmaska.myfeature.Fragment.ProfileFragment;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
    ViewPager pageatas;
    BottomNavigationView bottomNavigationView;
    MenuItem menuItem;
    TextView title;
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure want to exit?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.finish();
                    }
                }).setNegativeButton("No",null).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pageatas = findViewById(R.id.idtab);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.con,new HomeFragment());
        transaction.commit();
        title=(TextView)findViewById(R.id.textTitle);
        title.setText("Home");
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav);
        removeShiftMode(bottomNavigationView);
        buttom();


    }

    @SuppressLint("RestrictedApi")
    static void removeShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode");
        }
    }
    public void buttom (){

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.home2:
                        title.setText("Home");
                        fragment = new HomeFragment();
                        Toast.makeText(getApplication(), "Home clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.claim:
                        title.setText("Claime Report");
                        fragment = new ClaimFragment();
                        Toast.makeText(getApplication(), "Claim clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.reminder:
                        title.setText("Premi Reminder");
                        fragment = new FragmentReminderMain();
                        Toast.makeText(getApplication(), "Reminder clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.message:
                        title.setText("Message");
                        fragment = new MessageFragment();
                        Toast.makeText(getApplication(), "Message clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.profile:
                        title.setText("Profile");
                        fragment = new ProfileFragment();
                        Toast.makeText(getApplication(), "Profile clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.con, fragment)
                        .commit();

                return true;
            }
        });
    }



}
