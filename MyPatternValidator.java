import java.lang.annotation.*;
import java.util.regex.Pattern;


@Retention(RetentionPolicy.RUNTIME)

@Target(ElementType.FIELD)
@interface MyPattern {
    String regex();

    String message();
}

interface Validator {
    void validate(String value);

    boolean isValid();

    String getMessage();
}

class MyPatternValidator implements Validator {
    private final String regex;
    private final String message;
    private boolean valid;

    public MyPatternValidator(String regex, String message) {
        this.regex = regex;
        this.message = message;
        this.valid = true;
    }

    @Override
    public void validate(String value) {
        valid = Pattern.matches(regex, value);
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public String getMessage() {
        return message;
    }
}