package lab.lhss.category.update;

import io.vavr.API;
import io.vavr.control.Either;
import lab.lhss.admin.catalog.domain.category.Category;
import lab.lhss.admin.catalog.domain.category.CategoryGateway;
import lab.lhss.admin.catalog.domain.category.CategoryID;
import lab.lhss.admin.catalog.domain.exceptions.DomainException;
import lab.lhss.admin.catalog.domain.validation.Error;
import lab.lhss.admin.catalog.domain.validation.handler.Notification;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultUpdateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, UpdateCategoryOutput> execute(final UpdateCategoryCommand command) {
        final var id = CategoryID.from(command.id());
        final var category = this.categoryGateway.findById(id).orElseThrow(notFound(id));
        final var notification = Notification.create();
        category.update(command.name(), command.description(), command.isActive())
                .validate(notification);

        return notification.hasError() ? API.Left(notification) : update(category);
    }

    private Either<Notification, UpdateCategoryOutput> update(Category category) {
        return API.Try(() -> this.categoryGateway.update(category))
                .toEither()
                .bimap(Notification::create, UpdateCategoryOutput::from);
    }

    private static Supplier<DomainException> notFound(CategoryID id) {
        return () -> DomainException.with(new Error("Category with ID %s was not found".formatted(id.getValue())));
    }

}
