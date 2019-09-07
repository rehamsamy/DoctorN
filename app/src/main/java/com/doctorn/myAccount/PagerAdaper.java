package com.doctorn.myAccount;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.doctorn.myAccount.MyDataFragment.AccountMyDataFragment;
import com.doctorn.myAccount.RatesFragment.AccountRatesFragment;
import com.doctorn.myAccount.RegisterInfoFragemt.AccountRegisterInfoFragment;

public class PagerAdaper extends FragmentPagerAdapter {

    public PagerAdaper(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0 :
                return new AccountRatesFragment();
            case 1:
                return new AccountRegisterInfoFragment();
            case 2:
                return new AccountMyDataFragment();
                default:
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
        switch (position){
            case 0:
                return "التقييمات" ;

            case 1:
                return "معلومات التسجيل";

            case 2:
                return "معلوماتي";
                default:
                    return null;


        }
    }
}
