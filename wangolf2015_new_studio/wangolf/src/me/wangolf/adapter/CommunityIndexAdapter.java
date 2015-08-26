package me.wangolf.adapter;

/**
 * ============================================================
 * <p/>
 * 版权 ：美高传媒 版权所有 (c) 2015年3月4日
 * <p/>
 * 作者:copy
 * <p/>
 * 版本 ：1.0
 * <p/>
 * 创建日期 ： 2015年3月4日
 * <p/>
 * 描述 ：社区首页 置顶adapter
 * <p/>
 * <p/>
 * 修订历史 ：
 * <p/>
 * ============================================================
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import me.wangolf.ConstantValues;
import me.wangolf.bean.InfoEntity;
import me.wangolf.bean.community.CommunityPostsEntity;
import me.wangolf.community.CommunityDetailActivity;
import me.wangolf.community.CommunityImgActivit;
import me.wangolf.community.CommunityPostsListActivity;
import me.wangolf.community.CommunityPostsPraiseActivity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.newfragment.CommunityFra;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.usercenter.LoginActivity;
import me.wangolf.usercenter.UserFriendsInfoActivity;
import me.wangolf.usercenter.UserInfoNewActivity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.CommonUtil;
import me.wangolf.utils.FileUtils;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.ImageViewUtil;
import me.wangolf.utils.NoScrollGridView;
import me.wangolf.utils.ShowPickUtils;
import me.wangolf.utils.ToastUtils;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.meigao.mgolf.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommunityIndexAdapter extends BaseAdapter
{
	private Context									context;
	private ArrayList<CommunityPostsEntity>			posts_list;
	private CommunityDetailImgAdapter				img_adapter;
	private CommunityDetailIcoAdapter				ico_adapter;
	private String[]								imgs;
	private String[]								r_imgs;
	private CommunityFra							ac;
	private CommunityPostsListActivity				activity;
	private Map<String, CommunityDetailIcoAdapter>	icoMap		= new HashMap<String, CommunityDetailIcoAdapter>();
	private Map<String, CommunityPostsEntity>		postsMap	= new HashMap<String, CommunityPostsEntity>();
	private int										WIDTH;

	// private ArrayList<CommunityDetailIcoAdapter> ico_adapterList=new
	// ArrayList<CommunityDetailIcoAdapter>();

	public CommunityIndexAdapter(Context context)
	{
		super();
		this.context = context;

	}

	public Map<String, CommunityPostsEntity> getPostsMap()
	{
		return postsMap;
	}

	public void setPostsMap(Map<String, CommunityPostsEntity> postsMap)
	{
		this.postsMap = postsMap;
	}

	public Map<String, CommunityDetailIcoAdapter> getIcoMap()
	{
		return icoMap;
	}

	public void setIcoMap(Map<String, CommunityDetailIcoAdapter> icoMap)
	{
		this.icoMap = icoMap;
	}

	public CommunityDetailIcoAdapter getIco_adapter()
	{
		return ico_adapter;
	}

	public void setIco_adapter(CommunityDetailIcoAdapter ico_adapter)
	{
		this.ico_adapter = ico_adapter;
	}

	public CommunityIndexAdapter(CommunityFra context)
	{
		super();
		this.context = context.getActivity();
		this.ac = context;

	}

	public CommunityIndexAdapter(CommunityPostsListActivity context)
	{
		super();
		this.context = context;
		this.activity = context;
	}

	public ArrayList<CommunityPostsEntity> getPosts_list()
	{
		return posts_list;
	}

	public void setPosts_list(ArrayList<CommunityPostsEntity> posts_list)
	{
		this.posts_list = posts_list;
	}

	@Override
	public int getCount()
	{
		return posts_list == null ? 0 : posts_list.size();
	}

	@Override
	public Object getItem(int position)
	{

		return posts_list.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// 加载或复用item界面
		ViewHolder holder = null;

		if (convertView == null)
		{
			holder = new ViewHolder();
			
			convertView = View
					.inflate(context, R.layout.item_comm_list_new, null);
			
			holder.mTitle = (TextView) convertView.findViewById(R.id.tv_Title);
			
			holder.mNickname = (TextView) convertView
					.findViewById(R.id.tv_Nickname);
			
			holder.mIco = (CircleImageView) convertView
					.findViewById(R.id.IV_ico);
			
			holder.mTime = (TextView) convertView.findViewById(R.id.tv_Time);
			
			holder.mContent = (TextView) convertView
					.findViewById(R.id.tv_content);
			
			holder.mGvImages = (NoScrollGridView) convertView
					.findViewById(R.id.gv_images);
			
			holder.mGvIco = (NoScrollGridView) convertView
					.findViewById(R.id.gv_ico);
			
			holder.mll = (LinearLayout) convertView.findViewById(R.id.ll_list);
			
			holder.mPraise = (TextView) convertView
					.findViewById(R.id.tv_praise);
			
			holder.mAddress = (TextView) convertView
					.findViewById(R.id.tv_Address);
			
			holder.mReply_count = (TextView) convertView
					.findViewById(R.id.tv_reply_count);
			
			holder.mBtPraise = (RelativeLayout) convertView
					.findViewById(R.id.bt_praise);
			
			holder.mPraiseMore = (ImageView) convertView
					.findViewById(R.id.tv_praise_more);
			
			holder.mTagName = (TextView) convertView
					.findViewById(R.id.tv_posts_tag);
			
			holder.mLLIco = (LinearLayout) convertView
					.findViewById(R.id.ll_ico);
			
			holder.mPraiseNum = (TextView) convertView
					.findViewById(R.id.tv_praise_num);
			
			holder.mReply = (RelativeLayout) convertView
					.findViewById(R.id.bt_reply);
			
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		// 设数据
		CommunityPostsEntity bean = posts_list.get(position);
		
		if (postsMap.get(bean.getId() + "") == null)
		{
			postsMap.put(bean.getId() + "", bean);
		}
		// Log.i("wangolf",position+"positionposition");
		holder.mNickname.setText(bean.getName());
		
		holder.mTitle.setText(bean.getTitle());
		
		String path = bean.getAvatar();
		
		if (!CheckUtils.checkEmpty(path))
		{
			path = FileUtils.getPhotoPath(path);

		}
		
		ImageViewUtil.loadimg(path, holder.mIco, context);
		// Xutils.getBitmap(context, holder.mIco, path);
		holder.mIco.setOnClickListener(new OnClick(bean));
		
		holder.mTime.setText(bean.getUpdate_time());
		
		holder.mContent.setText(bean.getContent());
		
		holder.mAddress
				.setVisibility(CheckUtils.checkEmpty(bean.getAddress()) ? View.INVISIBLE : View.VISIBLE);
		
		holder.mAddress.setText(bean.getAddress());
		
		String[] getTags_name = bean.getTags_name().split(",");
		// String[] getTags_name={"返回定","子字符串","子字符串"};
		StringBuffer tagName = new StringBuffer();
		
		if (getTags_name.length > 0)
		{
			for (int i = 0; i < getTags_name.length; i++)
			{
				if (i == 2)
				{
					tagName.append("\n" + getTags_name[i] + "   ");
				}
				else
				{
					tagName.append(getTags_name[i] + "   ");
				}
			}
		}
		
		holder.mTagName.setText(tagName.toString());
		
		holder.mPraiseNum.setText(bean.getPraise_count() + "");
		
		holder.mReply_count.setText(bean.getReply_count() + "");
		
		if (!CheckUtils.checkEmpty(bean.getImg_list()))
		{
			imgs = bean.getImg_list().split(",");

			LinearLayout.LayoutParams lyouParams;
			if (imgs.length >= 3)
			{
				holder.mGvImages.setNumColumns(3);
				lyouParams = new LinearLayout.LayoutParams((ConstantValues.SCREENWIDTH - 40), ViewGroup.LayoutParams.WRAP_CONTENT);

			}
			else
			{
				holder.mGvImages.setNumColumns(imgs.length);
				lyouParams = new LinearLayout.LayoutParams((ConstantValues.SCREENWIDTH - 40) / 3 * imgs.length, ViewGroup.LayoutParams.WRAP_CONTENT);

			}
			img_adapter = new CommunityDetailImgAdapter(context, imgs);
			holder.mGvImages.setLayoutParams(lyouParams);
			holder.mGvImages.setAdapter(img_adapter);
			// 点看图片

			holder.mGvImages.setOnItemClickListener(new OnItemClick(bean));
			img_adapter.notifyDataSetChanged();
		}
		else
		{
			String[] img = {};
			img_adapter = new CommunityDetailImgAdapter(context, img);
			holder.mGvImages.setAdapter(img_adapter);
			img_adapter.notifyDataSetChanged();
		}
		// holder.mBtPraise.setBackground(bean.getIs_praise() == 1
		// ?context.getResources().getDrawable(R.drawable.icon_szan):context.getResources().getDrawable(R.drawable.icon_zan));

		holder.mPraise
				.setVisibility(bean.getPraise_info().size() <= 0 ? View.GONE : View.VISIBLE);
		if (bean.getPraise_info().size() >= 0)
		{
			holder.mLLIco
					.setVisibility(bean.getPraise_info().size() <= 0 ? View.GONE : View.VISIBLE);
			LinearLayout.LayoutParams IcoParams = new LinearLayout.LayoutParams((ConstantValues.SCREENWIDTH - 100) / 6 * (bean
					.getPraise_info().size() > 5 ? 5 : bean.getPraise_info()
					.size()), ViewGroup.LayoutParams.WRAP_CONTENT);
			holder.mGvIco.setLayoutParams(IcoParams);
			WIDTH = (ConstantValues.SCREENWIDTH - 100) / 6;
			holder.mGvIco
					.setNumColumns(bean.getPraise_info().size() > 5 ? 5 : bean
							.getPraise_info().size());

			ico_adapter = new CommunityDetailIcoAdapter(context, bean.getPraise_info());
			icoMap.put(bean.getId() + "", ico_adapter);

			holder.mGvIco.setAdapter(ico_adapter);

			ico_adapter.notifyDataSetChanged();

		}
		holder.mBtPraise
				.setOnClickListener(new OnClick(bean, position, holder.mGvIco, WIDTH, holder.mPraiseNum, holder.mPraiseMore, holder.mLLIco));
		if (bean.getPraise_info().size() >= 5)
		{
			holder.mPraiseMore.setVisibility(View.VISIBLE);

		}
		else
		{
			holder.mPraiseMore.setVisibility(View.GONE);
		}
		
		Drawable drawable1 = (bean.getIs_praise() == 1 ? context.getResources()
				.getDrawable(R.drawable.icon_zan_select) : context
				.getResources().getDrawable(R.drawable.icon_zan_default));

		drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1
				.getMinimumHeight());// 必须设置图片大小，否则不显示

		holder.mPraiseNum.setCompoundDrawables(drawable1, null, null, null);
		// Log.i("wangolf", bean.getIs_praise() + "**********");
		holder.mPraiseMore.setOnClickListener(new OnClick(bean));
		SpannableString msp = new SpannableString(bean.getTitle());
		Drawable drawable = context.getResources().getDrawable(R.drawable.icon);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable
				.getIntrinsicHeight());
		// msp.setSpan(new ImageSpan(drawable), 53, 57,
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		holder.mReply.setOnClickListener(new OnClick(bean));
		return convertView;
	}

	class ViewHolder
	{
		private TextView			mTitle;
		private CircleImageView		mIco;
		private TextView			mNickname;
		private TextView			mTime;
		private TextView			mContent;
		private NoScrollGridView	mGvImages;
		private NoScrollGridView	mGvIco;
		private LinearLayout		mll;
		private TextView			mPraise;
		private TextView			mAddress;
		private TextView			mReply_count;
		private RelativeLayout		mBtPraise;
		private ImageView			mPraiseMore;
		private TextView			mTagName;
		private LinearLayout		mLLIco;
		private TextView			mPraiseNum;
		private RelativeLayout		mReply;

	}

	// 内部类 OnItemClick事件==================================
	class OnItemClick implements AdapterView.OnItemClickListener
	{

		private CommunityPostsEntity	bean;

		public OnItemClick(CommunityPostsEntity bean)
		{
			super();
			this.bean = bean;
		}

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
		{
			r_imgs = bean.getImg_list().split(",");
			if (r_imgs.length > 0)
			{
				ArrayList<String> urlList = new ArrayList<String>(Arrays.asList(r_imgs));
				Intent intent = new Intent(context, CommunityImgActivit.class);
				intent.putStringArrayListExtra("url", urlList);
				intent.putExtra("setCurrentItem", arg2 + "");
				context.startActivity(intent);
			}

		}
	}

	// 内部类 OnClick事件==============================
	class OnClick implements View.OnClickListener
	{

		private CommunityPostsEntity	bean;
		private int						position;
		private NoScrollGridView		mGvIco;
		private int						WIDTH;
		private TextView				mPraiseNum;
		private LinearLayout			ll_ico;
		private ImageView				im_praise_more;

		public OnClick(CommunityPostsEntity bean)
		{
			super();
			this.bean = bean;
		}

		public OnClick(CommunityPostsEntity bean, int position, NoScrollGridView mGvIco, int width, TextView mPraiseNum, ImageView im_praise_more, LinearLayout ll_ico)
		{
			super();
			this.bean = bean;
			this.mGvIco = mGvIco;
			this.position = position;
			this.WIDTH = width;
			this.mPraiseNum = mPraiseNum;
			this.ll_ico = ll_ico;
			this.im_praise_more = im_praise_more;
		}

		@Override
		public void onClick(View v)
		{
			if (!ConstantValues.ISLOGIN)
			{
				// 去登录
				Intent toLogin = new Intent(context, LoginActivity.class);
				toLogin.putExtra("flag", "orderPrac");
				context.startActivity(toLogin);

				return;
			}
			
			if (!ConstantValues.ISCOMPLETEINFO)
			{
				ShowPickUtils.ShowDialogComm(context, "请完善个人资料");
				// ToastUtils.showInfo(context, "请先完成资料设置");
				// Intent editinfo = new Intent(context,
				// UserInfoEditInfoActivity.class);
				// context.startActivity(editinfo);
				return;
			}
			
			switch (v.getId())
			{
				case R.id.IV_ico:
					// 查看楼主y
					if (!CheckUtils.checkEmpty(ConstantValues.UID) && Integer
							.parseInt(ConstantValues.UID) != bean.getUser_id())
					{
						Intent friends = new Intent(context, UserFriendsInfoActivity.class);
						friends.putExtra("user_id", bean.getUser_id());
						friends.putExtra("friend_name", bean.getName());
						friends.putExtra("flag", 0);
						context.startActivity(friends);
					}
					else
					{
						Intent my_info = new Intent(context, UserInfoNewActivity.class);
						context.startActivity(my_info);
					}
					break;
				case R.id.bt_praise:
					// Log.i("wangolf", position %10+ "position");
					toPraise(bean, position, bean.getId(), mGvIco, WIDTH, mPraiseNum, im_praise_more, ll_ico);
					break;
				case R.id.tv_praise_more:
					Intent praise = new Intent(context, CommunityPostsPraiseActivity.class);
					praise.putExtra("posts_id", bean.getId());
					context.startActivity(praise);
					break;
				case R.id.bt_reply:
					if (CommonUtil.isNetworkAvailable(context) == 0)
					{
						ToastUtils.showInfo(context, ConstantValues.NONETWORK);
					}
					else
					{
						Intent intent = new Intent(context, CommunityDetailActivity.class);
						intent.putExtra("posts_id", bean.getId() + "");
						intent.putExtra("flag", "reply");
						context.startActivity(intent);
					}
					break;
				default:
					break;
			}
		}
	}

	private void toPraise(final CommunityPostsEntity beans, final int position, final int id, final NoScrollGridView mGvIco, final int width, final TextView mPraiseNum, final ImageView im_praise_more, final LinearLayout ll_ico)
	{
		String type = "0";
		final int size = beans.getPraise_count();
		final int finalType = 0;
		// type=bean.getIs_praise()==1?"1":"0";
		for (int i = 0; i < beans.getPraise_info().size(); i++)
		{
			if (Integer.parseInt(ConstantValues.UID) == beans.getPraise_info()
					.get(i).getPraise_user_id())
			{
				// posts_list.get(position).getPraise_info().remove(i);
				// icoMap.get(bean.getId()+"").notifyDataSetChanged();
				type = "1";

			}
		}
		// if("0".equals(type)){
		// PraiseInfoEntity praiseInfoEntity = new PraiseInfoEntity();
		// praiseInfoEntity.setPraise_avatar("http://img0.imgtn.bdimg.com/it/u=3623274618,1194033688&fm=21&gp=0.jpg");
		// praiseInfoEntity.setPraise_user_id(Integer.parseInt(ConstantValues.UID));
		// posts_list.get(position).getPraise_info().add(praiseInfoEntity);
		// icoMap.get(bean.getId()+"").getList().add(praiseInfoEntity);
		// icoMap.get(bean.getId()+"").setList(icoMap.get(bean.getId()+"").getList());
		// icoMap.get(bean.getId()+"").notifyDataSetChanged();
		// }

		try
		{

			ServiceFactory
					.getCommunityEngineInstatice()
					.toPraise(ConstantValues.UID, beans.getId(), type, new IOAuthCallBack()
					{
						@Override
						public void getIOAuthCallBack(String result)
						{
							if (result.equals(ConstantValues.FAILURE))
							{
								ToastUtils
										.showInfo(context, ConstantValues.NONETWORK);
							}
							else
							{
								InfoEntity bean = GsonTools
										.jsonToBean(result, InfoEntity.class);
								if ("1".equals(bean.getStatus()))
								{
									ToastUtils
											.showInfo(context, bean.getInfo());
									if (ac != null)
									{
										// Log.i("wangolf",
										// beans.getPraise_info().size() +
										// (ConstantValues.SCREENWIDTH - 100) /
										// 6 * (beans.getPraise_info().size() >
										// 5 ? 5 : beans.getPraise_info().size()
										// + 1) + "******" +
										// (ConstantValues.SCREENWIDTH - 100) /
										// 6 * (beans.getPraise_info().size() >
										// 5 ? 5 :
										// beans.getPraise_info().size()));

										ac.setRefresh(position, size, id, mGvIco, finalType, width, mPraiseNum, im_praise_more, ll_ico);

									}
									else if (activity != null)
										activity.setRefresh(position, size, id, mGvIco, finalType, width, mPraiseNum, im_praise_more, ll_ico);

									// icoMap.get(id+"").notifyDataSetChanged();
								}
							}
						}
					});
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
