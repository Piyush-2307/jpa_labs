package com.example.jap_labs.utilities;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public final class PageableUtility {
    private final static int MAX_SIZE = 100;

    private PageableUtility(){}

    public static Pageable sanitize(Pageable pageable){

        return PageRequest.of(
                pageable.getPageNumber(),
                Math.min(pageable.getPageSize(), MAX_SIZE),
                pageable.getSort()
        );
    }
}
