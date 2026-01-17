package com.codelingo.i18n

enum class Language(val code: String, val displayName: String) {
    SPANISH("es", "Español"),
    ENGLISH("en", "English");

    companion object {
        fun fromCode(code: String): Language {
            return entries.find { it.code == code } ?: SPANISH
        }
    }
}

/**
 * Texto localizado en múltiples idiomas.
 */
data class LocalizedText(
    val es: String,
    val en: String
) {
    fun get(language: Language): String = when (language) {
        Language.SPANISH -> es
        Language.ENGLISH -> en
    }
}

/**
 * Helper para crear textos localizados de forma más legible.
 */
fun localized(es: String, en: String) = LocalizedText(es = es, en = en)
