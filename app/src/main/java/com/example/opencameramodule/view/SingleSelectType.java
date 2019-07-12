package com.example.opencameramodule.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.opencameramodule.R;
import com.example.opencameramodule.view.model.Parent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingleSelectType extends LinearLayout {

    Builder builder;
    int lastCheckedPos;
    Map<String, String> selectedItem = new HashMap<>();

    public SingleSelectType(Context context) {
        super(context);
    }

    public SingleSelectType(Builder builder) {
        super(builder.context);
        this.builder = builder;
        addOptions(builder);
    }

    public SingleSelectType(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SingleSelectType(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static class Builder {

        Parent itemData;
        SingleTypeOptionSelectedListener singleTypeOptionSelectedListener;
        Context context;
        String previousSelectedId = "";

        public Builder setOptionList(Parent itemData) {
            this.itemData = itemData;
            return this;
        }

        public Builder setListener(SingleTypeOptionSelectedListener singleTypeOptionSelectedListener) {
            this.singleTypeOptionSelectedListener = singleTypeOptionSelectedListener;
            return this;
        }

        public Builder setPreviousSelectedId(String previousSelectedId) {
            this.previousSelectedId = previousSelectedId;
            return this;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public SingleSelectType buildOption() {
            return new SingleSelectType(this);
        }

    }


    public void addOptions(Builder builder) {
        if (builder.itemData == null) {
            return;
        }

        if (builder.itemData.getDataMap().getOptions() == null ||
                builder.itemData.getDataMap().getOptions().isEmpty()) {
            return;
        }

        this.setOrientation(VERTICAL);
        int listOfOptions = builder.itemData.getDataMap().getOptions().size();

        for (int pos = 0; pos < listOfOptions; pos++) {
            View view = LayoutInflater.from(builder.context).inflate(R.layout.item_single_coice, this, false);

            RadioButton radioButton = view.findViewById(R.id.radio_btn);
            radioButton.setTag(R.id.position, pos);
            radioButton.setOnClickListener(onClickListener);

            if (builder.previousSelectedId.substring(1,builder.previousSelectedId.length()-1).equals(builder.itemData.getDataMap().getOptions().get(pos))) {
                radioButton.setChecked(true);
            }
            /*if (builder.itemData.getStringMap().containsKey(builder.itemData.getId())){}*/

            TextView textView = view.findViewById(R.id.item_single_choice_text);
            textView.setText(builder.itemData.getDataMap().getOptions().get(pos));

            this.addView(view, pos);
        }

    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int pos = (int) v.getTag(R.id.position);

            if (lastCheckedPos != pos) {
                RadioButton previousSelectedRadioButton = SingleSelectType.this.getChildAt(lastCheckedPos).findViewById(R.id.radio_btn);
                previousSelectedRadioButton.setChecked(false);
                ((RadioButton) v).setChecked(true);
            } else {
                ((RadioButton) v).setChecked(true);
            }
            lastCheckedPos = pos;

            selectedItem.put(builder.itemData.getId(), builder.itemData.getDataMap().getOptions().get(pos));

            builder.itemData.getStringMap().clear();

            builder.itemData.getStringMap().put(builder.itemData.getId(), builder.itemData.getDataMap().getOptions().get(pos));

            builder.singleTypeOptionSelectedListener.onSingleSelected(selectedItem);

            invalidate();
        }
    };

}
