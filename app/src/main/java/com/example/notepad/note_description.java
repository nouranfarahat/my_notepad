package com.example.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notepad.Callbacks.NoteEventListener;
import com.example.notepad.DB.NoteDatabase;
import com.example.notepad.model.Note;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class note_description extends AppCompatActivity  {

    private EditText inputEditText;
    private NoteDatabase noteDatabase;
    private Button delete_note;
    public static final String Note_Extra_Key="note_id";
    public Note temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_description_layout);
        inputEditText=findViewById(R.id.note_et);
        noteDatabase=NoteDatabase.getData(this);
        delete_note=findViewById(R.id.bottom_nav);
        final String input=inputEditText.getText().toString();

        openNoteOnClick();

        delete_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog(view);
            }
        });


        inputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String input=inputEditText.getText().toString().trim();
                if(input.isEmpty())
                {
                    delete_note.setVisibility(View.GONE);
                }
                else
                    delete_note.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.save,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.save_note) {
            onSaveNote();
        }
        return super.onOptionsItemSelected(item);
    }

    private void onSaveNote() {
        String text=inputEditText.getText().toString();
        if(!text.isEmpty())
        {
            long date=new Date().getTime();
            temp.setNoteDescription(text);
            temp.setNoteDate(date);
            noteDatabase.noteOperations().insertNote(temp);

            finish();

        }
    }

    public void showAlertDialog(final View view) {
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setMessage("Are you sure you want to delete this note?");
        alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onDeleteClick(temp);
            }
        });
        alert.setNegativeButton("Cancel",null);
        alert.create().show();

    }


    public void onDeleteClick(Note note) {
        noteDatabase.noteOperations().deleteNoteById(note.getId());
        Toast.makeText(note_description.this,"Note deleted",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(note_description.this, MainActivity.class);
        startActivity(intent);
    }
    public void openNoteOnClick()
    {
        if(getIntent().getExtras()!=null)
        {
            int id=getIntent().getExtras().getInt(Note_Extra_Key,0);
            temp=noteDatabase.noteOperations().getNoteById(id);
            inputEditText.setText(temp.getNoteDescription());
            delete_note.setVisibility(View.VISIBLE); //34an awl ma yd5l tb2a el delete bayna 34an fe text
        }
        else
            temp=new Note();
    }
}