package io.github.metheax.repository.hibernateextension.domain;

import java.util.List;

public class HibernatePage<T> {
    private List<T> content;
    private long totalCount = 0L;

    public HibernatePage(List<T> content, long totalCount) {
        this.content = content;
        this.totalCount = totalCount;
    }

    public HibernatePage(List<T> content) {
        this.content = content;
    }

    public List<T> getContent() {
        return content;
    }

    public long getTotalCount() {
        return totalCount;
    }
}
