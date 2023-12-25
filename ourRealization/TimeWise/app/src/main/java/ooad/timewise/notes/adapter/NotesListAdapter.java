package ooad.timewise.notes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ooad.timewise.R;
import ooad.timewise.notes.Note;
import ooad.timewise.notes.NotesClickListener;

public class NotesListAdapter extends RecyclerView.Adapter<SingleNoteViewHolder> {
    Context context;
    List<Note> list;
    NotesClickListener listener;

    public NotesListAdapter(Context context, List<Note> list, NotesClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SingleNoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SingleNoteViewHolder(LayoutInflater.from(context).inflate(R.layout.single_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SingleNoteViewHolder holder, int position) {

        holder.textView_title.setText(list.get(position).getTitle());
        holder.textView_title.setSelected(true);

        holder.textView_notes.setText(list.get(position).getContent());

        holder.textView_date.setText(list.get(position).getDate());
        holder.textView_date.setSelected(true);

        holder.notes_container.setOnClickListener(view -> listener.onClick(list.get(holder.getAdapterPosition())));
        holder.notes_container.setOnLongClickListener(view -> {
            listener.onLongClick(list.get(holder.getAdapterPosition()), holder.notes_container);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}


