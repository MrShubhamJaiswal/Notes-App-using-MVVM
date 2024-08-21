package com.example.mynotes.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mynotes.entity.EntityNotes;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {
    @Query("SELECT * FROM tbl_notes")
    LiveData<List<EntityNotes>> getAllNotes();

    @Insert
    void insertNotes(EntityNotes...entityNotes);

    @Update
    void updateNotes(EntityNotes entityNotes);

    @Query("DELETE FROM tbl_notes WHERE id=:id")
    void deleteNotes(int id);
}
