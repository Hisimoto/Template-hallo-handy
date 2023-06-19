package HalloHandy.enums;

import org.springframework.data.domain.Sort;

public enum SortingDirection {
    ASC(Sort.Direction.ASC),
    DESC(Sort.Direction.DESC);

    private final Object direction;

    SortingDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public Sort.Direction getDirection() {
        return Sort.Direction.fromString(direction.toString());
    }
}
