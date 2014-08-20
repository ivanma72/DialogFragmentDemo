package com.dev.ivanma.dialogfragmentdemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Ivan on 8/15/2014.
 */
public class DialogTwoFragment extends DialogFragment {

    private int num;

    private EditText dialogEditNum;
    private Button dialogSaveButton;

    public static DialogTwoFragment newInstance(int num){
        DialogTwoFragment df = new DialogTwoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("num", num);
        df.setArguments(bundle);
        return df;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        num = getArguments().getInt("num");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_two, null);



        dialogEditNum = (EditText)v.findViewById(R.id.edit_num_field_two);
        dialogEditNum.setText(String.valueOf(num));

        dialogSaveButton = (Button)v.findViewById(R.id.save_num_btn_two);
        dialogSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newValue = dialogEditNum.getText().toString();
                num = Integer.parseInt(newValue);
                getArguments().putInt("num", num);
                ((DialogOneFragment.EditNumListener)getTargetFragment()).changeNum(num);

            }
        });

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.dialog_two_title)
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
