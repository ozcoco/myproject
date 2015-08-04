package me.wangolf.text.view;

import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.adapter.TextAdapter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class ViewRight extends RelativeLayout implements ViewBaseAction {

    private GridView mListView;
    private final String[] items = ConstantValues.shop_title_name;
    private String[] itemsVaule;// 隐藏id
    private OnSelectListener mOnSelectListener;
    private TextAdapter adapter;
    private String mDistance;
    private String showText = "item1";
    private Context mContext;

    public String getShowText() {
        return showText;
    }

    public ViewRight(Context context) {
        super(context);
        ArrayList<String> value = new ArrayList<String>();
        for (int i = 0; i < ConstantValues.shop_title_size; i++) {
            value.add(i + 1 + "");
        }
        itemsVaule = (String[]) value.toArray(new String[1]);

        init(context);
    }

    public ViewRight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public ViewRight(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_distance, this, true);
        setBackgroundDrawable(getResources().getDrawable(R.drawable.choosearea_bg_right));

        mListView = (GridView) findViewById(R.id.listView);
        adapter = new TextAdapter(context, items, R.drawable.choose_item_right, R.drawable.choose_eara_item_selector);
        adapter.setTextSize(13);
        if (mDistance != null) {
            for (int i = 0; i < itemsVaule.length; i++) {
                if (itemsVaule[i].equals(mDistance)) {
                    adapter.setSelectedPositionNoNotify(i);
                    showText = items[i];
                    break;
                }
            }
        }
        mListView.setAdapter(adapter);
        adapter.setOnItemClickListener(new TextAdapter.OnItemClickListener() {

            private int id;

            @Override
            public void onItemClick(View view, int position) {

                if (mOnSelectListener != null) {
                    showText = items[position];
                    id = position;
                    mOnSelectListener.getValue(itemsVaule[position], items[position], id);
                }
            }
        });
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }

    public interface OnSelectListener {
        public void getValue(String distance, String showText);

        void getValue(String distance, String showText, int id);
    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

}
