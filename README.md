# Pi Greco Calculator - JavaFX

Un'applicazione Java 1.8 che fa uso del toolkit JavaFX per il calcolo delle cifre del Pi Greco con interfaccia grafica avanzata.

## Caratteristiche

### Algoritmi di Calcolo
- **Formula di Machin**: Metodo veloce e preciso per calcoli di media precisione
- **Algoritmo di Chudnovsky**: Metodo molto efficiente per calcoli ad alta precisione

### Interfaccia Utente
- Selezione del metodo di calcolo
- Impostazione del numero di cifre decimali target (2-30)
- ✅ **Cancellazione automatica del valore precedente ad ogni calcolo**

### Screenshot

![Png](https://i.ibb.co/fV380fc7/Immagine-2025-07-07-230002.png)

## Architettura del Progetto

Il progetto segue una **separazione chiara tra logica di business e GUI**:

- **PiGrecoCalculator.java**: Gestisce l'interfaccia utente JavaFX
- **PiCalculator.java**: Contiene tutti gli algoritmi di calcolo del Pi

Questa separazione rende il codice più mantenibile e testabile.

## Struttura del Progetto

```
pi_greco_calculator/
├── src/
│   ├── PiGrecoCalculator.java     # Classe principale JavaFX (GUI)
│   ├── PiCalculator.java          # Motore di calcolo (logica business)
├── bin/                           # File compilati
├── .project                       # Configurazione Eclipse
├── .classpath                     # Classpath Eclipse
└── README.md                      # Questo file
```

## Requisiti

- Java 1.8 o superiore
- JavaFX (incluso in Java 8)
- Sistema operativo: Windows, macOS, Linux

## Come Utilizzare

1. **Avvio dell'Applicazione**
   - Compilare ed eseguire la classe `PiGrecoCalculator`
   - L'interfaccia si aprirà con le impostazioni predefinite

2. **Configurazione del Calcolo**
   - Selezionare il metodo di calcolo dal menu a tendina
   - Impostare il numero di cifre target con lo spinner
   - Il valore predefinito è 10 cifre

3. **Controllo del Calcolo**
   - **Avvia Calcolo**: Inizia il processo di calcolo (cancella automaticamente il valore precedente)

## Metodi di Calcolo

### Formula di Machin
- **Velocità**: Media-Alta
- **Precisione**: Buona
- **Uso consigliato**: Calcoli fino a 1.000 cifre
- **Formula**: π/4 = 4×arctan(1/5) - arctan(1/239)

### Algoritmo di Chudnovsky
- **Velocità**: Molto Alta
- **Precisione**: Eccellente
- **Uso consigliato**: Calcoli ad alta precisione (1.000+ cifre)
- **Caratteristiche**: Ogni iterazione aggiunge circa 14 cifre decimali

## Risoluzione Problemi

### JavaFX non trovato
- Verifica che JavaFX sia incluso nel JDK 1.8
- In Eclipse, controlla le librerie del progetto
- Aggiungi manualmente JavaFX al classpath se necessario

### Errori di compilazione
- Verifica che il JDK 1.8 sia configurato correttamente
- Pulisci e ricompila il progetto

### Performance
- Il calcolo può essere intensivo per CPU e RAM
- Riduci il numero di cifre target per calcoli più veloci

---

**Sviluppato con Java 1.8 e JavaFX**
