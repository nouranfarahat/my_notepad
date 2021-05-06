package com.example.notepad.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.Callbacks.NoteEventListener;
import com.example.notepad.NoteDate;
import com.example.notepad.R;
import com.example.notepad.model.Note;

import java.util.ArrayList;
import java.util.List;

public class note_adapter extends RecyclerView.Adapter<note_adapter.noteViewHolder> {

    List<Note>notes;
    NoteEventListener noteEventListener;

    public void setOnNoteSelected(NoteEventListener noteEventListener) {
        this.noteEventListener = noteEventListener;
    }

    public note_adapter(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public noteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.note_layout,null,false);
        return new noteViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull noteViewHolder holder, int position) {
        holder.onBind(notes.get(position),position);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class noteViewHolder extends RecyclerView.ViewHolder
    {
        TextView noteTv;
        TextView dateTv;
        public noteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTv=itemView.findViewById(R.id.note_tv);
            dateTv=itemView.findViewById(R.id.note_date_tv);
        }

        void onBind(final Note item,final int position)
        {
            noteTv.setText(item.getNoteDescription());
            dateTv.setText(NoteDate.dateFromLong(item.getNoteDate()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    noteEventListener.onNoteClick(item);
                    ///noteEventListener.onDeleteClick(item);
                }
            });


        }
    }



}
