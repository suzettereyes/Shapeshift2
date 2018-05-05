package com.example.fsa.shapeshift2;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import java.util.Date;

public class ModifyDateActivity extends AppCompatActivity implements View.OnClickListener {
    //private EditText titleText;
    private Button updateBtn, deleteBtn;
    //private EditText descText;

    //either long or string
    private long date;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify Log");

        setContentView(R.layout.activity_modify_results);

        dbManager = new DBManager(this);
        dbManager.open();

        //titleText = (EditText) findViewById(R.id.subject_edittext);
        //descText = (EditText) findViewById(R.id.description_edittext);
        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        //String name = intent.getStringExtra("title");
        //String desc = intent.getStringExtra("desc");

        //either string or long
        date = String.valueOf(Long.parseLong(date));
        //date = Long.parseLong(date);

        //titleText.setText(name);
        //descText.setText(desc);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                // String bmi = desc.getText().toString();
                // String bmr = descText.getText().toString();
                //String whtr = descText.getText().toString();
                //String tdee = descText.getText().toString();

                dbManager.update(date);
                this.returnHome();
                break;
            case R.id.btn_delete:
                dbManager.delete(date);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ListViewActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
/*
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    */
}