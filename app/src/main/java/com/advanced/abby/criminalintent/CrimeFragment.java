package com.advanced.abby.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;

import java.util.Date;


public class CrimeFragment extends Fragment
{
    Crime mCrime;
    EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;

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
        updateDate();
        //Create and show a DatePickerFragment when the date button is pressed
        mDateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }//end onClick
        });//end Listener

        //Get reference to the fragment_crime checkbox
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.solved_checkbox);
        //When the checkbox is toggled, change whether the Crime has been solved or not
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton c, boolean solved)
            {
                mCrime.setSolved(solved);
            }
        });//end Listener

        return v;
    }//emd onCreateView


    //For receiving a date from DatePickerFragment
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode != Activity.RESULT_OK)
        {
            return;
        }

        if(requestCode == REQUEST_DATE)
        {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);

            mCrime.setDate(date);
            updateDate();
        }
    }//end onActivityResult

    private void updateDate()
    {
        mDateButton.setText(mCrime.getDate().toString());
    }


}
