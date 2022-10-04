package Control;

public enum Option {

    EXIT(0, "Exit"),
    ADD_EXPENSE(1, "Add expense"),
    ADD_INCOME(2, "Add income"),
    DELETE_EXPENSE(3, "Delete expense"),
    DELETE_INCOME(4, "Delete income"),
    DISPLAY_ALL_EXPENSES_AND_INCOMES(5, "Display all expenses and incomes"),
    DISPLAY_ALL_EXPENSES(6, "Display all expenses"),
    DISPLAY_ALL_INCOMES(7, "Display all incomes"),
    DISPLAY_BALANCE(8, "Display balance"),
    DISPLAY_ALL_EXPENSES_GROUPING_BY_CATEGORY(9, "Display all expenses grouping by category"),
    DISPLAY_ALL_EXPENSES_BETWEEN_DATES(10, "Display all expenses between dates"),
    ADD_NEW_CATEGORY(11, "Add new category"),
    DELETE_CATEGORY(12, "Delete category");

    private final int value;
    private final String description;

    Option(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }

    static Option createFromInt(int option) {
        return Option.values()[option];
    }


}
