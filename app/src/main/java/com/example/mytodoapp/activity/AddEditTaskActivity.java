package com.example.mytodoapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mytodoapp.utility.AlarmBroadcast;
import com.example.mytodoapp.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddEditTaskActivity extends AppCompatActivity{
    public static final String EXTRA_ID =
            "package com.example.mytodoapp.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "package com.example.mytodoapp.EXTRA_TITLE";
    public static final String EXTRA_DATE =
            "package com.example.mytodoapp.EXTRA_DATE";
    public static final String EXTRA_TIME =
            "package com.example.mytodoapp.EXTRA_TIME";
    public static final String EXTRA_COMPLETE =
            "package com.example.mytodoapp.EXTRA_COMPLETE";
    public static final String EXTRA_PRIORITY =
            "package com.example.mytodoapp.EXTRA_PRIORITY";

    // Constant for date format
   // private static final String DATE_FORMAT = "dd/MM/yyy";
    // Date formatter
   // @SuppressLint("SimpleDateFormat")
   // private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    private EditText editTextTitle;
    private NumberPicker numberPickerPriority;
    private CheckBox isComplete;
    private EditText date_in;
    private EditText time_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addedit_task_detail);

        editTextTitle = findViewById(R.id.title_id);
        date_in = findViewById(R.id.date_task);
        time_in = findViewById(R.id.time_task);
        isComplete = findViewById(R.id.iscom_id);
        numberPickerPriority = findViewById(R.id.priority_number_pick);
        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(3);

        date_in.setInputType(InputType.TYPE_NULL);
        time_in.setInputType(InputType.TYPE_NULL);

        date_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDateDialog(date_in);
            }
        });

        time_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowTimeDialog(time_in);
            }
        });

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Task");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            date_in.setText(intent.getStringExtra(EXTRA_DATE));
            time_in.setText(intent.getStringExtra(EXTRA_TIME));
            isComplete.setChecked(Boolean.parseBoolean(String.valueOf(intent.getBooleanExtra(EXTRA_COMPLETE,false))));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));

        }else {
            setTitle("Add Task");
        }
    }

    private void ShowTimeDialog(final EditText time_in) {
       final Calendar calendar = Calendar.getInstance();
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                time_in.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new TimePickerDialog(AddEditTaskActivity.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
    }

    private void ShowDateDialog(final EditText date_in) {
       final Calendar calendar= Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat  simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                date_in.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new DatePickerDialog(AddEditTaskActivity.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void SaveTask() {
        String title = editTextTitle.getText().toString();
        String dateCreated = date_in.getText().toString();
        String timeCreated = time_in.getText().toString();
        boolean isCompl = isComplete.isChecked();
        int priority = numberPickerPriority.getValue();
        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a task", Toast.LENGTH_SHORT).show();
        }else if(dateCreated.trim().isEmpty() || timeCreated.trim().isEmpty()){
            Toast.makeText(this, "Please select date and time", Toast.LENGTH_SHORT).show();
        }else {
            Intent data = new Intent();
            data.putExtra(EXTRA_TITLE, title);
            data.putExtra(EXTRA_DATE, dateCreated);
            data.putExtra(EXTRA_TIME, timeCreated);
            data.putExtra(EXTRA_COMPLETE, isCompl);
            data.putExtra(EXTRA_PRIORITY, priority);

            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            if (id != -1) {
                data.putExtra(EXTRA_ID, id);
            }
            setResult(RESULT_OK, data);
            setAlarm(title, dateCreated, timeCreated);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_task_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_task) {
            SaveTask();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setAlarm(String text, String date, String time){
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), AlarmBroadcast.class);
        intent.putExtra("task",text);
        intent.putExtra("date",date);
        intent.putExtra("time",time);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0,intent,PendingIntent.FLAG_ONE_SHOT);
        String dateandtime = date + " " + time;
        @SuppressLint("SimpleDateFormat") DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date date1 = formatter.parse(dateandtime);
            assert date1 != null;
            am.set(AlarmManager.RTC_WAKEUP, date1.getTime(), pendingIntent);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        finish();
    }
}