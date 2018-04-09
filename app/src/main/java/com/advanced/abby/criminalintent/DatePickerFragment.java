package com.advanced.abby.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment
{
    private static final String ARG_DATE = "date";
    private DatePicker mDatePicker;
    public static final String EXTRA_DATE = "com.advanced.android.criminalintent.date";

    //Creates a new bundle including the variable ARG_DATE
    //Which will be used to pass the user's chosen date between the
    //DatePickerFragment and the CrimeFragment
    public static DatePickerFragment newInstance(Date date)
    {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //Will be called by the FragmentManager when displaying this fragment
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        //Get the Date from the arguments, and use a Calendar to get the integer values for the month/day/year
        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //inflate dialog_date layout
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);

        mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(year, month, day, null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int year = mDatePicker.getYear();
                                int month = mDatePicker.getMonth();
                                int day = mDatePicker.getDayOfMonth();
                                Date date = new GregorianCalendar(year, month, day).getTime();
                                sendResult(Activity.RESULT_OK, date);
                            }
                        })
                .create();

    }//end onCreateDialog


    //Creates an intent, puts the date on it as an extra, then calls CrimeFragment.onActivityResult
    //Used to communicate between this and CrimeFragment
    private void sendResult(int resultCode, Date date)
    {
        if(getTargetFragment() == null)
        {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }//end sendResult

}
