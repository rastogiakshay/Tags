package com.rastogiakshay.tags.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rastogiakshay.tags.R;
import com.rastogiakshay.tags.fragments.ExploreFragment;
import com.rastogiakshay.tags.fragments.HomeFragment;
import com.rastogiakshay.tags.fragments.SavedFragment;
import com.rastogiakshay.tags.fragments.TrendingFragment;

public class CategoryFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private static final int HOME =0;
    private static final int EXPLORE =1;
    private static final int TRENDING =2;
    private static final int SAVED =3;

    public CategoryFragmentPagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case HOME:
                return new HomeFragment();
            case EXPLORE:
                return new ExploreFragment();
            case TRENDING:
                return new TrendingFragment();
            case SAVED:
                return new SavedFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String ResID;
        switch (position){
            case HOME:
                ResID = "HOME";
                break;
            case EXPLORE:
                ResID = "EXPLORE";
                break;
            case TRENDING:
                ResID = "TRENDING";
                break;
            default:
                ResID = "SAVED";
                break;
        }
        return mContext.getString(position);
    }
}
