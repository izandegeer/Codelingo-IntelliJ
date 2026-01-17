package com.codelingo.listener

import com.codelingo.engine.TranslationEngine
import com.intellij.execution.process.ProcessAdapter
import com.intellij.execution.process.ProcessEvent
import com.intellij.execution.process.ProcessHandler
import com.intellij.openapi.util.Key

class CodeLingoProcessListener(private val processHandler: ProcessHandler) : ProcessAdapter() {

    companion object {
        private const val TAG = "[CodeLingo]"
        private const val SEPARATOR = "---------------------------------------------------"
    }

    override fun onTextAvailable(event: ProcessEvent, outputType: Key<*>) {
        val text = event.text

        // Evitar bucle infinito: ignorar nuestros propios mensajes
        if (text.contains(TAG)) return

        val explanation = TranslationEngine.explain(text) ?: return

        val formattedMessage = buildString {
            appendLine()
            appendLine("------------------- $TAG -------------------")
            appendLine(explanation)
            appendLine(SEPARATOR)
        }

        processHandler.notifyTextAvailable(formattedMessage, outputType)
    }
}
