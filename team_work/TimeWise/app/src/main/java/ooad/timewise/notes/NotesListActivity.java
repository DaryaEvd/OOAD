package ooad.timewise.notes;

import static ooad.timewise.ActivitiesUtils.switchToActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ooad.timewise.R;
import ooad.timewise.StartPageActivity;
import ooad.timewise.notes.database.RoomDB;
import ooad.timewise.notes.adapter.NotesListAdapter;

public class NotesListActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    Note selectedNote;
    RecyclerView recyclerView;
    NotesListAdapter notesListAdapter;
    Button add_note_btn;
    RoomDB database;
    List<Note> notes = new ArrayList<>();

    private void updateRecycle(List<Note> notes) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        notesListAdapter = new NotesListAdapter(NotesListActivity.this, notes, notesClickListener);
        recyclerView.setAdapter(notesListAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_list);

        Button backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(this::clickOnBackBtn);

        recyclerView = findViewById(R.id.notes_recycler_home);
        add_note_btn = findViewById(R.id.add_note_btn);

        database = RoomDB.getInstance(this);
        notes = database.mainDao().getAll();

        updateRecycle(notes);

        add_note_btn.setOnClickListener(view -> {
            Intent intent = new Intent(NotesListActivity.this, NotesTakerActivity.class);
            startActivityForResult(intent, 101);
        });
    }

    private void clickOnBackBtn(View v) {
        switchToActivity(StartPageActivity.class, this);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            if (resultCode == Activity.RESULT_OK) {
                Note new_notes = (Note) data.getSerializableExtra("note");
                database.mainDao().insert(new_notes);
                notes.clear();
                notes.addAll(database.mainDao().getAll());
                notesListAdapter.notifyDataSetChanged();
            }

        }
        if (requestCode == 102) {
            if (resultCode == Activity.RESULT_OK) {
                Note new_notes = (Note) data.getSerializableExtra("note");
                database.mainDao().update(new_notes.getID(), new_notes.getTitle(), new_notes.getContent());
                notes.clear();
                notes.addAll(database.mainDao().getAll());
                notesListAdapter.notifyDataSetChanged();
            }
        }
    }

    private final NotesClickListener notesClickListener = new NotesClickListener() {
        @Override
        public void onClick(Note notes) {
            Intent intent = new Intent(NotesListActivity.this, NotesTakerActivity.class);
            intent.putExtra("old_note", notes);
            startActivityForResult(intent, 102);

        }

        @Override
        public void onLongClick(Note note, CardView cardView) {
            selectedNote = new Note();
            selectedNote = note;
            showPopup(cardView);
        }
    };

    private void showPopup(CardView cardView) {
        PopupMenu popupMenu = new PopupMenu(this, cardView);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            database.mainDao().delete(selectedNote);
            notes.remove(selectedNote);
            notesListAdapter.notifyDataSetChanged();
            Toast.makeText(NotesListActivity.this, "Note removed", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}