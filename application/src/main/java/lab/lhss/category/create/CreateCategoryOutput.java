package lab.lhss.category.create;

import lab.lhss.admin.catalog.domain.category.Category;
import lab.lhss.admin.catalog.domain.category.CategoryID;

public record CreateCategoryOutput(CategoryID id) {
    public static CreateCategoryOutput from(final Category category) {
        return new CreateCategoryOutput(category.getId());
    }
}
