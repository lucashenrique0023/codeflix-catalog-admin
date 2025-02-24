package lab.lhss.category.create;

import lab.lhss.admin.catalog.domain.category.CategoryGateway;
import lab.lhss.admin.catalog.domain.validation.handler.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {

    @Mock
    CategoryGateway categoryGateway;

    @InjectMocks
    private DefaultCreateCategoryUseCase useCase;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(categoryGateway);
    }

    @Test
    public void givenAValidCommand_whenCallCreateCategory_thenReturnCategoryId() {

        final var expectedName = "Filmes";
        final var expectedDescription = "Filme description";
        final var expectedIsActive = true;

        final var command = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        when(categoryGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(command).get();

        assertNotNull(actualOutput.id());

        verify(categoryGateway, times(1)).create(argThat(category ->
                Objects.equals(expectedName, category.getName())
                && Objects.equals(expectedDescription, category.getDescription())
                && Objects.equals(expectedIsActive, category.isActive())
                && Objects.nonNull(category.getCreatedAt())
                && Objects.nonNull(category.getUpdatedAt())
                && Objects.isNull(category.getDeletedAt())
                && Objects.nonNull(category.getId())
        ));
    }

    @Test
    public void givenAInvalidName_whenCallCreateCategory_thenReturnDomainException() {
        final String expectedName = null;
        final var expectedDescription = "Filme description";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name' should not be null" ;
        final var expectedErrorCount = 1;

        final var command = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        Notification notification = useCase.execute(command).getLeft();

        assertEquals(expectedErrorCount, notification.getErrors().size());
        assertEquals(expectedErrorMessage, notification.firstError().message());
        verify(categoryGateway, times(0)).create(any());
    }

    @Test
    public void givenAValidCommandWithInactiveCategory_whenCallCreateCategory_thenReturnInactiveCategoryId() {
        final var expectedName = "Filmes";
        final var expectedDescription = "Filme description";
        final var expectedIsActive = false;

        final var command = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        when(categoryGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(command).get();

        assertNotNull(actualOutput.id());

        verify(categoryGateway, times(1)).create(argThat(category ->
                Objects.equals(expectedName, category.getName())
                        && Objects.equals(expectedDescription, category.getDescription())
                        && Objects.equals(expectedIsActive, category.isActive())
                        && Objects.nonNull(category.getCreatedAt())
                        && Objects.nonNull(category.getUpdatedAt())
                        && Objects.nonNull(category.getDeletedAt())
                        && Objects.nonNull(category.getId())
        ));
    }

    @Test
    public void givenAValidCommand_whenGatewayThrowsRandomException_thenReturnAException() {

        final var expectedName = "Filmes";
        final var expectedDescription = "Filme description";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "Gateway error";
        final var expectedErrorCount = 1;

        final var command = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        when(categoryGateway.create(any())).thenThrow(new IllegalStateException(expectedErrorMessage));

        Notification notification = useCase.execute(command).getLeft();

        assertEquals(expectedErrorCount, notification.getErrors().size());
        assertEquals(expectedErrorMessage, notification.firstError().message());

        verify(categoryGateway, times(1)).create(argThat(category ->
                Objects.equals(expectedName, category.getName())
                        && Objects.equals(expectedDescription, category.getDescription())
                        && Objects.equals(expectedIsActive, category.isActive())
                        && Objects.nonNull(category.getCreatedAt())
                        && Objects.nonNull(category.getUpdatedAt())
                        && Objects.isNull(category.getDeletedAt())
                        && Objects.nonNull(category.getId())
        ));
    }

}
