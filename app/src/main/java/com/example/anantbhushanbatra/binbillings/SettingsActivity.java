package com.example.anantbhushanbatra.binbillings;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatActivity {
    HttpManager httpManager;
    Call<User> balanceHttpQuery;
    private static final String TAG = "SettingsActivity";
    TextView name, email;
    TextView city, apartment_commmunity;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        httpManager = new HttpManager();
        balanceHttpQuery = httpManager.getUserInfo(1);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        city = findViewById(R.id.city);
        apartment_commmunity =findViewById(R.id.apartment_community);


        balanceHttpQuery.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                populateViews();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, t.getLocalizedMessage());
            }
        });
    }

    public void populateViews(){
        name.setText(user.getCustName());
        //city.setTextAlignment(u);
        email.setText(user.getCustEmail());
        Log.e(TAG, user.getCityName());
        city.setText(user.getCityName());
        apartment_commmunity.setText(user.getCommunityName());
    }
}
