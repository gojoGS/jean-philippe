package com.example.demo.backend.search;

import com.example.demo.backend.item.core.Item;

import java.util.List;

public interface SearchStrategy {
    List<Item> filterSearch(List<Item> hayStack);
}
