package me.wangolf.adapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.meigao.mgolf.R;
import me.wangolf.bean.event.EventEntity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.DateFormatUtils;
import me.wangolf.utils.Xutils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FgEventAdapter extends BaseAdapter {

	private boolean isFling;
	private ArrayList<EventEntity> list;
	private Context context;

	public boolean isFling() {
		return isFling;
	}

	public void setFling(boolean isFling) {
		this.isFling = isFling;
	}

	public ArrayList<EventEntity> getList() {
		return list;
	}

	public void setList(ArrayList<EventEntity> list) {
		this.list = list;
	}

	public FgEventAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public EventEntity getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return getItem(arg0).getId();
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// 加载或复用item界面
		ViewHolder holder = null;
		if (convertView == null) {
			// 无可复用item界面，加载新界面
			convertView = View.inflate(context, R.layout.item_fg_event, null);
			holder = new ViewHolder();
			holder.tvtitle = (TextView) convertView.findViewById(R.id.item_title);
			holder.tvbtime = (TextView) convertView.findViewById(R.id.item_btime);
           // holder.mBuyEvent = (Button) convertView.findViewById(R.id.bt_buy_event);
            holder.mOrprice = (TextView) convertView.findViewById(R.id.origin_price);
            holder.mOrprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			// holder.tvetime = (TextView)
			// convertView.findViewById(R.id.item_etime);
			holder.tvprice = (TextView) convertView.findViewById(R.id.item_price);
			holder.ivlogo = (ImageView) convertView.findViewById(R.id.item_logo);
			holder.ivsubmit = (ImageView) convertView.findViewById(R.id.iv_submit);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (isFling == false) {
			// 获取指定位置的数据
			EventEntity bean = (EventEntity) getItem(position);
			// 绑定数据到item界面
			String title = bean.getTitle();
			if (!CheckUtils.checkEmpty(title) && title.trim().length() >= 34) {
				title = title.trim().substring(0, 34) + "..";
			}
//            title = stringFilter( ToDBC(title)).trim();
//            int i = title.length();
//            if (i > 20) {
//                title = title.substring(0, 20) + "\n" + title.substring(20);
//            }
            holder.tvtitle.setText(title);

			String btime = bean.getBtime();
			String etime = bean.getEtime();
			try {
				Date bdate = DateFormatUtils.stringToDate(btime);
				btime = DateFormatUtils.format(bdate);
				// Date edate=DateFormatUtils.stringToDate(etime);
				// etime=DateFormatUtils.format(edate);
			} catch (Exception e) {
				e.printStackTrace();
			}
			holder.tvbtime.setText(btime);
			// holder.tvetime.setText(etime);
			holder.tvprice.setText("￥" + bean.getPrice() + "元");
            holder.mOrprice.setText("￥" + bean.getOrigin_price());

			if (bean.getSubmit() == 0) {
				holder.ivsubmit.setImageResource(R.drawable.submit0);
                //holder.mBuyEvent.setBackground(context.getResources().getDrawable(R.drawable.bt_event_gray_yuan));
			} else if (bean.getSubmit() == 1) {
				holder.ivsubmit.setImageResource(R.drawable.submit1);
			}
			String path = bean.getLogo();
			if(!CheckUtils.checkEmpty(path)){
			path = path.substring(0, path.lastIndexOf(".")) + "_180_180" + path.substring(path.lastIndexOf("."));
			Xutils.getBitmap(context, holder.ivlogo, path);
			}
		}
		// 返回item界面
		return convertView;
	}

	class ViewHolder {
		private ImageView ivlogo;
		private ImageView ivsubmit;
		private TextView tvtitle;
		private TextView tvbtime;
		// private TextView tvetime;
        private TextView mOrprice;
		private TextView tvprice;
        private Button mBuyEvent;
	}
    public String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (isChinese(c[i])) {
                if (c[i] == 12288) {
                    c[i] = (char) 32;
                    continue;
                }
                if (c[i] > 65280 && c[i] < 65375)
                    c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }

    private boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }
    /**
     * 去除特殊字符或将所有中文标号替换为英文标号
     *
     * @param str
     * @return
     */
    public static String stringFilter(String str) {
        str = str.replaceAll("【", "[").replaceAll("】", "]")
                .replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
        String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}
