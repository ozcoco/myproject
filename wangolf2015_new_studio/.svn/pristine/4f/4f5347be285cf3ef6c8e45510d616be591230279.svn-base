package me.wangolf.fragment;

import java.util.ArrayList;
import java.util.List;

import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.adapter.CommonPageAdapter;
import me.wangolf.ballprac.BallPracSearchActivity;
import me.wangolf.base.Mo_BaseFragment;
import me.wangolf.base.Mo_BasePage;
import me.wangolf.community.CommunityPage;
import me.wangolf.shop.ShopPage;
import me.wangolf.usercenter.LoginActivity;
import me.wangolf.usercenter.UserInfoPage;
import me.wangolf.utils.viewUtils.CustomViewPager;
import me.wangolf.utils.viewUtils.LazyViewPager.OnPageChangeListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class HomeFragment extends Mo_BaseFragment {
	/**
	 * 1 初始化viewpager 通过使用adapter的形式去实现
	 */

	private List<Mo_BasePage> list = new ArrayList<Mo_BasePage>();
	private View view;
	@ViewInject(R.id.viewpager)
	private CustomViewPager viewpager;
	@ViewInject(R.id.main_radio)
	private RadioGroup main_radio;
	@ViewInject(R.id.rb_index)
	private RadioButton rb_index;
	@ViewInject(R.id.rb_store)
	private RadioButton rb_store;
	@ViewInject(R.id.rb_college)
	private RadioButton rb_college;
	@ViewInject(R.id.rb_user)
	private RadioButton rb_user;
	@ViewInject(R.id.rb_setting)
	private RadioButton rb_setting;
	private int checkedId = R.id.rb_index;
	private int radio_id;
	private boolean flag;
	private boolean isLoginSuccess;

	@Override
	public View initView(LayoutInflater inflater) {
		view = inflater.inflate(R.layout.mg_index, null);
		ViewUtils.inject(this, view); // 注入view和事件
		return view;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		if (savedInstanceState != null) {

		}
		getDatd();
	}

	public void getDatd() {
		if (!isLoadSuccess) {
			list.add(new IndexPage(content));
			list.add(new BallPracSearchActivity(content));
			list.add(new ShopPage(content));
			list.add(new CommunityPage(content));
			list.add(new UserInfoPage(content));
			// list.add(new CustomerPage(content));
		}
		isLoadSuccess = true; // 加载完设成true
		CommonPageAdapter adapter = new CommonPageAdapter(content, list);
		viewpager.setAdapter(adapter);
		viewpager.setScrollable(false);
		// 不进行预加载
		viewpager.setOffscreenPageLimit(0);
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				Mo_BasePage page = list.get(position);
				page.initData();

			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub

			}
		});
		list.get(0).initData();
		main_radio.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_index:
					viewpager.setCurrentItem(0, false);
					checkedId = 0;
					radio_id = 0;
					break;

				case R.id.rb_store:

					viewpager.setCurrentItem(2, false);
					checkedId = 2;
					radio_id = 2;
					break;
				case R.id.rb_college:
					viewpager.setCurrentItem(1, false);
					checkedId = 1;
					radio_id = 1;
					break;
				case R.id.rb_user:
					rb_user.setChecked(false);
					if (isLogin() | ConstantValues.ISLOGIN) {
						rb_user.setChecked(true);
						if (!isLoginSuccess) {
							// list.add(3,new UserInfoPage(content));
							isLoginSuccess = true;
						}
						viewpager.setCurrentItem(4, false);
					} else if (!isLoginSuccess) {
						switch (radio_id) {
						case 0:
							rb_index.setChecked(true);

							break;
						case 1:

							rb_college.setChecked(true);
							break;
						case 2:
							rb_store.setChecked(true);

							break;
						case 3:
							rb_setting.setChecked(true);
							break;
						case 4:
							rb_user.setChecked(true);
							break;
						default:
							break;
						}

						viewpager.setCurrentItem(radio_id, false);
					}
					checkedId = 3;

					break;

				case R.id.rb_setting:
					viewpager.setCurrentItem(3, false);
					checkedId = 3;
					radio_id = 3;
					break;
				}

			}
		});
		main_radio.check(checkedId);
	}

	public boolean isLogin() {
		if (!flag & !ConstantValues.ISLOGIN) {
			Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
			loginIntent.putExtra("flag", "usercenter");
			getActivity().startActivity(loginIntent);
		}
		return flag;

	}

	@Override
	public void onResume() {

		if (!flag) {
			// 用于判断登录后的状态
			if (ConstantValues.HOME_ISLOGIN & ConstantValues.USERCENT_ISLOGIN) {
				flag = true;
				// ConstantValues.HOME_ISLOGIN = false;
				rb_user.setChecked(true);
			}
		}
		if (!ConstantValues.ISLOGIN & ConstantValues.HOME_ISLOGIN) {
			// 用于判断用户退出后的状态
			rb_index.setChecked(true);
			flag = false;
			ConstantValues.HOME_ISLOGIN = false;
			ConstantValues.USERCENT_ISLOGIN = false;
			isLoginSuccess = false;
		}
		if (ConstantValues.ballTags) {
			// 用户选择更换频道状态
			list.get(1).initData();
			ConstantValues.ballTags = false;
		}
		if (ConstantValues.isSendPosts) {
			// 发帖成功 更新界面

			list.get(3).upView();
		}

		super.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}

}
