package com.example.family.furi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Family on 1/2/2016.
 */
public class SysRankingActivity extends AppCompatActivity {

    Button selectSys;
    ListView selectedSys;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sys_ranking_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.sys_ranking_activity_container, new SysRankFragment()).commit();
        }

        //makes the selected system the users favored option, returns to ResultsActivity
        selectSys = (Button) findViewById(R.id.select_sys);
        selectSys.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //search for the selected system
                //      if not system is found --> mention it
                //make the selected system the one that is presented
                Intent intent = new Intent(SysRankingActivity.this, ResultsActivity.class);
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
    public static class SysRankFragment extends Fragment {

        ArrayAdapter<String> sys0Adapter;
        ArrayAdapter<String> sys1Adapter;
        ArrayAdapter<String> priceAdapter;

        public SysRankFragment() {
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
            ListView listView = (ListView) rootView.findViewById(R.id.listview_sys_data);
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
