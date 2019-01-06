package com.example.apple.todoapp.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
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

public class FragmentView extends Fragment {

    private View view;
    private TextInputEditText title, description, dateTime;
    private TimePickerDialog time;
    private DatePickerDialog date;
    private String previousDate;
    private CheckBox repeat;
    private int priorityBtnSelect;
    private RadioGroup priority;
    private String priorityBtnText;
    private int radioBtnSelect;
    private static String ARG_BUNDLE = "KEY";
    private static String s = "addBtn";
    private  MainFragment mainFragment;

    public FragmentView(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_creat_edit_fragment, container, false);
        return view;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        title = view.findViewById(R.id.titleEdit);
        description = view.findViewById(R.id.descriptionEdit);
        dateTime = view.findViewById(R.id.editTime);
        Info info = new Info();
        if(getArguments() != null){
            info.setId(arrivedData());
            s = "edit";
        }
        else{
            s = "addBtn";
        }
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
        repeat = view.findViewById(R.id.repeatView);
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup layout = view.findViewById(R.id.repeatViewGroup);
                if((repeat).isChecked()){
                    layout.setVisibility(View.VISIBLE);
                    radioBtnSelect = layout.getCheckedRadioButtonId();
                }
                else {
                    layout.setVisibility(View.GONE);
                    layout.clearCheck();
                }
            }
        });
        save(info);
    }

    private void timePicker(int hour, int minute){
        try {
            previousDate = dateTime.getText().toString();
        }catch (NullPointerException n){}
        time = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
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
        date = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateTime.setText(String.format("%02d/%02d/%02d",dayOfMonth, month + 1, year));
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

    public void save(final Info info){
        Button save = view.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check()) {
                    info.setTitle(title.getText().toString());
                    info.setDescription(description.getText().toString());
                    info.setDate(dateTime.getText().toString());
                    info.setPriority(priorityBtnText);
                    mainFragment = MainFragment.newInstance(info, s);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.placeHolder, mainFragment);
                    fragmentTransaction.commit();
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
            Toast.makeText(getContext(), "Please select thi priority", Toast.LENGTH_LONG).show();
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

    public static FragmentView newInstance(Info info) {
        FragmentView fragment = new FragmentView();
        Bundle args = new Bundle();
        args.putParcelable(ARG_BUNDLE, info);
        fragment.setArguments(args);
        return fragment;
    }

    private boolean isEmpty(TextInputEditText editText){
        return TextUtils.isEmpty(editText.getText().toString());
    }

    private boolean isEmpty(String priority){
        return TextUtils.isEmpty(priority);
    }

    private void radioGroupSelect(){
        try {
            priority = view.findViewById(R.id.priorityGroup);
            priorityBtnSelect = priority.getCheckedRadioButtonId();
            RadioButton btn = view.findViewById(priorityBtnSelect);
            priorityBtnText = btn.getText().toString();
        }
        catch (NullPointerException n){

        }
    }
    public String arrivedData(){
        Info info = getArguments().getParcelable(ARG_BUNDLE);
        title.setText(info.getTitle());
        description.setText(info.getDescription());
        dateTime.setText(info.getDate());
        String k = info.getId();
        return k;
    }
}
