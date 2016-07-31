package kritacademy.com.caller.actions;

import kritacademy.com.caller.entity.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Chertpong on 8/1/2016.
 */
public final class UserAction {
    public interface Caller {
        @POST("users/")
        Call<User> createUser(@Body User user);
    }

}
