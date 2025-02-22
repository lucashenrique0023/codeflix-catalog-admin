package lab.lhss.category.create;

import io.vavr.API;
import io.vavr.control.Either;
import lab.lhss.admin.catalog.domain.category.Category;
import lab.lhss.admin.catalog.domain.category.CategoryGateway;
import lab.lhss.admin.catalog.domain.validation.handler.Notification;

import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, CreateCategoryOutput> execute(final CreateCategoryCommand command) {
        final var notification = Notification.create();
        final var category = Category.newCategory(command.name(), command.description(), command.isActive());
        category.validate(notification);

        return notification.hasError() ? Left(notification) : create(category);
    }

    private Either<Notification, CreateCategoryOutput> create(final Category category) {
        return Try(() -> this.categoryGateway.create(category))
                .toEither()
                .bimap(Notification::create, CreateCategoryOutput::from);
    }
}
