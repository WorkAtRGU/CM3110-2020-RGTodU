package uk.ac.rgu.rgtodu.data;

import java.util.Date;

/**
 * A piece of work that needs to be done
 * @author David Corsar
 */
public class Task {

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", scheduleFor=" + scheduleFor +
                ", hoursToCompletion=" + hoursToCompletion +
                ", deadline=" + deadline +
                '}';
    }

    // reference name for the task
    private String name;
    // some more information about it
    private String description;
    // how important the task is
    private TaskPriority priority;
    // when it should be scheduled for
    private TaskScheduleFor scheduleFor;
    // estimate of how many hours it will take to complete
    private int hoursToCompletion;
    // when the task needs to be completed by
    private Date deadline;

    public String getName() {
        return name;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskScheduleFor getScheduleFor() {
        return scheduleFor;
    }

    public void setScheduleFor(TaskScheduleFor scheduleFor) {
        this.scheduleFor = scheduleFor;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHoursToCompletion() {
        return hoursToCompletion;
    }

    public void setHoursToCompletion(int hoursToCompletion) {
        this.hoursToCompletion = hoursToCompletion;
    }


}
