package uk.ac.rgu.rgtodu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import uk.ac.rgu.rgtodu.data.Task;
import uk.ac.rgu.rgtodu.data.TaskPriority;
import uk.ac.rgu.rgtodu.data.TaskRepository;
import uk.ac.rgu.rgtodu.data.TaskScheduleFor;

public class ViewTaskActivity extends AppCompatActivity implements View.OnClickListener {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        //  Check the Intent that launched this Activity
        Intent launcher = getIntent();
        // Check if there there details of the Task to be displayed?
        if (launcher.hasExtra(TaskRecyclerViewAdapter.EXTRA_TASK_NAME)){
            // There are (or at least a task name) so instantiate this.task with those details
            task = new Task();
            task.setName(launcher.getStringExtra(TaskRecyclerViewAdapter.EXTRA_TASK_NAME));
            task.setDescription(launcher.getStringExtra(TaskRecyclerViewAdapter.EXTRA_TASK_DESCRIPTION));
            // Intent#getLongExtra requires a default value in case there is no extra with that name
            // a deadline of 0 will be 1 Jan 1970 - so user should know something's gone wrong!
            // Its ok error handling, but could be improved
            task.setDeadline(new Date(
                    launcher.getLongExtra(TaskRecyclerViewAdapter.EXTRA_TASK_DEADLINE, 0)));
            // Intent#getIntExtra also requires a default value for the same reason,
            // No task should have -1 hours remaining - again ok error handling but could be improved
            task.setHoursToCompletion(
                    launcher.getIntExtra(TaskRecyclerViewAdapter.EXTRA_TASK_HOUR_REMAINING, -1));
            // restor the enums from the Strings passed in the intent
            task.setPriority(
                    TaskPriority.valueOf(
                            launcher.getStringExtra(TaskRecyclerViewAdapter.EXTRA_TASK_HOUR_PRIORITY)));
            task.setScheduleFor(
                    TaskScheduleFor.valueOf(
                            launcher.getStringExtra(TaskRecyclerViewAdapter.EXTRA_TASK_HOUR_SCHEDULED)));
        }

        // restore the instance state if there is one to restore
        else if (savedInstanceState != null) {
            // recreate the task
            task = new Task();
            task.setName(savedInstanceState.getString(KEY_TASK_NAME));
            task.setDescription(savedInstanceState.getString(KEY_TASK_DESCRIPTION));
            task.setHoursToCompletion(savedInstanceState.getInt(KEY_TASK_HOURS));
            task.setScheduleFor(TaskScheduleFor.valueOf(savedInstanceState.getString(KEY_TASK_SCHEDULED_FOR)));
            task.setPriority(TaskPriority.valueOf(savedInstanceState.getString(KEY_TASK_PRIORITY)));

            long deadline = savedInstanceState.getLong(KEY_TASK_DEADLINE);
            task.setDeadline(new Date(deadline));
        }
        else {
            task = TaskRepository.getRepository(getApplicationContext()).getTask();
        }
        displayTask(task);

        // add click listeners for the do task and add ot aclendar buttons
        ((Button)findViewById(R.id.btn_view_do_task)).setOnClickListener(this);
    }


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
    public void onClick(View view) {
        if (view.getId() == R.id.btn_view_do_task){
            // launch the clock app with a timer for 20 minutes
            Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER);
            intent.putExtra(AlarmClock.EXTRA_LENGTH, 1200);
            intent.putExtra(AlarmClock.EXTRA_MESSAGE, task.getName());
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
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