package com.corebank.api.corebank.domain.enums;

public enum CurrencyEnum {

    ARS("$", "Peso Argentino"),
    USD("$", "US Dollar"),
    EUR("€", "Euro");

    private final String symbol;
    private final String description;

    CurrencyEnum(String symbol, String description) {
        this.symbol = symbol;
        this.description = description;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDescription() {
        return description;
    }
}
