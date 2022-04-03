class NoArgsConstructorClass {
    private String string;
    public int integer;

    public NoArgsConstructorClass()
    {
    }
}

class NoArgsConstructorClassWithNonNullField {
    private @NonNull String string;
    public int integer;

    public NoArgsConstructorClassWithNonNullField()
    {
        this.string = null;
    }
}