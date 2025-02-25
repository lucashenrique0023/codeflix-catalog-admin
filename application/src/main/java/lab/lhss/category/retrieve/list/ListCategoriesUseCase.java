package lab.lhss.category.retrieve.list;

import lab.lhss.UseCase;
import lab.lhss.admin.catalog.domain.category.CategorySearchQuery;
import lab.lhss.admin.catalog.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase
        extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {
}
