package com.codelingo.engine

import org.junit.Test
import org.junit.Assert.*

class TranslationEngineTest {

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
    fun testIgnoredNormalLogs() {
        val normalLog = "System started successfully on port 8080"
        val explanation = TranslationEngine.explain(normalLog)
        
        assertNull("No debería explicar logs normales", explanation)
    }
}
