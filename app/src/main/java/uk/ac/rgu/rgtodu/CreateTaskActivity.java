package uk.ac.rgu.rgtodu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;

import uk.ac.rgu.rgtodu.data.Task;
import uk.ac.rgu.rgtodu.data.TaskPriority;
import uk.ac.rgu.rgtodu.data.TaskRepository;
import uk.ac.rgu.rgtodu.data.TaskScheduleFor;

public class CreateTaskActivity extends AppCompatActivity implements View.OnClickListener {

    // tag for the log messages
    private static final String TAG = CreateTaskActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        // add action listener to the buttons
        ((Button)findViewById(R.id.btnAction)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnCancel)).setOnClickListener(this);

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb_low:
                if (checked)
                    Log.d(TAG, "low priority");
                    break;
            case R.id.rb_medium:
                if (checked)
                    Log.d(TAG, "medium priority");
                    break;
            case R.id.rb_high:
                if (checked)
                    Log.d(TAG, "high priority");
                    break;
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnAction){
            // get all of the values that have been entered to create a new Task
            String taskName = String.valueOf(((TextView)findViewById(R.id.et_taskName)).getText());
            String taskDesc = String.valueOf(((TextView)findViewById(R.id.et_description)).getText());
            Date taskDeadline = new Date(((CalendarView)findViewById(R.id.cv_deadline)).getDate());
            int hoursToCompletion = Integer.parseInt(String.valueOf(((EditText)findViewById(R.id.et_hoursEstimate)).getText()));

            // create the new task with those values
            Task task = new Task();
            task.setName(taskName);
            task.setDescription(taskDesc);
            task.setHoursToCompletion(hoursToCompletion);
            task.setDeadline(taskDeadline);

            // now set the propority
            int taskPriorityButton = ((RadioGroup)findViewById(R.id.rb_taskPriority)).getCheckedRadioButtonId();
            if (taskPriorityButton == R.id.rb_low)
                task.setPriority(TaskPriority.LOW);
            else if (taskPriorityButton == R.id.rb_medium)
                task.setPriority(TaskPriority.MEDIUM);
            else if (taskPriorityButton == R.id.rb_high)
                task.setPriority(TaskPriority.HIGH);

            // now set the schedule for
            int taskScheduleFor = ((Spinner)findViewById(R.id.sp_scheduleFor)).getSelectedItemPosition();
            if (taskScheduleFor == 0){
                task.setScheduleFor(TaskScheduleFor.TOMORROW);
            } else if (taskScheduleFor == 1){
                task.setScheduleFor(TaskScheduleFor.NEXT_WEEK);
            } else if (taskScheduleFor == 2){
                task.setScheduleFor(TaskScheduleFor.NEXT_MONTH);
            } else if (taskScheduleFor == 3){
                task.setScheduleFor(TaskScheduleFor.NEVER);
            }

            // now do something with the task!

            TaskRepository.getRepository(getApplicationContext()).storeTasks(task);
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        } else if (view.getId() == R.id.btnCancel){
        }

    }
}