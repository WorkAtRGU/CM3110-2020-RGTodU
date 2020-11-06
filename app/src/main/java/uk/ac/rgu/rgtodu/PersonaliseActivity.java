package uk.ac.rgu.rgtodu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PersonaliseActivity extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalise);

        // add click listener to the save preferences button
        Button btnSave = findViewById(R.id.btn_personalise_save);
        btnSave.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_home_personalise){
            // save details of the personalisation
        }
    }
}