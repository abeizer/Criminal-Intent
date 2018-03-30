//This class is representative of a single crime.
//Contains only a title and a unique ID.

package com.advanced.abby.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Crime
{
    UUID mld;       //Crime ID
    String mTitle;  //Title of Crime
    private Date mDate;     //Date of the Crime
    private boolean mSolved;    //Whether the Crime has been solved

    //Default Constructor
    public Crime()
    {
        //assigns a randomized ID to this Crime
        this.mld = UUID.randomUUID();
        this.mDate = new Date(); //Sets to the current date (default date for crime)
        this.mTitle = "";
    }//end constructor

    //returns title of Crime
    public String getTitle()
    {
        return this.mTitle;
    }//end getTitle

    //sets title of Crime
    public void setTitle(String mTitle)
    {
        this.mTitle = mTitle;
    }//end setTitle

    //returns the Crime ID
    public UUID getId()
    {
        return this.mld;
    }//end getId

    //returns the Date of the Crime
    public Date getDate() { return this.mDate; }//end getDate

    //sets the date of the Crime
    public void setDate(Date date) { this.mDate = date; }//end setDate

    //returns whether the Crime has been solved
    public boolean isSolved() { return this.mSolved; }//end isSolved

    //sets the Crime to solved
    public void setSolved(boolean b) {this.mSolved = b; }//end setSolved
}
