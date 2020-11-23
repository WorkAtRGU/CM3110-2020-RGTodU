package uk.ac.rgu.rgtodu.data;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }
    @TypeConverter
         public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static String fromScheduleFor(TaskScheduleFor value) {
        return value == null ? null : value.getLabel();
    }
    @TypeConverter
    public static TaskScheduleFor stringToScheduleFor(String value) {
        return value == null ? null : TaskScheduleFor.valueOf(value);
    }

    @TypeConverter
    public static String fromTaskPriority(TaskPriority value) {
        return value == null ? null : value.getLabel();
    }
    @TypeConverter
    public static TaskPriority stringToTaskPriority(String value) {
        return value == null ? null : TaskPriority.valueOf(value);
    }
}
