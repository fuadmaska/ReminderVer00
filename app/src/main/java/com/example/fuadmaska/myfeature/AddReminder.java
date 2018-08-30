package com.example.fuadmaska.myfeature;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fuadmaska.myfeature.Fragment.ReminderListFragment;
import com.example.fuadmaska.myfeature.Model.DataReminder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AddReminder extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TimePickerDialog timePickerDialog;
    private Spinner spinnerAdd;
    private Button btnUpdate;
    private TextView textTitle, Editdate, Edittime, Editnote;
    private EditText Edittotal;
    private String category;
    private String total;
    private String tanggal;
    private String waktu;
    private String note;
    public static ArrayList<DataReminder> data;
    private String[] jenisAsuransi = {
            "Cyber Data Breach Insurance",
            "MoTab Data Fraud Insurance",
            "Social Media Account Insurance",
    };
    ArrayAdapter<String> adapter;
    /*
     var status untuk memberi tanda bahwa akan dilakukan proses edit, yg akan dikirim melalui adapter reminder atau detail reminder,
      dengan mengirim value update. alasan menggunakan modifier static agar dapat diset diluar kelas ini dan membantu mengirim value update
      */
    public static String status = "";
    /*
    dilakukan pengecekan ulang atau memeriksa tanda, apabila statusDetail update maka yg meminta proses edit dari detail reminder
    , jika statusDetail = "" maka yg meminta proses edit adalah dari recycler atau kelas adapter reminder
     */
    public static String statusDetail = "";

    int posisiEdit = 0; // untuk mendapatkan posisi dari si data yang akan diedit supaya dapat me replace data dengan yang baru
    Intent intentEdit = getIntent();// membantu menangkap data yang akan di edit dan dikirim dari adapter reminder

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        loaddata();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_add);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        spinnerAdd = (Spinner) findViewById(R.id.selectinsu);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, jenisAsuransi);
        spinnerAdd.setAdapter(adapter);
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        initComponent();// casting komponen layout
        Edittotal.addTextChangedListener(new NumberTextWatcherForThousand(Edittotal));
        NumberTextWatcherForThousand.trimCommaOfString(Edittotal.getText().toString());

//        Edittotal.addTextChangedListener(new TextWatcher() {
//            private String current = "";
//
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//                if (!editable.toString().equals(current)) {
//                    Edittotal.removeTextChangedListener(this);
//                    Locale local = new Locale("id", "id");
//                    String replaceable = String.format("[Rp,.\\s]",
//                            NumberFormat.getCurrencyInstance().getCurrency()
//                                    .getSymbol(local));
//                    String cleanString = editable.toString().replaceAll(replaceable,
//                            "");
//                    double parsed;
//                    try {
//                        parsed = Double.parseDouble(cleanString);
//                    } catch (NumberFormatException e) {
//                        parsed = 0.00;
//                    }
//                    NumberFormat formatter = NumberFormat.getCurrencyInstance(local);
//                    formatter.setMaximumFractionDigits(0);
//                    formatter.setParseIntegerOnly(true);
//                    String formatted = formatter.format((parsed));
//                    String replace = String.format("[Rp\\s]", NumberFormat.getCurrencyInstance().getCurrency().getSymbol(local));
//                    String clean = formatted.replaceAll(replace, "");
//                    current = formatted;
//                    Edittotal.setText(clean);
//                    Edittotal.setSelection(clean.length());
//                    Edittotal.addTextChangedListener(this);
//
//
//                }
//            }
//        });

        Editnote.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    Editdate.setBackgroundResource(R.drawable.tv_addreminder_input);
                    Edittime.setBackgroundResource(R.drawable.tv_addreminder_input);

                } else {

                }
            }
        });

        Edittotal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    Editdate.setBackgroundResource(R.drawable.tv_addreminder_input);
                    Edittime.setBackgroundResource(R.drawable.tv_addreminder_input);

                } else {


                }
            }
        });
        //////////////////////

        Editdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //tambahkan sesuatu
                // Editdate.setFocusableInTouchMode(true);
                // Editdate.setFocusable(true);


                Edittotal.setFocusable(false);
                Edittotal.setFocusableInTouchMode(false);
                Editnote.setFocusable(false);
                Editnote.setFocusableInTouchMode(false);

                Editdate.setBackgroundResource(R.drawable.tv_addreminder_input_red);
                showDateDialog();


                Edittotal.setFocusable(true);
                Edittotal.setFocusableInTouchMode(true);
                Editnote.setFocusable(true);
                Editnote.setFocusableInTouchMode(true);
            }
        });
        Edittime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //tambahkan sesuatu
                //Edittime.setFocusableInTouchMode(true);
                //Edittime.setFocusable(true);


                Edittotal.setFocusable(false);
                Edittotal.setFocusableInTouchMode(false);
                Editnote.setFocusable(false);
                Editnote.setFocusableInTouchMode(false);
                Edittime.setBackgroundResource(R.drawable.tv_addreminder_input_red);
                showTimeDialog();


                Edittotal.setFocusable(true);
                Edittotal.setFocusableInTouchMode(true);
                Editnote.setFocusable(true);
                Editnote.setFocusableInTouchMode(true);
            }
        });

        /*
         proses pemilihan kondisi apakah yang akan dilakukan, apabila status = update maka akan masuk ke prosses update reminder.
         dan apabila status = "" maka akan dilakukan proses tambah reminder
          */
        if (status == "update") {
            //proses update atau edit
            btnUpdate.setText("Update");
            textTitle.setText("Edit Reminder");
            getDataEdit();// memanggil metod yang menjalankan proses penangkapan data
            status = "";// diset ulang agar status kembali seperti semula setelah selesai melakukan update
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //proses replace data
                    ambilData();
                    data.set(posisiEdit, new DataReminder(category, total, tanggal, waktu, note));
                    save();//simpan data yg telah diperbarui

                    if (statusDetail == "update") {
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        ambilData();
                        bundle.putSerializable("perbaruiData", new DataReminder(category, total, tanggal, waktu, note));
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                        statusDetail = "";//diset ulang agar status kembali seperti semula setelah selesai melakukan update
                    }

                    Toast.makeText(getBaseContext(), "Berhasil Update", Toast.LENGTH_LONG).show();
                    finish();
                }
            });
        } else {
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ambilData();

                    if (total.isEmpty()) {
                        Edittotal.setError("The Amount must be filled");
                        Edittotal.requestFocus();
                    } else if (tanggal.isEmpty()) {
                        Editdate.setError("Date must be filled");
                        Editdate.requestFocus();
                    } else if (waktu.isEmpty()) {
                        Edittime.setError("Time must be filled");
                        Edittime.requestFocus();
                    } else if (note.isEmpty()) {
                        Editnote.setError("Note must be filled");
                        Editnote.requestFocus();
                    } else {
                        ReminderListFragment rlf = new ReminderListFragment();
                        data.add(new DataReminder(category, total, tanggal, waktu, note));
                        save();
                        setAlarm(tanggal, waktu);
                        finish();
//                        Toast.makeText(getApplicationContext(), "Data Disimpan di Internal", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(AddReminder.this,ReminderListFragment.class);
//                        startActivity(intent);
//                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.container_remin,rlf);
//                        fragmentTransaction.commit();

                        //AddReminder.this.finish();

                    }
                }
            });
        }

    }

    private void initComponent() {
        Editdate = (TextView) findViewById(R.id.edit_date);
        Edittotal = (EditText) findViewById(R.id.edit_total);
        Edittime = (TextView) findViewById(R.id.edit_time);
        btnUpdate = (Button) findViewById(R.id.button_update);
        Editnote = (TextView) findViewById(R.id.edit_note);
        textTitle = (TextView) findViewById(R.id.textTitle);
    }

    private void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                Editdate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void showTimeDialog() {

        /**
         * Calendar untuk mendapatkan waktu saat ini
         */
        Calendar calendar = Calendar.getInstance();

        /**
         * Initialize TimePicker Dialog
         */
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                /**
                 * Method ini dipanggil saat kita selesai memilih waktu di DatePicker
                 */
                if (minute < 10 && hourOfDay < 10) {
                    Edittime.setText("0" + String.valueOf(hourOfDay)
                            + ":0" + String.valueOf(minute));
                } else if (hourOfDay < 10) {
                    Edittime.setText("0" + String.valueOf(hourOfDay)
                            + ":" + String.valueOf(minute));
                }
            }
        },
                /**
                 * Tampilkan jam saat ini ketika TimePicker pertama kali dibuka
                 */
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),

                /**
                 * Cek apakah format waktu menggunakan 24-hour format
                 */
                DateFormat.is24HourFormat(this));

        timePickerDialog.show();
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // jika menit kurangdari 10, tambahkan 0 di depan (agar format menit konsisten 2 digit)

    }

    public void save() {
        SharedPreferences sharedPreferences = getSharedPreferences("datasave", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString("datalist", json);
        editor.apply();

    }

    public void getDataEdit() {
        //proses penangkapan data yang dikirim dari adapter reminder yang akan di edit, dan prosess set layout
        Bundle bundle = getIntent().getExtras();
        DataReminder reminder = (DataReminder) bundle.getSerializable("data");
        int spinnerPosisi = adapter.getPosition(reminder.getCategory()); // proses mencari posisi data yang ada di spinner dengan value berikut
        spinnerAdd.setSelection(spinnerPosisi);
        Editdate.setText(reminder.getTanggal());
        Edittime.setText(reminder.getWaktu());
        Edittotal.setText(reminder.getTotal());
        Editnote.setText(reminder.getNote());
        posisiEdit = bundle.getInt("posisi");

    }

    private void ambilData() {
        category = spinnerAdd.getSelectedItem().toString();
        total = Edittotal.getText().toString();
        tanggal = Editdate.getText().toString();
        waktu = Edittime.getText().toString();
        note = Editnote.getText().toString();
    }

    public void loaddata() {
        SharedPreferences sharedPreferences = getSharedPreferences("datasave", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("datalist", null);
        Type type = new TypeToken<ArrayList<DataReminder>>() {
        }.getType();
        data = gson.fromJson(json, type);

        if (data == null) {
            data = new ArrayList<>();
        }

    }


    private void setAlarm(String tanggal, String waktu) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String string = tanggal + " " + waktu;


        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = format.parse(string);
            System.out.println(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar.setTime(date);
        System.out.println(calendar.getTimeInMillis());

        //
        AlarmManager alarmManager = (AlarmManager) AddReminder.this.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(AddReminder.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                AddReminder.this, 1, intent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }
}


