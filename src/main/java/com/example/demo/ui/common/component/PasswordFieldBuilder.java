package com.example.demo.ui.common.component;

import com.vaadin.flow.component.textfield.PasswordField;

public class PasswordFieldBuilder {
    private String label = "";
    private String helperText = "";
    private String placeholder = "";
    private boolean clearButtonVisible = true;
    private String pattern = "^.*$";
    private String errorMessage = "";

    public PasswordFieldBuilder() {

    }

    public PasswordFieldBuilder setHelperText(String helperText) {
        this.helperText = helperText;
        return this;
    }

    public PasswordFieldBuilder setLabel(String label) {
        this.label = label;
        return this;
    }

    public PasswordFieldBuilder setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public PasswordFieldBuilder setCleatButtonVisible(boolean clearButtonVisible) {
        this.clearButtonVisible = clearButtonVisible;
        return this;
    }

    public PasswordFieldBuilder setPattern(String pattern) {
        this.pattern = pattern;
        return this;
    }

    public PasswordFieldBuilder setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public PasswordField build() {
        var instance = new PasswordField(label, placeholder);

        instance.setHelperText(helperText);
        instance.setClearButtonVisible(clearButtonVisible);
        instance.setPattern(pattern);
        instance.setErrorMessage(errorMessage);

        return instance;
    }
}
