package me.wangolf.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.meigao.mgolf.R;

import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;

import me.wangolf.ConstantValues;
import me.wangolf.bean.community.CommunityPostsEntity;
import me.wangolf.bean.community.PraiseInfoEntity;
import me.wangolf.usercenter.LoginActivity;
import me.wangolf.usercenter.UserFriendsInfoActivity;
import me.wangolf.usercenter.UserInfoNewActivity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.FileUtils;
import me.wangolf.utils.ImageViewUtil;
import me.wangolf.utils.ShowPickUtils;
import me.wangolf.utils.Xutils;

public class CommunityDetailIcoAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<PraiseInfoEntity> list;

	public CommunityDetailIcoAdapter(Context context) {
		super();
		this.context = context;
	}

	public CommunityDetailIcoAdapter(Context context, ArrayList<PraiseInfoEntity> list) {
		super();
		this.context = context;
		this.list = list;
	}

	public ArrayList<PraiseInfoEntity> getList() {
		return list;
	}

	public void setList(ArrayList<PraiseInfoEntity> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list==null?0:list.size()>=5?5:list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 加载或复用item界面
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.item_comm_detail_ico, null);
			holder.mImg = (ImageView) convertView.findViewById(R.id.c_d_img);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		PraiseInfoEntity bean = list.get(position);
		String path = bean.getPraise_avatar();
        if (!CheckUtils.checkEmpty(path)) {
            //path = path.substring(0, path.lastIndexOf(".")) + "_180_180" + path.substring(path.lastIndexOf("."));
			path = FileUtils.getPhotoPath(path);

        }
		ImageViewUtil.loadimg(path, holder.mImg, context);
		holder.mImg.setOnClickListener(new OnClick(bean));
		return convertView;
	}

	class ViewHolder {
		private ImageView mImg;
	}

	// 内部类 OnClick事件==============================
	class OnClick implements View.OnClickListener {

		private PraiseInfoEntity bean;
		private int position;

		public OnClick(PraiseInfoEntity bean) {
			super();
			this.bean = bean;
		}

		public OnClick(PraiseInfoEntity bean, int position) {
			super();
			this.bean = bean;
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			if (!ConstantValues.ISLOGIN) {
				// 去登录
				Intent toLogin = new Intent(context, LoginActivity.class);
				toLogin.putExtra("flag", "orderPrac");
				context.startActivity(toLogin);
				return;
			}
			if (!ConstantValues.ISCOMPLETEINFO) {
				// ToastUtils.showInfo(this, "请先完成资料设置");
				ShowPickUtils.ShowDialogComm(context, "请完善个人资料");
				return;

			}
			switch (v.getId()) {
				case R.id.c_d_img:
					// 查看楼主y
					if (!CheckUtils.checkEmpty(ConstantValues.UID) && Integer.parseInt(ConstantValues.UID) != bean.getPraise_user_id()) {
						Intent friends = new Intent(context, UserFriendsInfoActivity.class);
						friends.putExtra("user_id", bean.getPraise_user_id());
						friends.putExtra("friend_name", "");
						friends.putExtra("flag", 0);
						context.startActivity(friends);
					} else {
						Intent my_info = new Intent(context, UserInfoNewActivity.class);
						context.startActivity(my_info);
					}
					break;

				default:
					break;
			}
		}
	}
}
