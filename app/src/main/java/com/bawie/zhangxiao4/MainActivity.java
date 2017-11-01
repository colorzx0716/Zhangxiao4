package com.bawie.zhangxiao4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private BeanInterface beanInterface;
    private Retrofit retrofit;
    private Call<Bean> context;

    private Call<Nike> nikeCall;

    private EditText et_nickname;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nickname = findViewById(R.id.et_nickname);//昵称是一个萝卜
        btn = findViewById(R.id.btn);//修改昵称的按钮




      btn.setOnClickListener(this);


        retrofit = new Retrofit.Builder().baseUrl(Api.User_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //接口---BeanInterface接口类
        beanInterface = retrofit.create(BeanInterface.class);
        //post请求并传入参数
        context = beanInterface.getPost(146);
        context.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {
                if(response != null && response.isSuccessful()&& response.body()!= null){

                }
            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {

            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn:
                String name = et_nickname.getText().toString();


                System.out.println("昵称hhhh = " + name);

                retrofit = new Retrofit.Builder().baseUrl(Api.Nike_url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                //接口---BeanInterface接口类
                beanInterface = retrofit.create(BeanInterface.class);
                //post请求并传入参数

               nikeCall = beanInterface.getNike(146,name);
                nikeCall.enqueue(new Callback<Nike>() {
                    @Override
                    public void onResponse(Call<Nike> call, Response<Nike> response) {
                        if(response != null && response.isSuccessful()&& response.body()!= null){
                            String msg = response.body().getMsg();

                            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Nike> call, Throwable t) {

                    }
                });

                break;
        }
    }
}
