package com.example.edudialogtrainer;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DialogActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        TextView textView = findViewById(R.id.dialogText);
        textView.setText("Hier startet dein mündlicher Dialog.");
    }
}
