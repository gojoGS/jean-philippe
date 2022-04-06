@Converter(autoApply = true)
public class DishTypeConverter implements AttributeConverter<DishType, String> {
    @Override
    public String convertToDatabaseColumn(DishType dishType) {
        if (dishType == null) { return null; }
        return dishType.getName();
    }

    @Override
    public DishType convertToEntityAttribute(String s) {
        if (s == null) { return null; }
        return DishType.valueOf(s);
    }
}
