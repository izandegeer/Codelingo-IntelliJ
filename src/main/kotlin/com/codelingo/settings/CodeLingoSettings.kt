package com.codelingo.settings

import com.codelingo.i18n.Language
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@Service(Service.Level.APP)
@State(
    name = "CodeLingoSettings",
    storages = [Storage("CodeLingoSettings.xml")]
)
class CodeLingoSettings : PersistentStateComponent<CodeLingoSettings.State> {

    data class State(
        var languageCode: String = "es",
        var enableRuntimeErrors: Boolean = true,
        var enableCompileErrors: Boolean = true,
        var enableWarnings: Boolean = true,
        var enableJUnitErrors: Boolean = true,
        var enableStackTraceExplainer: Boolean = true
    )

    private var myState = State()

    override fun getState(): State = myState

    override fun loadState(state: State) {
        myState = state
    }

    var language: Language
        get() = Language.fromCode(myState.languageCode)
        set(value) {
            myState.languageCode = value.code
        }

    var enableRuntimeErrors: Boolean
        get() = myState.enableRuntimeErrors
        set(value) {
            myState.enableRuntimeErrors = value
        }

    var enableCompileErrors: Boolean
        get() = myState.enableCompileErrors
        set(value) {
            myState.enableCompileErrors = value
        }

    var enableWarnings: Boolean
        get() = myState.enableWarnings
        set(value) {
            myState.enableWarnings = value
        }

    var enableJUnitErrors: Boolean
        get() = myState.enableJUnitErrors
        set(value) {
            myState.enableJUnitErrors = value
        }

    var enableStackTraceExplainer: Boolean
        get() = myState.enableStackTraceExplainer
        set(value) {
            myState.enableStackTraceExplainer = value
        }

    companion object {
        fun getInstance(): CodeLingoSettings {
            return ApplicationManager.getApplication().getService(CodeLingoSettings::class.java)
        }
    }
}
