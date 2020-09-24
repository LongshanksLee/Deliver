package com.vinci.exception;

/**
 * @Author:Vinci_Ma
 * @Oescription: 自定义异常
 * @Date Created in 2020-08-21-17:18
 * @Modified By:
 */
public class DuplicateCodeException extends Exception{
    /**
     * Constructs a new com.vinci.exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public DuplicateCodeException() {
    }

    /**
     * Constructs a new com.vinci.exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public DuplicateCodeException(String message) {
        super(message);
    }
}
