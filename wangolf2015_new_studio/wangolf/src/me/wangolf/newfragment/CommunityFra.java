package me.wangolf.newfragment;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import java.util.ArrayList;
import java.util.Map;

import me.wangolf.ConstantValues;
import me.wangolf.adapter.CommunityDetailIcoAdapter;
import me.wangolf.adapter.CommunityIndexAdapter;
import me.wangolf.adapter.CommunityIndexHotAdapter;
import me.wangolf.adapter.CommunityIndexTagAdapter;
import me.wangolf.base.BaseFragment;
import me.wangolf.bean.community.CommunityHotPostsEntity;
import me.wangolf.bean.community.CommunityPostsEntity;
import me.wangolf.bean.community.CommunityTagEntity;
import me.wangolf.bean.community.PraiseInfoEntity;
import me.wangolf.community.AlbumEditActivity;
import me.wangolf.community.CommunityDetailActivity;
import me.wangolf.community.CommunityPostsListActivity;
import me.wangolf.community.CommunityPostsTagActivity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.usercenter.LoginActivity;
import me.wangolf.usercenter.UserInfoEditInfoActivity;
import me.wangolf.utils.CommonUtil;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.MyListView;
import me.wangolf.utils.NoScrollGridView;
import me.wangolf.utils.ShowPickUtils;
import me.wangolf.utils.ToastUtils;
import me.wangolf.utils.viewUtils.PullToRefreshBase;
import me.wangolf.utils.viewUtils.PullToRefreshListView;

/**
 * Created by Administrator on 2015/4/2.
 */
public class CommunityFra extends BaseFragment implements
		AdapterView.OnItemClickListener
{
	@ViewInject(R.id.common_title)
	private TextView					common_title;
	@ViewInject(R.id.common_img)
	private ImageView					common_img;
	@ViewInject(R.id.comm_hot_list)
	private MyListView					comm_hot_list;
	@ViewInject(R.id.pull_refresh_list)
	private PullToRefreshListView		mPullList;
	@ViewInject(R.id.community_tag)
	private NoScrollGridView			mTag;
	private CommunityIndexHotAdapter	adapter_hot;
	private CommunityIndexAdapter		adapter_list;
	private String						user_id;		// 用户ID
	private String						praise_user_id;
	private int							page	= 1;	// 页数
	private int							number	= 10;	// 分页外数
	private boolean						isRefresh;		// 是否下拉刷新
	private Dialog						dialog;		// 加载页
	private CommunityIndexTagAdapter	tagAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.ac_community, null);
		ViewUtils.inject(this, view);
		View head = inflater.inflate(R.layout.comm_hot_head, null);
		ViewUtils.inject(this, head);
		View head_tab = inflater.inflate(R.layout.comm_tag_head, null);
		ViewUtils.inject(this, head_tab);
		if (tagAdapter == null)
		{
			tagAdapter = new CommunityIndexTagAdapter(getActivity());
		}
		else
		{
			tagAdapter.notifyDataSetChanged();
		}
		if (adapter_hot == null)
		{
			adapter_hot = new CommunityIndexHotAdapter(getActivity());
		}
		if (adapter_list == null)
		{
			adapter_list = new CommunityIndexAdapter(this);
		}
		adapter_list.notifyDataSetChanged();
		adapter_hot.notifyDataSetChanged();
		mTag.setAdapter(tagAdapter);
		comm_hot_list.setAdapter(adapter_hot);
		mPullList.getRefreshableView().addHeaderView(mTag);
		mPullList.getRefreshableView().addHeaderView(comm_hot_list);
		mPullList.getRefreshableView().setAdapter(adapter_list);

		initData();

		return view;
	}

	@Override
	public void initData()
	{

		initView();// 视图数据
		setHotOnclick();// hot点击事件
		getTagsList();// 标签列表
		getHotData();// hot数据
		getListData();// 列表数据;
		initRefreshableView();// 下拉刷新

		dialog = DialogUtil.getDialog(getActivity());
		dialog.show();
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public void initView()
	{
		common_title.setText("社区");
		common_img.setVisibility(View.VISIBLE);
		common_img.setBackground(getActivity().getResources()
				.getDrawable(R.drawable.r2_c6));
		common_img.setOnClickListener(this);
		mTag.setOnItemClickListener(this);
	}

	// 获取社区标签
	private void getTagsList()
	{
		try
		{
			ServiceFactory.getCommunityEngineInstatice()
					.getTagsList(new IOAuthCallBack()
					{
						@Override
						public void getIOAuthCallBack(String result)
						{
							if (result.equals(ConstantValues.FAILURE))
							{
								ToastUtils.showInfo(getActivity(), ConstantValues.NONETWORK);
							}
							else
							{
								CommunityTagEntity bean = GsonTools
										.changeGsonToBean(result, CommunityTagEntity.class);
								if (bean.getStatus() == 1)
								{
									ArrayList<CommunityTagEntity> data = bean
											.getData();
									tagAdapter.setPosts_tag(data);
									tagAdapter.notifyDataSetChanged();

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

	// 置顶数据
	public void getHotData()
	{
		try
		{
			ServiceFactory.getCommunityEngineInstatice()
					.getHotPosts(new IOAuthCallBack()
					{

						@Override
						public void getIOAuthCallBack(String result)
						{
							if (result.equals(ConstantValues.FAILURE))
							{
								ToastUtils.showInfo(getActivity(), ConstantValues.NONETWORK);
							}
							else
							{
								CommunityHotPostsEntity bean = GsonTools
										.changeGsonToBean(result, CommunityHotPostsEntity.class);
								if ("1".equals(bean.getStatus()))
								{
									ArrayList<CommunityHotPostsEntity> data = bean
											.getData();
									adapter_hot.setHot_list(data);
									adapter_hot.notifyDataSetChanged();

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

	// 列表数据
	public void getListData()
	{
		praise_user_id = ConstantValues.UID;

		try
		{
			ServiceFactory
					.getCommunityEngineInstatice()
					.getPostsList(user_id, praise_user_id, "", page, number, new IOAuthCallBack()
					{

						@Override
						public void getIOAuthCallBack(String result)
						{

							if (result.equals(ConstantValues.FAILURE))
							{
								ToastUtils
										.showInfo(getActivity(), ConstantValues.NONETWORK);
							}
							else
							{
								CommunityPostsEntity bean = GsonTools
										.changeGsonToBean(result, CommunityPostsEntity.class);
								if ("1".equals(bean.getStatus()))
								{
									ArrayList<CommunityPostsEntity> data = bean
											.getData();
									ArrayList<CommunityPostsEntity> mList = adapter_list
											.getPosts_list();
									if (data.size() == 0)
									{
										// 没有更多数据
										ToastUtils
												.showInfo(getActivity(), ConstantValues.NOMORE);
										adapter_list.notifyDataSetChanged();
										dialog.cancel();
										onLoaded();
										return;
									}
									if (isRefresh)
									{
										// 下拉刷新
										isRefresh = false;
										if (mList != null) mList.clear();
										adapter_list.setPosts_list(data);
									}
									else
									{
										// mList == null初始化。mList ！= null 加载更多
										if (mList == null)
										{
											adapter_list.setPosts_list(data);

										}
										else
										{
											mList.addAll(data);
										}
									}
									adapter_list.notifyDataSetChanged();
								}

							}
							dialog.cancel();
							onLoaded();
						}
					});
		}
		catch(Exception e)
		{
			dialog.cancel();
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.common_img:
				if (ConstantValues.ISLOGIN & ConstantValues.ISCOMPLETEINFO)
				{
					// 登录成功
					toEditPosts();
				}
				else
				{
					// 去登录
					if (!ConstantValues.ISLOGIN)
					{
						Intent toLogin = new Intent(getActivity(), LoginActivity.class);
						toLogin.putExtra("flag", "orderPrac");
						((FragmentActivity) getActivity())
								.startActivityForResult(toLogin, ConstantValues.ORDERPRAC);
					}
					else if (!ConstantValues.ISCOMPLETEINFO)
					{
						ToastUtils.showInfo(getActivity(), "请先完成资料设置");
						Intent editinfo = new Intent(getActivity(), UserInfoEditInfoActivity.class);
						getActivity().startActivity(editinfo);
					}
				}
				break;

			default:
				break;
		}
	}

	// =============下拉刷新=============
	@Override
	public void initRefreshableView()
	{
		mPullList.setPullLoadEnabled(false);
		// 滚动到底自动加载可用
		mPullList.setScrollLoadEnabled(true);
		// 得到实际的ListView 设置点击
		mPullList.getRefreshableView()
				.setOnItemClickListener(new AdapterView.OnItemClickListener()
				{

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id)
					{
						if (CommonUtil.isNetworkAvailable(getActivity()) == 0)
						{
							ToastUtils.showInfo(getActivity(), ConstantValues.NONETWORK);
						}
						else
						{
							CommunityPostsEntity bean = (CommunityPostsEntity) adapter_list
									.getItem(position - 2);
							Intent intent = new Intent(getActivity(), CommunityDetailActivity.class);
							intent.putExtra("posts_id", bean.getId() + "");
							getActivity().startActivity(intent);
						}
					}
				});

		// 设置下拉刷新的listener
		mPullList
				.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>()
				{

					@Override
					public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView)
					{

						isRefresh = true;
						page = 1;
						getListData();
					}

					@Override
					public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView)
					{

						page++;
						getListData();

					}
				});
	}

	// 置顶点击事件
	public void setHotOnclick()
	{
		comm_hot_list
				.setOnItemClickListener(new AdapterView.OnItemClickListener()
				{

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
					{

						if (CommonUtil.isNetworkAvailable(getActivity()) == 0)
						{
							ToastUtils
									.showInfo(getActivity(), ConstantValues.NONETWORK);
						}
						else
						{
							CommunityHotPostsEntity bean = (CommunityHotPostsEntity) adapter_hot
									.getItem(position);
							Intent intent = new Intent(getActivity(), CommunityDetailActivity.class);
							intent.putExtra("posts_id", bean.getId() + "");
							getActivity().startActivity(intent);
						}

					}
				});
	}

	// 去发帖
	public void toEditPosts()
	{
		Intent send = new Intent(getActivity(), AlbumEditActivity.class);
		getActivity().startActivityForResult(send, 105);

	}

	// 关闭刷新
	private void onLoaded()
	{
		mPullList.onPullDownRefreshComplete();
		mPullList.onPullUpRefreshComplete();
	}

	@Override
	public void onResume()
	{

		if (ConstantValues.isSendPosts)
		{
			isRefresh = true;
			page = 1;
			getListData();
			ConstantValues.isSendPosts = false;
		}
		super.onResume();
	}

	public void setRefresh(int position, int size, int id, NoScrollGridView mGvIco, int type, int width, TextView mPraiseNum, ImageView im_praise_more, LinearLayout ll_ico)
	{
		// isRefresh = true;
		// page = 1;
		// getListData();
		getPraiseData(position, size, id, mGvIco, type, width, mPraiseNum, im_praise_more, ll_ico);
	}

	// 列表数据
	public void getPraiseData(final int position, final int size, final int id, final NoScrollGridView mGvIco, final int type, final int width, final TextView mPraiseNum, final ImageView im_praise_more, final LinearLayout ll_ico)
	{
		dialog.show();
		praise_user_id = ConstantValues.UID;
		try
		{
			ServiceFactory
					.getCommunityEngineInstatice()
					.getPostsList(user_id, praise_user_id, "", position > 10 ? position / 10 + 1 : 1, number, new IOAuthCallBack()
					{

						@Override
						public void getIOAuthCallBack(String result)
						{

							if (result.equals(ConstantValues.FAILURE))
							{
								ToastUtils
										.showInfo(getActivity(), ConstantValues.NONETWORK);
							}
							else
							{
								CommunityPostsEntity bean = GsonTools
										.changeGsonToBean(result, CommunityPostsEntity.class);
								if ("1".equals(bean.getStatus()))
								{
									ArrayList<CommunityPostsEntity> data = bean
											.getData();
									if (data.size() > 0)
									{
										ArrayList<PraiseInfoEntity> mPList = data
												.get(position % 10)
												.getPraise_info();
										Map<String, CommunityDetailIcoAdapter> icoMap = adapter_list
												.getIcoMap();
										Map<String, CommunityPostsEntity> postsMap = adapter_list
												.getPostsMap();
										icoMap.get(id + "").getList().clear();
										icoMap.get(id + "").setList(mPList);
										// postsMap.get(id+"").setPraise_info(mPList);
										adapter_list
												.getPosts_list()
												.get(position)
												.setIs_praise(data
														.get(position % 10)
														.getIs_praise());
										adapter_list
												.getPosts_list()
												.get(position)
												.setPraise_info(data
														.get(position % 10)
														.getPraise_info());
										// Log.i("wangolf", position % 10 +
										// "position%10" + position + "position"
										// + "***" + mPList.size() +
										// data.get(position % 10).getTitle());
										//

										// adapter_list.getIco_adapterList().get(position).notifyDataSetInvalidated();
										// adapter_list.notifyDataSetInvalidated();
										// mPullList.getRefreshableView().setSelection(position);

										Drawable drawable1 = (data
												.get(position % 10)
												.getIs_praise() == 1 ? getActivity()
												.getResources()
												.getDrawable(R.drawable.icon_zan_select) : getActivity()
												.getResources()
												.getDrawable(R.drawable.icon_zan_default));

										drawable1.setBounds(0, 0, drawable1
												.getMinimumWidth(), drawable1
												.getMinimumHeight());// 必须设置图片大小，否则不显示

										// mPraiseNum.getCompoundDrawables()[0]=getActivity().getResources().getDrawable(R.drawable.icon_zan_select);
										mPraiseNum
												.setCompoundDrawables(drawable1, null, null, null);
										mPraiseNum.setText(data
												.get(position % 10)
												.getPraise_count() + "");
										LinearLayout.LayoutParams IcoParams = new LinearLayout.LayoutParams(width * (mPList
												.size() > 5 ? 5 : mPList.size()), ViewGroup.LayoutParams.WRAP_CONTENT);
										mGvIco.setLayoutParams(IcoParams);
										mGvIco.setNumColumns(mPList.size() > 5 ? 5 : mPList
												.size());
										// Log.i("wangolf",
										// mPraiseNum.getCompoundDrawables()[0].getCurrent()+"***********"+mPraiseNum.getCompoundDrawables()[1]+mPraiseNum.getCompoundDrawables()[2]+mPraiseNum.getCompoundDrawables()[3]+getActivity().getResources().getDrawable(R.drawable.icon_zan_select).getChangingConfigurations());
										if (mPList.size() >= 5)
										{
											im_praise_more
													.setVisibility(View.VISIBLE);

										}
										else
										{
											im_praise_more
													.setVisibility(View.GONE);
										}
										ll_ico.setVisibility(mPList.size() <= 0 ? View.GONE : View.VISIBLE);

										icoMap.get(id + "")
												.notifyDataSetChanged();
									}
								}

							}
							dialog.cancel();
							onLoaded();
						}
					});
		}
		catch(Exception e)
		{
			dialog.cancel();
			e.printStackTrace();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		if (CommonUtil.isNetworkAvailable(getActivity()) == 0)
		{
			ToastUtils.showInfo(getActivity(), ConstantValues.NONETWORK);
		}
		else
		{
			if (position == 5)
			{
				Intent tag = new Intent(getActivity(), CommunityPostsTagActivity.class);
				getActivity().startActivity(tag);

			}
			else
			{
				CommunityTagEntity bean = (CommunityTagEntity) tagAdapter
						.getItem(position);
				Intent intent = new Intent(getActivity(), CommunityPostsListActivity.class);
				intent.putExtra("tags_id", bean.getId());
				intent.putExtra("tags_name", bean.getName());
				getActivity().startActivity(intent);
			}
		}

	}

}
