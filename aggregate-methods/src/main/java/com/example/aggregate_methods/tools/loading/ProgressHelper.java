package com.example.aggregate_methods.tools.loading;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.KeyEvent;

/**
 * CREATE BY LiYang
 * ON 2021-01-27
 * SUPPLY : Thanks for watching
 */

public class ProgressHelper {

    private static ProgressBarDialog dialog;
    private static Activity context;

    public static void show(Activity activity) {
        context = (Activity) activity;
        if (!context.isFinishing()) {
            if (dialog == null) {
                dialog = new ProgressBarDialog(activity);
                dialog.setOnKeyListener(onKeyListener);
            }
            dialog.show();
        }
    }

    public static void hide() {
        if (null != dialog) {
            dialog.dismiss();
            dialog = null;
            context = null;
        }
    }

    public static DialogInterface.OnKeyListener onKeyListener = new DialogInterface.OnKeyListener() {
        @Override
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                context.finish();
                hide();
            }
            return false;
        }
    };
}
