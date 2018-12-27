package com.example.apple.todoapp.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.apple.todoapp.R;
import com.example.apple.todoapp.viewType.Info;

import java.util.Calendar;

public class CreatEdit extends AppCompatActivity {

    private TimePickerDialog time;
    private DatePickerDialog date;
    public static final String KEY = "todo";
    private int priorityBtnSelect;
    private CheckBox repeat;
    private TextInputEditText title;
    private TextInputEditText description;
    private TextInputEditText dateTime;
    private String priorityBtnText;
    private RadioGroup priority;
    private int radioBtnSelect;
    private String previousDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_edit_fragment);

        title = findViewById(R.id.titleEdit);
        description = findViewById(R.id.descriptionEdit);
        dateTime = findViewById(R.id.editTime);
        dateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);

                timePicker(hour, minute);
                datePicker(day, month, year);
            }
        });

        repeat = findViewById(R.id.repeatView);
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup layout = findViewById(R.id.repeatViewGroup);
                ViewGroup.LayoutParams layoutParams = layout.getLayoutParams();
                if(((CheckBox)v).isChecked()){
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    layout.setLayoutParams(layoutParams);
                    radioBtnSelect = layout.getCheckedRadioButtonId();
                }
                else {
                    layoutParams.height = 0;
                    layoutParams.width = 0;
                    layout.setLayoutParams(layoutParams);
                    layout.clearCheck();
                }
            }
        });
        Info info = new Info();
        if(getIntent().hasExtra(KEY)){
            info.setId(arrivedData());
        }
        save(info);
    }

    public void save(final Info info){
        Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check()) {
                    Intent intent = new Intent();
                    info.setTitle(title.getText().toString());
                    info.setDescription(description.getText().toString());
                    info.setDate(dateTime.getText().toString());
                    info.setPriority(priorityBtnText);
                    intent.putExtra(KEY, info);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private boolean check(){
        radioGroupSelect();
        boolean checked;
        if(isEmpty(title))
        {
            title.setError("Fill this field");
            checked = false;
        }
        else if(isEmpty(description)){
            description.setError("Fill this field");
            checked = false;
        }
        else if(isEmpty(dateTime)){
            dateTime.setError("Fill this field");
            checked = false;
        }
        else if(isEmpty(priorityBtnText)){
            Toast.makeText(this, "Please select thi priority", Toast.LENGTH_LONG).show();
            checked = false;
        }
        else {
            checked = true;
            title.setError(null);
            description.setError(null);
            dateTime.setError(null);
        }
        return checked;
    }

    private boolean isEmpty(TextInputEditText editText){
        return TextUtils.isEmpty(editText.getText().toString());
    }

    private boolean isEmpty(String priority){
        return TextUtils.isEmpty(priority);
    }

    private void radioGroupSelect(){
        try {
            priority = findViewById(R.id.priorityGroup);
            priorityBtnSelect = priority.getCheckedRadioButtonId();
            RadioButton btn = findViewById(priorityBtnSelect);
            priorityBtnText = btn.getText().toString();
        }
        catch (NullPointerException n){

        }
    }

    private void timePicker(int hour, int minute){
        try {
            previousDate = dateTime.getText().toString();
        }catch (NullPointerException n){}
            time = new TimePickerDialog(CreatEdit.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    dateTime.setText(dateTime.getText() + " " + String.format("%02d:%02d", hourOfDay, minute));
                }
            }, hour, minute, true);
            time.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    if(previousDate.isEmpty()) {
                        dateTime.setText("");
                    }
                    else{
                        dateTime.setText(previousDate);
                    }
                }
            });
            time.show();
    }
    private void datePicker(int day, int month, int year){
        try {
            previousDate = dateTime.getText().toString();
        } catch (NullPointerException n){}
        date = new DatePickerDialog(CreatEdit.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateTime.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, day);
        date.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                time.cancel();
                dateTime.setText(previousDate);
            }
        });
        date.show();
    }

    public String arrivedData(){
            Info info = getIntent().getParcelableExtra(KEY);
            title.setText(info.getTitle());
            description.setText(info.getDescription());
            dateTime.setText(info.getDate());
            String k = info.getId();
            return k;
    }
}
