package com.codelingo.engine

import com.codelingo.i18n.Language
import com.codelingo.i18n.LocalizedText
import com.codelingo.i18n.localized
import com.codelingo.settings.CodeLingoSettings

/**
 * Categoría del error/mensaje.
 */
enum class ErrorCategory {
    RUNTIME_ERROR,
    COMPILE_ERROR,
    WARNING,
    JUNIT_ERROR
}

/**
 * Explicación de error localizada.
 */
data class ErrorExplanation(
    val emoji: String,
    val title: LocalizedText,
    val meaning: LocalizedText,
    val solution: LocalizedText,
    val category: ErrorCategory
) {
    fun format(language: Language): String = """
        $emoji ${title.get(language)}

        ${if (language == Language.SPANISH) "❓ ¿Qué significa?" else "❓ What does it mean?"}
        ${meaning.get(language)}

        ${if (language == Language.SPANISH) "✅ Posible Solución:" else "✅ Possible Solution:"}
        ${solution.get(language)}
    """.trimIndent()
}

/**
 * Patrón de error con regex y explicación.
 */
data class ErrorPattern(
    val regex: Regex,
    val explanation: ErrorExplanation
)

object TranslationEngine {

    private val errorPatterns: List<ErrorPattern> = buildList {
        // ═══════════════════════════════════════════════════════════════
        // RUNTIME ERRORS
        // ═══════════════════════════════════════════════════════════════

        add(ErrorPattern(
            regex = Regex("NullPointerException"),
            explanation = ErrorExplanation(
                emoji = "🛑",
                title = localized(
                    es = "NullPointerException Detectado",
                    en = "NullPointerException Detected"
                ),
                meaning = localized(
                    es = "Estás intentando usar algo (una variable, un objeto) que está vacío (null), como si intentaras abrir una caja que no existe.",
                    en = "You're trying to use something (a variable, an object) that is empty (null), like trying to open a box that doesn't exist."
                ),
                solution = localized(
                    es = "Busca la línea del error. Probablemente una variable es null.\nPrueba a añadir un chequeo: if (variable != null) { ... }",
                    en = "Look at the error line. A variable is probably null.\nTry adding a check: if (variable != null) { ... }"
                ),
                category = ErrorCategory.RUNTIME_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("StringIndexOutOfBoundsException"),
            explanation = ErrorExplanation(
                emoji = "📏",
                title = localized(
                    es = "Fuera de Límites de Texto (StringIndexOutOfBounds)",
                    en = "String Index Out Of Bounds"
                ),
                meaning = localized(
                    es = "Intentaste leer una letra en una posición que no existe en el texto (ej: letra 10 de una palabra de 5 letras).",
                    en = "You tried to read a character at a position that doesn't exist in the string (e.g., character 10 of a 5-letter word)."
                ),
                solution = localized(
                    es = "Asegúrate de que el índice que usas es menor que 'texto.length()'.",
                    en = "Make sure the index you use is less than 'text.length()'."
                ),
                category = ErrorCategory.RUNTIME_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("ArrayIndexOutOfBoundsException"),
            explanation = ErrorExplanation(
                emoji = "📏",
                title = localized(
                    es = "Fuera de Límites de Array (ArrayIndexOutOfBounds)",
                    en = "Array Index Out Of Bounds"
                ),
                meaning = localized(
                    es = "Estás intentando acceder a una posición de un array que no existe. Ejemplo: Tienes un array de 3 elementos e intentas acceder al índice 5.",
                    en = "You're trying to access an array position that doesn't exist. Example: You have a 3-element array and try to access index 5."
                ),
                solution = localized(
                    es = "Revisa tus bucles for o while. Asegúrate de que el índice sea menor que array.length.",
                    en = "Check your for or while loops. Make sure the index is less than array.length."
                ),
                category = ErrorCategory.RUNTIME_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("IndexOutOfBoundsException"),
            explanation = ErrorExplanation(
                emoji = "📏",
                title = localized(
                    es = "Fuera de Límites (IndexOutOfBounds)",
                    en = "Index Out Of Bounds"
                ),
                meaning = localized(
                    es = "Estás intentando acceder a una posición de una lista o array que no existe. Ejemplo: Tienes una lista de 3 cosas e intentas pedir la número 5.",
                    en = "You're trying to access a list or array position that doesn't exist. Example: You have a list of 3 items and try to get item 5."
                ),
                solution = localized(
                    es = "Revisa tus bucles for o while. Asegúrate de que el índice sea menor que lista.size().",
                    en = "Check your for or while loops. Make sure the index is less than list.size()."
                ),
                category = ErrorCategory.RUNTIME_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("ArithmeticException.*(/|divide).*zero", RegexOption.IGNORE_CASE),
            explanation = ErrorExplanation(
                emoji = "➗",
                title = localized(
                    es = "División por Cero",
                    en = "Division by Zero"
                ),
                meaning = localized(
                    es = "Has intentado dividir un número por 0, lo cual es matemáticamente imposible para el ordenador.",
                    en = "You tried to divide a number by 0, which is mathematically impossible for the computer."
                ),
                solution = localized(
                    es = "Revisa el divisor en tu operación. Pon un if para asegurarte de que no sea 0 antes de dividir.",
                    en = "Check the divisor in your operation. Add an if to make sure it's not 0 before dividing."
                ),
                category = ErrorCategory.RUNTIME_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("NumberFormatException"),
            explanation = ErrorExplanation(
                emoji = "🔢",
                title = localized(
                    es = "Error de Formato Numérico",
                    en = "Number Format Error"
                ),
                meaning = localized(
                    es = "Intentaste convertir un texto en un número, pero el texto no tenía números válidos (ej: convertir \"Hola\" a entero).",
                    en = "You tried to convert text to a number, but the text didn't contain valid numbers (e.g., converting \"Hello\" to integer)."
                ),
                solution = localized(
                    es = "Revisa qué texto estás intentando convertir. Si viene de un Scanner o TextField, asegúrate de que el usuario solo escriba números.",
                    en = "Check what text you're trying to convert. If it comes from a Scanner or TextField, make sure the user only enters numbers."
                ),
                category = ErrorCategory.RUNTIME_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("InputMismatchException"),
            explanation = ErrorExplanation(
                emoji = "⌨️",
                title = localized(
                    es = "Error de Entrada (Scanner)",
                    en = "Input Mismatch Error (Scanner)"
                ),
                meaning = localized(
                    es = "Le pediste un tipo de dato al usuario (ej: un número con nextInt()) pero el usuario escribió otra cosa (ej: letras).",
                    en = "You asked the user for a data type (e.g., a number with nextInt()) but they entered something else (e.g., letters)."
                ),
                solution = localized(
                    es = "Usa scanner.hasNextInt() antes de leer para validar la entrada, o asegúrate de escribir el tipo de dato correcto en la consola.",
                    en = "Use scanner.hasNextInt() before reading to validate input, or make sure to enter the correct data type in the console."
                ),
                category = ErrorCategory.RUNTIME_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("StackOverflowError"),
            explanation = ErrorExplanation(
                emoji = "🥞",
                title = localized(
                    es = "Desbordamiento de Pila (StackOverflow)",
                    en = "Stack Overflow Error"
                ),
                meaning = localized(
                    es = "Tu programa ha entrado en un bucle infinito de llamadas a sí mismo (recursividad infinita).",
                    en = "Your program has entered an infinite loop of calls to itself (infinite recursion)."
                ),
                solution = localized(
                    es = "Revisa tus funciones recursivas. Asegúrate de tener una \"condición base\" que detenga la recursión.",
                    en = "Check your recursive functions. Make sure you have a \"base case\" that stops the recursion."
                ),
                category = ErrorCategory.RUNTIME_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("ClassCastException"),
            explanation = ErrorExplanation(
                emoji = "🎭",
                title = localized(
                    es = "Error de Conversión de Tipo (ClassCast)",
                    en = "Class Cast Error"
                ),
                meaning = localized(
                    es = "Intentaste tratar un objeto como algo que no es. Por ejemplo, tratar un texto como si fuera un número entero directamente.",
                    en = "You tried to treat an object as something it's not. For example, treating text as if it were an integer directly."
                ),
                solution = localized(
                    es = "Revisa tus '(Castings)'. Usa 'instanceof' antes de convertir para asegurarte de que es posible.",
                    en = "Check your '(Castings)'. Use 'instanceof' before casting to make sure it's possible."
                ),
                category = ErrorCategory.RUNTIME_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("ConcurrentModificationException"),
            explanation = ErrorExplanation(
                emoji = "🔄",
                title = localized(
                    es = "Modificación Concurrente",
                    en = "Concurrent Modification Error"
                ),
                meaning = localized(
                    es = "Intentaste modificar una lista (borrar o añadir elementos) mientras la estabas recorriendo con un bucle for-each.",
                    en = "You tried to modify a list (delete or add elements) while iterating over it with a for-each loop."
                ),
                solution = localized(
                    es = "Usa un 'Iterator' para borrar elementos mientras recorres la lista, o crea una lista nueva con los elementos que quieres guardar.",
                    en = "Use an 'Iterator' to remove elements while iterating, or create a new list with the elements you want to keep."
                ),
                category = ErrorCategory.RUNTIME_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("FileNotFoundException"),
            explanation = ErrorExplanation(
                emoji = "📁",
                title = localized(
                    es = "Archivo No Encontrado",
                    en = "File Not Found"
                ),
                meaning = localized(
                    es = "El programa intentó abrir un archivo que no está donde dijiste.",
                    en = "The program tried to open a file that isn't where you specified."
                ),
                solution = localized(
                    es = "1. Revisa la ruta del archivo (path).\n2. Asegúrate de que el archivo realmente existe en esa carpeta.\n3. Comprueba si necesitas permisos de administrador.",
                    en = "1. Check the file path.\n2. Make sure the file actually exists in that folder.\n3. Check if you need admin permissions."
                ),
                category = ErrorCategory.RUNTIME_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("OutOfMemoryError"),
            explanation = ErrorExplanation(
                emoji = "💾",
                title = localized(
                    es = "Memoria Agotada (OutOfMemory)",
                    en = "Out Of Memory Error"
                ),
                meaning = localized(
                    es = "Tu programa se ha quedado sin memoria RAM disponible.",
                    en = "Your program has run out of available RAM memory."
                ),
                solution = localized(
                    es = "1. ¿Tienes un bucle infinito creando objetos?\n2. ¿Estás cargando archivos demasiado grandes en memoria?",
                    en = "1. Do you have an infinite loop creating objects?\n2. Are you loading files that are too large into memory?"
                ),
                category = ErrorCategory.RUNTIME_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("IllegalArgumentException"),
            explanation = ErrorExplanation(
                emoji = "🚫",
                title = localized(
                    es = "Argumento Ilegal",
                    en = "Illegal Argument"
                ),
                meaning = localized(
                    es = "Has pasado un valor no permitido a un método. Por ejemplo, un número negativo donde solo se aceptan positivos.",
                    en = "You passed an invalid value to a method. For example, a negative number where only positives are accepted."
                ),
                solution = localized(
                    es = "Revisa los parámetros que pasas al método. Lee la documentación para saber qué valores son válidos.",
                    en = "Check the parameters you pass to the method. Read the documentation to know what values are valid."
                ),
                category = ErrorCategory.RUNTIME_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("IllegalStateException"),
            explanation = ErrorExplanation(
                emoji = "⚠️",
                title = localized(
                    es = "Estado Ilegal",
                    en = "Illegal State"
                ),
                meaning = localized(
                    es = "Intentaste hacer algo en un momento que no estaba permitido. Por ejemplo, leer de un Scanner ya cerrado.",
                    en = "You tried to do something at a time when it wasn't allowed. For example, reading from an already closed Scanner."
                ),
                solution = localized(
                    es = "Revisa el orden de tus operaciones. ¿Cerraste algo antes de tiempo? ¿Llamaste a un método en el momento incorrecto?",
                    en = "Check the order of your operations. Did you close something too early? Did you call a method at the wrong time?"
                ),
                category = ErrorCategory.RUNTIME_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("NoSuchElementException"),
            explanation = ErrorExplanation(
                emoji = "🔍",
                title = localized(
                    es = "Elemento No Encontrado",
                    en = "No Such Element"
                ),
                meaning = localized(
                    es = "Intentaste obtener un elemento que no existe. Común al usar Scanner.next() sin datos o Iterator sin más elementos.",
                    en = "You tried to get an element that doesn't exist. Common when using Scanner.next() without data or Iterator with no more elements."
                ),
                solution = localized(
                    es = "Usa hasNext(), hasNextLine() o hasNextInt() antes de leer. Comprueba que la colección no esté vacía.",
                    en = "Use hasNext(), hasNextLine() or hasNextInt() before reading. Check that the collection is not empty."
                ),
                category = ErrorCategory.RUNTIME_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("UnsupportedOperationException"),
            explanation = ErrorExplanation(
                emoji = "🚧",
                title = localized(
                    es = "Operación No Soportada",
                    en = "Unsupported Operation"
                ),
                meaning = localized(
                    es = "Intentaste hacer algo que esa estructura no permite. Por ejemplo, modificar una lista inmutable creada con Arrays.asList().",
                    en = "You tried to do something that structure doesn't allow. For example, modifying an immutable list created with Arrays.asList()."
                ),
                solution = localized(
                    es = "Crea una copia modificable: new ArrayList<>(Arrays.asList(...)) en lugar de usar la lista directamente.",
                    en = "Create a modifiable copy: new ArrayList<>(Arrays.asList(...)) instead of using the list directly."
                ),
                category = ErrorCategory.RUNTIME_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("NegativeArraySizeException"),
            explanation = ErrorExplanation(
                emoji = "📐",
                title = localized(
                    es = "Tamaño de Array Negativo",
                    en = "Negative Array Size"
                ),
                meaning = localized(
                    es = "Intentaste crear un array con tamaño negativo (ej: new int[-5]).",
                    en = "You tried to create an array with negative size (e.g., new int[-5])."
                ),
                solution = localized(
                    es = "Revisa la variable que usas como tamaño del array. Asegúrate de que siempre sea >= 0.",
                    en = "Check the variable you use as array size. Make sure it's always >= 0."
                ),
                category = ErrorCategory.RUNTIME_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("ArrayStoreException"),
            explanation = ErrorExplanation(
                emoji = "📦",
                title = localized(
                    es = "Error al Guardar en Array",
                    en = "Array Store Error"
                ),
                meaning = localized(
                    es = "Intentaste guardar un objeto de tipo incorrecto en un array. Por ejemplo, guardar un Integer en un String[].",
                    en = "You tried to store an object of the wrong type in an array. For example, storing an Integer in a String[]."
                ),
                solution = localized(
                    es = "Revisa el tipo del array y el tipo del objeto que intentas guardar. Deben ser compatibles.",
                    en = "Check the array type and the type of object you're trying to store. They must be compatible."
                ),
                category = ErrorCategory.RUNTIME_ERROR
            )
        ))

        // ═══════════════════════════════════════════════════════════════
        // COMPILE ERRORS
        // ═══════════════════════════════════════════════════════════════

        add(ErrorPattern(
            regex = Regex("cannot find symbol|no se puede encontrar el símbolo", RegexOption.IGNORE_CASE),
            explanation = ErrorExplanation(
                emoji = "🔍",
                title = localized(
                    es = "Símbolo no encontrado (Error de Compilación)",
                    en = "Cannot Find Symbol (Compilation Error)"
                ),
                meaning = localized(
                    es = "Java no reconoce una palabra que has escrito. Puede ser una variable que no has declarado o un método que no existe.",
                    en = "Java doesn't recognize a word you wrote. It could be a variable you haven't declared or a method that doesn't exist."
                ),
                solution = localized(
                    es = "1. Revisa si has escrito bien el nombre (¿Mayúsculas/Minúsculas?).\n2. Asegúrate de haber declarado la variable. ¿La borraste sin querer?\n3. ¿Te falta algún import?",
                    en = "1. Check if you spelled the name correctly (uppercase/lowercase?).\n2. Make sure you declared the variable. Did you accidentally delete it?\n3. Are you missing an import?"
                ),
                category = ErrorCategory.COMPILE_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("variable.*might not have been initialized|variable.*no se ha inicializado|podría no haberse inicializado", RegexOption.IGNORE_CASE),
            explanation = ErrorExplanation(
                emoji = "⚠️",
                title = localized(
                    es = "Variable no inicializada (Error de Compilación)",
                    en = "Variable Not Initialized (Compilation Error)"
                ),
                meaning = localized(
                    es = "Estás intentando usar una variable que no tiene ningún valor guardado todavía.",
                    en = "You're trying to use a variable that doesn't have any value stored yet."
                ),
                solution = localized(
                    es = "Asegúrate de darle un valor a la variable antes de usarla (ej: int x = 0;).\n¿Quizás borraste la línea donde le dabas valor?",
                    en = "Make sure to give the variable a value before using it (e.g., int x = 0;).\nMaybe you deleted the line where you assigned its value?"
                ),
                category = ErrorCategory.COMPILE_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("incompatible types|tipos incompatibles", RegexOption.IGNORE_CASE),
            explanation = ErrorExplanation(
                emoji = "❌",
                title = localized(
                    es = "Tipos Incompatibles (Error de Compilación)",
                    en = "Incompatible Types (Compilation Error)"
                ),
                meaning = localized(
                    es = "Intentas guardar un tipo de dato en una variable de otro tipo (ej: guardar texto \"Hola\" en una variable 'int').",
                    en = "You're trying to store one data type in a variable of another type (e.g., storing text \"Hello\" in an 'int' variable)."
                ),
                solution = localized(
                    es = "1. Cambia el tipo de la variable para que coincida.\n2. Convierte el valor antes de guardarlo (ej: Integer.parseInt()).",
                    en = "1. Change the variable type to match.\n2. Convert the value before storing it (e.g., Integer.parseInt())."
                ),
                category = ErrorCategory.COMPILE_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("missing return statement|falta.*sentencia return|falta.*return", RegexOption.IGNORE_CASE),
            explanation = ErrorExplanation(
                emoji = "↩️",
                title = localized(
                    es = "Falta Return (Error de Compilación)",
                    en = "Missing Return Statement (Compilation Error)"
                ),
                meaning = localized(
                    es = "Tu función promete devolver un valor (ej: 'int', 'String'), pero no tiene un 'return' al final o en todos los casos posibles.",
                    en = "Your function promises to return a value (e.g., 'int', 'String'), but doesn't have a 'return' at the end or in all possible cases."
                ),
                solution = localized(
                    es = "Asegúrate de que todas las rutas de tu código terminen con 'return valor;'.",
                    en = "Make sure all paths in your code end with 'return value;'."
                ),
                category = ErrorCategory.COMPILE_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("unreachable statement|sentencia inaccesible|código inalcanzable", RegexOption.IGNORE_CASE),
            explanation = ErrorExplanation(
                emoji = "🚫",
                title = localized(
                    es = "Código Inaccesible (Error de Compilación)",
                    en = "Unreachable Statement (Compilation Error)"
                ),
                meaning = localized(
                    es = "Has escrito código después de un 'return', 'break' o 'continue' que nunca se ejecutará.",
                    en = "You wrote code after a 'return', 'break' or 'continue' that will never execute."
                ),
                solution = localized(
                    es = "Mueve o borra el código que está después de la instrucción de salida.",
                    en = "Move or delete the code that's after the exit statement."
                ),
                category = ErrorCategory.COMPILE_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("non-static method.*static context|método no.?estático.*contexto estático", RegexOption.IGNORE_CASE),
            explanation = ErrorExplanation(
                emoji = "🗿",
                title = localized(
                    es = "Static vs No-Static (Error de Compilación)",
                    en = "Static vs Non-Static (Compilation Error)"
                ),
                meaning = localized(
                    es = "Estás intentando usar una función o variable normal (de instancia) dentro de una función estática (como 'main').",
                    en = "You're trying to use a regular function or variable (instance) inside a static function (like 'main')."
                ),
                solution = localized(
                    es = "1. Haz que lo que llamas sea también 'static'.\n2. O crea un objeto de la clase (new Clase()) para usarlo.",
                    en = "1. Make what you're calling 'static' too.\n2. Or create an object of the class (new Class()) to use it."
                ),
                category = ErrorCategory.COMPILE_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("non-static variable.*static context|variable no.?estática.*contexto estático", RegexOption.IGNORE_CASE),
            explanation = ErrorExplanation(
                emoji = "🗿",
                title = localized(
                    es = "Variable No-Static en Contexto Static",
                    en = "Non-Static Variable in Static Context"
                ),
                meaning = localized(
                    es = "Estás intentando usar una variable de instancia dentro de un método estático (como 'main').",
                    en = "You're trying to use an instance variable inside a static method (like 'main')."
                ),
                solution = localized(
                    es = "1. Declara la variable como 'static'.\n2. O crea un objeto de la clase y accede a la variable a través de él.",
                    en = "1. Declare the variable as 'static'.\n2. Or create an object of the class and access the variable through it."
                ),
                category = ErrorCategory.COMPILE_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("is abstract.*cannot be instantiated|es abstract.*no se puede instanciar|no se puede crear una instancia", RegexOption.IGNORE_CASE),
            explanation = ErrorExplanation(
                emoji = "👻",
                title = localized(
                    es = "Clase Abstracta (Error de Compilación)",
                    en = "Abstract Class (Compilation Error)"
                ),
                meaning = localized(
                    es = "Intentaste crear un objeto (new Clase()) de una clase que es abstracta o una interfaz.",
                    en = "You tried to create an object (new Class()) from a class that is abstract or an interface."
                ),
                solution = localized(
                    es = "No puedes crear objetos directos de abstractas. Debes crear una clase hija que herede de ella y crear el objeto de la hija.",
                    en = "You can't create direct objects from abstract classes. You must create a child class that inherits from it and create the object from the child."
                ),
                category = ErrorCategory.COMPILE_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("not a statement|no es una sentencia", RegexOption.IGNORE_CASE),
            explanation = ErrorExplanation(
                emoji = "⚠️",
                title = localized(
                    es = "Sentencia no válida (Error de Compilación)",
                    en = "Not a Statement (Compilation Error)"
                ),
                meaning = localized(
                    es = "Has escrito algo que Java no entiende como una instrucción completa. A veces pasa por olvidar paréntesis o escribir código \"suelto\".",
                    en = "You wrote something Java doesn't understand as a complete instruction. Sometimes happens when forgetting parentheses or writing \"loose\" code."
                ),
                solution = localized(
                    es = "Revisa la línea. ¿Falta un igual (=)? ¿Has puesto un nombre de variable sin hacer nada con él?",
                    en = "Check the line. Missing an equals (=)? Did you put a variable name without doing anything with it?"
                ),
                category = ErrorCategory.COMPILE_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("';' expected|se esperaba ';'|falta ';'", RegexOption.IGNORE_CASE),
            explanation = ErrorExplanation(
                emoji = "📝",
                title = localized(
                    es = "Falta punto y coma (Error de Compilación)",
                    en = "Missing Semicolon (Compilation Error)"
                ),
                meaning = localized(
                    es = "Se te ha olvidado cerrar una instrucción con ';'. Es como olvidar el punto final de una frase.",
                    en = "You forgot to close a statement with ';'. It's like forgetting the period at the end of a sentence."
                ),
                solution = localized(
                    es = "Añade un ';' al final de la línea indicada.",
                    en = "Add a ';' at the end of the indicated line."
                ),
                category = ErrorCategory.COMPILE_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("\\).*expected|se esperaba.*\\)", RegexOption.IGNORE_CASE),
            explanation = ErrorExplanation(
                emoji = "📝",
                title = localized(
                    es = "Falta paréntesis (Error de Compilación)",
                    en = "Missing Parenthesis (Compilation Error)"
                ),
                meaning = localized(
                    es = "Te falta cerrar un paréntesis en alguna parte del código.",
                    en = "You're missing a closing parenthesis somewhere in the code."
                ),
                solution = localized(
                    es = "Revisa que cada '(' tenga su ')' correspondiente. Cuenta los paréntesis de apertura y cierre.",
                    en = "Check that each '(' has its corresponding ')'. Count the opening and closing parentheses."
                ),
                category = ErrorCategory.COMPILE_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("\\}.*expected|se esperaba.*\\}", RegexOption.IGNORE_CASE),
            explanation = ErrorExplanation(
                emoji = "📝",
                title = localized(
                    es = "Falta llave (Error de Compilación)",
                    en = "Missing Brace (Compilation Error)"
                ),
                meaning = localized(
                    es = "Te falta cerrar una llave '}' en alguna parte del código.",
                    en = "You're missing a closing brace '}' somewhere in the code."
                ),
                solution = localized(
                    es = "Revisa que cada '{' tenga su '}' correspondiente. Usa la indentación para encontrar el bloque sin cerrar.",
                    en = "Check that each '{' has its corresponding '}'. Use indentation to find the unclosed block."
                ),
                category = ErrorCategory.COMPILE_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("class.*is public.*should be declared in a file named|clase.*es.*public.*debe declararse en un archivo", RegexOption.IGNORE_CASE),
            explanation = ErrorExplanation(
                emoji = "📄",
                title = localized(
                    es = "Nombre de archivo incorrecto",
                    en = "Incorrect File Name"
                ),
                meaning = localized(
                    es = "El nombre de tu clase pública no coincide con el nombre del archivo .java.",
                    en = "Your public class name doesn't match the .java file name."
                ),
                solution = localized(
                    es = "Renombra el archivo para que coincida exactamente con el nombre de la clase (incluyendo mayúsculas).",
                    en = "Rename the file to exactly match the class name (including uppercase)."
                ),
                category = ErrorCategory.COMPILE_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("reached end of file while parsing|se alcanzó el final del archivo|fin de archivo inesperado", RegexOption.IGNORE_CASE),
            explanation = ErrorExplanation(
                emoji = "📄",
                title = localized(
                    es = "Fin de archivo inesperado",
                    en = "Unexpected End of File"
                ),
                meaning = localized(
                    es = "Java llegó al final del archivo pero esperaba más código. Probablemente te falta cerrar una llave '}'.",
                    en = "Java reached the end of the file but expected more code. You're probably missing a closing brace '}'."
                ),
                solution = localized(
                    es = "Revisa que todas las llaves '{' tengan su '}' correspondiente. Cuenta las llaves de apertura y cierre.",
                    en = "Check that all braces '{' have their corresponding '}'. Count the opening and closing braces."
                ),
                category = ErrorCategory.COMPILE_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("illegal start of expression|inicio ilegal de expresión|comienzo ilegal", RegexOption.IGNORE_CASE),
            explanation = ErrorExplanation(
                emoji = "🚫",
                title = localized(
                    es = "Inicio de expresión ilegal",
                    en = "Illegal Start of Expression"
                ),
                meaning = localized(
                    es = "Java encontró algo donde no lo esperaba. Puede ser un modificador mal colocado o un error de sintaxis.",
                    en = "Java found something where it wasn't expected. Could be a misplaced modifier or syntax error."
                ),
                solution = localized(
                    es = "Revisa la línea anterior y la actual. ¿Olvidaste un ';'? ¿Pusiste 'public' o 'private' donde no debías?",
                    en = "Check the previous line and the current one. Did you forget a ';'? Did you put 'public' or 'private' where you shouldn't?"
                ),
                category = ErrorCategory.COMPILE_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("method.*in class.*cannot be applied|método.*en.*clase.*no se puede aplicar", RegexOption.IGNORE_CASE),
            explanation = ErrorExplanation(
                emoji = "📞",
                title = localized(
                    es = "Parámetros incorrectos en método",
                    en = "Wrong Method Parameters"
                ),
                meaning = localized(
                    es = "Estás llamando a un método con parámetros de tipo incorrecto o cantidad incorrecta.",
                    en = "You're calling a method with wrong parameter types or wrong number of parameters."
                ),
                solution = localized(
                    es = "Revisa la firma del método. ¿Cuántos parámetros espera? ¿De qué tipo son?",
                    en = "Check the method signature. How many parameters does it expect? What types are they?"
                ),
                category = ErrorCategory.COMPILE_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("constructor.*in class.*cannot be applied|constructor.*en.*clase.*no se puede aplicar", RegexOption.IGNORE_CASE),
            explanation = ErrorExplanation(
                emoji = "🏗️",
                title = localized(
                    es = "Parámetros incorrectos en constructor",
                    en = "Wrong Constructor Parameters"
                ),
                meaning = localized(
                    es = "Estás creando un objeto con parámetros que no coinciden con ningún constructor de la clase.",
                    en = "You're creating an object with parameters that don't match any constructor of the class."
                ),
                solution = localized(
                    es = "Revisa los constructores disponibles de la clase. ¿Cuántos parámetros esperan? ¿De qué tipo?",
                    en = "Check the available constructors of the class. How many parameters do they expect? What types?"
                ),
                category = ErrorCategory.COMPILE_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("cannot assign a value to final variable|no se puede asignar.*variable final", RegexOption.IGNORE_CASE),
            explanation = ErrorExplanation(
                emoji = "🔒",
                title = localized(
                    es = "Variable final no modificable",
                    en = "Cannot Modify Final Variable"
                ),
                meaning = localized(
                    es = "Intentaste cambiar el valor de una variable declarada como 'final'. Las variables final solo se pueden asignar una vez.",
                    en = "You tried to change the value of a variable declared as 'final'. Final variables can only be assigned once."
                ),
                solution = localized(
                    es = "Si necesitas cambiar el valor, quita el modificador 'final' de la declaración.",
                    en = "If you need to change the value, remove the 'final' modifier from the declaration."
                ),
                category = ErrorCategory.COMPILE_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("variable.*is already defined|variable.*ya está definida|ya se ha definido", RegexOption.IGNORE_CASE),
            explanation = ErrorExplanation(
                emoji = "👯",
                title = localized(
                    es = "Variable duplicada",
                    en = "Duplicate Variable"
                ),
                meaning = localized(
                    es = "Has declarado dos veces una variable con el mismo nombre en el mismo ámbito.",
                    en = "You declared a variable with the same name twice in the same scope."
                ),
                solution = localized(
                    es = "Cambia el nombre de una de las variables o elimina la declaración duplicada.",
                    en = "Change the name of one of the variables or remove the duplicate declaration."
                ),
                category = ErrorCategory.COMPILE_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("package.*does not exist|paquete.*no existe|el paquete.*no existe", RegexOption.IGNORE_CASE),
            explanation = ErrorExplanation(
                emoji = "📦",
                title = localized(
                    es = "Paquete no encontrado",
                    en = "Package Not Found"
                ),
                meaning = localized(
                    es = "Intentas importar un paquete que Java no puede encontrar.",
                    en = "You're trying to import a package that Java can't find."
                ),
                solution = localized(
                    es = "1. Revisa que el nombre del paquete esté bien escrito.\n2. ¿Añadiste la librería/dependencia al proyecto?\n3. ¿Está la clase en el classpath?",
                    en = "1. Check that the package name is spelled correctly.\n2. Did you add the library/dependency to the project?\n3. Is the class in the classpath?"
                ),
                category = ErrorCategory.COMPILE_ERROR
            )
        ))

        // ═══════════════════════════════════════════════════════════════
        // COMPILER WARNINGS
        // ═══════════════════════════════════════════════════════════════

        add(ErrorPattern(
            regex = Regex("unchecked call|unchecked cast|unchecked conversion"),
            explanation = ErrorExplanation(
                emoji = "⚠️",
                title = localized(
                    es = "Conversión sin verificar (Warning)",
                    en = "Unchecked Conversion (Warning)"
                ),
                meaning = localized(
                    es = "Estás usando una colección sin especificar el tipo genérico, o haciendo un cast que podría fallar en tiempo de ejecución.",
                    en = "You're using a collection without specifying the generic type, or making a cast that could fail at runtime."
                ),
                solution = localized(
                    es = "Usa genéricos: List<String> en lugar de List. Si el cast es necesario, añade @SuppressWarnings(\"unchecked\") con cuidado.",
                    en = "Use generics: List<String> instead of List. If the cast is necessary, carefully add @SuppressWarnings(\"unchecked\")."
                ),
                category = ErrorCategory.WARNING
            )
        ))

        add(ErrorPattern(
            regex = Regex("raw type|raw use of parameterized class"),
            explanation = ErrorExplanation(
                emoji = "⚠️",
                title = localized(
                    es = "Tipo sin parametrizar (Warning)",
                    en = "Raw Type (Warning)"
                ),
                meaning = localized(
                    es = "Estás usando una clase genérica sin especificar el tipo (ej: List en vez de List<String>).",
                    en = "You're using a generic class without specifying the type (e.g., List instead of List<String>)."
                ),
                solution = localized(
                    es = "Añade el tipo entre <>: ArrayList<String>, HashMap<Integer, String>, etc.",
                    en = "Add the type in <>: ArrayList<String>, HashMap<Integer, String>, etc."
                ),
                category = ErrorCategory.WARNING
            )
        ))

        add(ErrorPattern(
            regex = Regex("deprecated|@Deprecated"),
            explanation = ErrorExplanation(
                emoji = "🗑️",
                title = localized(
                    es = "Código Obsoleto (Warning)",
                    en = "Deprecated Code (Warning)"
                ),
                meaning = localized(
                    es = "Estás usando un método, clase o campo que está marcado como obsoleto. Funciona, pero podría eliminarse en futuras versiones.",
                    en = "You're using a method, class or field that is marked as deprecated. It works, but could be removed in future versions."
                ),
                solution = localized(
                    es = "Busca la alternativa moderna en la documentación. Suele indicarse con @see o en el JavaDoc.",
                    en = "Look for the modern alternative in the documentation. It's usually indicated with @see or in the JavaDoc."
                ),
                category = ErrorCategory.WARNING
            )
        ))

        add(ErrorPattern(
            regex = Regex("unused|never used|is never assigned"),
            explanation = ErrorExplanation(
                emoji = "🧹",
                title = localized(
                    es = "Código sin usar (Warning)",
                    en = "Unused Code (Warning)"
                ),
                meaning = localized(
                    es = "Has declarado una variable, método o import que nunca usas en tu código.",
                    en = "You declared a variable, method or import that you never use in your code."
                ),
                solution = localized(
                    es = "Si no lo necesitas, bórralo para mantener el código limpio. Si lo vas a usar después, ignora el warning.",
                    en = "If you don't need it, delete it to keep the code clean. If you'll use it later, ignore the warning."
                ),
                category = ErrorCategory.WARNING
            )
        ))

        add(ErrorPattern(
            regex = Regex("possible loss of precision|lossy conversion"),
            explanation = ErrorExplanation(
                emoji = "📉",
                title = localized(
                    es = "Posible pérdida de precisión (Warning)",
                    en = "Possible Loss of Precision (Warning)"
                ),
                meaning = localized(
                    es = "Estás convirtiendo un número de mayor precisión a uno menor (ej: double a int), lo que puede perder decimales.",
                    en = "You're converting a number of higher precision to lower (e.g., double to int), which may lose decimals."
                ),
                solution = localized(
                    es = "Si la pérdida es aceptable, usa cast explícito: (int) miDouble. Si no, cambia el tipo de la variable destino.",
                    en = "If the loss is acceptable, use explicit cast: (int) myDouble. If not, change the destination variable type."
                ),
                category = ErrorCategory.WARNING
            )
        ))

        add(ErrorPattern(
            regex = Regex("division by zero"),
            explanation = ErrorExplanation(
                emoji = "➗",
                title = localized(
                    es = "Posible división por cero (Warning)",
                    en = "Possible Division by Zero (Warning)"
                ),
                meaning = localized(
                    es = "El compilador detectó que podrías dividir por cero en algún caso.",
                    en = "The compiler detected you might divide by zero in some case."
                ),
                solution = localized(
                    es = "Añade una comprobación: if (divisor != 0) antes de la división.",
                    en = "Add a check: if (divisor != 0) before the division."
                ),
                category = ErrorCategory.WARNING
            )
        ))

        add(ErrorPattern(
            regex = Regex("redundant cast|unnecessary cast"),
            explanation = ErrorExplanation(
                emoji = "🔄",
                title = localized(
                    es = "Cast innecesario (Warning)",
                    en = "Unnecessary Cast (Warning)"
                ),
                meaning = localized(
                    es = "Estás haciendo un cast que no es necesario porque el tipo ya es correcto.",
                    en = "You're making a cast that isn't necessary because the type is already correct."
                ),
                solution = localized(
                    es = "Elimina el cast: en lugar de (String) miString, solo usa miString.",
                    en = "Remove the cast: instead of (String) myString, just use myString."
                ),
                category = ErrorCategory.WARNING
            )
        ))

        add(ErrorPattern(
            regex = Regex("serialVersionUID"),
            explanation = ErrorExplanation(
                emoji = "🔢",
                title = localized(
                    es = "Falta serialVersionUID (Warning)",
                    en = "Missing serialVersionUID (Warning)"
                ),
                meaning = localized(
                    es = "Tu clase implementa Serializable pero no tiene un serialVersionUID definido.",
                    en = "Your class implements Serializable but doesn't have a serialVersionUID defined."
                ),
                solution = localized(
                    es = "Añade: private static final long serialVersionUID = 1L; a tu clase.",
                    en = "Add: private static final long serialVersionUID = 1L; to your class."
                ),
                category = ErrorCategory.WARNING
            )
        ))

        // ═══════════════════════════════════════════════════════════════
        // JUNIT ERRORS
        // ═══════════════════════════════════════════════════════════════

        add(ErrorPattern(
            regex = Regex("AssertionError.*expected:.*<(.*)>.*but was:.*<(.*)>"),
            explanation = ErrorExplanation(
                emoji = "❌",
                title = localized(
                    es = "Test Fallido: Valor incorrecto",
                    en = "Test Failed: Wrong Value"
                ),
                meaning = localized(
                    es = "Tu test esperaba un valor pero el código devolvió otro diferente.",
                    en = "Your test expected one value but the code returned a different one."
                ),
                solution = localized(
                    es = "Revisa la lógica de tu código. El resultado no coincide con lo esperado. ¿Hay un error en el cálculo o en el test?",
                    en = "Check your code logic. The result doesn't match what was expected. Is there an error in the calculation or in the test?"
                ),
                category = ErrorCategory.JUNIT_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("AssertionError.*expected.*true.*but.*false|AssertionError.*expected.*false.*but.*true"),
            explanation = ErrorExplanation(
                emoji = "❌",
                title = localized(
                    es = "Test Fallido: Booleano incorrecto",
                    en = "Test Failed: Wrong Boolean"
                ),
                meaning = localized(
                    es = "Tu test esperaba true/false pero el código devolvió lo contrario.",
                    en = "Your test expected true/false but the code returned the opposite."
                ),
                solution = localized(
                    es = "Revisa la condición que estás evaluando. ¿La lógica está invertida? ¿Falta un '!' o sobra uno?",
                    en = "Check the condition you're evaluating. Is the logic inverted? Missing a '!' or have an extra one?"
                ),
                category = ErrorCategory.JUNIT_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("AssertionError.*expected.*null|AssertionError.*but was.*null"),
            explanation = ErrorExplanation(
                emoji = "❌",
                title = localized(
                    es = "Test Fallido: Null inesperado",
                    en = "Test Failed: Unexpected Null"
                ),
                meaning = localized(
                    es = "Tu código devolvió null cuando no debía, o esperabas null y recibiste un valor.",
                    en = "Your code returned null when it shouldn't, or you expected null and received a value."
                ),
                solution = localized(
                    es = "Revisa la inicialización de objetos y los returns de tus métodos. ¿Olvidaste crear o retornar algo?",
                    en = "Check object initialization and your method returns. Did you forget to create or return something?"
                ),
                category = ErrorCategory.JUNIT_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("AssertionError.*arrays.*differ"),
            explanation = ErrorExplanation(
                emoji = "❌",
                title = localized(
                    es = "Test Fallido: Arrays diferentes",
                    en = "Test Failed: Arrays Differ"
                ),
                meaning = localized(
                    es = "Los arrays no son iguales. Pueden diferir en tamaño o en alguno de sus elementos.",
                    en = "The arrays are not equal. They may differ in size or in some of their elements."
                ),
                solution = localized(
                    es = "Compara elemento por elemento para encontrar la diferencia. Revisa también que el tamaño sea el correcto.",
                    en = "Compare element by element to find the difference. Also check that the size is correct."
                ),
                category = ErrorCategory.JUNIT_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("ComparisonFailure"),
            explanation = ErrorExplanation(
                emoji = "❌",
                title = localized(
                    es = "Test Fallido: Strings diferentes",
                    en = "Test Failed: Strings Differ"
                ),
                meaning = localized(
                    es = "Los textos (Strings) no son iguales. Puede haber diferencias en mayúsculas, espacios o caracteres.",
                    en = "The texts (Strings) are not equal. There may be differences in case, spaces or characters."
                ),
                solution = localized(
                    es = "Compara los strings carácter a carácter. Cuidado con espacios invisibles, saltos de línea o diferencias de mayúsculas.",
                    en = "Compare the strings character by character. Watch out for invisible spaces, line breaks or case differences."
                ),
                category = ErrorCategory.JUNIT_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("@Before.*Exception|@BeforeEach.*Exception|setUp.*Exception"),
            explanation = ErrorExplanation(
                emoji = "🔧",
                title = localized(
                    es = "Error en Setup del Test",
                    en = "Test Setup Error"
                ),
                meaning = localized(
                    es = "El método de preparación (@Before/@BeforeEach) falló antes de ejecutar el test.",
                    en = "The setup method (@Before/@BeforeEach) failed before running the test."
                ),
                solution = localized(
                    es = "Revisa tu método de setup. Algo que inicializas ahí está fallando (¿null? ¿archivo no encontrado?).",
                    en = "Check your setup method. Something you initialize there is failing (null? file not found?)."
                ),
                category = ErrorCategory.JUNIT_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("@After.*Exception|@AfterEach.*Exception|tearDown.*Exception"),
            explanation = ErrorExplanation(
                emoji = "🧹",
                title = localized(
                    es = "Error en Limpieza del Test",
                    en = "Test Cleanup Error"
                ),
                meaning = localized(
                    es = "El método de limpieza (@After/@AfterEach) falló después de ejecutar el test.",
                    en = "The cleanup method (@After/@AfterEach) failed after running the test."
                ),
                solution = localized(
                    es = "Revisa tu método de limpieza. Probablemente algo que intentas cerrar o limpiar ya no existe.",
                    en = "Check your cleanup method. Probably something you're trying to close or clean up no longer exists."
                ),
                category = ErrorCategory.JUNIT_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("test.*timed out|timeout"),
            explanation = ErrorExplanation(
                emoji = "⏱️",
                title = localized(
                    es = "Test Timeout: Demasiado lento",
                    en = "Test Timeout: Too Slow"
                ),
                meaning = localized(
                    es = "Tu test tardó demasiado en ejecutarse. Puede haber un bucle infinito o una operación muy lenta.",
                    en = "Your test took too long to execute. There may be an infinite loop or a very slow operation."
                ),
                solution = localized(
                    es = "Busca bucles infinitos o llamadas a recursos externos lentos. Si el código es correcto, aumenta el timeout del test.",
                    en = "Look for infinite loops or slow external resource calls. If the code is correct, increase the test timeout."
                ),
                category = ErrorCategory.JUNIT_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("Expected exception|should have thrown|expected.*to throw"),
            explanation = ErrorExplanation(
                emoji = "💥",
                title = localized(
                    es = "Test Fallido: Excepción esperada no lanzada",
                    en = "Test Failed: Expected Exception Not Thrown"
                ),
                meaning = localized(
                    es = "El test esperaba que el código lanzara una excepción, pero no la lanzó.",
                    en = "The test expected the code to throw an exception, but it didn't."
                ),
                solution = localized(
                    es = "Revisa las condiciones que deberían causar la excepción. ¿El código maneja el caso sin lanzar error?",
                    en = "Check the conditions that should cause the exception. Does the code handle the case without throwing an error?"
                ),
                category = ErrorCategory.JUNIT_ERROR
            )
        ))

        add(ErrorPattern(
            regex = Regex("No tests found|No runnable methods"),
            explanation = ErrorExplanation(
                emoji = "🔍",
                title = localized(
                    es = "No se encontraron tests",
                    en = "No Tests Found"
                ),
                meaning = localized(
                    es = "JUnit no encontró ningún método de test que ejecutar.",
                    en = "JUnit couldn't find any test methods to run."
                ),
                solution = localized(
                    es = "Asegúrate de que tus métodos de test tienen la anotación @Test y son públicos (JUnit 4) o package-private (JUnit 5).",
                    en = "Make sure your test methods have the @Test annotation and are public (JUnit 4) or package-private (JUnit 5)."
                ),
                category = ErrorCategory.JUNIT_ERROR
            )
        ))
    }

    /**
     * Busca una explicación para el mensaje de error dado.
     */
    fun explain(errorMessage: String): String? {
        val settings = try {
            CodeLingoSettings.getInstance()
        } catch (e: Exception) {
            null
        }

        val language = settings?.language ?: Language.SPANISH

        val pattern = errorPatterns.firstOrNull { pattern ->
            val matches = pattern.regex.containsMatchIn(errorMessage)
            if (!matches) return@firstOrNull false

            // Check if category is enabled
            when (pattern.explanation.category) {
                ErrorCategory.RUNTIME_ERROR -> settings?.enableRuntimeErrors ?: true
                ErrorCategory.COMPILE_ERROR -> settings?.enableCompileErrors ?: true
                ErrorCategory.WARNING -> settings?.enableWarnings ?: true
                ErrorCategory.JUNIT_ERROR -> settings?.enableJUnitErrors ?: true
            }
        }

        return pattern?.explanation?.format(language)
    }

    /**
     * Explica cómo leer un stack trace.
     */
    fun explainStackTrace(stackTrace: String): String? {
        val settings = try {
            CodeLingoSettings.getInstance()
        } catch (e: Exception) {
            null
        }

        if (settings?.enableStackTraceExplainer == false) return null

        val language = settings?.language ?: Language.SPANISH
        val lines = stackTrace.lines().filter { it.trim().startsWith("at ") }

        if (lines.size < 2) return null

        val explanation = if (language == Language.SPANISH) {
            buildString {
                appendLine("📚 Cómo leer este Stack Trace:")
                appendLine()
                appendLine("Lee de ABAJO hacia ARRIBA para entender el flujo:")
                appendLine()
                lines.reversed().take(5).forEachIndexed { index, line ->
                    val cleanLine = line.trim().removePrefix("at ")
                    appendLine("${index + 1}. $cleanLine")
                }
                if (lines.size > 5) {
                    appendLine("   ... (${lines.size - 5} líneas más)")
                }
                appendLine()
                appendLine("💡 El error ocurrió en la PRIMERA línea (arriba del todo).")
                appendLine("   Las líneas de abajo muestran cómo llegaste ahí.")
            }
        } else {
            buildString {
                appendLine("📚 How to read this Stack Trace:")
                appendLine()
                appendLine("Read from BOTTOM to TOP to understand the flow:")
                appendLine()
                lines.reversed().take(5).forEachIndexed { index, line ->
                    val cleanLine = line.trim().removePrefix("at ")
                    appendLine("${index + 1}. $cleanLine")
                }
                if (lines.size > 5) {
                    appendLine("   ... (${lines.size - 5} more lines)")
                }
                appendLine()
                appendLine("💡 The error occurred at the FIRST line (at the very top).")
                appendLine("   The lines below show how you got there.")
            }
        }

        return explanation
    }
}
