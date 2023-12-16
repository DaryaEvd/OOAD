package ooad.timewise.notes;

import androidx.cardview.widget.CardView;

public interface NotesClickListener {
    void onClick(Note note);
    void onLongClick (Note note, CardView cardView);
}
