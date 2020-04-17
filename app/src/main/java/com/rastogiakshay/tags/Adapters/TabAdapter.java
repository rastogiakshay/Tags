package com.rastogiakshay.tags.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabAdapter extends FragmentStatePagerAdapter {
private final List<Fragment> fragments = new ArrayList<Fragment>();
private final List<String> fragmentName = new ArrayList<String>();
    public TabAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addFragment(Fragment frag, String name){
        fragments.add(frag);
        fragmentName.add(name);
    }
    public CharSequence getPageTitle(int pos){
        return fragmentName.get(pos);
    }
}
