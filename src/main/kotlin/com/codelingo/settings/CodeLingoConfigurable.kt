package com.codelingo.settings

import com.codelingo.i18n.Language
import com.intellij.openapi.options.Configurable
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.dsl.builder.panel
import javax.swing.JComponent
import javax.swing.JComboBox

class CodeLingoConfigurable : Configurable {

    private var languageComboBox: JComboBox<Language>? = null
    private var runtimeErrorsCheckBox: JBCheckBox? = null
    private var compileErrorsCheckBox: JBCheckBox? = null
    private var warningsCheckBox: JBCheckBox? = null
    private var junitErrorsCheckBox: JBCheckBox? = null
    private var stackTraceCheckBox: JBCheckBox? = null

    override fun getDisplayName(): String = "CodeLingo"

    override fun createComponent(): JComponent {
        val settings = CodeLingoSettings.getInstance()

        return panel {
            group("🌐 Language / Idioma") {
                row("Select language:") {
                    languageComboBox = comboBox(Language.entries)
                        .component
                    languageComboBox?.selectedItem = settings.language
                }
            }

            group("⚙️ Features / Funcionalidades") {
                row {
                    runtimeErrorsCheckBox = checkBox("Runtime Errors (NullPointer, IndexOutOfBounds...)")
                        .component
                    runtimeErrorsCheckBox?.isSelected = settings.enableRuntimeErrors
                }
                row {
                    compileErrorsCheckBox = checkBox("Compilation Errors (missing semicolon, cannot find symbol...)")
                        .component
                    compileErrorsCheckBox?.isSelected = settings.enableCompileErrors
                }
                row {
                    warningsCheckBox = checkBox("Compiler Warnings (unchecked, deprecated...)")
                        .component
                    warningsCheckBox?.isSelected = settings.enableWarnings
                }
                row {
                    junitErrorsCheckBox = checkBox("JUnit Test Errors (AssertionError, test failures...)")
                        .component
                    junitErrorsCheckBox?.isSelected = settings.enableJUnitErrors
                }
                row {
                    stackTraceCheckBox = checkBox("Stack Trace Explainer (explain how to read stack traces)")
                        .component
                    stackTraceCheckBox?.isSelected = settings.enableStackTraceExplainer
                }
            }

            group("ℹ️ Info") {
                row {
                    label("CodeLingo v1.1.0 - Made with ❤️ by izandegeer")
                }
                row {
                    label("Helps students understand Java errors in plain language.")
                }
            }
        }
    }

    override fun isModified(): Boolean {
        val settings = CodeLingoSettings.getInstance()
        return languageComboBox?.selectedItem != settings.language ||
                runtimeErrorsCheckBox?.isSelected != settings.enableRuntimeErrors ||
                compileErrorsCheckBox?.isSelected != settings.enableCompileErrors ||
                warningsCheckBox?.isSelected != settings.enableWarnings ||
                junitErrorsCheckBox?.isSelected != settings.enableJUnitErrors ||
                stackTraceCheckBox?.isSelected != settings.enableStackTraceExplainer
    }

    override fun apply() {
        val settings = CodeLingoSettings.getInstance()
        settings.language = languageComboBox?.selectedItem as? Language ?: Language.SPANISH
        settings.enableRuntimeErrors = runtimeErrorsCheckBox?.isSelected ?: true
        settings.enableCompileErrors = compileErrorsCheckBox?.isSelected ?: true
        settings.enableWarnings = warningsCheckBox?.isSelected ?: true
        settings.enableJUnitErrors = junitErrorsCheckBox?.isSelected ?: true
        settings.enableStackTraceExplainer = stackTraceCheckBox?.isSelected ?: true
    }

    override fun reset() {
        val settings = CodeLingoSettings.getInstance()
        languageComboBox?.selectedItem = settings.language
        runtimeErrorsCheckBox?.isSelected = settings.enableRuntimeErrors
        compileErrorsCheckBox?.isSelected = settings.enableCompileErrors
        warningsCheckBox?.isSelected = settings.enableWarnings
        junitErrorsCheckBox?.isSelected = settings.enableJUnitErrors
        stackTraceCheckBox?.isSelected = settings.enableStackTraceExplainer
    }
}
