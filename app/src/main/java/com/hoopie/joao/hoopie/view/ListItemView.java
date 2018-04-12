package com.hoopie.joao.hoopie.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hoopie.joao.hoopie.R;

/**
 * John was here on 12/04/2018, exactly by 03:22
 */
public class ListItemView extends RelativeLayout {

    private ImageView icon;
    private TextView text, subText;

    public ListItemView(Context context) {
        super(context);
        init(context);
    }

    public ListItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.view_list_item, this, true);

        icon = findViewById(R.id.listItem_iv_icon);
        text = findViewById(R.id.listItem_tv_text);
        subText = findViewById(R.id.listItem_tv_subtext);
    }

    public void setIcon(int res) {
        icon.setImageResource(res);
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    public void setSubText(String text) {
        this.subText.setText(text);
    }
}
