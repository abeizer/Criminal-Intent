package com.advanced.abby.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Button;


public class CrimeFragment extends Fragment
{
    Crime mCrime;
    EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

	public CrimeFragment()
	{
		
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        mCrime = new Crime();
        super.onCreate(savedInstanceState);
    }//end onCreate

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_crime, parent, false);

        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence c, int start, int before, int count)
            {
                mCrime.setTitle(c.toString());
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                //do nothing
            }
        });

        //Get reference to fragment_crime button
        mDateButton = (Button) v.findViewById(R.id.date_button);
        //Set button text to the correct date
        mDateButton.setText(mCrime.getDate().toString());
        //TODO: enable mDateButton
        mDateButton.setEnabled(false);

        //Get reference to the fragment_crime checkbox
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.solved_checkbox);
        //When the checkbox is toggled, change whether the Crime has been solved or not
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton c, boolean solved)
            {
                mCrime.setSolved(solved);
            }
        });

        return v;
    }


}
