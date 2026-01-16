package com.codelingo.listener

import com.intellij.execution.ExecutionListener
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.project.Project

class CodeLingoExecutionListener : ExecutionListener {
    override fun processStarted(executorId: String, env: ExecutionEnvironment, handler: ProcessHandler) {
        // Attach our listener to the process
        handler.addProcessListener(CodeLingoProcessListener(handler))
    }
}
