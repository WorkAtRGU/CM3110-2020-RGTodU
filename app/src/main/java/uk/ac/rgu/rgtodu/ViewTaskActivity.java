package uk.ac.rgu.rgtodu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import uk.ac.rgu.rgtodu.data.Task;
import uk.ac.rgu.rgtodu.data.TaskPriority;
import uk.ac.rgu.rgtodu.data.TaskRegistry;
import uk.ac.rgu.rgtodu.data.TaskScheduleFor;

public class ViewTaskActivity extends AppCompatActivity {

    // Key values for storing instance state
    private static final String KEY_TASK_NAME = "name";
    private static final String KEY_TASK_HOURS = "hours";
    private static final String KEY_TASK_DEADLINE = "deadline";
    private static final String KEY_TASK_DESCRIPTION = "description";
    private static final String KEY_TASK_PRIORITY = "priority";
    private static final String KEY_TASK_SCHEDULED_FOR = "scheduledFor";

    // the current Task that is being displayed
    private Task task;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // store all the details of the task
        outState.putString(KEY_TASK_NAME, task.getName());
        outState.putInt(KEY_TASK_HOURS, task.getHoursToCompletion());
        outState.putLong(KEY_TASK_DEADLINE, task.getDeadline().getTime());
        outState.putString(KEY_TASK_DESCRIPTION, task.getDescription());
        outState.putString(KEY_TASK_PRIORITY, task.getPriority().getLabel());
        outState.putString(KEY_TASK_SCHEDULED_FOR, task.getScheduleFor().getLabel());
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        // restore the instance state if there is one to restore
        if (savedInstanceState != null) {
            // recreate the task
            task = new Task();
            task.setName(savedInstanceState.getString(KEY_TASK_NAME));
            task.setDescription(savedInstanceState.getString(KEY_TASK_DESCRIPTION));
            task.setHoursToCompletion(savedInstanceState.getInt(KEY_TASK_HOURS));
            task.setScheduleFor(TaskScheduleFor.valueOf(savedInstanceState.getString(KEY_TASK_SCHEDULED_FOR)));
            task.setPriority(TaskPriority.valueOf(savedInstanceState.getString(KEY_TASK_PRIORITY)));

            long deadline = savedInstanceState.getLong(KEY_TASK_DEADLINE);
            task.setDeadline(new Date(deadline));
        } else {
            task = TaskRegistry.getRepository(getApplicationContext()).getTask();
        }
        displayTask(task);
    }


    /**
     * Updates the UI to display details of task
     * @param task
     */
    private void displayTask(Task task) {
        // display the task name
        TextView tv_viewTaskName = findViewById(R.id.tv_viewTaskName);
        tv_viewTaskName.setText(task.getName());

        // display the task description
        TextView tv_viewTaskDescription = findViewById(R.id.tv_viewTaskDescription);
        tv_viewTaskDescription.setText(task.getDescription());

        // display the task priority
        TextView tv_taskPriority = findViewById(R.id.tv_viewPriorityValue);
        TaskPriority priority = task.getPriority();
        switch (priority){
            case LOW: tv_taskPriority.setText(getString(R.string.rb_low));
            break;
            case MEDIUM:tv_taskPriority.setText(getString(R.string.rb_medium));
                break;
            case HIGH:tv_taskPriority.setText(getString(R.string.rb_high));
                break;
        }

        // display the task scheduled for
        TextView tv_taskSchedule = findViewById(R.id.tv_viewScheduleForValue);
        TaskScheduleFor scheduleFor = task.getScheduleFor();
        String[] scheduleForArray = getResources().getStringArray(R.array.edit_task_schedule_for);
        switch (scheduleFor){
            case TOMORROW:
                tv_taskSchedule.setText(scheduleForArray[0]);
                break;
            case NEXT_WEEK:
                tv_taskSchedule.setText(scheduleForArray[1]);
                break;
            case NEXT_MONTH:
                tv_taskSchedule.setText(scheduleForArray[2]);
                break;
            case NEVER:
                tv_taskSchedule.setText(scheduleForArray[3]);
                break;
        }

        // display the task hours to completion
        TextView tv_hours = findViewById(R.id.tv_viewHoursCompleteValue);
        tv_hours.setText(getString(R.string.tv_viewHoursCompleteValue,
                task.getHoursToCompletion()));

        // display the task deadline
        TextView tv_dateValue = findViewById(R.id.tv_viewTaskDeadlineValue);
        DateFormat format = SimpleDateFormat.getDateInstance();
        String formattedDate = format.format(task.getDeadline());
        tv_dateValue.setText(formattedDate);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}