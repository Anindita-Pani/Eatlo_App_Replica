package com.app.eatlo.util;

/* Displays all the dialogs of the application - leave cart and location closed */
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.app.eatlo.R;

public class DialogMessage extends DialogFragment {

	Context context;
	OnClickListener confirmClickListner, cancelClickListner;
	String msgToShow, btnConfirmText;
	Boolean displayMsg; // if confirmation required or only message display

	public DialogMessage(Context context, OnClickListener confirmClickListner,
			OnClickListener cancelClickListner, String msgToShow,
			String btnConfirmText, Boolean displayMsg) {
		this.context = context;
		this.confirmClickListner = confirmClickListner;
		this.cancelClickListner = cancelClickListner;
		this.msgToShow = msgToShow;
		this.btnConfirmText = btnConfirmText;
		this.displayMsg = displayMsg;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		View dialogView = inflater.inflate(R.layout.dialog, null);
		builder.setView(dialogView);
		TextView tvMsg = (TextView) dialogView.findViewById(R.id.tvMsg);
		TextView tvConfirm = (TextView) dialogView.findViewById(R.id.tvConfirm);
		tvConfirm.setOnClickListener(confirmClickListner);
		tvConfirm.setText(btnConfirmText);
		tvMsg.setText(msgToShow);
		if (!displayMsg) {
			// confirmation required - Show the cancel button
			TextView tvCancel = (TextView) dialogView
					.findViewById(R.id.tvCancel);
			tvCancel.setOnClickListener(cancelClickListner);
			tvCancel.setVisibility(View.VISIBLE);
		}
		AlertDialog resultDialog = builder.create();
		resultDialog.setView(dialogView, 0, 0, 0, 0);
		return resultDialog;
	}

}
