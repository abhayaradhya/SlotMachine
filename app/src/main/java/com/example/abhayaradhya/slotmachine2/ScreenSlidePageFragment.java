package com.example.abhayaradhya.slotmachine2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by abhayaradhya on 3/15/18.
 */

public class ScreenSlidePageFragment extends Fragment{

    //private String text;
    private TextView pagerText;
    private String displayText;
    /*public void setText(int position){
        pagerText.setText(displayText[position]);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);
        pagerText = (TextView)rootView.findViewById(R.id.pagerTextView);
        displayText = getArguments().getString("displayText");
        pagerText.setText(displayText);
        //((TextView)rootView.findViewById(R.id.pagerTextView)).setText("asdf");
        return rootView;
    }
}
