class BuilderClass {
    int i;
    double d;

    BuilderClass(int i, double d) {
        this.i = i;
        this.d = d;
    }

    public static BuilderClassBuilder builder() { return new BuilderClassBuilder(); }

    public static class BuilderClassBuilder {
        private int i;
        private double d;

        BuilderClassBuilder() {}

        public BuilderClassBuilder i(int i) {
            this.i = i;
            return this;
        }

        public BuilderClassBuilder d(double d) {
            this.d = d;
            return this;
        }

        public BuilderClass build() { return new BuilderClass(i, d); }

        public String toString() { // ...
        }
    }
}
