package uk.ac.rgu.rgtodu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import uk.ac.rgu.rgtodu.data.Task;
import uk.ac.rgu.rgtodu.data.TaskRepository;

public class TasksListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list_view);

        // get the tasks to be displayed
        List<Task> tasks = TaskRepository.getRepository(getApplicationContext()).getTasks(1000);

//        List<String> taskStrings = new ArrayList<String>();
//        for (Task t : tasks){
//            taskStrings.add(t.getName());
//        }

        // get the ListView to display them
        ListView lv_Tasks = findViewById(R.id.lv_tasks);

        // get an list adapter to inform Android how to display each item in the list
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                getApplicationContext(),
//                android.R.layout.simple_list_item_1,
//                taskStrings
//        );

        TaskListItemViewAdapter adapter = new TaskListItemViewAdapter(
                getApplicationContext(),
                R.layout.task_list_view_item,
                tasks
        );

        lv_Tasks.setAdapter(adapter);

        lv_Tasks.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Log.d("TASKS", "ListView item at " + position + " clicked");
        String taskStr = (String)adapterView.getItemAtPosition(position);
        Log.d("TASKS", "ListView item is " + taskStr);
    }
}