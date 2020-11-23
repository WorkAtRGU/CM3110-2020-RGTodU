package uk.ac.rgu.rgtodu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import uk.ac.rgu.rgtodu.data.Task;
import uk.ac.rgu.rgtodu.data.TaskJsonConverter;
import uk.ac.rgu.rgtodu.data.TaskRepository;

public class TaskRecyclerViewActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = TaskRecyclerViewActivity.class.getCanonicalName();

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_recycler_view);

        // create 1000 tasks for testing
        List<Task> tasks = new ArrayList<Task>();//TaskRepository.getRepository(getApplicationContext()).getTasks(1000);

        // get the RecycylerView on the UI
        recyclerView = findViewById(R.id.rv_taskRecyclerView);
        // setup the layout manager on the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

               // add onClickListener to Sync with Cloud button to, well, sync from Firebase
        ((Button)findViewById(R.id.btn_rv_sync_cloud)).setOnClickListener(this);
    }



    /**
     * Returns a List containing all of the Tasks retrieved from the cloud storage.
     * @return A List of Task Objects
     */
    public void getTasksFromCloud(){
        String url = "https://cm3110-2020.firebaseio.com/dcorsar.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Success " + response);
                TaskJsonConverter converter = new TaskJsonConverter();
                List<Task> tasks = converter.convertJsonStringToTasks(response);
                RecyclerView.Adapter adapter = new TaskRecyclerViewAdapter(getApplicationContext(), tasks);
                // set the recycler view's adapter
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error" + error.getLocalizedMessage());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_rv_sync_cloud){
            getTasksFromCloud();
        }
    }
}