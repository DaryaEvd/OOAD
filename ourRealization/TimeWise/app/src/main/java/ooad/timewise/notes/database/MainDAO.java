package ooad.timewise.notes.database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ooad.timewise.notes.Note;

@Dao
public interface MainDAO {
    @Insert(onConflict = REPLACE)
    void insert(Note note);

    @Query("SELECT * FROM notes ORDER BY id DESC")
    List<Note> getAll();

    @Query("UPDATE notes SET title=:title, content=:content WHERE id=:id")
    void update(int id, String title, String content);

    @Delete
    void delete(Note note);
}
