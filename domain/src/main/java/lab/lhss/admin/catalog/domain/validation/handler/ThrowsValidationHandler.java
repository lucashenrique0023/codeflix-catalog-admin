package lab.lhss.admin.catalog.domain.validation.handler;

import lab.lhss.admin.catalog.domain.exceptions.DomainException;
import lab.lhss.admin.catalog.domain.validation.Error;
import lab.lhss.admin.catalog.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(final Error error) {
        throw DomainException.with(List.of(error));
    }

    @Override
    public ValidationHandler append(final ValidationHandler handler) {
        throw DomainException.with(handler.getErrors());
    }

    @Override
    public ValidationHandler append(final Validation validation) {
        try {
            validation.validate();
        } catch (final Exception e) {
            throw DomainException.with(List.of(new Error(e.getMessage())));
        }

        return this;
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }

}
