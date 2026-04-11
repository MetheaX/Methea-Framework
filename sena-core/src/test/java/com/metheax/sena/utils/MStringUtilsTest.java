package com.metheax.sena.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MStringUtilsTest {

    @Test
    void nullToEmptyOrValue_nullInput_returnsEmpty() {
        assertEquals("", MStringUtils.nullToEmptyOrValue(null));
    }

    @Test
    void nullToEmptyOrValue_emptyInput_returnsEmpty() {
        assertEquals("", MStringUtils.nullToEmptyOrValue(""));
    }

    @Test
    void nullToEmptyOrValue_whitespaceOnlyInput_returnsEmpty() {
        assertEquals("", MStringUtils.nullToEmptyOrValue("   "));
    }

    @Test
    void nullToEmptyOrValue_validInput_returnsTrimmedValue() {
        assertEquals("hello", MStringUtils.nullToEmptyOrValue("  hello  "));
    }

    @Test
    void nullToEmptyOrValue_noWhitespace_returnsSameValue() {
        assertEquals("world", MStringUtils.nullToEmptyOrValue("world"));
    }

    @Test
    void validateAndTrim_nullInput_throwsRuntimeException() {
        assertThrows(RuntimeException.class, () -> MStringUtils.validateAndTrim(null));
    }

    @Test
    void validateAndTrim_emptyInput_throwsRuntimeException() {
        assertThrows(RuntimeException.class, () -> MStringUtils.validateAndTrim(""));
    }

    @Test
    void validateAndTrim_validInput_returnsTrimmed() {
        assertEquals("hello", MStringUtils.validateAndTrim("  hello  "));
    }

    @Test
    void validateAndTrim_multipleInternalSpaces_collapseToSingle() {
        assertEquals("hello world", MStringUtils.validateAndTrim("  hello   world  "));
    }

    @Test
    void validateAndTrim_singleWord_returnsSameWord() {
        assertEquals("hello", MStringUtils.validateAndTrim("hello"));
    }

    @Test
    void validateAndTrimOrEmpty_nullInput_returnsEmpty() {
        assertEquals("", MStringUtils.validateAndTrimOrEmpty(null));
    }

    @Test
    void validateAndTrimOrEmpty_emptyInput_returnsEmpty() {
        assertEquals("", MStringUtils.validateAndTrimOrEmpty(""));
    }

    @Test
    void validateAndTrimOrEmpty_validInput_returnsTrimmed() {
        assertEquals("hello", MStringUtils.validateAndTrimOrEmpty("  hello  "));
    }

    @Test
    void validateAndTrimOrEmpty_multipleInternalSpaces_collapseToSingle() {
        assertEquals("hello world", MStringUtils.validateAndTrimOrEmpty("  hello   world  "));
    }

    @Test
    void validateAndTrimOrEmpty_singleWord_returnsSameWord() {
        assertEquals("hello", MStringUtils.validateAndTrimOrEmpty("hello"));
    }
}
