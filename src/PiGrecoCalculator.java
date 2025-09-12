import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Calcolatore Pi Greco - Implementazione matematicamente corretta
 * Entrambi gli algoritmi devono produrre lo stesso identico valore
 */
public class PiGrecoCalculator extends Application {
    
    private ComboBox<String> algorithmComboBox;
    private Spinner<Integer> digitsSpinner;
    private Button startButton;
    private Button stopButton;
    private Button clearButton;
    private TextArea resultArea;
    private ProgressBar progressBar;
    private Label statusLabel;
    private Label algorithmLabel;
    private Label timeLabel;
    private Label etaLabel;
    
    private AtomicBoolean isCalculating = new AtomicBoolean(false);
    private AtomicBoolean shouldStop = new AtomicBoolean(false);
    private long startTime;
    private Thread calculationThread;
    private PiCalculator calculator;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calcolatore Pi Greco");
        
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        
        // Pannello controlli
        VBox controlPanel = createControlPanel();
        
        // Area risultati
        Label resultLabel = new Label("Valore di Pi Greco (identico per entrambi gli algoritmi):");
        resultLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        
        resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPrefRowCount(6);
        resultArea.setText("3.14");
        resultArea.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 14px;");
        
        // Barra di progresso
        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(400);
        progressBar.setVisible(false);
        
        // Etichette stato e tempo
        statusLabel = new Label("Pronto per il calcolo");
        algorithmLabel = new Label("Algoritmo: Nessuno selezionato");
        timeLabel = new Label("");
        etaLabel = new Label("");
        
        VBox progressPanel = new VBox(5);
        progressPanel.setAlignment(Pos.CENTER);
        progressPanel.getChildren().addAll(progressBar, timeLabel, etaLabel);
        
        root.getChildren().addAll(
            controlPanel,
            new Separator(),
            resultLabel,
            resultArea,
            progressPanel,
            statusLabel,
            algorithmLabel
        );
        
        Scene scene = new Scene(root, 700, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Inizializza il calcolatore
        calculator = new PiCalculator();
    }
    
    private VBox createControlPanel() {
        VBox panel = new VBox(12);
        panel.setAlignment(Pos.CENTER);
        
        // Selezione algoritmo
        HBox algorithmBox = new HBox(10);
        algorithmBox.setAlignment(Pos.CENTER);
        algorithmBox.getChildren().add(new Label("Algoritmo:"));
        
        algorithmComboBox = new ComboBox<>();
        algorithmComboBox.getItems().addAll(
            "Formula di Machin",
            "Algoritmo di Chudnovsky",
            "Validazione Incrociata (Entrambi)"
        );
        algorithmComboBox.setValue("Formula di Machin");
        algorithmComboBox.setPrefWidth(200);
        algorithmBox.getChildren().add(algorithmComboBox);
        
        // Selezione cifre decimali
        HBox digitsBox = new HBox(10);
        digitsBox.setAlignment(Pos.CENTER);
        digitsBox.getChildren().add(new Label("Cifre decimali (max 100):"));
        
        digitsSpinner = new Spinner<>(3, 100, 10);
        digitsSpinner.setEditable(true);
        digitsSpinner.setPrefWidth(100);
        digitsBox.getChildren().add(digitsSpinner);
        
        // Pulsanti controllo
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        
        startButton = new Button("Avvia Calcolo");
        stopButton = new Button("Ferma");
        clearButton = new Button("Pulisci");
        
        startButton.setOnAction(e -> handleStart());
        stopButton.setOnAction(e -> handleStop());
        clearButton.setOnAction(e -> handleClear());
        
        stopButton.setDisable(true);
        
        buttonBox.getChildren().addAll(startButton, stopButton, clearButton);
        
        panel.getChildren().addAll(algorithmBox, digitsBox, buttonBox);
        return panel;
    }
    
    private void handleStart() {
        // Avvia nuovo calcolo
        // Legge il valore inserito manualmente nello spinner
        int tempDigits;
        try {
            // Prima prova a leggere il testo inserito manualmente
            String editorText = digitsSpinner.getEditor().getText().trim();
            if (!editorText.isEmpty()) {
                tempDigits = Integer.parseInt(editorText);
                // Verifica che sia nel range valido
                if (tempDigits < 3) tempDigits = 3;
                if (tempDigits > 100) tempDigits = 100;
                // Aggiorna lo spinner con il valore corretto
                digitsSpinner.getValueFactory().setValue(tempDigits);
            } else {
                // Se il campo è vuoto, usa il valore dello spinner
                tempDigits = digitsSpinner.getValue();
            }
        } catch (NumberFormatException e) {
            // Se il testo non è un numero valido, usa il valore dello spinner
            tempDigits = digitsSpinner.getValue();
        }
        
        final int targetDigits = tempDigits;
        String algorithm = algorithmComboBox.getValue();
        
        isCalculating.set(true);
        shouldStop.set(false);
        startTime = System.currentTimeMillis();
        
        updateButtonStates(true);
        algorithmComboBox.setDisable(true);
        digitsSpinner.setDisable(true);
        
        // Mostra barra di progresso e reset etichette tempo
        progressBar.setProgress(0);
        progressBar.setVisible(true);
        timeLabel.setText("Tempo trascorso: 0s");
        etaLabel.setText("Tempo stimato rimanente: Calcolando...");
        
        statusLabel.setText("Calcolo in corso...");
        algorithmLabel.setText("Algoritmo: " + algorithm);
        
        calculationThread = new Thread(() -> {
            try {
                CalculationCallback callback = new CalculationCallback();
                if ("Validazione Incrociata (Entrambi)".equals(algorithm)) {
                    calculator.calculatePiWithCrossValidation(targetDigits, callback);
                } else {
                    calculator.calculatePi(algorithm, targetDigits, callback);
                }
            } catch (Exception e) {
                Platform.runLater(() -> {
                    statusLabel.setText("Errore: " + e.getMessage());
                    resetControls();
                });
            }
        });
        
        calculationThread.start();
    }
    
    private void handleStop() {
        shouldStop.set(true);
        if (calculationThread != null && calculationThread.isAlive()) {
            calculationThread.interrupt();
        }
        resetControls();
        statusLabel.setText("Calcolo interrotto dall'utente");
    }
    
    private void handleClear() {
        resultArea.setText("3.14");
        progressBar.setVisible(false);
        timeLabel.setText("");
        etaLabel.setText("");
        statusLabel.setText("Pronto per il calcolo");
        algorithmLabel.setText("Algoritmo: Nessuno selezionato");
    }
    
    private void updateButtonStates(boolean calculating) {
        startButton.setDisable(calculating);
        stopButton.setDisable(!calculating);
    }
    
    private void resetControls() {
        isCalculating.set(false);
        shouldStop.set(false);
        startButton.setText("Avvia Calcolo");
        updateButtonStates(false);
        algorithmComboBox.setDisable(false);
        digitsSpinner.setDisable(false);
        progressBar.setVisible(false);
        timeLabel.setText("");
        etaLabel.setText("");
    }
    
    /**
     * Callback per aggiornamenti dal calcolatore
     */
    public class CalculationCallback {
        private double lastProgress = 0;
        private long lastUpdateTime = 0;
        
        public void onProgress(double progress) {
            Platform.runLater(() -> {
                progressBar.setProgress(progress);
                
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;
                
                // Aggiorna tempo trascorso
                timeLabel.setText(String.format("Tempo trascorso: %ds", elapsedTime / 1000));
                
                // Calcola tempo stimato rimanente solo se abbiamo progresso significativo
                if (progress > 0.05 && progress < 0.95) {
                    long estimatedTotalTime = (long) (elapsedTime / progress);
                    long estimatedRemainingTime = estimatedTotalTime - elapsedTime;
                    
                    if (estimatedRemainingTime > 0) {
                        etaLabel.setText(String.format("Tempo stimato rimanente: %ds", 
                                       estimatedRemainingTime / 1000));
                    }
                } else if (progress >= 0.95) {
                    etaLabel.setText("Quasi completato...");
                }
                
                lastProgress = progress;
                lastUpdateTime = currentTime;
            });
        }
        
        public void onDigitsUpdate(String piValue) {
            Platform.runLater(() -> resultArea.setText(piValue));
        }
        
        public void onComplete(String finalValue) {
            Platform.runLater(() -> {
                progressBar.setProgress(1.0);
                resultArea.setText(finalValue);
                long totalTime = System.currentTimeMillis() - startTime;
                statusLabel.setText(String.format("Calcolo completato in %ds - Valore matematicamente corretto!", totalTime / 1000));
                etaLabel.setText("Completato!");
                resetControls();
            });
        }
        
        public void onError(String error) {
            Platform.runLater(() -> {
                statusLabel.setText("Errore: " + error);
                resetControls();
            });
        }
        
        public boolean isPaused() {
            return false;
        }
        
        public boolean isStopped() {
            return shouldStop.get() || !isCalculating.get();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}