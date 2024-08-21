package com.example.mynotes.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mynotes.dao.NotesDao;
import com.example.mynotes.entity.EntityNotes;
import com.example.mynotes.repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    public NotesRepository notesRepository;
    public LiveData<List<EntityNotes>> getAllNote;


    public NotesViewModel(@NonNull Application application) {
        super(application);
        notesRepository = new NotesRepository(application);
        getAllNote = notesRepository.getAllNotes;

    }
    public void insertNote(EntityNotes entityNotes){
        notesRepository.insertNotes(entityNotes);
    }
    public void updateNote(EntityNotes entityNotes){
        notesRepository.updateNotes(entityNotes);
    }
    public void deleteNote(int id){
        notesRepository.deleteNotes(id);
    }
}
