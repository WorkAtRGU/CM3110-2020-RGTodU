package uk.ac.rgu.rgtodu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import uk.ac.rgu.rgtodu.data.Task;
import uk.ac.rgu.rgtodu.data.TaskRepository;

public class TaskRecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_recycler_view);

        // create 1000 tasks for testing
        List<Task> tasks = TaskRepository.getRepository(getApplicationContext()).getTasks(1000);

        // get the RecycylerView on the UI
        RecyclerView recyclerView = findViewById(R.id.rv_taskRecyclerView);
        // create a new Adapter for the Tasks
        RecyclerView.Adapter adapter = new TaskRecyclerViewAdapter(getApplicationContext(), tasks);
        // set the recycler view's adapter
        recyclerView.setAdapter(adapter);
        // setup the layout manager on the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}