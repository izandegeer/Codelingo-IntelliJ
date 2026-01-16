package com.codelingo.listener

import com.codelingo.engine.TranslationEngine
import com.intellij.execution.process.ProcessAdapter
import com.intellij.execution.process.ProcessEvent
import com.intellij.execution.process.ProcessHandler
import com.intellij.openapi.util.Key

class CodeLingoProcessListener(private val processHandler: ProcessHandler) : ProcessAdapter() {

    override fun onTextAvailable(event: ProcessEvent, outputType: Key<*>) {
        val text = event.text
        
        // Anti-loop protection: Ignore our own messages
        if (text.contains("[CodeLingo]")) {
            return
        }

        val explanation = TranslationEngine.explain(text)

        if (explanation != null) {
             // ... logic ...
            val duckyMessage = "\n" +
                    "------------------- [CodeLingo] -------------------\n" +
                    explanation + "\n" +
                    "---------------------------------------------------\n"

            // Notify with the generic Key
            processHandler.notifyTextAvailable(duckyMessage, outputType)
        }
    }
}
