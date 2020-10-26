package uk.ac.rgu.rgtodu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import uk.ac.rgu.rgtodu.data.Task;
import uk.ac.rgu.rgtodu.data.TaskRepository;

public class TasksListViewActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list_view);

        // get 1000 Tasks and create a List of Strings from these to be displayed
        List<Task> tasks = TaskRepository.getRepository(getApplicationContext()).getTasks(1000);

        // get the ListView that they will be displayed in
        ListView lv_Tasks = findViewById(R.id.lv_tasks);

        // Create our own adapter to use to display the Tasks in the ListView
        TaskListItemViewAdapter adapter = new TaskListItemViewAdapter(
            getApplicationContext(),
            R.layout.task_list_view_item,
            tasks);

        // Associate the Adapter with the ListView
        lv_Tasks.setAdapter(adapter);

        // set a listener for when the user clicks on a row in the ListView
//        lv_Tasks.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Log.d("TASKS", "ListView item at " + position + " clicked");
        Task taskStr = (Task)adapterView.getItemAtPosition(position);
        Log.d("TASKS", "ListView item is " + taskStr.getName());
    }


//        List<String> taskStrs = new ArrayList<String>();
//        for (Task task : tasks){
//            taskStrs.add(task.getName());
//        }

    // Create an ArrayAdapter that will adapt the Strings for display
    // in the ListView
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                getApplicationContext(),
//                android.R.layout.simple_list_item_1,
//                taskStrs
//        );




//     //
//
//
//
//
//



}