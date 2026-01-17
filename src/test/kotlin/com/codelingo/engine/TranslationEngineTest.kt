package com.codelingo.engine

import org.junit.Test
import org.junit.Assert.*

class TranslationEngineTest {

    // ═══════════════════════════════════════════════════════════════
    // TESTS DE ERRORES RUNTIME
    // ═══════════════════════════════════════════════════════════════

    @Test
    fun testNullPointerExceptionDetection() {
        val errorInput = "Exception in thread \"main\" java.lang.NullPointerException at com.example.Main.main(Main.java:10)"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar el NullPointerException", explanation)
        assertTrue(explanation!!.contains("NullPointerException Detectado"))
    }

    @Test
    fun testIndexOutOfBoundsDetection() {
        val errorInput = "java.lang.IndexOutOfBoundsException: Index 5 out of bounds for length 3"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar IndexOutOfBounds", explanation)
        assertTrue(explanation!!.contains("Fuera de Límites"))
    }

    @Test
    fun testArrayIndexOutOfBoundsDetection() {
        val errorInput = "java.lang.ArrayIndexOutOfBoundsException: Index 10 out of bounds for length 5"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar ArrayIndexOutOfBounds", explanation)
        assertTrue(explanation!!.contains("Fuera de Límites de Array"))
    }

    @Test
    fun testStringIndexOutOfBoundsDetection() {
        val errorInput = "java.lang.StringIndexOutOfBoundsException: String index out of range: 15"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar StringIndexOutOfBounds", explanation)
        assertTrue(explanation!!.contains("Fuera de Límites de Texto"))
    }

    @Test
    fun testStackOverflowDetection() {
        val errorInput = "java.lang.StackOverflowError"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar StackOverflowError", explanation)
        assertTrue(explanation!!.contains("Desbordamiento de Pila"))
    }

    @Test
    fun testArithmeticExceptionDetection() {
        val errorInput = "java.lang.ArithmeticException: / by zero"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar división por cero", explanation)
        assertTrue(explanation!!.contains("División por Cero"))
    }

    @Test
    fun testNumberFormatExceptionDetection() {
        val errorInput = "java.lang.NumberFormatException: For input string: \"abc\""
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar NumberFormatException", explanation)
        assertTrue(explanation!!.contains("Error de Formato Numérico"))
    }

    @Test
    fun testInputMismatchExceptionDetection() {
        val errorInput = "java.util.InputMismatchException"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar InputMismatchException", explanation)
        assertTrue(explanation!!.contains("Error de Entrada"))
    }

    @Test
    fun testClassCastExceptionDetection() {
        val errorInput = "java.lang.ClassCastException: class java.lang.String cannot be cast to class java.lang.Integer"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar ClassCastException", explanation)
        assertTrue(explanation!!.contains("Error de Conversión de Tipo"))
    }

    @Test
    fun testConcurrentModificationExceptionDetection() {
        val errorInput = "java.util.ConcurrentModificationException"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar ConcurrentModificationException", explanation)
        assertTrue(explanation!!.contains("Modificación Concurrente"))
    }

    @Test
    fun testFileNotFoundExceptionDetection() {
        val errorInput = "java.io.FileNotFoundException: archivo.txt (No such file or directory)"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar FileNotFoundException", explanation)
        assertTrue(explanation!!.contains("Archivo No Encontrado"))
    }

    @Test
    fun testOutOfMemoryErrorDetection() {
        val errorInput = "java.lang.OutOfMemoryError: Java heap space"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar OutOfMemoryError", explanation)
        assertTrue(explanation!!.contains("Memoria Agotada"))
    }

    @Test
    fun testIllegalArgumentExceptionDetection() {
        val errorInput = "java.lang.IllegalArgumentException: Invalid argument"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar IllegalArgumentException", explanation)
        assertTrue(explanation!!.contains("Argumento Ilegal"))
    }

    @Test
    fun testIllegalStateExceptionDetection() {
        val errorInput = "java.lang.IllegalStateException: Scanner closed"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar IllegalStateException", explanation)
        assertTrue(explanation!!.contains("Estado Ilegal"))
    }

    @Test
    fun testNoSuchElementExceptionDetection() {
        val errorInput = "java.util.NoSuchElementException"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar NoSuchElementException", explanation)
        assertTrue(explanation!!.contains("Elemento No Encontrado"))
    }

    @Test
    fun testUnsupportedOperationExceptionDetection() {
        val errorInput = "java.lang.UnsupportedOperationException"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar UnsupportedOperationException", explanation)
        assertTrue(explanation!!.contains("Operación No Soportada"))
    }

    // ═══════════════════════════════════════════════════════════════
    // TESTS DE ERRORES DE COMPILACIÓN
    // ═══════════════════════════════════════════════════════════════

    @Test
    fun testCannotFindSymbolDetection() {
        val errorInput = "error: cannot find symbol"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar símbolo no encontrado", explanation)
        assertTrue(explanation!!.contains("Símbolo no encontrado"))
    }

    @Test
    fun testIncompatibleTypesDetection() {
        val errorInput = "error: incompatible types: java.lang.String cannot be converted to int"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar tipos incompatibles", explanation)
        assertTrue(explanation!!.contains("Tipos Incompatibles"))
    }

    @Test
    fun testStaticContextDetection() {
        val errorInput = "error: non-static method sayHello() cannot be referenced from a static context"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar error de contexto estático", explanation)
        assertTrue(explanation!!.contains("Static vs No-Static"))
    }

    @Test
    fun testNotInitializedDetection() {
        val errorInput = "error: variable x might not have been initialized"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar variable no inicializada", explanation)
        assertTrue(explanation!!.contains("Variable no inicializada"))
    }

    @Test
    fun testMissingReturnDetection() {
        val errorInput = "error: missing return statement"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar falta de return", explanation)
        assertTrue(explanation!!.contains("Falta Return"))
    }

    @Test
    fun testUnreachableStatementDetection() {
        val errorInput = "error: unreachable statement"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar código inaccesible", explanation)
        assertTrue(explanation!!.contains("Código Inaccesible"))
    }

    @Test
    fun testAbstractClassDetection() {
        val errorInput = "error: Animal is abstract; cannot be instantiated"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar clase abstracta", explanation)
        assertTrue(explanation!!.contains("Clase Abstracta"))
    }

    @Test
    fun testMissingSemicolonDetection() {
        val errorInput = "error: ';' expected"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar falta de punto y coma", explanation)
        assertTrue(explanation!!.contains("Falta punto y coma"))
    }

    @Test
    fun testMissingParenthesisDetection() {
        val errorInput = "error: ')' expected"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar falta de paréntesis", explanation)
        assertTrue(explanation!!.contains("Falta paréntesis"))
    }

    @Test
    fun testMissingBraceDetection() {
        val errorInput = "error: '}' expected"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar falta de llave", explanation)
        assertTrue(explanation!!.contains("Falta llave"))
    }

    @Test
    fun testPublicClassFileNameDetection() {
        val errorInput = "error: class MyClass is public, should be declared in a file named MyClass.java"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar nombre de archivo incorrecto", explanation)
        assertTrue(explanation!!.contains("Nombre de archivo incorrecto"))
    }

    @Test
    fun testIllegalStartOfExpressionDetection() {
        val errorInput = "error: illegal start of expression"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar inicio de expresión ilegal", explanation)
        assertTrue(explanation!!.contains("Inicio de expresión ilegal"))
    }

    @Test
    fun testFinalVariableDetection() {
        val errorInput = "error: cannot assign a value to final variable x"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar variable final", explanation)
        assertTrue(explanation!!.contains("Variable final no modificable"))
    }

    @Test
    fun testDuplicateVariableDetection() {
        val errorInput = "error: variable x is already defined in method main"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar variable duplicada", explanation)
        assertTrue(explanation!!.contains("Variable duplicada"))
    }

    @Test
    fun testPackageNotFoundDetection() {
        val errorInput = "error: package com.example.utils does not exist"
        val explanation = TranslationEngine.explain(errorInput)

        assertNotNull("Debería detectar paquete no encontrado", explanation)
        assertTrue(explanation!!.contains("Paquete no encontrado"))
    }

    // ═══════════════════════════════════════════════════════════════
    // TESTS DE CASOS NEGATIVOS
    // ═══════════════════════════════════════════════════════════════

    @Test
    fun testIgnoredNormalLogs() {
        val normalLog = "System started successfully on port 8080"
        val explanation = TranslationEngine.explain(normalLog)

        assertNull("No debería explicar logs normales", explanation)
    }

    @Test
    fun testIgnoredInfoMessages() {
        val infoLog = "INFO: Application initialized"
        val explanation = TranslationEngine.explain(infoLog)

        assertNull("No debería explicar mensajes INFO", explanation)
    }

    @Test
    fun testIgnoredDebugMessages() {
        val debugLog = "DEBUG: Processing request"
        val explanation = TranslationEngine.explain(debugLog)

        assertNull("No debería explicar mensajes DEBUG", explanation)
    }
}
