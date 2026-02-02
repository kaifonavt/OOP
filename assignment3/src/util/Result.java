package util;

public class Result<T> {
    private T data;
    private String message;
    private boolean isSuccess;

    public Result(T data, String message, boolean isSuccess) {
        this.data = data;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public T getData() {return data;}
    public String getMessage() {return message;}
    public boolean isSuccess() {return isSuccess;}

    public static <T> Result<T> success(T data) { return new Result<>(data, null, true); }
    public static <T> Result<T> error(String msg) { return new Result<>(null, msg, false); }
}
