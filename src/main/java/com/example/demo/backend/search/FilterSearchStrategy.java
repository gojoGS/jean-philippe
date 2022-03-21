package com.example.demo.backend.search;

import com.example.demo.backend.item.core.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

import java.util.List;

@AllArgsConstructor
@Log
public class FilterSearchStrategy implements SearchStrategy{
    private SearchProperties properties;

    @Override
    public List<Item> filterSearch(List<Item> hayStack) {
        log.info(properties.toString());

        return hayStack
                .stream()
                .filter(item -> item.getPriceInHuf() >= properties.getMinPrice())
                .filter(item -> item.getPriceInHuf() <= properties.getMaxPrice())
                .filter(item -> item.getName().contains(properties.getName()))
                .toList();
    }

    @AllArgsConstructor
    @Getter
    @ToString
    public static class SearchProperties {
        private String name;
        private long minPrice;
        private long maxPrice;
    }
}
