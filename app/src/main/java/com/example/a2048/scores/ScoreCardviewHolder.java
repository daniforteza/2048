package com.example.a2048.scores;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2048.R;


public class ScoreCardviewHolder extends RecyclerView.ViewHolder {

    public TextView scorecamp, timecamp, usernamecamp, idcamp;
    public ImageButton editButton;
    public ScoreCardviewHolder(@NonNull View itemView, ItemClickListener listener) {
        super(itemView);
        scorecamp = itemView.findViewById(R.id.scorecamp);
        timecamp = itemView.findViewById(R.id.timecamp);
        usernamecamp = itemView.findViewById(R.id.usernamecampp);
        idcamp = itemView.findViewById(R.id.idcamp);
        editButton = itemView.findViewById(R.id.editbutton);
        editButton.setOnClickListener(v -> {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.editClick(position);
                }
            }

        });

    }
}
