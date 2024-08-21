package com.example.mynotes.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.MainActivity;
import com.example.mynotes.R;
import com.example.mynotes.activity.UpdateNoteActivity;
import com.example.mynotes.entity.EntityNotes;

import java.util.List;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    MainActivity mainActivity;
    List<EntityNotes> entityNotes;

    public NotesAdapter(MainActivity mainActivity, List<EntityNotes> entityNotes) {
        this.mainActivity = mainActivity;
        this.entityNotes = entityNotes;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.srd_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        EntityNotes notes = entityNotes.get(position);
        holder.titleNotesSrd.setText(notes.title);
        holder.subtitileNotesSrd.setText(notes.subtitle);
        holder.dateNotesSrd.setText(notes.date);
        switch (notes.priority) {
            case "1":
                holder.priorityNotesSrd.setBackgroundResource(R.drawable.shape_green);
                break;
            case "2":
                holder.priorityNotesSrd.setBackgroundResource(R.drawable.shape_yellow);
                break;
            case "3":
                holder.priorityNotesSrd.setBackgroundResource(R.drawable.shape_red);
                break;
        }
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mainActivity, UpdateNoteActivity.class);
            intent.putExtra("id",notes.id);
            intent.putExtra("title",notes.title);
            intent.putExtra("subtitle",notes.subtitle);
            intent.putExtra("notesData",notes.notes);
            intent.putExtra("date",notes.date);
            intent.putExtra("priority",notes.priority);
            mainActivity.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return entityNotes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {
        View priorityNotesSrd;
        TextView titleNotesSrd, subtitileNotesSrd, dateNotesSrd;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            priorityNotesSrd = itemView.findViewById(R.id.priorityNotesSrd);
            titleNotesSrd = itemView.findViewById(R.id.titleNotesSrd);
            subtitileNotesSrd = itemView.findViewById(R.id.subtitileNotesSrd);
            dateNotesSrd = itemView.findViewById(R.id.dateNotesSrd);
        }
    }
}
