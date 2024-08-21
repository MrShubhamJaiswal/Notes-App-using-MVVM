package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.activity.InsertNoteActivity;
import com.example.mynotes.adapter.NotesAdapter;
import com.example.mynotes.viewmodel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fbtnCreateNewNotes;
    NotesViewModel notesViewModel;
    RecyclerView rcyNotesMain;
    NotesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fbtnCreateNewNotes = findViewById(R.id.fbtnCreateNewNotes);
        rcyNotesMain = findViewById(R.id.rcyNotesMain);
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);//view model class se insert update methods ko access krne k lie
        fbtnCreateNewNotes.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), InsertNoteActivity.class));
        });
        notesViewModel.getAllNote.observe(this,entityNotes -> {
            rcyNotesMain.setLayoutManager(new GridLayoutManager(this,2));
            adapter = new NotesAdapter(MainActivity.this,entityNotes);
            rcyNotesMain.setAdapter(adapter);
        });
    }
}