package uk.ac.rgu.rgtodu.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Task task);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTasks(Task... tasks);

    @Query("SELECT * from task ORDER BY deadline ASC")
    public List<Task> getAllTasks();

    @Query("SELECT * from task WHERE name like :searchForName")
    public List<Task> findTaskByName(String searchForName);

    @Update
    public void update(Task task);
    @Update
    public void updateTasks(Task... task);

    @Delete
    public void delete(Task task);
    @Delete
    public void deleteTasks(Task... task);

}
