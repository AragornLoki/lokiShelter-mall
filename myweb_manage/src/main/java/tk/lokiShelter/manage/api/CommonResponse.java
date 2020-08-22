package tk.lokiShelter.manage.api;

public class CommonResponse<T> {
    private long code;
    private String message;
    private T data;

    protected CommonResponse() {
    }

    protected CommonResponse(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
