package com.testapp.problem1.feature_downloadfile.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.testapp.problem1.R;
import com.testapp.problem1.feature_downloadfile.callbacks.DownloadFileActivityCallback;
import com.testapp.problem1.feature_downloadfile.presenter.DownloadFileActivityPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DownloadFileActivity extends AppCompatActivity implements DownloadFileActivityCallback {

    @BindView(R.id.edtUrl)
    protected EditText edtUrl;

    @BindView(R.id.btnDonwload)
    protected Button btnDonwload;

    @BindView(R.id.txtDownloadStatus)
    protected TextView txtDownloadStatus;

    private DownloadFileActivityPresenter mDownloadFileActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_file);
        ButterKnife.bind(this);
        mDownloadFileActivityPresenter = new DownloadFileActivityPresenter();
        mDownloadFileActivityPresenter.registerCallback(this);
    }

    @OnClick(R.id.btnDonwload)
    protected void onViewClick(View view) {
        if(view.getId() == R.id.btnDonwload) {

            String fileUrl = edtUrl.getText().toString();
            if(!fileUrl.trim().isEmpty() && fileUrl.trim().startsWith("http")) {
                mDownloadFileActivityPresenter.requestDownloadFile(getApplicationContext(), fileUrl);
            } else {
                Toast.makeText(getApplicationContext(), "Invalid url", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDownloadFileActivityPresenter.unRegisterCallback();
    }

    @Override
    public void onDownloadUpdate(final int downloadProgress) {
        txtDownloadStatus.setText("Completed " + downloadProgress + " %");
    }

    @Override
    public void onDownloadSuccess() {
        txtDownloadStatus.setText("File has been downloaded successfully !");
    }

    @Override
    public void onDownloadFailure() {
        txtDownloadStatus.setText("File download failed !");
    }

    @Override
    public void onDownloadPause() {
        txtDownloadStatus.setText("File download paused ! ");
    }
}
