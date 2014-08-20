package com.dev.ivanma.dialogfragmentdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ivan on 8/15/2014.
 */
public class DialogDemoFragment extends Fragment implements DialogOneFragment.EditNumListener {

    private Button dialogOne;
    private TextView numLabel;
    private int num;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            num = savedInstanceState.getInt("num");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("num", num);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        dialogOne = (Button)v.findViewById(R.id.dialog_btn_one);
        dialogOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getFragmentManager();
                DialogOneFragment dialog = DialogOneFragment.newInstance(num);
                dialog.setTargetFragment(DialogDemoFragment.this, 0);
                dialog.show(fm, "dialog1");
            }
        });

        numLabel = (TextView)v.findViewById(R.id.text_field_num);
        numLabel.setText("The number is: " + String.valueOf(num));
        return v;
    }

    @Override
    public void changeNum(int n) {
        num = n;
        numLabel.setText("The number is: " + String.valueOf(num));
        Toast.makeText(getActivity(), R.string.value_saved, Toast.LENGTH_SHORT).show();
    }
}
