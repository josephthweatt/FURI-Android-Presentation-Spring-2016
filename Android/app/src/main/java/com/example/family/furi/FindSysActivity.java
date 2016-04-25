package com.example.family.furi;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.example.family.furi.ProductObjects.Goal;
import com.example.family.furi.SystemCreation.SystemManager;

/**
 * Created by Family on 12/27/2015.
 */
public class FindSysActivity extends AppCompatActivity {
    public SystemCreator creation;

    private EditText address;
    private EditText budget;
    private EditText sizeInKW;
    private EditText maxSpace;

    private Button editSys;
    private Button getSys;
    private Button getInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_sys_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        address = (EditText) findViewById(R.id.location_entry);
        budget = (EditText) findViewById(R.id.budget_entry);
        sizeInKW = (EditText) findViewById(R.id.size_in_KW_entry);
        maxSpace = (EditText) findViewById(R.id.available_space_entry);

        //transition to my_sys
        editSys = (Button) findViewById(R.id.edit_prev_sys);
        editSys.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(FindSysActivity.this, MySysActivity.class);
                startActivity(intent);
            }
        });

        //transition to sys_results_activity
        getSys = (Button) findViewById(R.id.get_results);
        getSys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creation = new SystemCreator(getApplicationContext(),
                        address.getText().toString(),
                        Double.parseDouble(budget.getText().toString()),
                        Double.parseDouble(sizeInKW.getText().toString()),
                        Double.parseDouble(maxSpace.getText().toString())
                );
                Intent newIntent = new Intent(FindSysActivity.this, ResultsActivity.class);
                startActivity(newIntent);
            }
        });

        //open Chrome tab to find info
        getInfo = (Button) findViewById(R.id.get_info);
        getInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    String googlePack = "com.android.chrome";
                    PackageManager manager = getPackageManager();
                    Intent intent = manager.getLaunchIntentForPackage(googlePack);
                    if (intent == null) {
                        throw new PackageManager.NameNotFoundException();
                    }
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    startActivity(intent);
                } catch (PackageManager.NameNotFoundException e) { }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
