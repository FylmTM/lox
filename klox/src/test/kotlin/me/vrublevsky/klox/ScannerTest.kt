package me.vrublevsky.klox

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class ScannerTest {

    @Test
    fun twoCharOperators() {
        val source = """
            ! != = == < <= > >=
        """.trimIndent()
        val tokens = Scanner(source).scanTokens()
        assertThat(tokens).containsExactly(
            Token(TokenType.BANG, "!", null, 1),
            Token(TokenType.BANG_EQUAL, "!=", null, 1),
            Token(TokenType.EQUAL, "=", null, 1),
            Token(TokenType.EQUAL_EQUAL, "==", null, 1),
            Token(TokenType.LESS, "<", null, 1),
            Token(TokenType.LESS_EQUAL, "<=", null, 1),
            Token(TokenType.GREATER, ">", null, 1),
            Token(TokenType.GREATER_EQUAL, ">=", null, 1),
            Token(TokenType.EOF, "", null, 1)
        )
    }

    @Test
    fun comment() {
        val source = """
            // comment
        """.trimIndent()
        val tokens = Scanner(source).scanTokens()
        assertThat(tokens).containsExactly(
            Token(TokenType.EOF, "", null, 1)
        )
    }

    @Test
    fun string() {
        val source = """
            "hello"
        """.trimIndent()
        val tokens = Scanner(source).scanTokens()
        assertThat(tokens).containsExactly(
            Token(TokenType.STRING, "\"hello\"", "hello", 1),
            Token(TokenType.EOF, "", null, 1)
        )
    }

    @Test
    fun number() {
        val source = """
            12 127.5
        """.trimIndent()
        val tokens = Scanner(source).scanTokens()
        assertThat(tokens).containsExactly(
            Token(TokenType.NUMBER, "12", 12.toDouble(), 1),
            Token(TokenType.NUMBER, "127.5", 127.5, 1),
            Token(TokenType.EOF, "", null, 1)
        )
    }
}
