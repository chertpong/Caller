package kritacademy.com.caller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.apache.activemq.transport.stomp.StompConnection;

import kritacademy.com.caller.actions.UserAction;
import kritacademy.com.caller.entity.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String API_URL = "http://localhost:8080";
    private static Retrofit retrofit = null;
    private Button mCallButton;
    private StompConnection stompConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final UserAction.Caller userAction = retrofit.create(UserAction.Caller.class);
        mCallButton = (Button) findViewById(R.id.call_button);
        mCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User("test");
                Call<User> call = userAction.createUser(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.d("Debug",response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("Fail",t.getMessage());
                    }
                });
            }
        });
        stompConnection = new StompConnection();
        try{
            stompConnection.open("",8080);

        }
        catch (Exception e){

        }
    }
}
