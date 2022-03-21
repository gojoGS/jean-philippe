package com.example.demo.backend.search;

import org.springframework.stereotype.Component;

@Component
public class FilterSearchStrategyFactory {
    public FilterSearchStrategy get(FilterSearchStrategy.SearchProperties properties) {
        return new FilterSearchStrategy(properties);
    }
}
