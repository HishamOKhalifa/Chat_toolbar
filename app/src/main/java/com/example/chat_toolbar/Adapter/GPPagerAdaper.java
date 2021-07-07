package com.example.chat_toolbar.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.chat_toolbar.Fragment.CompanyFragment;
import com.example.chat_toolbar.Fragment.ResumeFragment;
import com.example.chat_toolbar.Fragment.ScholarFragment;
import com.example.chat_toolbar.Fragment.SettingFragment;

public class GPPagerAdaper extends FragmentStateAdapter {
    public GPPagerAdaper(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ResumeFragment();
            case 1:
                return new CompanyFragment();
            case 2:
                return new ScholarFragment();
            default:
                return new SettingFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
