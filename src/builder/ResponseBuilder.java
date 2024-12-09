package builder;

import abstraction.Response;

public class ResponseBuilder<T> {
    private Boolean isSuccess;
    private T data;
    private String message;

    public ResponseBuilder(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public ResponseBuilder<T> withData(T data) {
        this.data = data;
        return this;
    }

    public ResponseBuilder<T> withMessage(String message) {
        this.message = message;
        return this;
    }

    public Response<T> build() {
        return new Response<>(isSuccess, data, message);
    }
}