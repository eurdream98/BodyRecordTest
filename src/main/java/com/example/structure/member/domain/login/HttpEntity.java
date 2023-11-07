package com.example.structure.member.domain.login;

import org.springframework.http.HttpHeaders;

public class HttpEntity<T> {
    public static final HttpHeaders EMPTY_HEADERS = new HttpHeaders();
    public static final HttpEntity<?> EMPTY = new HttpEntity<>(EMPTY_HEADERS);

    private final HttpHeaders headers;
    private final T body;

    public HttpEntity(HttpHeaders headers) {
        this.headers = headers;
        this.body = null;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public T getBody() {
        return body;
    }
}

