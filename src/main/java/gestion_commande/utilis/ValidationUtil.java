package gestion_commande.utilis;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ValidationUtil {
    private static ValidationUtil instance;
    private Validator validator;

    private ValidationUtil() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public static synchronized ValidationUtil getInstance() {
        if (instance == null) {
            instance = new ValidationUtil();
        }
        return instance;
    }

    public Map<String, String> validate(Object entity) {
        Set<ConstraintViolation<Object>> violations = validator.validate(entity);
        Map<String, String> errors = new HashMap<>();

        for (ConstraintViolation<Object> violation : violations) {
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return errors;
    }
}
