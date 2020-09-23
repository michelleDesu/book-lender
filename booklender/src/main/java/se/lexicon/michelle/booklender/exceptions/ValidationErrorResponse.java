package se.lexicon.michelle.booklender.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public class ValidationErrorResponse extends MyExceptionResponse{

    private List<Violation> violations;

    public ValidationErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path, List<Violation> violations) {
        super(timestamp, status, error, message, path);
        this.violations = violations;
    }

    public List<Violation> getViolations() {
        return violations;
    }
}
