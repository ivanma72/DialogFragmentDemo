package com.dev.ivanma.dialogfragmentdemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Ivan on 8/15/2014.
 */
public class DialogOneFragment extends DialogFragment {

    int num;
    Button dialog2Button;
    Button dialog2SaveButton;
    EditText dialog2EditNum;

    public interface EditNumListener{
        public void changeNum(int n);
    }

    public static DialogOneFragment newInstance(int num){
        DialogOneFragment df = new DialogOneFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("num", num);
        df.setArguments(bundle);
        return df;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_one, null);

        num = getArguments().getInt("num");

        dialog2Button = (Button)v.findViewById(R.id.dialog_btn_two);
        dialog2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                FragmentManager fm = getActivity().getFragmentManager();
                DialogTwoFragment dialog = DialogTwoFragment.newInstance(num);
                dialog.setTargetFragment(fm.findFragmentById(R.id.fragmentContainer), 0);
                dialog.show(fm, "dialog2");
            }
        });

        dialog2EditNum = (EditText)v.findViewById(R.id.edit_num_field);
        dialog2EditNum.setText(String.valueOf(num));

        dialog2SaveButton = (Button)v.findViewById(R.id.save_num_btn);
        dialog2SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String newValue = dialog2EditNum.getText().toString();
               num = Integer.parseInt(newValue);
               getArguments().putInt("num", num);
               ((EditNumListener)getTargetFragment()).changeNum(num);
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.dialog_one_title)
                .setView(v)
                .setPositiveButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //nothing
                    }
                })
                .create();
    }
}
