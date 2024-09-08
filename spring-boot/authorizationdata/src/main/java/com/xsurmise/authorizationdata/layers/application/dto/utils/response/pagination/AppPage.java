package com.xsurmise.authorizationdata.layers.application.dto.utils.response.pagination;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class AppPage<T> {
    private List<T> data;
    private PageInfo info;

    public AppPage(List<T> data, int currentPage, int pageSize, int pages, long count) {
        this.data = data;
        this.info = PageInfo.builder()
                .currentPage(currentPage)
                .pageSize(pageSize)
                .pages(pages)
                .count(count)
                .build();
    }
}
