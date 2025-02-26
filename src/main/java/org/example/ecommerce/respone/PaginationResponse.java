package org.example.ecommerce.respone;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class PaginationResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private int page;
    private int size;
    private int totalPages;
    private long totalElements;

    public PaginationResponse(boolean success, String message, T data, int page, int size, int totalPages, long totalElements) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }
}
