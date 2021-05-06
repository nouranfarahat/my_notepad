package com.example.notepad.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "Note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private  int id;
    @ColumnInfo
    private String noteDescription;
    @ColumnInfo
    private long noteDate;

    public Note() {
    }

    @Ignore
    public Note(String noteDescription, long noteDate) {
        this.noteDescription = noteDescription;
        this.noteDate = noteDate;
    }

    @Ignore
    public Note(int id, String noteDescription, long noteDate) {
        this.id = id;
        this.noteDescription = noteDescription;
        this.noteDate = noteDate;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public long getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(long noteDate) {
        this.noteDate = noteDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
