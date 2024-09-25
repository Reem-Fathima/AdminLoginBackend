package com.ust.project.sustainability.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to be thrown when a supplier is not found.
 * This exception will result in a 404 Not Found HTTP response status.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SupplierNotFoundException extends RuntimeException {
    /**
     * Constructs a new SupplierNotFoundException with a specific message.
     *
     * @param supplierId The ID of the supplier that was not found.
     */
    public SupplierNotFoundException(String supplierId) {
        super(" " + supplierId);
    }
}
