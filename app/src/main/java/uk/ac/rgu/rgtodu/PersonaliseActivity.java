package uk.ac.rgu.rgtodu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PersonaliseActivity extends AppCompatActivity implements View.OnClickListener,
        SharedPreferences.OnSharedPreferenceChangeListener {

    // member variable for the SharedPreferences
    private SharedPreferences sharedPreferences;
    private SharedPreferences.OnSharedPreferenceChangeListener listener;
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_personalise_name))){
            // TODO do something with the knowledge the personalise name has changed
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalise);

        listener = this;

        // from onClick
        // setup the SharedPreferences
        sharedPreferences = getSharedPreferences(getString(R.string.preferences_file), MODE_PRIVATE);

        // get and update the personalised name if it exists
        String personalisedName =
                sharedPreferences.getString(
                        getString(R.string.pref_personalise_name), // the key
                        "" // default value in case sharedPreferences doesn't have a value for the key
                        );
        EditText etPersonaliseName = findViewById(R.id.et_personalise_name);
        etPersonaliseName.setText(personalisedName);


        // add click listener to the save preferences button
        Button btnSave = findViewById(R.id.btn_personalise_save);
        btnSave.setOnClickListener(this);



    }

    @Override
    protected void onPause() {
        super.onPause();
        // save details of the personalisation
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();

        // store the name to use for referencing to the user
        String prefNameKey = "personalise_name";
        EditText etPersonaliseName = findViewById(R.id.et_personalise_name);
        String etPersonalsiseName = String.valueOf(etPersonaliseName.getText());
        prefsEditor.putString(prefNameKey, etPersonalsiseName);

        // apply the changes sharedPreferences to ensure they are saved
        prefsEditor.apply();




        // store the name to use for the cloud
        String prefCloudKey = getString(R.string.pref_personalise_cloud_details);
        EditText etCloudDetails = findViewById(R.id.et_personalise_cloud_details);
        String etCloudDetailsStr = String.valueOf(etCloudDetails.getText());
        prefsEditor.putString(prefCloudKey, etCloudDetailsStr);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_home_personalise){
            finish();
        }
    }
}