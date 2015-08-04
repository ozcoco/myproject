package me.wangolf.city;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;





import com.meigao.mgolf.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class PinyinAdapter extends BaseExpandableListAdapter {

	// 字符串
		private List<String> strList;

		private AssortPinyinList assort = new AssortPinyinList();

		private Context context;

		private LayoutInflater inflater;
		// 中文排序
		private LanguageComparator_CN cnSort = new LanguageComparator_CN();

		public PinyinAdapter(Context context, List<String> strList) {
			super();
			this.context = context;
			this.inflater = LayoutInflater.from(context);
			this.strList = strList;
			if (strList == null) {
				strList = new ArrayList<String>();
			}

			long time = System.currentTimeMillis();
			// 排序
			sort();
			
			// TODO 所用时间
			/*Toast.makeText(context,
					String.valueOf(System.currentTimeMillis() - time), 1).show();*/

		}

		private void sort() {
			// 分类
			for (String str : strList) {
				assort.getHashList().add(str);
			}
			assort.getHashList().sortKeyComparator(cnSort);
			for(int i=0,length=assort.getHashList().size();i<length;i++)
			{
				Collections.sort((assort.getHashList().getValueListIndex(i)),cnSort);
			}
			
		}

		public Object getChild(int group, int child) {
			// TODO Auto-generated method stub
			return assort.getHashList().getValueIndex(group, child);
		}

		public long getChildId(int group, int child) {
			// TODO Auto-generated method stub
			return child;
		}

		public View getChildView(int group, int child, boolean arg2,
				View contentView, ViewGroup arg4) {
			// TODO Auto-generated method stub
			if (contentView == null) {
				contentView = inflater.inflate(R.layout.adapter_chat, null);
			}
			TextView textView = (TextView) contentView.findViewById(R.id.name);
			textView.setText(assort.getHashList().getValueIndex(group, child));
			return contentView;
		}

		public int getChildrenCount(int group) {
			// TODO Auto-generated method stub
			return assort.getHashList().getValueListIndex(group).size();
		}

		public Object getGroup(int group) {
			// TODO Auto-generated method stub
			return assort.getHashList().getValueListIndex(group);
		}

		public int getGroupCount() {
			// TODO Auto-generated method stub
			return assort.getHashList().size();
		}

		public long getGroupId(int group) {
			// TODO Auto-generated method stub
			return group;
		}

		public View getGroupView(int group, boolean arg1, View contentView,
				ViewGroup arg3) {
			if (contentView == null) {
				contentView = inflater.inflate(R.layout.list_group_item, null);
				contentView.setClickable(true);
			}
			TextView textView = (TextView) contentView.findViewById(R.id.name);
			textView.setText(assort.getFirstChar(assort.getHashList()
					.getValueIndex(group, 0)));
			// 禁止伸展

			return contentView;
		}

		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return true;
		}

		public boolean isChildSelectable(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return true;
		}

		public AssortPinyinList getAssort() {
			return assort;
		}


}
