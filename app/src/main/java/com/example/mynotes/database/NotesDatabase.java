package com.example.mynotes.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mynotes.dao.NotesDao;
import com.example.mynotes.entity.EntityNotes;

@Database(entities = EntityNotes.class,exportSchema = false,version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    public static final String DB_NAME = "db_notes";
    public static NotesDatabase instance;
    public abstract NotesDao notesDao();

    public static synchronized NotesDatabase getInstance(Context context){
     if (instance==null){
         instance = Room.databaseBuilder(context,NotesDatabase.class,DB_NAME)
                 .fallbackToDestructiveMigration()
                 .allowMainThreadQueries()
                 .build();
     }
     return instance;
    }
}
