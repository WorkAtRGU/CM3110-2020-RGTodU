package uk.ac.rgu.rgtodu.data;

public enum TaskScheduleFor {
    TOMORROW("TOMORROW"),
    NEXT_WEEK("NEXT_WEEK"),
    NEXT_MONTH("NEXT_MONTH"),
    NEVER("NEVER");

    private String label;

    private TaskScheduleFor(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }
}
