package uk.ac.rgu.rgtodu.data;

public class Task {

    private String name;
    private String description;
    private TaskPriority priority;
    private TaskScheduleFor scheduleFor;
    private int hoursToCompletion;
    private long deadline;

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

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
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
