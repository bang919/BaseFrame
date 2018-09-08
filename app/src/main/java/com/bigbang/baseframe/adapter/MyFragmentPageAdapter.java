package com.bigbang.baseframe.adapter;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.bigbang.baseframe.R;

/**
 * 用于Fragment的切换
 */
public class MyFragmentPageAdapter {

    private FragmentActivity mFragmentActivity;
    private Fragment mFragment;
    private int containerViewId;
    private Fragment currentFragment;

    public MyFragmentPageAdapter(FragmentActivity fragmentActivity, @IdRes int containerViewId) {
        mFragmentActivity = fragmentActivity;
        this.containerViewId = containerViewId;
    }

    public MyFragmentPageAdapter(Fragment fragment, @IdRes int containerViewId) {
        mFragment = fragment;
        this.containerViewId = containerViewId;
    }

    public void switchToFragment(Fragment targetFragment) {
        switchToFragment(targetFragment, false);
    }

    public void switchToFragment(Fragment targetFragment, boolean isAddToBackStack) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(
                R.anim.anim_dialog_next_enter,
                R.anim.anim_dialog_exit,
                R.anim.anim_dialog_enter,
                R.anim.anim_dialog_next_exit
        );
        if (!isAddToBackStack) {
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            if (!targetFragment.isAdded()) {
                transaction.add(containerViewId, targetFragment);
            } else {
                transaction.show(targetFragment);
            }
            transaction.commit();
            currentFragment = targetFragment;
        } else {
            transaction.replace(containerViewId, targetFragment, targetFragment.getClass().getName());
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        }
    }

    private FragmentManager getFragmentManager() {
        return mFragmentActivity != null ? mFragmentActivity.getSupportFragmentManager() : mFragment.getChildFragmentManager();
    }
}
