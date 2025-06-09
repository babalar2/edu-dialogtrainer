# Edu Dialogtrainer

Dies ist ein Projekt zur Entwicklung eines interaktiven Dialogtrainers für Deutschlernende.

## Vosk Setup

Um Spracherkennung zu nutzen, wird das Vosk Toolkit mit dem deutschen Modell `model-de.zip` benötigt. Achten Sie darauf, dass sich die Modelldateien direkt im Wurzelverzeichnis der ZIP-Datei befinden. Andernfalls kann beim Entpacken der Fehler

```
Model unpack failed: model-de/uuid
```

auftreten. Er weist häufig darauf hin, dass die ZIP-Datei einen zusätzlichen Ordner enthält und die eigentlichen Modelldateien eine Ebene tiefer liegen.

Nach dem korrekten Entpacken sollte die Ordnerstruktur beispielsweise so aussehen:

```
model-de/
├── am
├── conf
├── graph
└── other-files...
```

Die Dateien `am`, `conf` und `graph` müssen sich direkt unterhalb von `model-de/` befinden.
