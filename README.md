# 🕹️ CodeLingo - IntelliJ Plugin

**Your friendly neighborhood Java error translator.**
CodeLingo intercepts cryptic Java error messages in your console and translates them into plain, human-readable explanations with actionable solutions.

> **Now 100% Console Native!** No annoying side panels. Just run your code, crash it, and read the explanation right there.

## ✨ Features

- **🌐 Bilingual Support:** Full Spanish and English explanations. Works regardless of your JDK/compiler language!
- **50+ Error Types Covered:**
  - **Runtime Errors:** `NullPointerException`, `IndexOutOfBoundsException`, `ArithmeticException`, `NumberFormatException`, `StackOverflowError`, and many more.
  - **Compilation Errors:** Missing semicolons, "cannot find symbol", incompatible types, missing return statements, static context errors, etc.
  - **Warnings:** Unchecked casts, raw types, deprecated code, unused variables.
  - **JUnit Errors:** Test failures, assertion errors, setup/teardown issues, timeouts.
- **📚 Stack Trace Explainer:** Learn how to read stack traces step by step.
- **⚙️ Configurable:** Enable/disable specific categories in Settings > Tools > CodeLingo.
- **Zero Configuration:** Just install and run.

## 🚀 How to use

1. **Install** the plugin in IntelliJ IDEA.
2. **Run** any Java program that produces an error.
3. Look at your **Run Console**. CodeLingo will inject a helpful block like this:

```text
Exception in thread "main" java.lang.NullPointerException...

------------------- [CodeLingo] -------------------
🛑 NullPointerException Detectado

❓ ¿Qué significa?
Estás intentando usar algo (una variable, un objeto) que está vacío (null)...

✅ Posible Solución:
Busca la línea del error. Probablemente una variable es null.
---------------------------------------------------
```

## 🛠 Installation

### From JetBrains Marketplace (Recommended)
*Coming soon...*

### Manual Installation
1. Download the latest `.zip` release.
2. Open IntelliJ IDEA.
3. Go to **Settings > Plugins > ⚙️ > Install Plugin from Disk...**
4. Select the `.zip` file.
5. Restart IDE.

## 🏗 Development

Run the plugin in a sandbox instance:
```bash
./gradlew runIde
```

Build the distribution file:
```bash
./gradlew buildPlugin
```
(File will be at `build/distributions/CodeLingo-IntelliJ-1.0.0.zip`)

---
Created by [izandegeer](https://github.com/izandegeer)