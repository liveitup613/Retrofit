package com.todo.vidyanandmishra.retofitexample.commonutils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.todo.vidyanandmishra.retofitexample.models.ErrorResponseModel;
import com.todo.vidyanandmishra.retofitexample.network.Presenter;

import java.io.IOException;

/**
 * Created by vidyanandmishra on 13/12/16.
 */
public class ErrorUtils {

    /**
     * Common method to handle all onFailure call back
     * @param context
     * @param errObj
     */
    public static void handleError(Context context, Object errObj, Presenter presenter) {

        if (errObj instanceof ErrorResponseModel) {
            ErrorResponseModel errModel = (ErrorResponseModel) errObj;
            if (errModel != null) {
                errHandleDialog(context, errModel, presenter);
            }
        } else {
            Throwable throwable = (Throwable) errObj;
            if (throwable instanceof IOException) {
                Toast.makeText(context, "No internet connection available!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * Showing error dialog
     *
     * @param context
     * @param errorResponseModel
     */
    public static void errHandleDialog(final Context context, final ErrorResponseModel errorResponseModel, final Presenter presenter) {

        final AlertDialog alert = new AlertDialog.Builder(context)
                .setTitle("Alert")
                .setMessage(errorResponseModel.getError().getReason())
                .setPositiveButton("Ok", null).create();

        alert.setCanceledOnTouchOutside(false);
        alert.show();

        Button btnOkay = alert.getButton(AlertDialog.BUTTON_POSITIVE);

        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (errorResponseModel.getError().getCode()) {

                    case 1001:
                        //TODO: DO YOUR THINGS AS PER THE ERROR CODE
                        break;

                    case 1002:
                        //TODO: DO YOUR THINGS AS PER THE ERROR CODE
                        break;

                    case 1003:
                        //TODO: DO YOUR THINGS AS PER THE ERROR CODE
                        break;

                        // SO ON ...
                }

                alert.dismiss();
            }
        });
    }
}
