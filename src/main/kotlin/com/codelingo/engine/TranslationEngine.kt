package com.codelingo.engine

object TranslationEngine {

    fun explain(errorMessage: String): String? {
        // --- ERRORES DE EJECUCIÓN (RUNTIME) ---
        
        if (errorMessage.contains("NullPointerException")) {
            return """
                🛑 NullPointerException Detectado
                
                ❓ ¿Qué significa?
                Estás intentando usar algo (una variable, un objeto) que está vacío (null), como si intentaras abrir una caja que no existe.
                
                ✅ Posible Solución:
                Busca la línea del error. Probablemente una variable es null.
                Prueba a añadir un chequeo: if (variable != null) { ... }
            """.trimIndent()
        }
        
        if (errorMessage.contains("IndexOutOfBoundsException")) {
            return """
                📏 Fuera de Límites (IndexOutOfBounds)
                
                ❓ ¿Qué significa?
                Estás intentando acceder a una posición de una lista o array que no existe.
                Ejemplo: Tienes una lista de 3 cosas e intentas pedir la número 5.
                
                ✅ Posible Solución:
                Revisa tus bucles for o while. Asegúrate de que el índice sea menor que lista.size.
            """.trimIndent()
        }

        if (errorMessage.contains("ArithmeticException: / by zero")) {
            return """
                ➗ División por Cero
                
                ❓ ¿Qué significa?
                Has intentado dividir un número por 0, lo cual es matemáticamente imposible para el ordenador.
                
                ✅ Posible Solución:
                Revisa el divisor en tu operación. Pon un if para asegurarte de que no sea 0 antes de dividir.
            """.trimIndent()
        }

        if (errorMessage.contains("NumberFormatException")) {
            return """
                🔢 Error de Formato Numérico
                
                ❓ ¿Qué significa?
                Intentaste convertir un texto en un número, pero el texto no tenía números (ej: intentar convertir "Hola" a entero).
                
                ✅ Posible Solución:
                Revisa qué texto estás intentando convertir. Si viene de un Scanner o un TextField, asegúrate de que el usuario solo escriba números.
            """.trimIndent()
        }

        if (errorMessage.contains("InputMismatchException")) {
            return """
                ⌨️ Error de Entrada (Scanner)
                
                ❓ ¿Qué significa?
                Le pediste un tipo de dato al usuario (ej: un número con nextInt()) pero el usuario escribió otra cosa (ej: letras).
                
                ✅ Posible Solución:
                Usa scanner.hasNextInt() antes de leer para validar la entrada, o asegúrate de escribir el tipo de dato correcto en la consola.
            """.trimIndent()
        }

        // --- ERRORES DE COMPILACIÓN (BUILD) ---

        if (errorMessage.contains("cannot find symbol")) {
            return """
                🔍 Símbolo no encontrado (Error de Compilación)
                
                ❓ ¿Qué significa?
                Java no reconoce una palabra que has escrito. Puede ser una variable que no has declarado o un método que no existe.
                
                ✅ Posible Solución:
                1. Revisa si has escrito bien el nombre (¿Mayúsculas/Minúsculas?).
                2. Asegúrate de haber declarado la variable antes de usarla.
                3. ¿Te falta algún import?
            """.trimIndent()
        }

        if (errorMessage.contains("not a statement")) {
            return """
                ⚠️ Sentencia no válida (Error de Compilación)
                
                ❓ ¿Qué significa?
                Has escrito algo que Java no entiende como una instrucción completa. A veces pasa por olvidar paréntesis o escribir código "suelto".
                
                ✅ Posible Solución:
                Revisa la línea. ¿Falta un igual (=)? ¿Has puesto un nombre de variable sin hacer nada con él?
            """.trimIndent()
        }

        if (errorMessage.contains("expected") && errorMessage.contains(";")) {
             return """
                📝 Falta punto y coma (Error de Compilación)
                
                ❓ ¿Qué significa?
                Se te ha olvidado cerrar una instrucción con ';'. Es como olvidar el punto final de una frase.
                
                ✅ Posible Solución:
                Añade un ';' al final de la línea indicada.
            """.trimIndent()
        }

        return null // No translation found
    }
}
