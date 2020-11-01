package uk.ac.rgu.rgtodu;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uk.ac.rgu.rgtodu.data.Task;

public class TaskRecyclerViewAdapter
        extends RecyclerView.Adapter<TaskRecyclerViewAdapter.TaskViewHolder> {

    // Names of Extras used for passing data from this Activity in an Intent
    public final static String EXTRA_TASK_NAME = "uk.ac.rgu.rgtodu.TASK_NAME";
    public final static String EXTRA_TASK_DESCRIPTION = "uk.ac.rgu.rgtodu.TASK_DESCRIPTION";
    public final static String EXTRA_TASK_DEADLINE = "uk.ac.rgu.rgtodu.TASK_DEADLINE";
    public final static String EXTRA_TASK_HOUR_REMAINING = "uk.ac.rgu.rgtodu.TASK_HOURS";
    public final static String EXTRA_TASK_HOUR_SCHEDULED = "uk.ac.rgu.rgtodu.TASK_SCHEDULED";
    public final static String EXTRA_TASK_HOUR_PRIORITY = "uk.ac.rgu.rgtodu.TASK_PRIORITY";


    // member variables for the context the adapter is working in
    private Context context;
    // the data thats going to be displayed
    private List<Task> tasks;

    /**
     * Creates a new {@link TaskRecyclerViewAdapter}
     * @param context that the adapter is working in
     * @param tasks data to be displayed
     */
    public TaskRecyclerViewAdapter(Context context, List<Task> tasks){
        super();
        // initialise the member variables
        this.context = context;
        this.tasks = tasks;
    }


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.task_list_view_item, parent, false);
        TaskViewHolder viewHolder = new TaskViewHolder(itemView, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        // get the task at position
        Task task = this.tasks.get(position);

        // update the task name
        TextView tv_taskName = holder.taskItemView.findViewById(R.id.tv_taskListItemName);
        tv_taskName.setText(task.getName());

        // update the hours
        TextView tv_taskHours = holder.taskItemView.findViewById(R.id.tv_taskListItemHours);
        String msg = context.getString(R.string.tv_taskListItemHours, task.getHoursToCompletion());
        tv_taskHours.setText(msg);
    }

    @Override
    public int getItemCount() {
        return this.tasks.size();
    }

    /**
     * ViewHolder for the RecyclerView that's going to display Tasks
     */
    class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View taskItemView;
        private TaskRecyclerViewAdapter adapter;

        public TaskViewHolder(View taskItemVew, TaskRecyclerViewAdapter adapter) {
            super(taskItemVew);
            this.taskItemView = taskItemVew;
            this.adapter = adapter;
            // add a listener to the button in the taskItemView
            taskItemVew.findViewById(R.id.btn_taskListItemView).setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // get the clicked item's position
            int position = getAdapterPosition();

            // get the task at that position
            Task task = tasks.get(position);

            if (view.getId() == R.id.btn_taskListItemView) {
                // Create the Intent using this application context
                // and the Class of the activity to launch
                Intent intent = new Intent(context,
                        ViewTaskActivity.class);
                intent.putExtra("TASK", task);

                // Add details of the task to be displayed as extras
                intent.putExtra(EXTRA_TASK_NAME, task.getName());
                intent.putExtra(EXTRA_TASK_DESCRIPTION, task.getDescription());
                // Convert the deadline from a java.util.Data to a long
                intent.putExtra(EXTRA_TASK_DEADLINE, task.getDeadline().getTime());
                intent.putExtra(EXTRA_TASK_HOUR_REMAINING, task.getHoursToCompletion());
                // Convert the enums to strings
                intent.putExtra(EXTRA_TASK_HOUR_SCHEDULED, task.getScheduleFor().getLabel());
                intent.putExtra(EXTRA_TASK_HOUR_PRIORITY, task.getPriority().getLabel());
                // The following line is ONLY needed when not starting another Activity from an
                // Activity (we're in TaskRecyclerViewAdapter.java here, not TaskRecyclerViewActivity.java
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                // Start the Activity - here we use the context to do this, again, this is not
                // required if starting an Activity from an Activity.
                context.startActivity(intent);
            }


            else {
                Log.d("TASK_RECYCLER", "user clicked on item " + task.getName());
            }
        }
    }
}
