package com.example.fsa.shapeshift2;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddCalcResultsActivity extends Activity implements View.OnClickListener {
    private Button addToLog;
    private EditText dateEditText;
    private TextView bmiTextView;
    private TextView bmrTextView;
    private TextView whtrTextView;
    private TextView tdeeTextView;


    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add to Log");

        setContentView(R.layout.activity_calcresults);
        bmiTextView = (TextView) findViewById(R.id.bmiResult);
        bmrTextView = (TextView) findViewById(R.id.bmrResult);
        whtrTextView = (TextView) findViewById(R.id.whtrResult);
        tdeeTextView = (TextView) findViewById(R.id.tdeeResult);
        addToLog = (Button) findViewById(R.id.add_bn);

        dbManager = new DBManager(this);
        dbManager.open();
        addToLog.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_bn:

                final String date = dateEditText.getText().toString();
                final String bmi = bmiTextView.getText().toString();
                final String bmr = bmrTextView.getText().toString();
                final String whtr = whtrTextView.getText().toString();
                final String tdee = tdeeTextView.getText().toString();

                dbManager.insert(bmi, bmr, whtr, tdee); //date shouldnt be added here yet?
                Intent main = new Intent(AddCalcResultsActivity.this, ListViewActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
        }
    }

}
