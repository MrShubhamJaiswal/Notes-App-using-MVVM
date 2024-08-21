package com.example.mynotes.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mynotes.dao.NotesDao;
import com.example.mynotes.database.NotesDatabase;
import com.example.mynotes.entity.EntityNotes;

import java.util.List;

public class NotesRepository {
    public NotesDao notesDao;
    public LiveData<List<EntityNotes>> getAllNotes;

    public NotesRepository(Application application){
        NotesDatabase notesDatabase = NotesDatabase.getInstance(application);
        notesDao = notesDatabase.notesDao();
        getAllNotes = notesDao.getAllNotes();
    }

    public void insertNotes(EntityNotes entityNotes){
        notesDao.insertNotes(entityNotes);
    }
    public void updateNotes(EntityNotes entityNotes){
        notesDao.updateNotes(entityNotes);
    }
    public void deleteNotes(int id){
        notesDao.deleteNotes(id);
    }


}
