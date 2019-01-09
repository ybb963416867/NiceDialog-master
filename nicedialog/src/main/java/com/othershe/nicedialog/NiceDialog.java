package com.othershe.nicedialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class NiceDialog extends BaseNiceDialog {
    private ViewConvertListener convertListener;

    private DialogInterface   onDismissListener;

    public  NiceDialog setOnDismissListener(DialogInterface onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }

    public static NiceDialog init() {
        return new NiceDialog();
    }

    @Override
    public int intLayoutId() {
        return layoutId;
    }

    @Override
    public void convertView(ViewHolder holder, BaseNiceDialog dialog) {
        if (convertListener != null) {
            convertListener.convertView(holder, dialog);
        }
    }


    public NiceDialog setLayoutId(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public NiceDialog setConvertListener(ViewConvertListener convertListener) {
        this.convertListener = convertListener;
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            convertListener = savedInstanceState.getParcelable("listener");
        }
    }

    /**
     * 保存接口
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("listener", convertListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(getContext(), "onDestroyView", Toast.LENGTH_SHORT).show();
        if (onDismissListener!=null){
            onDismissListener.dismiss();
        }
        convertListener = null;
    }
}
