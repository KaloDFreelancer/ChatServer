package bg.kmitev.ChatServer.validator;

import bg.kmitev.ChatServer.model.EmotionMessageData;
import bg.kmitev.ChatServer.model.MessageData;
import bg.kmitev.ChatServer.model.TextMessageData;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageDataValidatorTests {
    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    public static void close() {
        validatorFactory.close();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "Valid payload", "1234523424", "Valid 1234", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"})
    void testTextMessageValidation_withValidPayload_shouldNotHaveViolations(String validPayload) {
        MessageData messageData = new TextMessageData();

        messageData.setPayload(validPayload);
        Set<ConstraintViolation<MessageData>> constraintViolations = validator.validate(messageData);
        assertThat(constraintViolations).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"})
    void testTextMessageValidation_withInvalidPayload_shouldHaveViolations(String invalidPayload) {
        MessageData messageData = new TextMessageData();
        messageData.setPayload(invalidPayload);
        Set<ConstraintViolation<MessageData>> constraintViolations = validator.validate(messageData);
        assertThat(constraintViolations).isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"ab", "ababrhdvbd"})
    void testEmotionMessageValidation_withValidPayload_shouldNotHaveViolations(String validPayload) {
        MessageData messageData = new EmotionMessageData();

        messageData.setPayload(validPayload);
        Set<ConstraintViolation<MessageData>> constraintViolations = validator.validate(messageData);
        assertThat(constraintViolations).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "12", "ab2bbd", "ababababaaa"})
    void testEmotionMessageValidation_withInvalidPayload_shouldHaveViolations(String invalidPayload) {
        MessageData messageData = new EmotionMessageData();
        messageData.setPayload(invalidPayload);
        Set<ConstraintViolation<MessageData>> constraintViolations = validator.validate(messageData);
        assertThat(constraintViolations).isNotEmpty();
    }
}
