package com.example.mynotes.activity;

import android.os.Bundle;
import android.text.format.DateFormat;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.mynotes.R;
import com.example.mynotes.databinding.ActivityInsertNoteBinding;
import com.example.mynotes.entity.EntityNotes;
import com.example.mynotes.viewmodel.NotesViewModel;

import java.util.Date;

public class InsertNoteActivity extends AppCompatActivity {
    ActivityInsertNoteBinding binding;
    String title, subtitle, notesData;
    NotesViewModel notesViewModel;
    String priority="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNoteBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);//view model class se insert update methods ko access krne k lie
        binding.priorityGreenInsertNote.setOnClickListener(v -> {
            binding.priorityGreenInsertNote.setImageResource(R.drawable.baseline_done_24);
            binding.priorityYellowInsertNote.setImageResource(0);//done ka icon agr yellow pe ho to remove ho jayega
            binding.priorityRedInsertNote.setImageResource(0);//done ka icon agr red pe ho to remove ho jayega
            priority = "1";
        });
        binding.priorityYellowInsertNote.setOnClickListener(v -> {
            binding.priorityYellowInsertNote.setImageResource(R.drawable.baseline_done_24);
            binding.priorityGreenInsertNote.setImageResource(0);
            binding.priorityRedInsertNote.setImageResource(0);
            priority = "2";
        });
        binding.priorityRedInsertNote.setOnClickListener(v -> {
            binding.priorityRedInsertNote.setImageResource(R.drawable.baseline_done_24);
            binding.priorityYellowInsertNote.setImageResource(0);
            binding.priorityGreenInsertNote.setImageResource(0);
            priority = "3";
        });
        binding.fbtnDoneNotes.setOnClickListener(v -> {
            title = binding.edtTitleInsertNote.getText().toString();
            subtitle = binding.edtSubtitleInsertNote.getText().toString();
            notesData = binding.edtNotesDataInsertNote.getText().toString();
            createNotes(title,subtitle,notesData);
        });
    }

    private void createNotes(String title, String subtitle, String notesData) {
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d, yyyy",date.getTime());
        EntityNotes entityNotes = new EntityNotes();
        entityNotes.title = title;
        entityNotes.subtitle = subtitle;
        entityNotes.notes = notesData;
        entityNotes.date = sequence.toString();
        entityNotes.priority = priority;
        notesViewModel.insertNote(entityNotes);
        finish();
    }
}