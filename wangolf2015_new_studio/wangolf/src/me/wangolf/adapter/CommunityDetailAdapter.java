package me.wangolf.adapter;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年3月4日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年3月4日
 * 
 * 描述 ：社区详情 adapter
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.util.ArrayList;
import java.util.Arrays;

import com.meigao.mgolf.R;

import de.hdodenhof.circleimageview.CircleImageView;
import me.wangolf.ConstantValues;
import me.wangolf.bean.community.CommunityDetailEntity;
import me.wangolf.community.CommunityDetailActivity;
import me.wangolf.community.CommunityImgActivit;
import me.wangolf.community.CommunityPostsPraiseActivity;
import me.wangolf.usercenter.LoginActivity;
import me.wangolf.usercenter.UserFriendsInfoActivity;
import me.wangolf.usercenter.UserInfoNewActivity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.FileUtils;
import me.wangolf.utils.ImageViewUtil;
import me.wangolf.utils.NoScrollGridView;
import me.wangolf.utils.ShowPickUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CommunityDetailAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<CommunityDetailEntity> list;
	private CommunityDetailOneImgAdapter adapter;
	private CommunityDetailImgAdapter r_adapter;
	private String[] imgs;
	private String[] r_imgs;
	private CommunityDetailEntity data;
	private CommunityDetailEntity reply;
	private CommunityDetailActivity activity;
	private CommunityDetailIcoAdapter ico_adapter;
	public CommunityDetailAdapter(Context context) {
		super();
		this.context = context;
		this.activity = (CommunityDetailActivity) context;
	}

	public ArrayList<CommunityDetailEntity> getList() {
		return list;
	}

	public void setList(ArrayList<CommunityDetailEntity> list) {
		this.list = list;
	}

	@Override
	public int getCount() {

		return list == null ? 0 : list.get(0).getReply().size() + 1;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// 加载或复用item界面
		ViewHolder holder = null;
		ViewHolder holder1 = null;
		if (holder == null) {
			holder = new ViewHolder();
			holder1 = new ViewHolder();
		}
		if (list != null) {
			if (position == 0) {
				data = list.get(position);
				convertView = View.inflate(context, R.layout.item_comm_detail, null);
				holder.c_d_title = (TextView) convertView.findViewById(R.id.c_d_title);
				holder.c_d_content = (TextView) convertView.findViewById(R.id.c_d_content);
				holder.c_d_time = (TextView) convertView.findViewById(R.id.c_d_time);
				holder.c_d_name = (TextView) convertView.findViewById(R.id.c_d_name);
				holder.c_d_location = (TextView) convertView.findViewById(R.id.c_d_location);
				holder.c_d_gv = (NoScrollGridView) convertView.findViewById(R.id.c_d_gv);
				holder.mAvatar = (CircleImageView) convertView.findViewById(R.id.c_d_avatar);
				convertView.setBackgroundColor(context.getResources().getColor(R.color.white));
				holder.mPraiseMore = (ImageView) convertView.findViewById(R.id.tv_praise_more);
				holder.mGvIco = (NoScrollGridView) convertView.findViewById(R.id.gv_ico);
				holder.mPraise = (TextView) convertView.findViewById(R.id.tv_praise);
				holder.mLine= convertView.findViewById(R.id.line_praise);
				holder.mTagNmae= (TextView) convertView.findViewById(R.id.tv_tagName);
				holder.mLLico = (LinearLayout) convertView.findViewById(R.id.ll_ico);

				holder.c_d_title.setText(data.getTitle());
				holder.c_d_name.setText(data.getName());
				holder.c_d_content.setText(data.getContent());
				holder.c_d_time.setText(data.getUpdate_time());
				holder.c_d_location.setText(data.getAddress());
				String[] getTags_name =data.getTags_name().split(",");
				//String[] getTags_name={"返回定","子字符串","子字符串"};
				StringBuffer tagName=new StringBuffer();
				if(getTags_name.length>0){
					for(int i=0;i<getTags_name.length;i++){
						if(i==2){
							tagName.append("\n"+getTags_name[i]+"  ");
						}
						else {
							tagName.append(getTags_name[i]+"  ");
						}
					}
				}
				holder.mTagNmae.setText(tagName);
				String path = data.getAvatar();
				if (!CheckUtils.checkEmpty(path)) {
					//path = path.substring(0, path.lastIndexOf(".")) + "_180_180" + path.substring(path.lastIndexOf("."));
					path = FileUtils.getPhotoPath(path);
				}
				ImageViewUtil.loadimg(path, holder.mAvatar, context);
				if (!CheckUtils.checkEmpty(data.getImg_list())) {

					imgs = data.getImg_list().split(",");
					if (adapter == null)
						adapter = new CommunityDetailOneImgAdapter(context, imgs);
					holder.c_d_gv.setAdapter(adapter);
					// 点看图片
					holder.c_d_gv.setOnItemClickListener(new OnItemClick(data));
					adapter.notifyDataSetChanged();
				}
				// 查看用户信息
				holder.mAvatar.setOnClickListener(new OnClick(data));
				if(data.getPraise_info()!=null) {
					holder.mPraise.setVisibility(data.getPraise_info().size() <= 0 ? View.GONE : View.VISIBLE);
					holder.mLLico.setVisibility(data.getPraise_info().size() <= 0 ? View.GONE : View.VISIBLE);
					holder.mLine.setVisibility(View.GONE);
					if (data.getPraise_info().size() > 0) {
						holder.mLine.setVisibility(View.VISIBLE);
						LinearLayout.LayoutParams IcoParams = new LinearLayout.LayoutParams((ConstantValues.SCREENWIDTH - 100) /6 * (data.getPraise_info().size()>5?5:data.getPraise_info().size()), ViewGroup.LayoutParams.WRAP_CONTENT);
						holder.mGvIco.setLayoutParams(IcoParams);
						holder.mGvIco.setNumColumns(data.getPraise_info().size()>5?5:data.getPraise_info().size());

						ico_adapter = new CommunityDetailIcoAdapter(context, data.getPraise_info());
						holder.mGvIco.setAdapter(ico_adapter);

						ico_adapter.notifyDataSetChanged();

					}
					holder.mPraiseMore.setOnClickListener(new OnClick(data));
					if(data.getPraise_info().size() >= 5) {
						holder.mPraiseMore.setVisibility(View.VISIBLE);

					} else {
						holder.mPraiseMore.setVisibility(View.GONE);
					}
					//holder.mPraiseMore.setOnClickListener(new OnClick(data));
				}
			} else {
				reply = list.get(0).getReply().get(position - 1);
				convertView = View.inflate(context, R.layout.item_comm_detail_level, null);
				convertView.setBackgroundColor(context.getResources().getColor(R.color.all_bg_color));
				holder1.c_d_level = (TextView) convertView.findViewById(R.id.c_d_level);
				holder.c_d_level0 = (TextView) convertView.findViewById(R.id.c_d_level0);
				holder1.c_d_time = (TextView) convertView.findViewById(R.id.c_d_time);
				holder1.c_d_gv = (NoScrollGridView) convertView.findViewById(R.id.c_d_gv);
				holder1.c_d_level_li = (LinearLayout) convertView.findViewById(R.id.c_d_level_li);
				holder1.c_d_name = (TextView) convertView.findViewById(R.id.c_d_name);
				holder1.c_d_content = (TextView) convertView.findViewById(R.id.c_d_content);
				holder1.more_receive = (TextView) convertView.findViewById(R.id.more_receive);
				holder1.mAvatar = (CircleImageView) convertView.findViewById(R.id.c_d_avatar);
				holder1.mIco = (ImageView) convertView.findViewById(R.id.c_d_level_ico);
				holder1.c_d_line = convertView.findViewById(R.id.c_d_line);
				holder1.c_d_level.setVisibility(View.VISIBLE);
				holder1.c_d_level.setText("第" + position + "楼");
				holder1.c_d_name.setText(reply.getName());
				holder1.c_d_time.setText(reply.getAddtime());
				holder1.c_d_content.setText(reply.getContent());
				holder1.mIco.setVisibility((data.getUser_id() == reply.getUser_id()) ? View.VISIBLE : View.GONE);
				String path = reply.getAvatar();
				if (!CheckUtils.checkEmpty(path)) {
					path = FileUtils.getPhotoPath(path);
					//path = path.substring(0, path.lastIndexOf(".")) + "_180_180" + path.substring(path.lastIndexOf("."));
				}
				ImageViewUtil.loadimg(path, holder1.mAvatar, context);
				CommunityDetailEntity reply1 = list.get(0).getReply().get(position - 1);
				if (!CheckUtils.checkEmpty(reply1.getImg_list())) {
					r_imgs = reply1.getImg_list().split(",");
					r_adapter = new CommunityDetailImgAdapter(context, r_imgs);
					holder1.c_d_gv.setAdapter(r_adapter);
					r_adapter.notifyDataSetChanged();
					holder1.c_d_gv.setVisibility(View.VISIBLE);
					// 点看图片
					holder1.c_d_gv.setOnItemClickListener(new OnItemClick(reply1));
				}
				// 评论w
				holder.c_d_level0.setOnClickListener(new OnClick(reply1, position));


				// 查看用户信息
				holder1.mAvatar.setOnClickListener(new OnClick(reply1));
				convertView.setTag(holder);
				if (reply.getLevel_two_reply().size() < 4)
					holder1.more_receive.setVisibility(View.GONE);
				if (reply.getLevel_two_reply().size() > 0) {
					holder1.c_d_level_li.addView(add(context, reply.getLevel_two_reply().size(), 3, reply.getLevel_two_reply(), reply.getId(),
							position));
					holder1.c_d_line.setVisibility(View.VISIBLE);

				}
			}
		}

		return convertView;
	}

	/**
	 * 递归加载楼层的方法
	 * 
	 * @param
	 * @param
	 *            ，同时也是取用户评论信息和背景色的下标，引参数的大小必须是从集合中获得的用户名数组或从集合中获得的评论内容数据的大小减一
	 * @param pad
	 *            楼层的间距
	 * @param
	 *
	 * @param
	 *
	 * @param
	 *
	 * @return 返回一个楼层的LinearLayout布局对象
	 */
	private LinearLayout add(Context context, int i, int pad, ArrayList<CommunityDetailEntity> level_two_reply, int reply_id, final int position) {
		// 加载一个布局
		CommunityDetailEntity level_two_reply_data = level_two_reply.get(i - 1);
		TextView layout1 = (TextView) LayoutInflater.from(context).inflate(R.layout.item_comm_detail_level_tx, null);
		// 评论某个人
		layout1.setOnClickListener(new OnClick(level_two_reply_data, reply_id));
		// 获得显示用户名、楼层数、用户评论内容的TextView
		TextView c_d_level_content = (TextView) layout1.findViewById(R.id.c_d_level_content);
		// 设置显示用户名、楼层数、用户评论内容TextView的内容
		String content = "回复" + level_two_reply_data.getReceive_name() + ":  " + level_two_reply_data.getContent() + "   ";

		c_d_level_content.setText(Html.fromHtml("<font color=\"#8BACF8\">" + level_two_reply_data.getName() + "</font>" + content + "<font color=\"#969696\"><small>" + level_two_reply_data.getAddtime() + "</small></font>" ));
		// 动态生成一个LinearLayout来装载获得的布局
		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		// layout.setPadding(pad, pad, pad, pad);
		// 当i的值为零时，递归结束
		if (i != 1) {
			layout.addView(add(context, --i, pad, level_two_reply, reply_id, position));
		}
		layout.addView(layout1);

		return layout;
	}

	class ViewHolder {
		// private ImageView hot_ico;
		private TextView c_d_title;// 标题
		private TextView c_d_content;// 帖子内容
		private TextView c_d_time;// 时间
		private TextView c_d_level0;
		private TextView c_d_level;
		private TextView c_d_location;
		private LinearLayout c_d_level_li;
		private NoScrollGridView c_d_gv;
		private CircleImageView mAvatar;
		private TextView c_d_name;// 回复人名
		private TextView more_receive;//
		private View c_d_line;
		private ImageView mIco;
		private NoScrollGridView mGvIco;
		private ImageView mPraiseMore;
		private TextView mPraise;
		private View mLine;
		private TextView mTagNmae;
		private LinearLayout mLLico;
	}

	// 内部类 OnClick事件==============================
	class OnClick implements OnClickListener {

		private CommunityDetailEntity bean;
		private int position;

		public OnClick(CommunityDetailEntity bean) {
			super();
			this.bean = bean;
		}

		public OnClick(CommunityDetailEntity bean, int position) {
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
			case R.id.c_d_avatar:
				// 查看楼主y
				if (!CheckUtils.checkEmpty(ConstantValues.UID) && Integer.parseInt(ConstantValues.UID) != bean.getUser_id()) {
					Intent friends = new Intent(context, UserFriendsInfoActivity.class);
					friends.putExtra("user_id", bean.getUser_id());
					friends.putExtra("friend_name", bean.getName());
					friends.putExtra("flag", 0);
					context.startActivity(friends);
				} else {
					Intent my_info = new Intent(context, UserInfoNewActivity.class);
					context.startActivity(my_info);
				}
				break;
			case R.id.c_d_level0:
				// 评论
				activity.toCReply(bean.getUser_id(), bean.getId(), "论评:" + "第" + position + "楼", position);
				break;
			case R.id.c_d_level_content:
				// 评论某人
				activity.toCReply(bean.getUser_id(), position, "回复" + bean.getName(), position);
				break;
				case R.id.tv_praise_more:
					Intent praise = new Intent(context, CommunityPostsPraiseActivity.class);
					praise.putExtra("posts_id", bean.getId());
					context.startActivity(praise);
					break;
			default:
				break;
			}
		}
	}

	// 内部类 OnItemClick事件==================================
	class OnItemClick implements OnItemClickListener {

		private CommunityDetailEntity bean;

		public OnItemClick(CommunityDetailEntity bean) {
			super();
			this.bean = bean;
		}

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			r_imgs = bean.getImg_list().split(",");
			if (r_imgs.length > 0) {
				ArrayList<String> urlList = new ArrayList<String>(Arrays.asList(r_imgs));
				Intent intent = new Intent(context, CommunityImgActivit.class);
				intent.putStringArrayListExtra("url", urlList);
				intent.putExtra("setCurrentItem", arg2 + "");
				context.startActivity(intent);
			}

		}
	}
}
