package com.rastogiakshay.tags;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.PopupWindowCompat;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.rastogiakshay.tags.Adapters.CategoryFragmentPagerAdapter;
import com.rastogiakshay.tags.Adapters.TabAdapter;

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 0;
    Button SignIn,OtpLogin;
    EditText UserName,Pwd;
    TabAdapter tabAdapter;
    GoogleSignInClient mGoogleSignInClient;
    ViewPager viewPager;
    LayoutInflater inflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        OtpLogin = findViewById(R.id.otp);
        SignIn = findViewById(R.id.sign_in);
        UserName = findViewById(R.id.username);
        Pwd = findViewById(R.id.password);
        findViewById(R.id.googleLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
       // mSignInButton.setSize(SignInButton.SIZE_STANDARD);

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserName.getText().toString().equals("ak") && Pwd.getText().toString().equals("ak")){
                    Toast.makeText(getApplicationContext(),"Login is Succesfull",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Unsuccesfull",Toast.LENGTH_LONG).show();
                }
            }
        });

        OtpLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //view = inflater.inflate(findViewById(R.layout.floating_otp));
                MobLogin();

            }
        });

    }
    private void MobLogin(){
        Intent intent = new Intent(this,DummyPhoneAuthActivity.class);
        startActivity(intent);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG","signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }

    }

    private void updateUI(GoogleSignInAccount account) {
        viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        CategoryFragmentPagerAdapter fm = new CategoryFragmentPagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(fm);


    }

}
