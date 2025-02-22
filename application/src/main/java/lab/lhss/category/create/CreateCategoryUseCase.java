package lab.lhss.category.create;

import io.vavr.control.Either;
import lab.lhss.UseCase;
import lab.lhss.admin.catalog.domain.validation.handler.Notification;

public abstract class CreateCategoryUseCase extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {
}
