# Edu Dialogtrainer

Dies ist ein Projekt zur Entwicklung eines interaktiven Dialogtrainers für Deutschlernende.

## Offline-Spracherkennung und -Synthese

Die App nutzt [Vosk](https://alphacephei.com/vosk/) für die offline Spracherkennung und [RHVoice](https://github.com/RHVoice/RHVoice) als Text-to-Speech-Engine.

### Abhängigkeiten

Füge der `build.gradle` deines Moduls folgende Abhängigkeit hinzu, um Vosk zu verwenden:

```gradle
implementation 'com.alphacephei:vosk-android:0.3.47'
```

Installiere außerdem die RHVoice TTS-Engine auf dem Gerät und setze sie als Standard-Sprachsynthese.

### Funktionsweise

Beim Start der `DialogActivity` liest RHVoice eine Frage vor. Anschließend wird mit Hilfe von Vosk die gesprochene Antwort aufgenommen. Die App vergleicht diese mit einer vorgegebenen Zielantwort und informiert den Nutzer, ob die Antwort korrekt war.
