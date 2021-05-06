package com.example.notepad.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notepad.model.Note;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Update
    void updateNote(Note note);

    @Query("select * from Note_table")
    List<Note> getNotes();

    @Query("select * from Note_table where id =:noteId")
    Note getNoteById(int noteId);

    @Query("delete from Note_table where id =:noteId")
    void deleteNoteById(int noteId);

}
