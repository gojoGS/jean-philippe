package com.example.demo.ui.common.component.nav;

// TOPIC builder pattern
public interface NavBarBuilder {
    NavBarBuilder addOption(NavBar.NavOption option);

    NavBarBuilder setLabel(String label);

    void reset();

    NavBar build();
}
