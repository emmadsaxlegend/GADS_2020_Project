package com.globomed.gads_2020_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectSubmissionActivity extends AppCompatActivity {
EditText firstname;
EditText lastname;
EditText email;
EditText githublink;
TextView submitBtn;
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.project_submission);
        firstname=findViewById(R.id.submit_firstname);
        lastname=findViewById(R.id.submit_lastname);
        email=findViewById(R.id.submit_email);
        githublink=findViewById(R.id.submit_githublink);
        submitBtn=findViewById(R.id.submit);

        submitBtn.setOnClickListener(
                view -> {
                    if(!isEmpty(firstname.getText().toString())&&!isEmpty(lastname.getText().toString())&&!isEmpty(email.getText().toString())
                    &&!isEmpty(githublink.getText().toString())){
                        confirmSubmit(firstname.getText().toString(),lastname.getText().toString(),email.getText().toString(),githublink.getText().toString());
                    }
                    else{
                        askToEnterValues();
                    }
                }
        );
ImageView btnExit=findViewById(R.id.image_exit);
btnExit.setOnClickListener(
        view->{
            Intent intent=new Intent(ProjectSubmissionActivity.this,LeaderBoardActivity.class);
            startActivity(intent);
        }
);
View viewExit=findViewById(R.id.image_exit);
viewExit.setOnClickListener(
        view->{
            Intent intent=new Intent(ProjectSubmissionActivity.this,LeaderBoardActivity.class);
            startActivity(intent);
        }
);
    }
    private boolean isEmpty(String xyz){
        return xyz==null||xyz.equals("");
    }
    private void confirmSubmit(String fname,String lname,String email,String glink){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this,R.style.AlertDialog);
        View view=getLayoutInflater().inflate(R.layout.submission_confirm_submit,null);
        alertDialog.setView(view);
        AlertDialog alert=alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.setCancelable(false);
        TextView submitBt=view.getRootView().findViewById(R.id.submit_yes);
        ImageView img_cancel=view.getRootView().findViewById(R.id.submit_cancel);
        img_cancel.setOnClickListener(
                view_e->alert.cancel()
        );
        submitBt.setOnClickListener(
                view_ee->{
                    alert.cancel();
                    new RetrofitPostRequest(fname,lname,email,glink);
                }
        );
    alert.show();
    }
    private void askToEnterValues() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this,R.style.AlertDialog);
        alertDialog.setTitle("Empty Fields!");
        alertDialog.setMessage("Please fill out all input fields to continue");
        alertDialog.setPositiveButton("OK",(dialog,which)->{
            dialog.cancel();
        });
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.setCancelable(false);
        alert.show();
    }

    private void alertSendFailed(){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this,R.style.AlertDialog);
        View view=getLayoutInflater().inflate(R.layout.submission_not_successful,null);
        alertDialog.setView(view);
        AlertDialog alert=alertDialog.create();
        alert.setCanceledOnTouchOutside(true);
        alert.setCancelable(true);
        alert.show();
    }

    private void alertSendSuccessful(){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this,R.style.AlertDialog);
        View view=getLayoutInflater().inflate(R.layout.submission_successful,null);
        alertDialog.setView(view);
        AlertDialog alert=alertDialog.create();
        alert.setCanceledOnTouchOutside(true);
        alert.setCancelable(true);
        alert.show();
    }

    public class RetrofitPostRequest {
        public RetrofitPostRequest(String firstname, String lastname, String email, String githublink) {
            SendPostInterface poster = RetrofitPost.getRetrofit().create(SendPostInterface.class);
            Call<Void> call = poster.sendPost(firstname,lastname,email,githublink);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.isSuccessful()){
                        alertSendSuccessful();
                    }
                    else{
                        alertSendFailed();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                alertSendFailed();
                }
            });
        }
    }
    }
