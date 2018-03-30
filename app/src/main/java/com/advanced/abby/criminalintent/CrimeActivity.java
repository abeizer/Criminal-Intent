//Will host each instance of a Crime

package com.advanced.abby.criminalintent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment()
    {
        return new CrimeFragment();
    }



}
