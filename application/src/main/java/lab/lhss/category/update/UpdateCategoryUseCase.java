package lab.lhss.category.update;

import io.vavr.control.Either;
import lab.lhss.UseCase;
import lab.lhss.admin.catalog.domain.validation.handler.Notification;

public abstract class UpdateCategoryUseCase extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
