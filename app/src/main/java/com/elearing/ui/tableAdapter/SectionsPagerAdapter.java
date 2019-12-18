package com.elearing.ui.tableAdapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.elearing.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        if(position == 0){
            return PlaceholderFragment.newInstance(1);

        }else if( position == 1){
            return TeacherFragment.newInstance(2);
        }else if(position == 2){
            return MaterialFragment.newInstance(3);
        }
        return MaterialFragment.newInstance(1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return new String("Course Message");
        }else if(position ==1){
            return new String("Teacher");
        }else if(position == 2){
            return new String("material");
        }
        return "d  ";
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}