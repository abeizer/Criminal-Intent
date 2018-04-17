//This class acts as a centralized data stash that stores Crime objects

package com.advanced.abby.criminalintent;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab
{
    private static CrimeLab sCrimeLab;
    private ArrayList<Crime> mCrimes;

    //Default constructor
    private CrimeLab(Context context)
    {
       mCrimes = new ArrayList<>();

       //populate arraylist with 100 placeholder crimes
       /*
       for(int i = 0; i < 100; i++)
       {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved( (i%2) == 0);   //Every other crime
            mCrimes.add(crime);
       }//end for
       */
    }//end constructor

    //returns this
    public static CrimeLab get(Context context)
    {
        //If sCrimeLab has not been initialized, initialize it
        if(sCrimeLab == null)
        {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }//end get

    //returns the arraylist of crimes
    public List<Crime> getCrimes()
    {
        return mCrimes;
    }//end getCrimes

    public Crime getCrime(UUID id)
    {
        for(Crime crime : mCrimes)
        {
            if(crime.getId().equals(id))
            {
                return crime;
            }
        }//end for
        return null;
    }//end getCrime

    public void addCrime(Crime c)
    {
        mCrimes.add(c);
    }

    public void deleteCrime(Crime c)
    {
        for(Crime crime : mCrimes)
        {
            if(crime == c)
            {
                mCrimes.remove(c);
                return;
            }
        }
    }



}//end class
