package com.example.varunsai.vce;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.LoginAuthorizationType;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Login extends AppCompatActivity implements android.support.v4.app.LoaderManager.LoaderCallbacks,View.OnClickListener {
    private static final int RC_SIGN_IN = 0;
   FirebaseAuth auth;
    public static String name;
    public  static  String key1;
    public static user userg;
    public static String usname="null";
    public static boolean fb=false;
    static SharedPreferences preferences;
   // public static FirebaseUser user;
    CallbackManager callbackManager;
    ConnectionResult mConnectionResult;
    static boolean sign_status=false;
    Button b1;
    FirebaseOptions firebaseOptions;
    static GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    private SignInButton GoogleSignInButton;
    boolean signedInUser;
    boolean mIntentInProgress;
    // UI references.
    private AutoCompleteTextView mEmailView;
    private static final String TAG = Login.class.getSimpleName();
    private EditText mPasswordView;
     RadioGroup gp;
    private View mProgressView;
    private View mLoginFormView;
    static GoogleSignInClient mGoogleSignInClient;
    TextView t2;
    public static DatabaseReference db;
    RadioButton r1,r2,r3;
    public static String logintype="student";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       auth = FirebaseAuth.getInstance();
       gp=(RadioGroup)findViewById(R.id.rd);
       r1=(RadioButton)findViewById(R.id.radio1);
       r2=(RadioButton)findViewById(R.id.radio2);
       r3=(RadioButton)findViewById(R.id.radio3);
       db = FirebaseDatabase.getInstance().getReference();
       // user = auth.getCurrentUser();
        setContentView(R.layout.activity_login);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        SignInButton signInButton = findViewById(R.id.google);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        findViewById(R.id.google).setOnClickListener(this);
        findViewById(R.id.signout_button).setOnClickListener(this);
        findViewById(R.id.signout_button).setVisibility(View.GONE);
        callbackManager = CallbackManager.Factory.create();
        
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio1:
                if (checked)
                   // r1.setBackgroundColor(getResources().getColor(R.color.btn_logut_bg));
                logintype="student";
                Log.d("name", "hhh");
                break;
            case R.id.radio2:
                if (checked)
                   // r1.setBackgroundColor(getResources().getColor(R.color.btn_logut_bg));
                logintype="faculty" ;
                Log.d("ff", "ggggg");
                break;
            case R.id.radio3:
                if (checked)
                    // r1.setBackgroundColor(getResources().getColor(R.color.btn_logut_bg));
                    logintype="hr" ;
                Log.d("ff", "ggggg");
                break;

        }
    }
    public  void openActivity1(GoogleSignInAccount account)
    {   final String email=account.getEmail().toString();
        final GoogleSignInAccount account1=account;
        Toast.makeText(Login.this, "abc",Toast.LENGTH_LONG).show();
        read(new Firebasecallback() {
            @Override
            public void onCallback(List<user> list) {
                int c=0;
                 Toast.makeText(Login.this, "msg12",Toast.LENGTH_LONG).show();
                for (user e:list)
                {        //   Toast.makeText(LoginActivity.this, "list",Toast.LENGTH_LONG).show();
                    if(e.getMail().equals(email))
                    {      //  Toast.makeText(LoginActivity.this, "msg5",Toast.LENGTH_LONG).show();
                        c=1;
                        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                        SharedPreferences.Editor edit = preferences.edit();
                        edit.putString("key",e.uid);
                        edit.commit();
                        name=e.mail;
                        usname=e.username;
                        userg=new user(e.uid,e.username,e.mail,e.url);
                        if(logintype.equals("faculty")) {
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        if(logintype.equals("student")) {
                            Intent intent = new Intent(Login.this, Company.class);
                            startActivity(intent);
                        }
                        else
                            if(logintype.equals("hr"))
                            {
                                Intent intent=new Intent(Login.this,VerifyHr.class);
                                startActivity(intent);

                            }
                    }
                }
                if(c==0)
                    existing2(account1);
                 Toast.makeText(Login.this, "msg6", Toast.LENGTH_LONG).show();
            }
        });

    }




    public void existing2(final GoogleSignInAccount account1) {
        GoogleSignInAccount account = account1;
        name = account.getEmail();
        usname = account.getDisplayName();
        String img = null;
        if (account.getPhotoUrl() == null) {
            img = "null";
        } else {
            img = account.getPhotoUrl().toString();
        }
        DatabaseReference reference =FirebaseDatabase.getInstance().getReference(logintype);
        key1 = reference.push().getKey();
        userg = new user(key1, usname, name, img);
        reference.child(key1).setValue(userg);
        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("key", key1);
        edit.commit();
        if(logintype.equals("faculty")) {
            Intent intent = new Intent(Login.this, inform.class);
            startActivity(intent);
        }
        else
        if(logintype.equals("student")) {
            Intent intent = new Intent(Login.this,Profile.class);
            startActivity(intent);
        }
        else
        if(logintype.equals("hr"))
        {
            Intent intent=new Intent(Login.this,VerifyHr.class);
             startActivity(intent);

        }

        // Toast.makeText(LoginActivity.this,"final" ,Toast.LENGTH_LONG ).show();
    }

    @NonNull
    @Override
    public Loader onCreateLoader(int i, @Nullable Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader loader, Object o) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            Toast.makeText(Login.this, "msg49",Toast.LENGTH_LONG).show();
            handleSignInResult(task);
        }
       /* else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
            Toast.makeText(Login.this, "msg43",Toast.LENGTH_LONG).show();
        }        */
    }

    public void updateUI(FirebaseAuth user)
    {
        findViewById(R.id.google).setVisibility(View.GONE);
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            Toast.makeText(Login.this,"msg50", Toast.LENGTH_LONG);
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Toast.makeText(Login.this,"msg44", Toast.LENGTH_LONG);
            // Signed in successfully, show authenticated UI.

            Toast.makeText(Login.this, logintype.toString(), Toast.LENGTH_LONG);
           openActivity1(account);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }
    public void signOut() {

        auth.signOut();
        LoginManager.getInstance().logOut();
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        Toast.makeText(Login.this, "msg41",Toast.LENGTH_LONG).show();
    }


    @Override

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.google:
                sign_status=true;
                signIn();
                Toast.makeText(Login.this, "msg10",Toast.LENGTH_LONG).show();
                break;
            // ...
            case  R.id.signout_button:
                signOut();
                break;
        }

    }

    public void read(final Firebasecallback firebasecallback){
        //int selectionid=gp.getCheckedRadioButtonId();
        //RadioButton rr=(RadioButton)findViewById(selectionid);
        //logintype=rr.getText().toString().toLowerCase();
        final List<user> list=new ArrayList<user>();
        list.clear();
      //  Toast.makeText(Login.this, "msg3  "+logintype,Toast.LENGTH_LONG).show();
        FirebaseDatabase.getInstance().getReference(logintype.toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot==null){
                    
                    Toast.makeText(Login.this, "alert 789",Toast.LENGTH_LONG).show();

                }
                else {
                     Toast.makeText(Login.this, "msg18",Toast.LENGTH_LONG).show();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String key=snapshot.getKey();
                        String mail = (String) snapshot.child("mail").getValue();
                        String url = (String) snapshot.child("url").getValue();
                        String name = (String) snapshot.child("username").getValue();
                        user user1 = new user(key,name, mail, url);
                        // Toast.makeText(MainActivity.this, mail,Toast.LENGTH_LONG).show();
                        list.add(user1);
                        //  Toast.makeText(LoginActivity.this, "msg20",Toast.LENGTH_LONG).show();

                    }

                }

                // Toast.makeText(LoginActivity.this, "msg4",Toast.LENGTH_LONG).show();
                firebasecallback.onCallback(list);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public interface Firebasecallback {

        void onCallback(List<user> list);
    }


}