package com.example.fsa.shapeshift2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Eddie on 4/14/18.
 */

public class CalcResultsActivity extends AppCompatActivity {

    private static final String LOG = "CalcResultsActivity";

    Button add;
    Button delete;
    Button Viewlog;
    Button update;
    ImageButton Cam;
    ImageView CamImage;
    DatabaseHelperActivity myDB;
    EditText date;
    ImageButton bmi;
    ImageButton bmr;
    ImageButton whtr;
    ImageButton tdee;
    private String selectedBMI;
    private int selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_calcresults);

        add = (Button) findViewById(R.id.add_bn);
        delete = (Button) findViewById(R.id.delete_bn);
        Viewlog = (Button) findViewById(R.id.logview_bn);
        Cam = (ImageButton) findViewById(R.id.imageButton_Cam);
        CamImage = (ImageView) findViewById(R.id.imageView_Cam);
        date = (EditText) findViewById(R.id.editText_date);
        myDB = new DatabaseHelperActivity(this);
        bmi = (ImageButton) findViewById(R.id.imageButton_BMI);
        bmr = (ImageButton) findViewById(R.id.imageButton_BMR);
        whtr = (ImageButton) findViewById(R.id.imageButton_WHtR);
        tdee = (ImageButton) findViewById(R.id.imageButton_TDEE);
        //bmiResult = (TextView) findViewById(R.id.bmiResult);
        //bmrResult = (TextView) findViewById(R.id.bmrResult);
        // tddeResult = (TextView) findViewById(R.id.tddeResult);
        //whtrResult = (TextView) findViewById(R.id.whtrResult);
        //AddData();

        //get the intent extra from the ListViewActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedDate = receivedIntent.getIntExtra("date",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedBMI = receivedIntent.getStringExtra("bmi");

        //set the text to show the current selected name
        date.setText(selectedBMI);

        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CalcResultsActivity.this, bmipop.class));
            }
        });

        bmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CalcResultsActivity.this, bmrpop.class));
            }
        });

        whtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CalcResultsActivity.this, whtrpop.class));
            }
        });

        tdee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CalcResultsActivity.this, tdeepop.class));
            }
        });


        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayShowHomeEnabled(true);
        //actionBar.setIcon(R.mipmap.ic_launcher);
        boolean ifMale = getIntent().getExtras().getBoolean("ifMale");
        int age = getIntent().getExtras().getInt("age");
        int height = getIntent().getExtras().getInt("height");
        double waist = getIntent().getExtras().getDouble("waist");
        double mass = getIntent().getExtras().getDouble("mass");
        double bodyfat = getIntent().getExtras().getDouble("bodyfatPercent");
        double heightInMetres = (double) (height * 1.0) / (100);
        int activityLevel = getIntent().getExtras().getInt(("activityLevel"));
        double activityMultiplier = 1;
        double bmi = (mass / (heightInMetres * heightInMetres));

        switch (activityLevel) {
            case 0:
                activityMultiplier = 1.2;
                break;
            case 1:
                activityMultiplier = 1.375;
                break;
            case 2:
                activityMultiplier = 1.550;
                break;
            case (3):
                activityMultiplier = 1.725;
                break;
            default:
                activityMultiplier = 1.2;
                break;
        }

        if (bodyfat == 0) {
            TextView bmiResultText = (TextView) findViewById(R.id.bmiResult);
            TextView bmrResultText = (TextView) findViewById(R.id.bmrResult);
            TextView tddeResultText = (TextView) findViewById(R.id.tdeeResult);
            TextView whtrResultText = (TextView) findViewById(R.id.whtrResult);
            //Mifflin = (10.m + 6.25h - 5.0a) + s
            //m is mass in kg, h is height in cm, a is age in years, s is +5 for males and -151 for females
            int formulaNumber = -1;
            if (ifMale) {
                formulaNumber = 5;
            } else {
                formulaNumber = -151;
            }
            //Women: BMR = 655 + (9.6 x weight in kg) + (1.8 x height in cm) - (4.7 x age in years)

            //Men: BMR = 66 + (13.7 x weight in kg) + (5 x height in cm) - (6.8 x age in years)
            double bmrDouble = (((10) * mass) + (6.25 * height) - (5 * age + formulaNumber));
            double tddeDouble = bmrDouble * activityMultiplier;
            double whtrDouble = (waist / height) * 100;
            int bmr = (int) bmrDouble;
            bmiResultText.setText("" + round(bmi, 2));
            tddeResultText.setText("" + tddeDouble);
            bmrResultText.setText("" + bmr);
            whtrResultText.setText("" + whtrDouble);
        } else {
            TextView bmiResultText = (TextView) findViewById(R.id.bmiResult);
            TextView bmrResultText = (TextView) findViewById(R.id.bmrResult);
            TextView tddeResultText = (TextView) findViewById(R.id.tdeeResult);
            TextView whtrResultText = (TextView) findViewById(R.id.whtrResult);
            //Katch = 370 + (21.6 * LBM)
            double lbm = mass - (((bodyfat * mass) / 100));
            double bmrDouble = (370 + (21.6 * lbm));
            double tddeDouble = bmrDouble * activityMultiplier;
            double whtrDouble = (waist / height) * 100;
            int bmr = (int) bmrDouble;
            tddeResultText.setText("" + tddeDouble);
            bmiResultText.setText("" + round(bmi, 2));
            bmrResultText.setText("" + bmr);
            whtrResultText.setText("" + whtrDouble);
        }

        Viewlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CalcResultsActivity.this, AddCalcResultsActivity.class);
                startActivity(intent);
            }
        });

        Cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                Intent camintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camintent, 0);
            }

        });
/*
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = date.getText().toString();
                if(!item.equals("")){
                    myDB.updateBMI( item, selectedDate, selectedBMI);
                }
                else{
                    toastMessage("You must enter a date");
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.deleteBMI(selectedDate, selectedBMI);
                date.setText("");
                toastMessage("removed from database");
            }
        });
*/
    }

    public void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
/*
    public void insert (String newEntry){
        boolean insertData = myDB.insert(newEntry);

        if (insertData == true){
            Toast.makeText(CalcResultsActivity.this, "Successfully entered data!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText( CalcResultsActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

*/

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap cambitmap = (Bitmap)data.getExtras().get("data");
        CamImage.setImageBitmap(cambitmap);
    }

    public void onReturn(View view)
    {
        Intent swapIntent = new Intent(this, InputCalcActivity.class);
        startActivity(swapIntent);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    ///////////////NEW STUFF FOR SQLITE////////////////////////////////////////////////////////////////////////
    /*
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_log:

                final String date = .getText().toString();
                final String bmi = bmiResult.getText().toString();
                final String bmr = .getText().toString();
                final String whtr = descEditText.getText().toString();
                final String tdee = descEditText.getText().toString();

                dbManager.insert(date, bmi, bmr, whtr, tdee);
                Intent main = new Intent(CalcResultsActivity.this, ListViewActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }
    */

}
