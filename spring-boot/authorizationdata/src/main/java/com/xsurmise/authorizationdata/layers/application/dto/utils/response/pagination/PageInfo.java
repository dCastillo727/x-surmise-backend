package com.xsurmise.authorizationdata.layers.application.dto.utils.response.pagination;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {
    private int currentPage;
    private int pageSize;
    private int pages;
    private long count;
}
