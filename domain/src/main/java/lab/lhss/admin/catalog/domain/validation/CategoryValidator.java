package lab.lhss.admin.catalog.domain.validation;

import lab.lhss.admin.catalog.domain.category.Category;

public class CategoryValidator extends Validator {

    public static final int NAME_MAX_LENGTH = 255;
    public static final int NAME_MIN_LENGTH = 3;
    private final Category category;

    public CategoryValidator(final Category category, final ValidationHandler validationHandler) {
        super(validationHandler);
        this.category = category;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final String name = this.category.getName();
        if (name == null) {
            this.getHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.getHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.getHandler().append(new Error("'name' should be between 3 and 255 characters"));
        }
    }

}
