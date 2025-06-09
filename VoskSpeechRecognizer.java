package com.example.edudialogtrainer;

import android.content.Context;

import org.vosk.Model;
import org.vosk.Recognizer;
import org.vosk.android.RecognitionListener;
import org.vosk.android.SpeechService;
import org.vosk.android.StorageService;

import java.io.IOException;

public class VoskSpeechRecognizer implements RecognitionListener {
    public interface Callback {
        void onResult(String text);
        void onError(String error);
    }

    private final Context context;
    private final Callback callback;
    private Model model;
    private SpeechService speechService;

    public VoskSpeechRecognizer(Context context, Callback callback) {
        this.context = context;
        this.callback = callback;
    }

    public void start() {
        StorageService.unpack(context, "model-de", "model",
                model -> {
                    this.model = model;
                    startService();
                },
                exception -> callback.onError("Model unpack failed: " + exception.getMessage()));
    }

    private void startService() {
        try {
            Recognizer rec = new Recognizer(model, 16000.0f);
            speechService = new SpeechService(rec, 16000.0f);
            speechService.startListening(this);
        } catch (IOException e) {
            callback.onError(e.getMessage());
        }
    }

    public void stop() {
        if (speechService != null) {
            speechService.stop();
            speechService.shutdown();
            speechService = null;
        }
    }

    @Override
    public void onResult(String hypothesis) {
        callback.onResult(hypothesis);
    }

    @Override
    public void onFinalResult(String hypothesis) {
        callback.onResult(hypothesis);
    }

    @Override
    public void onPartialResult(String hypothesis) {
        // Ignored
    }

    @Override
    public void onError(Exception e) {
        callback.onError(e.getMessage());
    }

    @Override
    public void onTimeout() {
        stop();
    }
}
