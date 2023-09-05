import java.util.*
import kotlin.random.Random

fun main() {
    val letterMap = mapOf(
        'й' to "цыф",
        'ц' to "йыву",
        'у' to "цывк",
        'к' to "увае",
        'е' to "капн",
        'н' to "епрг",
        'г' to "нрош",
        'ш' to "голщ",
        'щ' to "шлдз",
        'з' to "щджх",
        'х' to "зжэъ",
        'ъ' to "хэ/",
        'ф' to "йцычя",
        'ы' to "фцвчя",
        'в' to "ыуасч",
        'а' to "вкпмс",
        'п' to "аерим",
        'р' to "пноти",
        'о' to "ргльт",
        'л' to "ошдбь",
        'д' to "блщжю",
        'ж' to "юдзэ.",
        'э' to "ъхзжю.",
        'я' to "фыч",
        'ч' to "яывс",
        'с' to "чвам",
        'м' to "сапи",
        'и' to "мпрт",
        'т' to "ироь",
        'ь' to "толб",
        'б' to "ьлдю",
        'ю' to "бдж."
    )

    val scanner = Scanner(System.`in`)
    val lines = mutableListOf<String>()

    println("Введите текст (для завершения ввода введите пустую строку):")

    while (true) {
        val line = scanner.nextLine()
        if (line.isEmpty()) {
            break
        }
        lines.add(line)
    }

    println("Введенный текст:")
    for (line in lines) {
        println(processText(line, letterMap, true, 1))
    }
}

fun processText(text: String, letterMap: Map<Char, String>, onlyOnePerWord: Boolean, minLength: Int): String {
    val random = Random.Default
    val words = text.split(" ")

    val processedWords = words.map { word ->
        var wordHasReplaced = false
        val processedWord = word.map { char ->
            if (char.isLetter() && char !in "AEIOUaeiou") {
                val replacementString = letterMap[char] ?: ""
                if (replacementString.isNotEmpty() && (!onlyOnePerWord || !wordHasReplaced) && (word
                        .replace(" ", "")
                        .replace(",", "").length > minLength
                            || Random.nextInt(11) == 10)) {
                    val randomIndex = random.nextInt(replacementString.length)
                    val replacementChar = replacementString[randomIndex]
                    wordHasReplaced = true
                    replacementChar
                } else {
                    char
                }
            } else {
                char
            }
        }.joinToString("")
        processedWord
    }

    return processedWords.joinToString(" ")
}