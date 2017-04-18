package com.epam.autum.selection.util;

/**
 *
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
