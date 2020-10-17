package uk.ac.rgu.rgtodu;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import uk.ac.rgu.rgtodu.data.Task;

/**
 * Adapter class for displaying {@link Task}s in an {@link android.widget.ListView}.
 */
public class TaskListItemViewAdapter extends ArrayAdapter<Task>  {

    // the context that the adapter is operating in
    private Context context;
    // the tasks being displayed
    private List<Task> tasks;

    public TaskListItemViewAdapter(@NonNull Context context, int resource,  @NonNull List<Task> objects) {
        super(context, resource, objects);
        this.context = context;
        this.tasks = objects;
    }

    private int count = 0;

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // get the View that will be used to display the Task
        View itemView = convertView;
        if (itemView == null){
            itemView = LayoutInflater.from(this.context).inflate(R.layout.task_list_view_item, parent, false);
            Log.d("INFLATE ", String.valueOf(++count));
        }

        // get the Task that is being displayed
        Task task = this.tasks.get(position);

        // Now update itemView to display task
        TextView tv_taskName = itemView.findViewById(R.id.tv_taskListItemName);
        tv_taskName.setText(task.getName());

        TextView tv_taskHours = itemView.findViewById(R.id.tv_taskListItemHours);
        String msg = context.getString(R.string.tv_taskListItemHours, task.getHoursToCompletion());
        tv_taskHours.setText(msg);

        Button btn = itemView.findViewById(R.id.btn_taskListItemView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task task = tasks.get(position);
                Log.d("TASK_CLICKED", task.getName());
            }
        });

        return itemView;
    }
}