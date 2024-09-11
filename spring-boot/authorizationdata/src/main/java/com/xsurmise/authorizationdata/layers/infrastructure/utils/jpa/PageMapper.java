package com.xsurmise.authorizationdata.layers.infrastructure.utils.jpa;

import com.xsurmise.authorizationdata.layers.application.dto.utils.response.pagination.AppPage;
import com.xsurmise.authorizationdata.layers.application.dto.utils.response.pagination.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.function.Function;

@RequiredArgsConstructor
public class PageMapper<M, E> {
    private final Function<E, M> mapper;

    public AppPage<M> toAppPage(Page<E> page) {
        Page<M> convertedPage = page.map(mapper);
        return new AppPage<>(
                convertedPage.getContent(),
                PageInfo.builder()
                        .currentPage(convertedPage.getNumber())
                        .pageSize(convertedPage.getSize())
                        .pages(convertedPage.getTotalPages())
                        .count(convertedPage.getTotalElements())
                        .build()
        );
    }
}
