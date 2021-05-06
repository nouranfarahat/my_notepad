package com.example.notepad;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.notepad.Callbacks.NoteEventListener;
import com.example.notepad.DB.NoteDatabase;
import com.example.notepad.adapters.note_adapter;
import com.example.notepad.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.notepad.note_description.Note_Extra_Key;

public class MainActivity extends AppCompatActivity implements NoteEventListener{

    RecyclerView recyclerView;
    List<Note> noteList;
    note_adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    NoteDatabase noteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteList=new ArrayList<>();
        noteDatabase=NoteDatabase.getData(this);

        recyclerView=findViewById(R.id.note_rv);
        layoutManager=new LinearLayoutManager(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // init floating button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onAddNewNote();
            }
        });

    }

    private void onAddNewNote() {
        Intent intent=new Intent(getApplicationContext(), note_description.class);
        startActivity(intent);
    }

    private void loadNote() {
        adapter=new note_adapter(noteDatabase.noteOperations().getNotes());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        noteList=noteDatabase.noteOperations().getNotes();
        adapter.setOnNoteSelected(this); ///
        swipeToDeleteHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNote();
    }

    @Override
    public void onNoteClick(Note note) {
        Intent edit=new Intent(this,note_description.class);
        edit.putExtra(Note_Extra_Key,note.getId());
        startActivity(edit);
    }

    private ItemTouchHelper swipeToDeleteHelper=new ItemTouchHelper(new ItemTouchHelper
            .SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if(noteList!=null)
            {
               Note noteSwipe= noteList.get(viewHolder.getAdapterPosition());
               if(noteSwipe!=null)
               {
                   swipeToDelete(noteSwipe,viewHolder);
               }
            }

        }
    } );

    private void swipeToDelete(final Note noteSwipe, final RecyclerView.ViewHolder viewHolder) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage("Delete Note?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        noteDatabase.noteOperations().deleteNoteById(noteSwipe.getId());
                        //noteList.remove(noteSwipe);
                        adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                        Toast.makeText(MainActivity.this,"Note deleted",Toast.LENGTH_SHORT).show();
                        loadNote();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        recyclerView.getAdapter().notifyItemChanged(viewHolder.getAdapterPosition());
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }
}