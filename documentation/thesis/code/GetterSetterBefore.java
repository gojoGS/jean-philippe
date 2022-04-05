@Getter
class GetterSetterClass {
    int foo;
    @Setter
    double bar;
    @Setter(AccessLevel.PROTECTED)
    String baz;
    @Getter(AccessLevel.NONE)
    int bat;
}