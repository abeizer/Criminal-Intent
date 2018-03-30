package com.advanced.abby.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    //Inflates the RecyclerView layout in the CrimeListFragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }//end onCreateView

    private void updateUI()
    {
        //Get the list of crimes from the CrimeLab
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        //Pass the crime list to the adapter and associate the adapter with the RecyclerView
        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);

    }//end updateUI

    private class CrimeHolder extends RecyclerView.ViewHolder{

        public TextView mTitleTextView; //Title of the crime displayed as a TextView

        public CrimeHolder(View itemView)
        {
            super(itemView);
            mTitleTextView = (TextView) itemView;
        }
    }//end inner class


    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>
    {
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes)
        {
            mCrimes = crimes;
        }

        //create a new view and wrap it in a ViewHolder
        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);

            return new CrimeHolder(view);
        }//end onCreateViewHolder


        //Binds a ViewHolder's view to a single Crime
        @Override
        public void onBindViewHolder(CrimeHolder holder, int position)
        {
            Crime crime = mCrimes.get(position);
            holder.mTitleTextView.setText(crime.getTitle());
        }//end onBindViewHolder

        @Override
        public int getItemCount()
        {
            return mCrimes.size();
        }//end getItemCount
    }//end inner class

}//end outer class
