package lab.lhss.category.create;

import lab.lhss.admin.catalog.domain.category.Category;

public record CreateCategoryOutput(String id) {

    public static CreateCategoryOutput from(final String anId) {
        return new CreateCategoryOutput(anId);
    }

    public static CreateCategoryOutput from(final Category category) {
        return new CreateCategoryOutput(category.getId().getValue());
    }
}
