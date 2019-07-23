package com.todo.vidyanandmishra.retofitexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.todo.vidyanandmishra.retofitexample.commonutils.AppPreferenceManager;
import com.todo.vidyanandmishra.retofitexample.commonutils.ErrorUtils;
import com.todo.vidyanandmishra.retofitexample.models.LoginRequestModel;
import com.todo.vidyanandmishra.retofitexample.models.SuccessResponseModel;
import com.todo.vidyanandmishra.retofitexample.models.UpdateUserModel;
import com.todo.vidyanandmishra.retofitexample.network.NetworkService;
import com.todo.vidyanandmishra.retofitexample.network.Presenter;

/**
 * Created by vidyanandmishra on 13/12/16.
 * <p>
 * NOTE:-
 * <p>
 * 1. This is the only activity in this demo project.
 * 2. I have only focused on the networking part not on UI.
 * 3. All button click is only to show the calls of the APIs.
 * 4. Please add your own URLs, keys and endpoints in "ApiConstants.class" file
 * and make the API calls as per your needs.
 * 5. Rest of the things will work fine once you will follow the above points.
 * 6. Happy coding...!!
 */
public class MainActivity extends AppCompatActivity {

    private EditText etUserName;
    private EditText etPassword;

    private NetworkService service;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = ((ApplicationControl) getApplication()).getNetworkService();
        presenter = new Presenter(this, service);

        etUserName = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);

        Button btnLogin = (Button) findViewById(R.id.btn_login);
        Button btnConfig = (Button) findViewById(R.id.btn_config);
        Button btnUpdate = (Button) findViewById(R.id.btn_update);
        Button btnLogout = (Button) findViewById(R.id.btn_logout);

        btnLogin.setOnClickListener(new OnClickLogin());
        btnConfig.setOnClickListener(new OnClickConfig());
        btnUpdate.setOnClickListener(new OnClickUpdate());
        btnLogout.setOnClickListener(new OnClickLogout());
    }

    //POST API example on login button click
    private class OnClickLogin implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            if (isValidInput()) {

                LoginRequestModel loginRequestModel = new LoginRequestModel();
                loginRequestModel.setUserName(etUserName.getText().toString());
                loginRequestModel.setPassword(etPassword.getText().toString());

                presenter.login(loginRequestModel, new Presenter.OnResponseListener() {
                    @Override
                    public void onSuccessResponse(Object response) {

                        if (response == null) return;

                        SuccessResponseModel responseLogin = (SuccessResponseModel) response;

                        if (responseLogin != null && responseLogin.isOk()) {

                            AppPreferenceManager.getInstance().setSessionKey(responseLogin.getResponse().getSessionToken());

                            //TODO: Do Something with your response
                        }
                    }

                    @Override
                    public void onFailureResponse(Object error) {

                        if (error == null) return;

                        ErrorUtils.handleError(MainActivity.this, error, presenter);
                    }
                });
            }
        }
    }

    //GET API example on config button click
    private class OnClickConfig implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            presenter.getConfig(new Presenter.OnResponseListener() {

                @Override
                public void onSuccessResponse(Object response) {

                    if (response == null) return;

                    SuccessResponseModel responseConfig = (SuccessResponseModel) response;

                    if (responseConfig != null && responseConfig.isOk()) {
                        //TODO: Do Something with your response
                    }
                }

                @Override
                public void onFailureResponse(Object error) {

                    if (error == null) return;

                    ErrorUtils.handleError(MainActivity.this, error, presenter);
                }
            });
        }
    }

    //PUT API example on update button click
    private class OnClickUpdate implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            UpdateUserModel updateUserModel = new UpdateUserModel();
            updateUserModel.setUserName("Some Name");
            updateUserModel.setHouseNumber("Some Number");
            updateUserModel.setPhoneNumber("Some Phone Number");

            presenter.updateUser("<some user id>", updateUserModel, new Presenter.OnResponseListener() {

                @Override
                public void onSuccessResponse(Object response) {

                    if (response == null) return;

                    SuccessResponseModel responseUpdate = (SuccessResponseModel) response;

                    if (responseUpdate != null && responseUpdate.isOk()) {
                        //TODO: Do Something with your response
                    }
                }

                @Override
                public void onFailureResponse(Object error) {
                    if (error == null) return;

                    ErrorUtils.handleError(MainActivity.this, error, presenter);
                }
            });
        }
    }

    //POST API example on logout button click
    private class OnClickLogout implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            presenter.logout();
        }
    }


    /**
     * Method to validate user inputs [Edit as per your validations rule]
     *
     * @return true/false
     */
    private boolean isValidInput() {

        boolean isValid = true;

        if (TextUtils.isEmpty(etUserName.getText().toString())) {
            etUserName.setError("Enter valid username!");
            isValid = false;
        }

        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError("Enter valid password!");
            isValid = false;
        }

        return isValid;
    }
}
