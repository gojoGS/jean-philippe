public class NavBar extends HorizontalLayout {
    public NavBar(Optional<String> label, NavOption... options) {
        // ...
    }

    @Getter
    @AllArgsConstructor
    public static class NavOption {
        private String label;
        private Class<? extends Component> route;
    }
}
