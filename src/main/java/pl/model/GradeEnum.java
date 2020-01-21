package pl.model;

public enum GradeEnum {
    BRAK("-"),

    NIEDOSTATECZNY("2"),

    DOSTATECZNY("3"),

    DOSTATECZNY_PLUS("3.5"),

    DOBRY("4"),

    DOBRY_PLUS("4.5"),

    BARDZO_DOBRY("5");

    private String value;

    GradeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
