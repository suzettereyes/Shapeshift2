package com.example.fsa.shapeshift2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Eddie on 4/21/18.
 */

public class ListViewActivity extends AppCompatActivity {

    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelperActivity.DATE,
            DatabaseHelperActivity.BMI, DatabaseHelperActivity.BMR, DatabaseHelperActivity.WHTR,
            DatabaseHelperActivity.TDEE  };

    final int[] to = new int[] { R.id.editText_date, R.id.bmiResult, R.id.bmrResult, R.id.whtrResult, R.id.tdeeResult};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listview);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_results, cursor, from, to, 0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        // OnCLickListener For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView dateTextView = (TextView) view.findViewById(R.id.dateTextView);
                TextView bmiTextView = (TextView) view.findViewById(R.id.bmiResult);
                TextView bmrTextView = (TextView) view.findViewById(R.id.bmrResult);
                TextView whtrTextView = (TextView) view.findViewById(R.id.whtrResult);
                TextView tdeeTextView = (TextView) view.findViewById(R.id.tdeeResult);
                String date = dateTextView.getText().toString();
                String bmi = bmiTextView.getText().toString();
                String bmr = bmrTextView.getText().toString();
                String whtr = whtrTextView.getText().toString();
                String tdee = tdeeTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyDateActivity.class);
                modify_intent.putExtra("date", date);
                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_bn) {

            Intent add_mem = new Intent(this, InputCalcActivity.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }

}
