package com.example.demo.ui.common.component;

// TOPIC builder pattern
public interface NavBarBuilder {
    void addOption(NavBar.NavOption option);

    NavBar get();
}
