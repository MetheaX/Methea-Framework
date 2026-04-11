package com.metheax.sena.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MDateUtilTest {

    @Test
    void convertDateToString_validDate_returnsFormattedString() {
        // Pattern "DD/MM/YYYY HH:mm:ss" uses day-of-year (DD) and week-based year (YYYY)
        // For Jan 15 2024: day-of-year=15, month=01, week-based-year=2024
        LocalDateTime date = LocalDateTime.of(2024, 1, 15, 10, 30, 0);
        String result = MDateUtil.convertDateToString(date);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void convertDateToString_validDate_containsMonth() {
        LocalDateTime date = LocalDateTime.of(2024, 3, 5, 8, 0, 0);
        String result = MDateUtil.convertDateToString(date);
        // Month 03 should appear in result
        assertTrue(result.contains("03"), "Expected month '03' in: " + result);
    }

    @Test
    void convertDateToString_validDate_containsYear() {
        LocalDateTime date = LocalDateTime.of(2024, 6, 15, 12, 0, 0);
        String result = MDateUtil.convertDateToString(date);
        assertTrue(result.contains("2024"), "Expected year '2024' in: " + result);
    }

    @Test
    void convertDateToString_validDate_containsTime() {
        LocalDateTime date = LocalDateTime.of(2024, 1, 1, 14, 30, 59);
        String result = MDateUtil.convertDateToString(date);
        assertTrue(result.contains("14:30:59"), "Expected time '14:30:59' in: " + result);
    }

    @Test
    void convertDateToString_nullDate_returnsEmpty() {
        // Null causes NPE caught internally, returns empty string
        String result = MDateUtil.convertDateToString(null);
        assertEquals("", result);
    }

    @Test
    void convertDateToString_dayOfYearUsed_notDayOfMonth() {
        // March 5, 2024: day-of-year = 31(Jan) + 29(Feb, leap) + 5(Mar) = 65
        // If DD means day-of-year, result should start with "65"
        // If DD meant day-of-month, result would start with "05"
        LocalDateTime date = LocalDateTime.of(2024, 3, 5, 0, 0, 0);
        String result = MDateUtil.convertDateToString(date);
        // Verify it uses day-of-year (known behavior of 'DD' in DateTimeFormatter)
        assertTrue(result.startsWith("65"), "Expected day-of-year '65' at start, but got: " + result);
    }
}
