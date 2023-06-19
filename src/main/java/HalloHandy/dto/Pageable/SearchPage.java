package HalloHandy.dto.Pageable;


import HalloHandy.enums.SortingDirection;
import org.springframework.data.domain.Pageable;

public interface SearchPage {

    int getPage();
    void setPage(int page);

    int getPageSize();
    void setPageSize(int pageSize);

    int getTotalPages();
    void setTotalPages(int totalPages);

    long getTotalElements();
    void setTotalElements(long totalElements);

    String getSortKey();
    void setSortKey(String sortKey);

    SortingDirection getSortingDirection();
    void setSortingDirection(SortingDirection sortingDirection);

    Pageable getPageableObject();
}

