package com.gcorp.knitshceme;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


import static android.content.Intent.getIntent;

public class paintField extends Fragment {

    private PaintFieldViewModel mViewModel;
    Pattern pattern;
    Intent intent;


    public static paintField newInstance() {
        return new paintField();
    }
    public void setIntent(Intent itnt) {  intent =  itnt;}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.paint_field_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PaintFieldViewModel.class);
        // TODO: Use the ViewModel



        int rows = Integer.parseInt(intent.getStringExtra("rows"));
        int columns = Integer.parseInt(intent.getStringExtra("columns"));
        pattern = new Pattern(rows, columns);
        new DrawView(getActivity(), pattern);

    }


    public  void DrawOnFragmet(Pattern pattern)
    {
        new DrawView(getActivity(), pattern);
    }
}
