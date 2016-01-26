package com.example.family.furi;

import android.content.Intent;
import android.support.v4.app.Fragment;
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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Family on 12/28/2015.
 */
public class MySysActivity extends AppCompatActivity {

    private Button view;
    private Button edit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_sys_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.my_sys_container, new PlaceholderFragment()).commit();
        }

        //transitions to ResultsActivity
        view = (Button) findViewById(R.id.view_button);
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //!!Within this block: pass data of the selected system and ranks!!
                Intent intent = new Intent(MySysActivity.this, ResultsActivity.class);
                startActivity(intent);
            }
        });

        //transitions to FindSysActivity
        edit = (Button) findViewById(R.id.edit_button);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //!!Within this block: pass data of selected system into text entries!!
                Intent intent = new Intent(MySysActivity.this, FindSysActivity.class);
                startActivity(intent);
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

    //Populates activity with the Placeholers (will be swapped eventually for real data)
    public static class PlaceholderFragment extends Fragment {

        ArrayAdapter<String> sys0Adapter;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Create some dummy data for the ListView.
            // The String[][] will hold the model, power, price of the system for multiple systems
            String model = "A\n";
            String power = "B\n";
            String price = "C";

            String[] sys0 = {model + power + price};
            List<String> blankData0 = new ArrayList<String>(Arrays.asList(sys0));

            //insert List into an Adapter
            sys0Adapter =
                    new ArrayAdapter<String>(
                            getActivity(), // The current context (this activity)
                            R.layout.list_item_sys, // The name of the layout ID.
                            R.id.sys_text_data, // The ID of the textview to populate.
                            blankData0);

            View rootView = inflater.inflate(R.layout.list_sys_fragment, container, false);

            SeparatedListAdapter multiAdapt
                    = new SeparatedListAdapter(getContext(), R.layout.list_item_sys);
            final ListView listView = (ListView) rootView.findViewById(R.id.listview_sys_data);
            //Strings in addSection are Headers, I will either make headers the name of the
            //system type or take them out entirely (the latter modifies SLA.java)
            for (int i = 1; i <= 10; i++) {
                multiAdapt.addSection("System " + i, sys0Adapter);
            }
            listView.setAdapter(multiAdapt);

            return rootView;
        }
    }
}
