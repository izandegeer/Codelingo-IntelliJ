# 🦆 CodeLingo - IntelliJ Plugin

Tu amigo fiel que traduce los errores crípticos de Java a lenguaje humano.
Este plugin intercepta la consola de IntelliJ y muestra explicaciones sencillas en un panel lateral.

## 🚀 Cómo probarlo

1. Abre este proyecto en **IntelliJ IDEA**.
2. Espera a que Gradle sincronice las dependencias.
3. Busca la pestaña **Gradle** (a la derecha) -> `Tasks` -> `intellij` -> **`runIde`**.
   - Esto abrirá una *nueva* instancia de IntelliJ con el plugin instalado.
4. En esa nueva instancia, crea un archivo Java simple que falle:
   ```java
   public class Main {
       public static void main(String[] args) {
           String nada = null;
           System.out.println(nada.length()); // Esto provocará un NullPointerException
       }
   }
   ```
5. Ejecútalo y mira el panel lateral **"CodeLingo"** (a la derecha).

## 🛠 Estructura

- `TranslationEngine.kt`: La lógica que detecta errores.
- `LingoToolWindowFactory.kt`: La interfaz gráfica (panel lateral).
- `ErrorConsoleFilter.kt`: El "escucha" que lee tu consola.
