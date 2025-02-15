package lab.lhss.admin.catalog.domain.exceptions;

import lab.lhss.admin.catalog.domain.validation.Error;

import java.util.List;

public class DomainException extends NoStacktraceException {

    private final List<Error> errors;

    private DomainException(final String message, List<Error> errors) {
        super(message);
        this.errors = errors;
    }

    public static DomainException with(final Error error) {
        return new DomainException(error.message(), List.of(error));
    }

    public static DomainException with(final List<Error> error) {
        return new DomainException("", error);
    }

    public List<Error> getErrors() {
        return errors;
    }
}
