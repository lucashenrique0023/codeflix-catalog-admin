package lab.lhss.admin.catalog.domain.validation;

import lab.lhss.admin.catalog.domain.category.Category;

public class CategoryValidator extends Validator {

    private final Category category;

    public CategoryValidator(final Category category, final ValidationHandler validationHandler) {
        super(validationHandler);
        this.category = category;
    }

    @Override
    public void validate() {
        if (this.category.getName() == null) {
            this.getHandler().append(new Error("'name' should not be null"));
        }
    }

}
