package com.metheax.sena.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaginationTest {

    @Test
    void defaultConstructor_setsPageOneAndSizeTen() {
        Pagination p = new Pagination();
        assertEquals(1, p.getPage());
        assertEquals(10, p.getSize());
    }

    @Test
    void defaultConstructor_totalCountsIsZero() {
        Pagination p = new Pagination();
        assertEquals(0L, p.getTotalCounts());
    }

    @Test
    void getTotalPages_exactDivision_returnsQuotient() {
        Pagination p = new Pagination(1, 10, 0L, 20L);
        assertEquals(2L, p.getTotalPages());
    }

    @Test
    void getTotalPages_withRemainder_roundsUp() {
        Pagination p = new Pagination(1, 10, 0L, 21L);
        assertEquals(3L, p.getTotalPages());
    }

    @Test
    void getTotalPages_singleItem_returnsOne() {
        Pagination p = new Pagination(1, 10, 0L, 1L);
        assertEquals(1L, p.getTotalPages());
    }

    @Test
    void getTotalPages_zeroCounts_returnsZero() {
        Pagination p = new Pagination(1, 10, 0L, 0L);
        assertEquals(0L, p.getTotalPages());
    }

    @Test
    void getTotalPages_countLessThanPageSize_returnsOne() {
        Pagination p = new Pagination(1, 10, 0L, 5L);
        assertEquals(1L, p.getTotalPages());
    }

    @Test
    void getOffSet_firstPage_returnsZero() {
        Pagination p = new Pagination(1, 10, 0L, 0L);
        assertEquals(0, p.getOffSet());
    }

    @Test
    void getOffSet_secondPage_returnsPageSize() {
        Pagination p = new Pagination(2, 10, 0L, 0L);
        assertEquals(10, p.getOffSet());
    }

    @Test
    void getOffSet_thirdPageSizeFive_returnsTen() {
        Pagination p = new Pagination(3, 5, 0L, 0L);
        assertEquals(10, p.getOffSet());
    }

    @Test
    void getOffSet_pageOneCustomSize_returnsZero() {
        Pagination p = new Pagination(1, 25, 0L, 100L);
        assertEquals(0, p.getOffSet());
    }

    @Test
    void settersAndGetters_workCorrectly() {
        Pagination p = new Pagination();
        p.setPage(3);
        p.setSize(20);
        p.setTotalCounts(100L);

        assertEquals(3, p.getPage());
        assertEquals(20, p.getSize());
        assertEquals(100L, p.getTotalCounts());
        assertEquals(5L, p.getTotalPages());
        assertEquals(40, p.getOffSet());
    }
}
