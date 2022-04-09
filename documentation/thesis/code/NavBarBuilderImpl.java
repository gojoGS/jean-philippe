public class NavBarBuilderImpl implements NavBarBuilder {
    private List<NavBar.NavOption> options;
    private String label;

    public NavBarBuilderImpl() {
        options = new ArrayList<>();
        label = null;
    }

    @Override
    public NavBarBuilder addOption(NavBar.NavOption option) {
        this.options.add(option);
        return this;
    }

    @Override
    public NavBarBuilder setLabel(String label) {
        this.label = label;
        return this;
    }

    @Override
    public void reset() {
        options = new ArrayList<>();
        label = null;
    }

    @Override
    public NavBar build() {
        return new NavBar(Optional.of(label), options.toArray(NavBar.NavOption[]::new));
    }
}
