package com.xyfindables.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xyfindables.ui.dialogs.XYThrobberDialog;

import static com.xyfindables.core.XYBase.*;

public class XYBaseFragment extends Fragment {
    final private static String TAG = XYBaseFragment.class.getSimpleName();
    @Nullable
    private ProgressDialog _progressDialog;
    public XYThrobberDialog throbber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        logInfo(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        throbber = new XYThrobberDialog(getActivity());
    }

    protected void loadViews() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        logInfo(TAG, "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        loadViews();
    }

    public int dpToPx(@NonNull Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }

    @Override
    public void onResume() {
        logInfo(TAG, "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        logInfo(TAG, "onPause");
        super.onPause();
        throbber.hide();
    }

    protected void showToast(String message) {
        logInfo(TAG, "showToast");
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    private XYThrobberDialog _dialogThrobber = null;

    @Override
    public void onAttach(Context context) {
        logInfo(TAG, "onAttach");
        super.onAttach(context);
    }
}

