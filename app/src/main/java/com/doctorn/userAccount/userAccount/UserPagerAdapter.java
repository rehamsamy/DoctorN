package com.doctorn.userAccount.userAccount;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.doctorn.UserRatesFragment.UserRatesFragment;
import com.doctorn.userAccount.userAccount.articleFragment.UserArticleFragment;
import com.doctorn.doctorAccount.DoctorRatesFragment.DoctorRatesFragment;
import com.doctorn.doctorAccount.RegisterInfoFragemt.AccountRegisterInfoFragment;

public class UserPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = PagerAdapter.class.getSimpleName();

    //int flag= LoginActivity
    private String tabTitles2[] = new String[]{"معلوماتي", "بيانات التسجيل", "التقييمات"};
    private String tabTitles1[] = new String[]{"معلوماتي", "مفضله الاطباء", "مفضلة المقالات"};



    public UserPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;

        if (i == 0 ) {
            fragment = new AccountRegisterInfoFragment();
            return fragment;
        }  else if (i == 1 ) {
            fragment = new UserRatesFragment();
            return fragment;
        }  else if (i == 2 ) {
            fragment = new UserArticleFragment();
            return fragment;
        }  else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {


            return tabTitles1[position];


    }

}
