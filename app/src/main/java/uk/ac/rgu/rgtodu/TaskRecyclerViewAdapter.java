package uk.ac.rgu.rgtodu;

import android.content.Context;
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
                // do something with task
                Log.d("TASK_RECYCLER", "user clicked on button " + task.getName());
            } else {
                Log.d("TASK_RECYCLER", "user clicked on item " + task.getName());
            }
        }
    }
}
