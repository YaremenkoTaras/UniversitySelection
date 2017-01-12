package com.epam.autum.selection.util;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 21.04.16
 * Time: 14:56
 * Possible results of validating input data.
 */
public enum ValidationResult {
    ALL_RIGHT,
    PASS_INCORRECT,
    EMAIL_INCORRECT,
    PASS_NOT_MATCH,
    EMAIL_NOT_UNIQUE,
    NAME_INCORRECT,
    UNKNOWN_ERROR,
    SUBJECT_ALREADY_EXIST,
    LOW_MARK,
    MISSING_MARK;
}
