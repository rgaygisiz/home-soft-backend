package zone.gaygisiz.home.soft.web;

import lombok.Data;

@Data
public class ApplicationResponse<T> {

    private T data;

    public static <T> ApplicationResponse<T> data(T data) {
        ApplicationResponse<T> result= new ApplicationResponse<>();
        result.setData(data);
        return result;
    }

    public static <T> ApplicationResponse<T> data() {
        return data(null);
    }
}
