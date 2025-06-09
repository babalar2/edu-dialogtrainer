package com.example.edudialogtrainer;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class DialogActivity extends AppCompatActivity implements
        TextToSpeech.OnInitListener, VoskSpeechRecognizer.Callback {

    private TextToSpeech tts;
    private VoskSpeechRecognizer recognizer;
    private TextView textView;

    private final String question = "Wie heißt du?";
    private final String correctAnswer = "Ich heiße Anna";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        textView = findViewById(R.id.dialogText);
        textView.setText(question);

        tts = new TextToSpeech(this, this, "com.rhvoice.android");
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            tts.setLanguage(Locale.GERMAN);
            tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                @Override
                public void onStart(String utteranceId) {
                }

                @Override
                public void onDone(String utteranceId) {
                    runOnUiThread(() -> {
                        recognizer = new VoskSpeechRecognizer(DialogActivity.this, DialogActivity.this);
                        recognizer.start();
                    });
                }

                @Override
                public void onError(String utteranceId) {
                    runOnUiThread(() -> textView.setText("Fehler beim Sprechen"));
                }
            });
            tts.speak(question, TextToSpeech.QUEUE_FLUSH, null, "question");
        }
    }

    @Override
    public void onResult(String text) {
        if (text.toLowerCase().contains(correctAnswer.toLowerCase())) {
            textView.setText("Korrekt: " + text);
        } else {
            textView.setText("Falsch: " + text);
        }
    }

    @Override
    public void onError(String error) {
        textView.setText("Fehler: " + error);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.shutdown();
        }
        if (recognizer != null) {
            recognizer.stop();
        }
    }
}
