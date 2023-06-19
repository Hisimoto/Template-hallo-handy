package HalloHandy.dto.Pageable;

import HalloHandy.enums.SortingDirection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public abstract class Page implements SearchPage {

    private int page;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private String sortKey;
    private SortingDirection sortingDirection;
    private Sort sort;

    private final int DEFAULT_PAGE_SIZE = 10;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    public SortingDirection getSortingDirection() {
        return sortingDirection;
    }

    public void setSortingDirection(SortingDirection sortingDirection) {
        this.sortingDirection = sortingDirection;
    }

    @JsonIgnore
    public Pageable getPageableObject() {
        // page order starting at 0


        if (pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        Sort sort = Sort.by(Sort.Order.desc("id"));
        if (sortingDirection != null && sortKey != null) {
            sort = Sort.by(sortingDirection.getDirection(), sortKey).and(sort);
        }

        return PageRequest.of(page, pageSize, sort);
    }
}
