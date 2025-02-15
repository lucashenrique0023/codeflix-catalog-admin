package lab.lhss.admin.catalog.domain.validation;

public abstract class Validator {

    private final ValidationHandler handler;

    public Validator(ValidationHandler handler) {
        this.handler = handler;
    }

    public abstract void validate();

    protected ValidationHandler getHandler() {
        return this.handler;
    }

}
