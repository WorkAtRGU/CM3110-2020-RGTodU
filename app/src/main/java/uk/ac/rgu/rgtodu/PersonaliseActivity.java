package uk.ac.rgu.rgtodu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PersonaliseActivity extends AppCompatActivity implements View.OnClickListener {


    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalise);

        sharedPreferences = getSharedPreferences(getString(R.string.shared_prefs_file), MODE_PRIVATE);

        // add click listener to the save preferences button
        Button btnSave = findViewById(R.id.btn_personalise_save);
        btnSave.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EditText personaliseName = findViewById(R.id.et_personalise_name);
        String persaonliseNameValue = String.valueOf(personaliseName.getText());

        // get the SharedPreference Editors
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(getString(R.string.shared_pref_personalised_name));

        editor.putString(getString(R.string.shared_pref_personalised_name), persaonliseNameValue);

        editor.apply();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_home_personalise){

        }
    }
}