package com.brazhnik.fobres

import com.brazhnik.fobres.utilities.Validator
import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun login_isCorrect() {
        //Arrange test
        val validator = Validator()
        val inputString = "login10"
        val sizeOutput = 0

        //Act test
        val listError = validator.isValidLogin(inputString)

        //Assert test
        assertEquals(listError.size, sizeOutput)
    }

    @Test
    fun login_error_empty() {
        //Arrange test
        val validator = Validator()
        val inputString = ""
        val sizeOutput = 1

        //Act test
        val listError = validator.isValidLogin(inputString)

        //Assert test
        assertEquals(listError.size, sizeOutput)
    }

    @Test
    fun login_error_is_small() {
        //Arrange test
        val validator = Validator()
        val inputString = "lo"
        val outputString = Validator.LOGIN_IS_SMALL_ERROR

        //Act test
        val listError = validator.isValidLogin(inputString)

        //Assert test
        assertEquals(listError.getValue(Validator.LOGIN_IS_SMALL_KEY), outputString)
    }

    @Test
    fun login_error_is_large() {
        //Arrange test
        val validator = Validator()
        val inputString = "login123fdsfdsfdsfdsfdsfsdfsdfsdfsfdsfsdf"
        val outputString = Validator.LOGIN_IS_LARGE_ERROR

        //Act test
        val listError = validator.isValidLogin(inputString)

        //Assert test
        assertEquals(listError.getValue(Validator.LOGIN_IS_LARGE_KEY), outputString)
    }

    @Test
    fun login_error_first_sym_num() {
        //Arrange test
        val validator = Validator()
        val inputString = "1login10"
        val outputString = Validator.LOGIN_FIRST_SYMBOL_IS_DIGIT_ERROR

        //Act test
        val listError = validator.isValidLogin(inputString)

        //Assert test
        assertEquals(listError.getValue(Validator.LOGIN_FIRST_SYMBOL_IS_DIGIT_KEY), outputString)
    }

    @Test
    fun password_isCorrect() {
        //Arrange test
        val validator = Validator()
        val inputString = "Admin_10"
        val sizeOutput = 0

        //Act test
        val listError = validator.isValidPassword(inputString)

        //Assert test
        assertEquals(listError.size, sizeOutput)
    }

    @Test
    fun password_error_is_small() {
        //Arrange test
        val validator = Validator()
        val inputString = "pas"
        val outputString = Validator.PASSWORD_IS_SMALL_ERROR

        //Act test
        val listError = validator.isValidPassword(inputString)

        //Assert test
        assertEquals(listError.getValue(Validator.PASSWORD_IS_SMALL_KEY), outputString)
    }

    @Test
    fun password_error_is_large() {
        //Arrange test
        val validator = Validator()
        val inputString = "passwooooooooooooooooorrrrrrrrrrrrrrrrrrrrddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
        val outputString = Validator.PASSWORD_IS_LARGE_ERROR

        //Act test
        val listError = validator.isValidPassword(inputString)

        //Assert test
        assertEquals(listError.getValue(Validator.PASSWORD_IS_LARGE_KEY), outputString)
    }

    @Test
    fun password_error_not_contains_digit() {
        //Arrange test
        val validator = Validator()
        val inputString = "admin"
        val outputString = Validator.PASSWORD_NOT_CONTAINS_DIGIT_ERROR

        //Act test
        val listError = validator.isValidPassword(inputString)

        //Assert test
        assertEquals(listError.getValue(Validator.PASSWORD_NOT_CONTAINS_DIGIT_KEY), outputString)
    }

    @Test
    fun password_error_not_contains_special_symbol() {
        //Arrange test
        val validator = Validator()
        val inputString = "admin"
        val outputString = Validator.PASSWORD_NOT_CONTAINS_DIGIT_ERROR

        //Act test
        val listError = validator.isValidPassword(inputString)

        //Assert test
        assertEquals(listError.getValue(Validator.PASSWORD_NOT_CONTAINS_DIGIT_KEY), outputString)
    }
}