package org.spaceappschallenge.spacemission.roadmap.mgf.utilities;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.ProgressBar;

import org.spaceappschallenge.spacemission.roadmap.mgf.R;

/**
 * Created by lfeliu on 4/13/14.
 */
public class CustomProgressDialog {

        public static CustomizedDialog generateDialog(final Context context) {

            final CustomizedDialog dialog = new CustomizedDialog(context);

            dialog.setTitle(null);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(new ProgressBar(context, null, android.R.attr.progressBarStyleLarge));

            return dialog;

        }

        public static class CustomizedDialog extends Dialog {
            public CustomizedDialog(Context context) {
                super(context, R.style.Theme_SpaceApps_ProgressBarDialog);
            }
        }

}
