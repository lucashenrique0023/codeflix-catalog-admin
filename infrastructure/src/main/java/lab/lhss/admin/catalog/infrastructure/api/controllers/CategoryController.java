package lab.lhss.admin.catalog.infrastructure.api.controllers;

import lab.lhss.admin.catalog.domain.pagination.Pagination;
import lab.lhss.admin.catalog.domain.validation.handler.Notification;
import lab.lhss.admin.catalog.infrastructure.api.CategoryAPI;
import lab.lhss.admin.catalog.infrastructure.category.models.CreateCategoryApiInput;
import lab.lhss.category.create.CreateCategoryCommand;
import lab.lhss.category.create.CreateCategoryOutput;
import lab.lhss.category.create.CreateCategoryUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.function.Function;

@RestController
public class CategoryController implements CategoryAPI {

    private final CreateCategoryUseCase createCategoryUseCase;

    public CategoryController(final CreateCategoryUseCase createCategoryUseCase) {
        this.createCategoryUseCase = Objects.requireNonNull(createCategoryUseCase);
    }

    @Override
    public ResponseEntity<?> createCategory(final CreateCategoryApiInput input) {
        final var aCommand = CreateCategoryCommand.with(
                input.name(),
                input.description(),
                input.active() != null ? input.active() : true
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateCategoryOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/categories/" + output.id())).body(output);

        return this.createCategoryUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public Pagination<?> listCategories(String search, int page, int perPage, String sort, String direction) {
        return null;
    }
}
