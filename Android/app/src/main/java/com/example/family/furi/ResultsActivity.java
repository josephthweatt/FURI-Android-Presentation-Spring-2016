package com.example.family.furi;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Family on 12/28/2015.
 */
public class ResultsActivity extends AppCompatActivity {

    TextView bestOption;

    private Button modelRanks;
    private Button reviseSys;
    private Button saveSys;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sys_results_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setBestOption();

        //transitions to the model rankings
        modelRanks = (Button) findViewById(R.id.ranking_button);
        modelRanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(ResultsActivity.this, SysRankingActivity.class);
                startActivity(newIntent);
            }
        });

        //returns to find_sys_activity
        //!this return may require the origional inputs to be carried with it!
        reviseSys = (Button) findViewById(R.id.revise_sys);
        reviseSys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(ResultsActivity.this, FindSysActivity.class);
                startActivity(newIntent);
            }
        });

        //transfers data to system history
        //!will mean that inputs stored form find_system will be deleted (include later!!)
        saveSys = (Button) findViewById(R.id.save_button);
        saveSys.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int duration = Toast.LENGTH_SHORT;
                String message = "System data saved to My Systems";
                Toast toast = Toast.makeText(getApplicationContext(), message, duration);
                toast.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //sets the data for the best found option (dummy data currently used)
    public void setBestOption() {
        String model = "A\n";
        String power = "B\n";
        String price = "C";

        bestOption = (TextView) findViewById(R.id.best_option_data);
        bestOption.setText(model + power + price);
    }
}
