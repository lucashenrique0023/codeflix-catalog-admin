package lab.lhss.admin.catalog.application;

import lab.lhss.admin.catalog.IntegrationTest;
import lab.lhss.admin.catalog.infrastructure.category.persistence.CategoryRepository;
import lab.lhss.category.create.CreateCategoryUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
public class SampleIT {

    @Autowired
    private CreateCategoryUseCase useCase;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testInjects() {
        Assertions.assertNotNull(useCase);
        Assertions.assertNotNull(categoryRepository);
    }
}
