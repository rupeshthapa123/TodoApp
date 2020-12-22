package com.example.mytodoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.Toast;

public class TaskDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE =
            "package com.example.mytodoapp.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "package com.example.mytodoapp.EXTRA_DESCRIPTION";
    public static final String EXTRA_COMPLETE =
            "package com.example.mytodoapp.EXTRA_COMPLETE";
    public static final String EXTRA_PRIORITY =
            "package com.example.mytodoapp.EXTRA_PRIORITY";
    private EditText editTextTitle;
    private EditText editTextDesc;
    private NumberPicker numberPickerPriority;
    private CheckBox isComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        editTextTitle = findViewById(R.id.title_id);
        editTextDesc = findViewById(R.id.desc_id);
        isComplete = findViewById(R.id.iscom_id);
        numberPickerPriority = findViewById(R.id.priority_number_pick);
        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(5);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Task");
    }

    private void SaveTask() {
        String title = editTextTitle.getText().toString();
        String description = editTextDesc.getText().toString();
        Boolean isCompl = isComplete.isChecked();
        int priority = numberPickerPriority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_COMPLETE, isCompl);
        data.putExtra(EXTRA_PRIORITY, priority);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_task_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_task:
                SaveTask();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}