package com.xsurmise.authorizationdata.layers.application.dto.utils.response;

import java.util.List;

public class AppPage<T> {
    private List<T> data;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;
}
