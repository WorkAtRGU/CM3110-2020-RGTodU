package uk.ac.rgu.rgtodu.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Responsible for converting {@link Task}s to {@link org.json.JSONObject} and back again
 */
public class TaskJsonConverter {


    public List<Task> convertJsonStringToTasks(String jsonString){
        List<Task> tasks = new ArrayList<Task>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject tasksObject = jsonObject.getJSONObject("tasks");
            Iterator<String> taskKeysIter = tasksObject.keys();
            while (taskKeysIter.hasNext()){
                String taskKey = taskKeysIter.next();
                JSONObject taskObject = tasksObject.getJSONObject(taskKey);
                Task task = new Task();
                task.setName(taskObject.getString("name"));
                task.setDescription(taskObject.getString("description"));
                task.setHoursToCompletion(taskObject.getInt("hoursToCompletion"));
                task.setDeadline(new Date(taskObject.getLong("deadline")));
                task.setPriority(TaskPriority.valueOf(taskObject.getString("priority")));
                task.setScheduleFor(TaskScheduleFor.valueOf(taskObject.getString("scheduledFor")));
                tasks.add(task);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tasks;
    }

}
