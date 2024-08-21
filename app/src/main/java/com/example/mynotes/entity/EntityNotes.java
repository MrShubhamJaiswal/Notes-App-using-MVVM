package com.example.mynotes.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_notes")
public class EntityNotes {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "subtitle")
    public String subtitle;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "notes")
    public String notes;

    @ColumnInfo(name = "priority")
    public String priority;

}
