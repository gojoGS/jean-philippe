class AllArgsConstructorClass {
    private String string;
    public int integer;
    @NonNull
    protected Double number;

    public AllArgsConstructorClass(String string, int integer, @NonNull Double number)
    {
        this.string = string;
        this.integer = integer;
        this.number = number;
    }
}