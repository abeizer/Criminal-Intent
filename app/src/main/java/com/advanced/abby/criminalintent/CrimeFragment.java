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
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;


public class CrimeFragment extends Fragment
{
    Crime mCrime;
    EditText mTitleField;
    private Button mDateButton;
    private Button mTimeButton;
    private CheckBox mSolvedCheckBox;
    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";
    private static final String EXTRA_CRIME_ID = "edu.abby.android.criminalintent.crime_id";
    private static final int REQUEST_DATE = 0;

	public CrimeFragment()
	{
		
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }//end onCreate

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_crime, parent, false);

        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
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
        mDateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }//end onClick
        });//end Listener

        mTimeButton = (Button) v.findViewById(R.id.time_button);
        updateTime();
        mTimeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager manager = getFragmentManager();
                TimePickerFragment dialog = TimePickerFragment.newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);

            }
        });


        //Get reference to the fragment_crime checkbox
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.solved_checkbox);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
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

    //Accepts a UUID, creates an arguments bundle, and attaches the arguments to a new CrimeFragment
    public static CrimeFragment newInstance(UUID crimeId)
    {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }



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
            Date time = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_DATE);
            Calendar c1 = Calendar.getInstance();                       c1.setTime(date);
            Calendar c2 = Calendar.getInstance();                       c2.setTime(time);

            Date actual = new GregorianCalendar(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH),
                    c2.get(Calendar.HOUR_OF_DAY), c2.get(Calendar.MINUTE)).getTime();


            mCrime.setDate(actual);
            updateDate();
            updateTime();
        }
    }//end onActivityResult

    private void updateDate()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd yyyy");
        String date = formatter.format(mCrime.getDate());
        mDateButton.setText(date);
    }

    private void updateTime()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        String time = formatter.format(mCrime.getDate());
        mTimeButton.setText(time);
    }



}
