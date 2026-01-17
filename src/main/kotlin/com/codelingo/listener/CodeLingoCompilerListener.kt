package com.codelingo.listener

import com.codelingo.engine.TranslationEngine
import com.intellij.openapi.compiler.CompilationStatusListener
import com.intellij.openapi.compiler.CompileContext
import com.intellij.openapi.compiler.CompilerMessageCategory

class CodeLingoCompilerListener : CompilationStatusListener {

    companion object {
        private const val TAG = "[CodeLingo]"
    }

    override fun compilationFinished(aborted: Boolean, errors: Int, warnings: Int, context: CompileContext) {
        if (errors == 0) return

        val messages = context.getMessages(CompilerMessageCategory.ERROR)

        for (msg in messages) {
            val text = msg.message ?: continue
            val explanation = TranslationEngine.explain(text) ?: continue

            context.addMessage(
                CompilerMessageCategory.INFORMATION,
                "$TAG \uD83D\uDCA1\n$explanation",
                msg.virtualFile?.url,
                -1,
                -1
            )
        }
    }
}
