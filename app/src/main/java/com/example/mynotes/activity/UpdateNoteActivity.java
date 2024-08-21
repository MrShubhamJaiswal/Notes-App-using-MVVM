package com.example.mynotes.activity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProviders;

import com.example.mynotes.R;
import com.example.mynotes.databinding.ActivityUpdateNoteBinding;
import com.example.mynotes.entity.EntityNotes;
import com.example.mynotes.viewmodel.NotesViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdateNoteActivity extends AppCompatActivity {
    ActivityUpdateNoteBinding binding;
    String priority="1";
    String sTitle,sSubtitle,sNotes,sPriority;
    String title, subtitle, notesData;
    NotesViewModel notesViewModel;
    int iid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);//view model class se insert update methods ko access krne k lie
        iid = getIntent().getIntExtra("id",0);
        sTitle = getIntent().getStringExtra("title");
        sSubtitle = getIntent().getStringExtra("subtitle");
        sNotes = getIntent().getStringExtra("notesData");
        sPriority = getIntent().getStringExtra("priority");
        binding.edtTitleUpdateNote.setText(sTitle);
        binding.edtSubtitleUpdateNote.setText(sSubtitle);
        binding.edtNotesDataUpdateNote.setText(sNotes);
        switch (sPriority) {
            case "1":
                binding.priorityGreenUpdateNote.setImageResource(R.drawable.baseline_done_24);
                binding.priorityYellowUpdateNote.setImageResource(0);//done ka icon agr yellow pe ho to remove ho jayega
                binding.priorityRedUpdateNote.setImageResource(0);//done ka icon agr red pe ho to
                break;
            case "2":
                binding.priorityYellowUpdateNote.setImageResource(R.drawable.baseline_done_24);
                binding.priorityGreenUpdateNote.setImageResource(0);
                binding.priorityRedUpdateNote.setImageResource(0);
                break;
            case "3":
                binding.priorityRedUpdateNote.setImageResource(R.drawable.baseline_done_24);
                binding.priorityYellowUpdateNote.setImageResource(0);
                binding.priorityGreenUpdateNote.setImageResource(0);
                break;
        }
        binding.priorityGreenUpdateNote.setOnClickListener(v -> {
            binding.priorityGreenUpdateNote.setImageResource(R.drawable.baseline_done_24);
            binding.priorityYellowUpdateNote.setImageResource(0);//done ka icon agr yellow pe ho to remove ho jayega
            binding.priorityRedUpdateNote.setImageResource(0);//done ka icon agr red pe ho to remove ho jayega
            priority = "1";
        });
        binding.priorityYellowUpdateNote.setOnClickListener(v -> {
            binding.priorityYellowUpdateNote.setImageResource(R.drawable.baseline_done_24);
            binding.priorityGreenUpdateNote.setImageResource(0);
            binding.priorityRedUpdateNote.setImageResource(0);
            priority = "2";
        });
        binding.priorityRedUpdateNote.setOnClickListener(v -> {
            binding.priorityRedUpdateNote.setImageResource(R.drawable.baseline_done_24);
            binding.priorityYellowUpdateNote.setImageResource(0);
            binding.priorityGreenUpdateNote.setImageResource(0);
            priority = "3";
        });
        binding.fbtnUpdateNotes.setOnClickListener(v -> {
            title = binding.edtTitleUpdateNote.getText().toString();
            subtitle = binding.edtSubtitleUpdateNote.getText().toString();
            notesData = binding.edtNotesDataUpdateNote.getText().toString();
            updateNotes(title,subtitle,notesData);
        });

    }

    private void updateNotes(String title, String subtitle, String notesData) {
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d, yyyy",date.getTime());
        EntityNotes updateNotes = new EntityNotes();
        updateNotes.id = iid;
        updateNotes.title = title;
        updateNotes.subtitle = subtitle;
        updateNotes.notes = notesData;
        updateNotes.date = sequence.toString();
        updateNotes.priority = priority;
        notesViewModel.updateNote(updateNotes);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_notes,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.deleteNote){
            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdateNoteActivity.this,R.style.BottomSheetStyle);
            View view = LayoutInflater.from(UpdateNoteActivity.this).inflate(R.layout.delete_bottom_sheet,(LinearLayout)findViewById(R.id.bottom_sheet));
            sheetDialog.setContentView(view);
            TextView yes = view.findViewById(R.id.tv_yes_delete);
            TextView no = view.findViewById(R.id.tv_delete_no);
            yes.setOnClickListener(v -> {
                notesViewModel.deleteNote(iid);
                finish();
            });
            no.setOnClickListener(v -> {
                sheetDialog.dismiss();
            });
            sheetDialog.show();
        }
        return true;
    }
}