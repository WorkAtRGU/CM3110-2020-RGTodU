package uk.ac.rgu.rgtodu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(getString(R.string.shared_prefs_file), MODE_PRIVATE);

        String personalisedName = sharedPreferences.getString(getString(R.string.shared_pref_personalised_name), null);

        // get an alternative message for the TextView
        String altMsg = getString(R.string.tv_home_msg_add_alternative);
        if (personalisedName != null){
            altMsg = getString(R.string.tv_home_msg_add_task_personalised, personalisedName);
        }

        // get the TextView to change
        TextView tvMsg = findViewById(R.id.tv_home_add_task);
        tvMsg.setText(altMsg);

        // add click listeners for the view tasks and add task buttons
        ((Button)findViewById(R.id.btn_home_add_task)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_home_view_tasks)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_home_personalise)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // if the View Tasks button is clicked, launch the corresponding Activity
        if (view.getId() == R.id.btn_home_view_tasks) {
            // Create the Intent using this application contect
            // and the Class of the activity to launch
            Intent intent = new Intent(getApplicationContext(),
                    TaskRecyclerViewActivity.class);
            // Start the Activity
            startActivity(intent);
        } else if (view.getId() == R.id.btn_home_add_task){
            // Create an Intent with with application context
            // and the Class of the activity launch
            Intent intent = new Intent(getApplicationContext(), CreateTaskActivity.class);
            // Start the Activity
            startActivity(intent);
        } else if (view.getId() == R.id.btn_home_personalise){
            // Create an Intent with with application context
            // and the Class of the activity launch
            Intent intent = new Intent(getApplicationContext(), PersonaliseActivity.class);
            // Start the Activity
            startActivity(intent);
        }
    }
}
