package com.example.family.furi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button findSys;
    private Button mySys;
    private Button export;
    private EditText email;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        //transition to find_sys_activity
        findSys = (Button) findViewById(R.id.findSys);
        findSys.setOnClickListener(new View.OnClickListener() {
            public void onClick(View args) {
                Intent findIntent = new Intent(MainActivity.this, FindSysActivity.class);
                startActivity(findIntent);
            }
        });


        //transition to my_sys_activity
        mySys = (Button) findViewById(R.id.my_sys);
        mySys.setOnClickListener(new View.OnClickListener() {
            public void onClick(View args) {
                Intent findIntent = new Intent(MainActivity.this, MySysActivity.class);
                startActivity(findIntent);
            }
        });

        export = (Button) findViewById(R.id.export);
        export.setOnClickListener(new View.OnClickListener() {
            public void onClick(View args) {
                //include process of sending data to the email in the editText box
                //then, delete the email from the editText and show the toast

                email = (EditText) findViewById(R.id.email_entry);
                email.setText("");
                email.setHint(R.string.email_hint);

                int duration = Toast.LENGTH_SHORT;
                String message = "System data exported to email";
                Toast toast = Toast.makeText(getApplicationContext(),message,duration);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
