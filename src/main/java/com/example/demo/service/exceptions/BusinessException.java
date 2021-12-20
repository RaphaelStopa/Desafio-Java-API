package com.example.demo.service.exceptions;

import com.example.demo.service.exceptions.enumeration.ErrorConstants;
import lombok.Getter;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.helpers.MessageFormatter;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.lang.String.format;
import static org.zalando.problem.Status.*;

@Getter
public class BusinessException extends RuntimeException {

    private final String value;

    private final String message;

    private final StatusType status;

    private BusinessException(Throwable throwable) {
        super(throwable);
        this.value = null;
        this.message = null;
        this.status = null;
    }

    private BusinessException() {
        super();
        this.value = null;
        this.message = null;
        this.status = null;
    }

    private BusinessException(String message, Throwable throwable) {
        super(message, throwable);
        this.value = null;
        this.message = null;
        this.status = null;
    }

    private BusinessException(ErrorConstants error) {
        super(error.getValue());
        this.message = error.getMessage();
        this.value = error.getValue();
        this.status = ErrorConstants.ACCESS_DENIED.equals(error) ? FORBIDDEN : BAD_REQUEST;
    }

    private BusinessException(ErrorConstants errorConstants, Status status) {
        super(errorConstants.getValue());
        this.value = errorConstants.getValue();
        this.message = errorConstants.getMessage();
        this.status = status;
    }

    private BusinessException(String message, ErrorConstants error, Status status) {
        super(message);
        this.value = error.getValue();
        this.message = error.getMessage();
        this.status = status;
    }

    private BusinessException(String message, String detail, Status status) {
        super(message);
        this.message = detail;
        this.value = ErrorConstants.GENERAL.getValue();
        this.status = status;
    }

    private BusinessException(String message, Throwable cause, Status status, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
        this.message = null;
        this.value = null;
    }

    private BusinessException(String message) {
        super(message);
        this.message = message;
        this.value = null;
        this.status = null;
    }

    private BusinessException(String message, Status status) {
        super(message);
        this.status = status;
        this.message = message;
        this.value = ErrorConstants.GENERAL.getValue();
    }

    public BusinessException(ErrorConstants errorConstants, Status status, Throwable cause) {
        super(errorConstants.getMessage(), cause);
        this.message = errorConstants.getMessage();
        this.value = errorConstants.getValue();
        this.status = status;
    }

    private BusinessException(String message, Throwable cause, StatusType status, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
        this.message = null;
        this.value = null;
    }

    private BusinessException(Status status) {
        super(ErrorConstants.GENERAL.getMessage());
        this.status = status;
        this.value = null;
        this.message = null;
    }

    public BusinessException(String message, Status status, boolean enableSuppression, boolean writableStackTrace) {
        super(message, null, enableSuppression, writableStackTrace);
        this.status = status;
        this.message = null;
        this.value = null;
    }

    public static Supplier<BusinessException> of(ErrorConstants error) {
        return () -> new BusinessException(error);
    }

    public static Supplier<BusinessException> unauthorized(ErrorConstants error) {
        return () -> new BusinessException(error, UNAUTHORIZED);
    }

    public static Supplier<BusinessException> forbidden(ErrorConstants error) {
        return () -> new BusinessException(error, FORBIDDEN);
    }

    public static Supplier<BusinessException> notFound(ErrorConstants error) {
        return () -> new BusinessException(error, NOT_FOUND);
    }

    public static Supplier<BusinessException> internalServerError(ErrorConstants error) {
        return () -> new BusinessException(error, INTERNAL_SERVER_ERROR);
    }

    public static Supplier<BusinessException> badRequest(ErrorConstants error) {
        return () -> new BusinessException(error, BAD_REQUEST);
    }

    public static Supplier<BusinessException> of(ErrorConstants error, Status status) {
        return () -> new BusinessException(error, status);
    }

    public static Supplier<BusinessException> businessException(String error) {
        return () -> new BusinessException(error);
    }

    public static BusinessException throwsBusinessException(String error) {
        return new BusinessException(error);
    }

    public static Supplier<BusinessException> businessException(String message, String... args) {
        return () -> new BusinessException(format(message, (Object[]) args));
    }

    public static Supplier<BusinessException> byNotFound() {
        return () -> new BusinessException(ErrorConstants.NOT_FOUND);
    }

    public static Supplier<BusinessException> notFound() {
        return () -> new BusinessException(ErrorConstants.NOT_FOUND, NOT_FOUND);
    }

    public static Supplier<BusinessException> notFound(String message, Object... args) {
        return () -> new BusinessException(MessageFormatter.arrayFormat(message, args).getMessage());
    }

    public static Supplier<BusinessException> businessException(String message, Object... args) {
        return () -> new BusinessException(MessageFormatter.arrayFormat(message, args).getMessage());
    }

    public static Supplier<BusinessException> notFound(String message, Throwable t, Object... args) {
        var formatted = MessageFormatter.arrayFormat(message, args, t);
        return () -> new BusinessException(formatted.getMessage(), formatted.getThrowable());
    }

    public static Supplier<BusinessException> businessException(String message, Throwable t, Object... args) {
        var formatted = MessageFormatter.arrayFormat(message, args, t);
        return () -> new BusinessException(formatted.getMessage(), formatted.getThrowable());
    }

    public static void throwIf(Boolean conditional, String message, Status status) throws BusinessException {
        if (conditional) {
            throw new BusinessException(message, status);
        }
    }

    public static void throwIf(Boolean conditional, String message) throws BusinessException {
        if (conditional) {
            throw new BusinessException(message);
        }
    }

    public static void requireNotNull(Object args, String message) throws BusinessException {
        if (args == null) {
            throw new BusinessException(message);
        }
    }

    public static void requireAnyNotNull(String message, Object... args) throws BusinessException {
        if (args == null || args.length == 0) {
            throw new BusinessException(message);
        }
        if (Stream.of(args).allMatch(Objects::isNull)) {
            throw new BusinessException(message);
        }
    }

    public static void requireAnyNotNull(ErrorConstants message, Object... args) throws BusinessException {
        if (args == null || args.length == 0) {
            throw new BusinessException(message);
        }
        if (Stream.of(args).allMatch(Objects::isNull)) {
            throw new BusinessException(message);
        }
    }

    public static void requireAllNotNull(ErrorConstants message, Object... args) throws BusinessException {
        if (args == null || args.length == 0) {
            throw new BusinessException(message);
        }
        if (Stream.of(args).anyMatch(Objects::isNull)) {
            throw new BusinessException(message);
        }
    }

    public static Supplier<BusinessException> by(ErrorConstants errorConstants, Status status) {
        return () -> new BusinessException(errorConstants, status);
    }

    public static Supplier<BusinessException> by(ErrorConstants errorConstants) {
        return () -> new BusinessException(errorConstants, BAD_REQUEST);
    }

    public static BusinessException badRequest() {
        return new BusinessException(BAD_REQUEST);
    }

    public static BusinessException by(String message, ErrorConstants error) {
        return new BusinessException(message, error, BAD_REQUEST);
    }

    public static BusinessException by(String message, ErrorConstants error, Status status) {
        return new BusinessException(message, error, status);
    }

    public static BusinessException by(String message, Status status) {
        return new BusinessException(message, status);
    }

    public static BusinessException by(Status status) {
        return new BusinessException(status);
    }

    public static BusinessException by(String message, String detail, Status status) {
        return new BusinessException(message, detail, status);
    }

    public static BusinessException by(ErrorConstants errorConstants, Status status, Throwable cause) {
        return new BusinessException(errorConstants, status, cause);
    }

    public static BusinessException by(String message, Throwable cause) {
        return new BusinessException(message, cause, BAD_REQUEST, true, false);
    }

    public static BusinessException by(String message, Object... args) {
        return new BusinessException(MessageFormatter.arrayFormat(message, args).getMessage(), BAD_REQUEST, true, false);
    }

    public static BusinessException by(String message, Throwable cause, Object... args) {
        return new BusinessException(MessageFormatter.arrayFormat(message, args).getMessage(), cause, BAD_REQUEST, true, false);
    }

    public static BusinessException by(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        return new BusinessException(message, cause, BAD_REQUEST, enableSuppression, writableStackTrace);
    }

    public static BusinessException by(String message, Throwable cause, Status status) {
        return new BusinessException(message, cause, status, true, false);
    }

    public static BusinessException by(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Status status) {
        return new BusinessException(message, cause, status, enableSuppression, writableStackTrace);
    }

    public static void throwIf(Boolean conditional, ErrorConstants errorConstants, Status status) {
        if (Boolean.TRUE.equals(conditional)) {
            throw new BusinessException(errorConstants, status);
        }
    }

    public static void throwIf(Boolean conditional, ErrorConstants errorConstants) {
        BusinessException.throwIf(conditional, errorConstants, BAD_REQUEST);
    }

    public static void throwIfNot(Boolean conditional, ErrorConstants errorConstants, Status status) {
        BusinessException.throwIf(BooleanUtils.negate(conditional), errorConstants, status);
    }

    public static void throwIfNot(Boolean conditional, ErrorConstants errorConstants) {
        BusinessException.throwIf(BooleanUtils.negate(conditional), errorConstants);
    }
}
