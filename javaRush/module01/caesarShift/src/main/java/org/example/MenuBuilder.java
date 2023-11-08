package org.example;

public class MenuBuilder {
    private final StringBuilder sb;

    public MenuBuilder() {
        this.sb = new StringBuilder();
    }

    public MenuBuilder AddLine(String menuPoint) {
        sb.append(String.format("%s%s", menuPoint, System.lineSeparator()));
        return this;
    }

    public String Build() {
        return sb.toString();
    }
}
