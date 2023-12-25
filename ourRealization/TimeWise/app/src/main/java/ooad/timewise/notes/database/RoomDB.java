package ooad.timewise.notes.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ooad.timewise.notes.Note;

@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static RoomDB dataBase;
    private final static String DATABASE_NAME = "Notes";
    public synchronized  static RoomDB getInstance(Context context){
        if (dataBase == null){
            dataBase = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dataBase;
    }
    public abstract MainDAO mainDao();
}
