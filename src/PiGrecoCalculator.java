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

    private Button clearButton;
    private TextArea resultArea;

    private Label statusLabel;
    private Label algorithmLabel;
    
    private AtomicBoolean isCalculating = new AtomicBoolean(false);

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
        

        
        // Etichette stato
        statusLabel = new Label("Pronto per il calcolo");
        algorithmLabel = new Label("Algoritmo: Nessuno selezionato");
        
        root.getChildren().addAll(
            controlPanel,
            new Separator(),
            resultLabel,
            resultArea,

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
            "Algoritmo di Chudnovsky"
        );
        algorithmComboBox.setValue("Formula di Machin");
        algorithmComboBox.setPrefWidth(200);
        algorithmBox.getChildren().add(algorithmComboBox);
        
        // Selezione cifre decimali
        HBox digitsBox = new HBox(10);
        digitsBox.setAlignment(Pos.CENTER);
        digitsBox.getChildren().add(new Label("Cifre decimali (max 30):"));
        
        digitsSpinner = new Spinner<>(3, 30, 10);
        digitsSpinner.setEditable(true);
        digitsSpinner.setPrefWidth(100);
        digitsBox.getChildren().add(digitsSpinner);
        
        // Pulsanti controllo
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        
        startButton = new Button("Avvia Calcolo");

        clearButton = new Button("Pulisci");
        
        startButton.setOnAction(e -> handleStart());

        clearButton.setOnAction(e -> handleClear());
        

        
        buttonBox.getChildren().addAll(startButton, clearButton);
        
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
                if (tempDigits > 30) tempDigits = 30;
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

        
        updateButtonStates(true);
        algorithmComboBox.setDisable(true);
        digitsSpinner.setDisable(true);
        
        statusLabel.setText("Calcolo in corso...");
        algorithmLabel.setText("Algoritmo: " + algorithm);

        
        calculationThread = new Thread(() -> {
            try {
                CalculationCallback callback = new CalculationCallback();
                calculator.calculatePi(algorithm, targetDigits, callback);
            } catch (Exception e) {
                Platform.runLater(() -> {
                    statusLabel.setText("Errore: " + e.getMessage());
                    resetControls();
                });
            }
        });
        
        calculationThread.start();
    }
    

    
    private void handleClear() {
        resultArea.setText("3.14");

        statusLabel.setText("Pronto per il calcolo");
        algorithmLabel.setText("Algoritmo: Nessuno selezionato");
    }
    
    private void updateButtonStates(boolean calculating) {
        startButton.setDisable(calculating);
    }
    
    private void resetControls() {
        isCalculating.set(false);
        startButton.setText("Avvia Calcolo");
        startButton.setDisable(false);

        algorithmComboBox.setDisable(false);
        digitsSpinner.setDisable(false);
    }
    
    /**
     * Callback per aggiornamenti dal calcolatore
     */
    public class CalculationCallback {
        
        public void onProgress(double progress) {
            // Progress non più necessario
        }
        
        public void onDigitsUpdate(String piValue) {
            Platform.runLater(() -> resultArea.setText(piValue));
        }
        
        public void onComplete(String finalValue) {
            Platform.runLater(() -> {
                resultArea.setText(finalValue);
                statusLabel.setText("Calcolo completato - Valore matematicamente corretto!");

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
            return false;
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}