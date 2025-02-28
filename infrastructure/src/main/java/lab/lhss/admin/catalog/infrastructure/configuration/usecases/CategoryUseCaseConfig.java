package lab.lhss.admin.catalog.infrastructure.configuration.usecases;

import lab.lhss.admin.catalog.domain.category.CategoryGateway;
import lab.lhss.category.create.CreateCategoryUseCase;
import lab.lhss.category.create.DefaultCreateCategoryUseCase;
import lab.lhss.category.delete.DefaultDeleteCategoryUseCase;
import lab.lhss.category.delete.DeleteCategoryUseCase;
import lab.lhss.category.retrieve.get.DefaultGetCategoryByIdUseCase;
import lab.lhss.category.retrieve.get.GetCategoryByIdUseCase;
import lab.lhss.category.retrieve.list.DefaultListCategoriesUseCase;
import lab.lhss.category.retrieve.list.ListCategoriesUseCase;
import lab.lhss.category.update.DefaultUpdateCategoryUseCase;
import lab.lhss.category.update.UpdateCategoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryUseCaseConfig {

    private final CategoryGateway categoryGateway;

    public CategoryUseCaseConfig(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Bean
    public CreateCategoryUseCase createCategoryUseCase() {
        return new DefaultCreateCategoryUseCase(categoryGateway);
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase() {
        return new DefaultUpdateCategoryUseCase(categoryGateway);
    }

    @Bean
    public GetCategoryByIdUseCase getCategoryByIdUseCase() {
        return new DefaultGetCategoryByIdUseCase(categoryGateway);
    }

    @Bean
    public ListCategoriesUseCase listCategoriesUseCase() {
        return new DefaultListCategoriesUseCase(categoryGateway);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase() {
        return new DefaultDeleteCategoryUseCase(categoryGateway);
    }
}
