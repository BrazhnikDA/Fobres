package com.brazhnik.fobres.utilities

class Validator {
    private var listError: HashMap<Int, String> = hashMapOf()

    // Длина от 3 до 10 символов
    // Не должен содержать первый символ цифра
    fun isValidLogin(text: String): HashMap<Int, String> {
        listError.clear()
        if (text.isEmpty()) {
            listError.put(LOGIN_IS_EMPTY_KEY, LOGIN_IS_EMPTY_ERROR)
            return listError
        }
        if (text.length < 3) {
            listError.put(LOGIN_IS_SMALL_KEY, LOGIN_IS_SMALL_ERROR)
        }
        if (text.length > 10) {
            listError.put(LOGIN_IS_LARGE_KEY, LOGIN_IS_LARGE_ERROR)
        }
        if (text[0].code in 48..57) {
            listError.put(LOGIN_FIRST_SYMBOL_IS_DIGIT_KEY, LOGIN_FIRST_SYMBOL_IS_DIGIT_ERROR)
        }

        return listError
    }

    // Длина от 4 до 50 символов
    // Должен содержать хотя бы:
    // 1 цифру
    // 1 спец символ
    // 1 заглавную букву
    fun isValidPassword(text: String): HashMap<Int, String> {
        var isNum = false
        var isSpecSym = false
        var isCapital = false
        listError.clear()
        if(text.length == 0) {
            listError.put(PASSWORD_IS_EMPTY_KEY, PASSWORD_IS_EMPTY_ERROR)
            return listError
        }
        if (text.length < 4) {
            listError.put(PASSWORD_IS_SMALL_KEY, PASSWORD_IS_SMALL_ERROR)
        }
        if (text.length > 50) {
            listError.put(PASSWORD_IS_LARGE_KEY, PASSWORD_IS_LARGE_ERROR)
        }
        for (char in text) {
            if (isNum && isSpecSym && isCapital)
                break
            if (char.code in 48..57) {
                isNum = true
            }
            if (char.code in 1..47 || char.code in 58..64 || char.code in 123..126) {
                isSpecSym = true
            }
            if (char.code in 65..90 || char.code in 128..159) {
                isCapital = true
            }
        }

        if (!isNum) {
            listError.put(PASSWORD_NOT_CONTAINS_DIGIT_KEY, PASSWORD_NOT_CONTAINS_DIGIT_ERROR)
        }
        if (!isCapital) {
            listError.put(
                PASSWORD_NOT_CONTAINS_CAPITAL_LETTER_KEY,
                PASSWORD_NOT_CONTAINS_CAPITAL_LETTER_ERROR
            )
        }
        if (!isSpecSym) {
            listError.put(
                PASSWORD_NOT_CONTAINS_SPECIAL_SYMBOL_KEY,
                PASSWORD_NOT_CONTAINS_SPECIAL_SYMBOL_ERROR
            )
        }

        return listError
    }

    // Длинна от 2 символов до 15
    // Не должно содержать спец символы
    // Не должно содержать цифры
    fun isValidName(text: String): HashMap<Int, String> {
        listError.clear()
        if(text.length == 0) {

        }
        if(text.length < 2) {

        }
        if(text.length < 15) {

        }

        return listError
    }

    companion object {
        // Key for error

        // LOGIN
        // KEY
        const val LOGIN_IS_EMPTY_KEY = 1
        const val LOGIN_IS_SMALL_KEY = 10
        const val LOGIN_IS_LARGE_KEY = 11
        const val LOGIN_FIRST_SYMBOL_IS_DIGIT_KEY = 12

        // ERROR
        const val LOGIN_IS_EMPTY_ERROR = "Поле логин пустое"
        const val LOGIN_IS_SMALL_ERROR = "Логин содержит менее 3 символов"
        const val LOGIN_IS_LARGE_ERROR = "Логин содержит более 10 символов"
        const val LOGIN_FIRST_SYMBOL_IS_DIGIT_ERROR = "Первая буква логина - это цифра"

        // PASSWORD
        // KEY
        const val PASSWORD_IS_EMPTY_KEY = 2
        const val PASSWORD_IS_SMALL_KEY = 20
        const val PASSWORD_IS_LARGE_KEY = 21
        const val PASSWORD_NOT_CONTAINS_DIGIT_KEY = 22
        const val PASSWORD_NOT_CONTAINS_SPECIAL_SYMBOL_KEY = 23
        const val PASSWORD_NOT_CONTAINS_CAPITAL_LETTER_KEY = 24

        //ERROR
        const val PASSWORD_IS_EMPTY_ERROR = "Поле пароль пустое"
        const val PASSWORD_IS_SMALL_ERROR = "Пароль должен содержать минимум 4 символа"
        const val PASSWORD_IS_LARGE_ERROR = "Пароль не может быть более 50 символов"
        const val PASSWORD_NOT_CONTAINS_DIGIT_ERROR = "Пароль НЕ содержит цифру"
        const val PASSWORD_NOT_CONTAINS_SPECIAL_SYMBOL_ERROR =
            "Пароль НЕ содержит специальный символ"
        const val PASSWORD_NOT_CONTAINS_CAPITAL_LETTER_ERROR = "Пароль НЕ содержит заглавную букву"

        // NAME
        // KEY
        const val NAME_IS_SMALL_KEY = 30
        const val NAME_IS_LARGE_KEY = 31
        const val NAME_CONTAINS_SPECIAL_SYMBOL_KEY = 32
        const val NAME_CONTAINS_DIGIT_KEY = 33

        // ERROR
        const val NAME_IS_SMALL_ERROR = "Имя должно содержать не менее 2 символов"
        const val NAME_IS_LARGE_ERROR = "Имя не может содержать более 15 символов"
        const val NAME_CONTAINS_SPECIAL_SYMBOL_ERROR = "Имя содержит спец. символы"
        const val NAME_CONTAINS_DIGIT_ERROR = "Имя содержит цифру"
    }
}