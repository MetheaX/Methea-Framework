package io.methea.util;

/**
 * Author : DKSilverX
 * Date : 14/04/2020
 */
public class Pagination {
    private Integer page;
    private Integer size;
    private Long totalPages;
    private Long totalCounts;

    public Pagination(Integer page, Integer size, Long totalPages, Long totalCounts) {
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.totalCounts = totalCounts;
    }

    public Pagination() {
        this(1, 10, 0L, 0L);
    }

    public Long getTotalPages() {
        return (long) Math.ceil((double) this.totalCounts / size);
    }

    public Integer getOffSet() {
        return (this.page - 1) * this.size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(Long totalCounts) {
        this.totalCounts = totalCounts;
    }
}
