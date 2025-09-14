# English

# Pi Calculator - JavaFX

A Java 1.8 application that uses the JavaFX toolkit to calculate the digits of Pi with an advanced graphical interface.

## Features

### Calculation Algorithms
- **Machin's Formula**: Fast and accurate method for medium-precision calculations
- **Chudnovsky Algorithm**: Highly efficient method for high-precision calculations

### User Interface
- Selection of calculation method
- Setting the target number of decimal digits (2–100)
- ✅ **Automatic clearing of the previous value before each calculation**

### Screenshot

![Png](https://i.ibb.co/ZRtXLdsb/Immagine-2025-09-12-234357.png)

## Project Architecture

The project follows a **clear separation between business logic and GUI**:

- **PiGrecoCalculator.java**: Manages the JavaFX user interface
- **PiCalculator.java**: Contains all Pi calculation algorithms

This separation makes the code more maintainable and testable.

## Project Structure

```
pi_greco_calculator/
├── src/
│   ├── PiGrecoCalculator.java     # Main JavaFX class (GUI)
│   ├── PiCalculator.java          # Calculation engine (business logic)
├── bin/                           # Compiled files
├── .project                       # Eclipse project configuration
├── .classpath                     # Eclipse classpath
└── README.md                      # This file
```


## Requirements

- Java 1.8 or higher
- JavaFX (included in Java 8)
- Operating system: Windows, macOS, Linux

## How to Use

1. **Start the Application**
   - Compile and run the `PiGrecoCalculator` class
   - The interface will open with default settings

2. **Configure the Calculation**
   - Select the calculation method from the dropdown menu
   - Set the target number of digits using the spinner
   - The default value is 10 digits

3. **Control the Calculation**
   - **Start Calculation**: Begins the calculation process (automatically clears the previous value)

## Calculation Methods

### Machin's Formula
- **Speed**: Medium–High
- **Accuracy**: Good
- **Recommended use**: Calculations up to 1,000 digits
- **Formula**: π/4 = 4×arctan(1/5) - arctan(1/239)

### Chudnovsky Algorithm
- **Speed**: Very High
- **Accuracy**: Excellent
- **Recommended use**: High-precision calculations (1,000+ digits)
- **Characteristics**: Each iteration adds about 14 decimal digits

## Troubleshooting

### JavaFX not found
- Ensure JavaFX is included in JDK 1.8
- In Eclipse, check the project libraries
- Manually add JavaFX to the classpath if necessary

### Compilation errors
- Ensure JDK 1.8 is properly configured
- Clean and rebuild the project

### Performance
- The calculation can be CPU and RAM intensive
- Reduce the target number of digits for faster calculations

---

**Developed with Java 1.8 and JavaFX**

---

# Italiano

# Pi Greco Calculator - JavaFX

Un'applicazione Java 1.8 che fa uso del toolkit UI JavaFX per il calcolo delle cifre del Pi Greco con interfaccia grafica avanzata.

## Caratteristiche

### Algoritmi di Calcolo
- **Formula di Machin**: Metodo veloce e preciso per calcoli di media precisione
- **Algoritmo di Chudnovsky**: Metodo molto efficiente per calcoli ad alta precisione

### Interfaccia Utente
- Selezione del metodo di calcolo
- Impostazione del numero di cifre decimali target (2-100)
- ✅ **Cancellazione automatica del valore precedente ad ogni calcolo**

### Screenshot

![Png](https://i.ibb.co/ZRtXLdsb/Immagine-2025-09-12-234357.png)

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

---

# 简体中文

# 圆周率计算器 - JavaFX

一个使用 JavaFX 工具包的 Java 1.8 应用程序，用于通过高级图形界面计算圆周率的各位数字。

## 功能特点

### 计算算法
- **Machin 公式**：适用于中等精度计算的快速且精确的方法
- **Chudnovsky 算法**：适用于高精度计算的高效方法

### 用户界面
- 选择计算方法
- 设置目标小数位数（2–100）
- ✅ **每次计算前自动清除上一次的结果**

### 截图

![Png](https://i.ibb.co/ZRtXLdsb/Immagine-2025-09-12-234357.png)

## 项目架构

该项目遵循 **业务逻辑与图形界面清晰分离** 的原则：

- **PiGrecoCalculator.java**：管理 JavaFX 用户界面
- **PiCalculator.java**：包含所有圆周率计算算法

这种分离使代码更易维护和测试。

## 项目结构

```
pi_greco_calculator/
├── src/
│   ├── PiGrecoCalculator.java     # 主 JavaFX 类（图形用户界面）
│   ├── PiCalculator.java          # 计算引擎（业务逻辑）
├── bin/                           # 已编译文件
├── .project                       # Eclipse 项目配置
├── .classpath                     # Eclipse 类路径
└── README.md                      # 本文件
```

## 系统要求

- Java 1.8 或更高版本
- JavaFX（Java 8 已包含）
- 操作系统：Windows、macOS、Linux

## 使用方法

1. **启动应用程序**
   - 编译并运行 `PiGrecoCalculator` 类
   - 界面将以默认设置打开

2. **配置计算**
   - 从下拉菜单中选择计算方法
   - 使用微调框设置目标位数
   - 默认值为 10 位

3. **控制计算**
   - **开始计算**：启动计算过程（自动清除上一次的结果）

## 计算方法

### Machin 公式
- **速度**：中–高
- **精度**：良好
- **推荐用途**：计算至多 1,
