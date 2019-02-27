package com.deepshooter.appbundledemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.play.core.splitinstall.SplitInstallManager;
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory;
import com.google.android.play.core.splitinstall.SplitInstallRequest;
import com.google.android.play.core.tasks.OnFailureListener;
import com.google.android.play.core.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {


    private SplitInstallManager splitInstallManager = null;
    private String mFeatureBlueClass = "com.deepshooter.featureblue.FeatureBlueActivity";
    private String mFeaturePinkClass = "com.deepshooter.featurepink.FeaturePinkActivity";
    private Button mButtonFeatureBlue, mButtonFeaturePink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setValues();

    }

    private void initViews() {
        splitInstallManager =
                SplitInstallManagerFactory.create(getApplicationContext());

        mButtonFeatureBlue = findViewById(R.id.btn_feature_blue);
        mButtonFeaturePink = findViewById(R.id.btn_feature_pink);
    }

    private void setValues() {
        mButtonFeatureBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFeatureBlue();
            }
        });
        mButtonFeaturePink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFeaturePink();
            }
        });
    }

    public void loadFeatureBlue() {
        // Builds a request to install the feature blue module
        String mFeatureBlue = "featureblue";
        SplitInstallRequest request =
                SplitInstallRequest
                        .newBuilder()
                        // You can download multiple on demand modules per
                        // request by invoking the following method for each
                        // module you want to install.
                        .addModule(mFeatureBlue)
                        .build();

        // Begin the installation of the feature1 module and handle success/failure
        splitInstallManager
                .startInstall(request)
                .addOnSuccessListener(new OnSuccessListener<Integer>() {
                    @Override
                    public void onSuccess(Integer integer) {
                        // Module download successful
                        Intent intent = new Intent().setClassName(getPackageName(), mFeatureBlueClass);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        // Module download failed; handle the error here
                        Toast.makeText(getApplicationContext(), getString(R.string.could_not_download_feature_blue) + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void loadFeaturePink() {
        // Builds a request to install the feature pink module
        String mFeaturePink = "featurepink";
        SplitInstallRequest request =
                SplitInstallRequest
                        .newBuilder()
                        // You can download multiple on demand modules per
                        // request by invoking the following method for each
                        // module you want to install.
                        .addModule(mFeaturePink)
                        .build();

        // Begin the installation of the feature1 module and handle success/failure
        splitInstallManager
                .startInstall(request)
                .addOnSuccessListener(new OnSuccessListener<Integer>() {
                    @Override
                    public void onSuccess(Integer integer) {
                        // Module download successful
                        Intent intent = new Intent().setClassName(getPackageName(), mFeaturePinkClass);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        // Module download failed; handle the error here
                        Toast.makeText(getApplicationContext(), getString(R.string.could_not_download_feature_pink) + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
