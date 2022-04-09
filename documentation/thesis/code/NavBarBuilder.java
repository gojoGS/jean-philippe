public interface NavBarBuilder {
    NavBarBuilder addOption(NavBar.NavOption option);
    NavBarBuilder setLabel(String label);
    void reset();
    NavBar build();
}
