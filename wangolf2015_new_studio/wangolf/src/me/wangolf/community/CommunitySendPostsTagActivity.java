package me.wangolf.community;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import java.util.ArrayList;

import me.wangolf.ConstantValues;
import me.wangolf.adapter.CommunityPostsTagListAdapter;
import me.wangolf.adapter.CommunitySendTagAdapter;
import me.wangolf.base.BaseActivity;
import me.wangolf.bean.community.CommunityTagEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.usercenter.LoginActivity;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.ToastUtils;
import me.wangolf.utils.viewUtils.PullToRefreshBase;
import me.wangolf.utils.viewUtils.PullToRefreshListView;

/**
 * Created by Administrator on 2015/6/8.
 */
public class CommunitySendPostsTagActivity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener {
    @ViewInject(R.id.common_back)
    private TextView mBack;
    @ViewInject(R.id.common_title)
    private TextView mTitle;
    @ViewInject(R.id.common_bt)
    private TextView mBt;
    @ViewInject(R.id.lsit_tag)
    private ListView mTag;
    private CommunitySendTagAdapter adapter;
    private int page = 1;//页数
    private int number = 10;//分页外数
    private boolean isRefresh;// 是否下拉刷新
    private int posts_id;
    private StringBuffer tags_name= new StringBuffer();//标签名称(若多选则以空格隔开,卢tags_name=高尔夫 活动)
    private StringBuffer tags_id = new StringBuffer();//标签ID(若多选则以半角逗号隔开，如tags_id=1,3) ;//标签ID(若多选则以半角逗号隔开，如tags_id=1,3)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_sendtag_list);
        ViewUtils.inject(this);
        if(adapter==null){
            adapter= new CommunitySendTagAdapter(this);
        }
        mTag.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        initData();
    }


    @Override
    public void initData() {
        mTitle.setText("全部标签");
        mBt.setText("完成");
        mBt.setVisibility(View.VISIBLE);
        mBack.setVisibility(View.VISIBLE);
        mTitle.setVisibility(View.VISIBLE);
        mBack.setOnClickListener(this);
        mBt.setOnClickListener(this);
        getData();
        mTag.setOnItemClickListener(this);
        mTag.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
     //   mTag.setItemChecked(0, true);
    }
    @Override
    public void getData() {
        try {
            ServiceFactory.getCommunityEngineInstatice().getTagsList(new IOAuthCallBack() {
                @Override
                public void getIOAuthCallBack(String result) {
                    if (result.equals(ConstantValues.FAILURE)) {
                        ToastUtils.showInfo(CommunitySendPostsTagActivity.this, ConstantValues.NONETWORK);
                    } else {
                        CommunityTagEntity bean = GsonTools.changeGsonToBean(result, CommunityTagEntity.class);
                        if (bean.getStatus() == 1) {
                            ArrayList<CommunityTagEntity> data = bean.getData();
                            adapter.setTag_list(data);
                            adapter.notifyDataSetChanged();
                            mTag.getSelectedView();
                           // tags_id.append(data.get(0).getId() + "");
                            //tags_name.append(data.get(0).getName());
                        }
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.common_back:
                finish();
                break;
            case R.id.common_bt:
                Intent result  =new Intent();
                result.putExtra("tags_name",tags_name.toString());
                result.putExtra("tags_id",tags_id.toString());
                setResult(1006,result);
                finish();
                break;
            default:
                break;
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (ListView.CHOICE_MODE_MULTIPLE == mTag.getChoiceMode()) {
            CommunityTagEntity list;

            if (mTag.isItemChecked(position)) {
                mTag.setItemChecked(position, true);

            } else {
                mTag.setItemChecked(position, false);
            }
            tags_id=new StringBuffer();
            tags_name=new StringBuffer();
            for (int i = 0; i <adapter.getCount(); i++) {

                if(mTag.isItemChecked(i)){
                    list = (CommunityTagEntity) adapter.getItem(i);
                    tags_id.append( list.getId()+"," );
                    tags_name.append( list.getName()+"," );
                }
            }
            if(!CheckUtils.checkEmpty(tags_id.toString())){
                tags_id.replace(tags_id.lastIndexOf(","),tags_id.length(),"");
                tags_name.replace(tags_name.lastIndexOf(","), tags_name.length(), "");}
            //Log.i("wangolf", "当前为多选模式\n选中的条数："+tags_id.toString()+tags_name.toString());//如果选中了前四条，打印：100，101，102，103;由前面的adapter getItemId()返回的
        }
    }
}
