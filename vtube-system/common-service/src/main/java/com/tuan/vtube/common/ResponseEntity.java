package com.tuan.vtube.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
public class ResponseEntity<T> {
    private T data;
    private String message;
    private HttpStatus status;

    public static <T> ResponseEntity<T> ok(@Nullable T data) {
        return builder().status(HttpStatus.OK).message("success").data(data);
    }

    public static <T> ResponseEntity<T> error(@Nullable T data) {
        return builder().status(HttpStatus.OK).message("error").data(data);
    }

    public static <T> ResponseEntity<T> builder() {
        return new ResponseEntity<>();
    }

    public ResponseEntity status(HttpStatus status) {
        this.status = status;
        return this;
    }

    public ResponseEntity message(String message) {
        this.message = message;
        return this;
    }

    public ResponseEntity data(T data) {
        this.data = data;
        return this;
    }
}
