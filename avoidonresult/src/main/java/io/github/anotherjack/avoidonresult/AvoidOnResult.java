package io.github.anotherjack.avoidonresult;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import io.reactivex.Observable;

/**
 * Created by jack on 2017/12/27.
 */

public class AvoidOnResult {
    private static final String TAG = "AvoidOnResult";
    private AvoidOnResultFragment mAvoidOnResultFragment;

    public AvoidOnResult(FragmentActivity activity) {
        mAvoidOnResultFragment = getAvoidOnResultFragment(activity);
    }

    public AvoidOnResult(Fragment fragment){
        this(fragment.getActivity());
    }

    private AvoidOnResultFragment getAvoidOnResultFragment(FragmentActivity activity) {
        AvoidOnResultFragment avoidOnResultFragment = findAvoidOnResultFragment(activity);
        if (avoidOnResultFragment == null) {
            avoidOnResultFragment = new AvoidOnResultFragment();
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(avoidOnResultFragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return avoidOnResultFragment;
    }

    private AvoidOnResultFragment findAvoidOnResultFragment(FragmentActivity activity) {
        return (AvoidOnResultFragment) activity.getSupportFragmentManager().findFragmentByTag(TAG);
    }

    public Observable<ActivityResultInfo> startIntentSenderForResult(IntentSender intentSender,
                                                                     Intent fillingIntent,
                                                                     Integer flagsMask,
                                                                     Integer flagsValues,
                                                                     Integer extraFlags,
                                                                     Bundle options) {   //2
        return mAvoidOnResultFragment.startIntentSenderForResultRx(intentSender, fillingIntent, flagsMask, flagsValues, extraFlags, options);
    }

    public Observable<ActivityResultInfo> startForResult(Intent intent) {   //2
        return mAvoidOnResultFragment.startForResult(intent);
    }

    public Observable<ActivityResultInfo> startForResult(Class<?> clazz) {  //1
        Intent intent = new Intent(mAvoidOnResultFragment.getActivity(), clazz);
        return startForResult(intent);
    }

    public void startForResult(Intent intent, Callback callback) { //2
        mAvoidOnResultFragment.startForResult(intent, callback);
    }

    public void startForResult(Class<?> clazz, Callback callback) { //1
        Intent intent = new Intent(mAvoidOnResultFragment.getActivity(), clazz);
        startForResult(intent, callback);
    }

    public interface Callback {
        void onActivityResult(int resultCode, Intent data);
    }
}
