package uk.ac.rgu.rgtodu.data;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * This class provides the single point of truth in the app for {@link TaskRegistry}s, and
 * will in the future deal with downloading, storing, and retrieving them.
 * @author  David Corsar
 */
public class TaskRegistry {

    /**
     * A field for how dates should be formatted before displaying to users
     * with the day of the month as a number, and the month as text
     */
    private static final String DATE_FORMAT = "dd MMM";

    /**
     * The Singleton instance for this repository
     */
    private static TaskRegistry INSTANCE;

    /**
     * The Context that the app is operating within
     */
    private Context context;

    /**
     * Gets the singleton {@link TaskRegistry} for use when managing {@link Task}s
     * in the app.
     * @return The {@link TaskRegistry} to be used for managing {@link Task}s in the app.
     */
    public static TaskRegistry getRepository(Context context){
        if (INSTANCE == null){
            synchronized (TaskRegistry.class) {
                if (INSTANCE == null)
                    INSTANCE = new TaskRegistry();
                INSTANCE.context = context;
            }
        }
        return INSTANCE;
    }

    /**
     * Returns a {@link Task} with randomly generated info.
     * @return a {@link Task} for tomorrow, with randomly generated details.
     */
    public Task getTask(){
        // create a new Task
        Task t = new Task();

        // A random number generator for populating the fields
        Random random = new Random();

        int id = random.nextInt(10000);
        // set the name randomly
        t.setName(String.format("Task %s", id));

        // set the description to some placeholder
        t.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");

        // set estimated hours to completely to be random between 1 and 5
        t.setHoursToCompletion(random.nextInt(5));

        // set the priority randomly
        t.setPriority(TaskPriority.values()[random.nextInt(3)]);

        // set the schedule for randomly
        t.setScheduleFor(TaskScheduleFor.values()[random.nextInt(4)]);

        // schedule it for up to 14 days in the future
        long offset = 1000*60*60*24 * random.nextInt(14);
        t.setDeadline(new Date(System.currentTimeMillis() + offset));

        return t;
    }


}
