package com.softdesign.devintensive.ui.activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.softdesign.devintensive.R;
import com.softdesign.devintensive.data.managers.DataManager;
import com.softdesign.devintensive.utils.ConstantManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = ConstantManager.TAG_PREFIX + "Main Activity";
    private Toolbar mToolbar;
    private ImageView mUserPhoto;
    private EditText mUserPhoneNumber;
    private EditText mUserEmail;
    private EditText mUserVkProfile;
    private EditText mUserGithubProfile;
    private EditText mUserAbout;
    private boolean mIsEditMode;
    private FloatingActionButton mFloatingActionButton;
    private List<EditText> mUserInfoFieldsList = new ArrayList<EditText>();
    private DrawerLayout mNavigationDrawerLayout;
    private CoordinatorLayout mCoordinatorLayoutContainer;
    private DataManager mDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");

        initViews();
        setupDrawer();
        setupToolbar();

        loadUserInfoValue();

        if (savedInstanceState == null) {

        } else {
            mIsEditMode = savedInstanceState.getBoolean(ConstantManager.EDIT_MODE_KEY);
            changeEditMode(mIsEditMode);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mNavigationDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        mDataManager = DataManager.getInstance();

        mToolbar = (Toolbar) findViewById(R.id.widget_toolbar);
        mUserPhoto = (ImageView) findViewById(R.id.user_photo);
        mUserPhoneNumber = (EditText) findViewById(R.id.mobile_phone_number);
        mUserEmail = (EditText) findViewById(R.id.email);
        mUserVkProfile = (EditText) findViewById(R.id.vk_profile);
        mUserGithubProfile = (EditText) findViewById(R.id.repository_url);
        mUserAbout = (EditText) findViewById(R.id.about_me);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button);
        if (mFloatingActionButton != null) {
            mFloatingActionButton.setOnClickListener(this);
        }
        mNavigationDrawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);
        mCoordinatorLayoutContainer = (CoordinatorLayout) findViewById(R.id.coordinator_layout_container);

        mIsEditMode = false;

        mUserInfoFieldsList.add(mUserPhoneNumber);
        mUserInfoFieldsList.add(mUserEmail);
        mUserInfoFieldsList.add(mUserVkProfile);
        mUserInfoFieldsList.add(mUserGithubProfile);
        mUserInfoFieldsList.add(mUserAbout);
    }

    public void setupDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            updateDrawer();
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    showSnackbar(item.getTitle().toString());
                    item.setChecked(true);
                    mNavigationDrawerLayout.closeDrawer(GravityCompat.START);
                    return false;
                }
            });
        }
    }


    public void showSnackbar(String message) {
        Snackbar.make(mCoordinatorLayoutContainer, message, Snackbar.LENGTH_LONG).show();
    }


    public void updateDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            TextView menuUserEmail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.drawer_user_email);
            menuUserEmail.setText(mUserEmail.getText());
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ConstantManager.EDIT_MODE_KEY, mIsEditMode);
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        saveUserInfoValue();
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    /**
     * Dispatch onStart() to all fragments.  Ensure any created loaders are
     * now started.
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_action_button:
                mIsEditMode = !mIsEditMode;
                changeEditMode(mIsEditMode);
                break;
        }
    }

    private void changeEditMode(boolean isEditMode) {
        int buttonPicture;
        if (isEditMode) {
            buttonPicture = R.drawable.ic_check_white_24dp;
        } else {
            buttonPicture = R.drawable.ic_edit_white_24dp;
        }
        mFloatingActionButton.setImageResource(buttonPicture);
        for (EditText editText : mUserInfoFieldsList) {
            editText.setEnabled(isEditMode);
            editText.setFocusable(isEditMode);
            editText.setFocusableInTouchMode(isEditMode);
        }
    }

    public void setupToolbar() {
        Log.d(TAG, mToolbar.toString());
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void loadUserInfoValue() {
        List<String> userData = mDataManager.getPreferencesManager().loadUserProfileData();

        for (int i = 0; i < userData.size(); i++) {
            mUserInfoFieldsList.get(i).setText(userData.get(i));
        }
    }

    private void saveUserInfoValue() {
        List<String> userData = new ArrayList<>();

        for (EditText userFieldView : mUserInfoFieldsList) {
            userData.add(userFieldView.getText().toString());
        }
        mDataManager.getPreferencesManager().saveUserProfileData(userData);
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mNavigationDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
