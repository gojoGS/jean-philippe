@NoArgsConstructor
class NoArgsConstructorClass {
    private String string;
    public int integer;
}

@NoArgsConstructor(force = true)
class NoArgsConstructorClassWithNonNullField {
    private @NonNull String string;
    public int integer;
}
