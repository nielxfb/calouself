package abstraction;

public final class Response<T> {
    public Boolean isSuccess = false;
    public T data = null;
    public String message = "";

    public Response(Boolean isSuccess, T data, String message) {
        this.isSuccess = isSuccess;
        this.data = data;
        this.message = message;
    }
}
