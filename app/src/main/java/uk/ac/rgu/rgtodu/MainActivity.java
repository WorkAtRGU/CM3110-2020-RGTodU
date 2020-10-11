package uk.ac.rgu.rgtodu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get an alternative message for the TextView
        String altMsg = getString(R.string.tv_home_msg_add_alternative);

        // get the TextView to change
        TextView tvMsg = findViewById(R.id.tv_home_add_task);

        tvMsg.setText(altMsg);


    }
}
