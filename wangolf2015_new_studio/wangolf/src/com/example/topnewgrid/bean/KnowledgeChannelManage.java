package com.example.topnewgrid.bean;

import android.content.Context;
import android.database.SQLException;
import android.util.Log;

import com.example.topnewgrid.dao.ChannelDao;
import com.example.topnewgrid.db.SQLHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.wangolf.ConstantValues;
import me.wangolf.LocationApplication;
import me.wangolf.bean.college.NewsTagsEntity;
import me.wangolf.bean.knowledge.KnowledgeTagEntity;
import me.wangolf.bean.shop.CommodityEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.SharedPreferencesUtils;
import me.wangolf.utils.ToastUtils;

public class KnowledgeChannelManage {
    public KnowledgeChannelManage channelManage;
    /**
     * 默认的用户选择频道列表
     */
    public static List<ChannelItem> defaultUserChannels;
    /**
     * 默认的其他频道列表
     */
    public static List<ChannelItem> defaultOtherChannels;
    public List<ChannelItem> defaultShopOtherChannels;

    /**
     * 用户栏目列表
     */
    static ArrayList<ChannelItem> userChannelList = new ArrayList<ChannelItem>();
    private ChannelDao channelDao;
    /**
     * 判断数据库中是否存在用户数据
     */
    private boolean userExist = false;
    private ArrayList<KnowledgeTagEntity> data;
    private int[] orderid = new int[20];
    private boolean iscache = true;
    private boolean isDeleteTable;//如有更新清空清
    private boolean isFirst;

    public KnowledgeChannelManage() {

    }

    public void initData(final Context context) {

        //初始化高球常识
        initKnowledgeChannel(context);


    }

    // 初始化高球常识分类
    public void initKnowledgeChannel(final Context context) {
        final String cache = SharedPreferencesUtils.getString(context, "knowledge_channel" + ConstantValues.versionCode);
        defaultUserChannels = new ArrayList<ChannelItem>();
        defaultOtherChannels = new ArrayList<ChannelItem>();
        try {
            ServiceFactory.getKnowledgeEngineInstatice().getGeneralKnowledgeTags(new IOAuthCallBack() {

                @Override
                public void getIOAuthCallBack(String result) {
//Log.i("wangolf",result);
//
//                    if (!result.equals(cache) || CheckUtils.checkEmpty(cache)) {
                        isDeleteTable = true;//有更新清除所有频道

                        ProcessKnowledgedata(result, context);


//                    } else {
//                        //无更新使用
//                        Log.i("wangolf", "无更新"+cache+"***");
//                        // ProcessCollegedata(cache, context);
//                        getTitleandId(SQLHelper.TABLE_KNOWLEDGE, 0);
//                    }
                }
            });
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public void getTitleandId(String tableName, int flag) {
        isFirst = true;
        if (isDeleteTable)
            getManage(LocationApplication.getApp().getSQLHelper(), tableName).channelDao.clearFeedTable(tableName);
        userChannelList = ((ArrayList<ChannelItem>) getManage(LocationApplication.getApp().getSQLHelper(), tableName).getUserChannel(tableName));
        ArrayList<String> titles = new ArrayList<String>();
        ArrayList<Integer> titleid = new ArrayList<Integer>();
        for (int i = 0; i < userChannelList.size(); i++) {
            titles.add(userChannelList.get(i).getName());
            titleid.add(userChannelList.get(i).getId());

        }

        String[] title = (String[]) titles.toArray(new String[0]);
        Integer[] id = (Integer[]) titleid.toArray(new Integer[0]);

        ConstantValues.knoledge_title_name = title;
        ConstantValues.knoledge_title_id = id;
        ConstantValues.knoledge_title_size = userChannelList.size();


    }

    public KnowledgeChannelManage(SQLHelper paramDBHelper, String tableName) {
        if (channelDao == null) {
            channelDao = new ChannelDao(paramDBHelper.getContext());
            deleteAllChannel(tableName, isDeleteTable);
        }
        // NavigateItemDao(paramDBHelper.getDao(NavigateItem.class));

    }

    /**
     * 初始化频道管理类
     *
     * @param
     * @throws android.database.SQLException
     */
    public KnowledgeChannelManage getManage(SQLHelper dbHelper, String tableName) throws SQLException {
        if (channelManage == null)
            channelManage = new KnowledgeChannelManage(dbHelper, tableName);
        return channelManage;
    }

    /**
     * 清除所有的频道
     */
    public void deleteAllChannel(String tableName, boolean isDeleteTable) {

        if (!CheckUtils.checkEmpty(tableName) && isDeleteTable)
            channelDao.clearFeedTable(tableName);
    }

    /**
     * 获取其他的频道
     *
     * @return 数据库存在用户配置 ? 数据库内的用户选择频道 : 默认用户选择频道 ;
     */
    public List<ChannelItem> getUserChannel(String tableName) {
        Object cacheList = channelDao.listCache(SQLHelper.SELECTED + "= ?", new String[]{"1"}, tableName);
        if (cacheList != null && !((List) cacheList).isEmpty()) {
            userExist = true;
            List<Map<String, String>> maplist = (List) cacheList;
            int count = maplist.size();
            List<ChannelItem> list = new ArrayList<ChannelItem>();
            for (int i = 0; i < count; i++) {
                ChannelItem navigate = new ChannelItem();
                navigate.setId(Integer.valueOf(maplist.get(i).get(SQLHelper.ID)));
                navigate.setName(maplist.get(i).get(SQLHelper.NAME));
                navigate.setOrderId(Integer.valueOf(maplist.get(i).get(SQLHelper.ORDERID)));
                navigate.setSelected(Integer.valueOf(maplist.get(i).get(SQLHelper.SELECTED)));
                list.add(navigate);
            }
            return list;
        }
        if (isFirst) {
            initDefaultChannel(tableName);
            isFirst = false;
        }

        return defaultUserChannels;
    }

    /**
     * 获取其他的频道
     *
     * @return 数据库存在用户配置 ? 数据库内的其它频道 : 默认其它频道 ;
     */
    public List<ChannelItem> getOtherChannel(String tableName) {
        Object cacheList = channelDao.listCache(SQLHelper.SELECTED + "= ?", new String[]{"0"}, tableName);
        List<ChannelItem> list = new ArrayList<ChannelItem>();
        if (cacheList != null && !((List) cacheList).isEmpty()) {
            List<Map<String, String>> maplist = (List) cacheList;
            int count = maplist.size();
            for (int i = 0; i < count; i++) {
                ChannelItem navigate = new ChannelItem();
                navigate.setId(Integer.valueOf(maplist.get(i).get(SQLHelper.ID)));
                navigate.setName(maplist.get(i).get(SQLHelper.NAME));
                navigate.setOrderId(Integer.valueOf(maplist.get(i).get(SQLHelper.ORDERID)));
                navigate.setSelected(Integer.valueOf(maplist.get(i).get(SQLHelper.SELECTED)));
                list.add(navigate);
            }
            return list;
        }
        if (userExist) {
            return list;
        }
        cacheList = defaultOtherChannels;
        return (List<ChannelItem>) cacheList;
    }

    /**
     * 保存用户频道到数据库
     *
     * @param userList
     */
    public void saveUserChannel(List<ChannelItem> userList, String tableName) {
        for (int i = 0; i < userList.size(); i++) {
            ChannelItem channelItem = (ChannelItem) userList.get(i);
            channelItem.setOrderId(i);
            channelItem.setSelected(Integer.valueOf(1));
            channelDao.addCache(channelItem, tableName);
        }
    }

    /**
     * 保存其他频道到数据库
     *
     * @param otherList
     */
    public void saveOtherChannel(List<ChannelItem> otherList, String tableName) {
        for (int i = 0; i < otherList.size(); i++) {
            ChannelItem channelItem = (ChannelItem) otherList.get(i);
            channelItem.setOrderId(i);
            channelItem.setSelected(Integer.valueOf(0));
            channelDao.addCache(channelItem, tableName);
        }
    }

    /**
     * 初始化数据库内的频道数据
     */
    private void initDefaultChannel(String tableName) {
        Log.d("deleteAll", "deleteAll");
        deleteAllChannel(tableName, isDeleteTable);

        saveUserChannel(defaultUserChannels, tableName);
        saveOtherChannel(defaultOtherChannels, tableName);

    }


    public void ProcessKnowledgedata(String result, Context context) {
        //defaultUserChannels.clear();
        if (result.equals(ConstantValues.FAILURE)) {
            ToastUtils.showInfo(context, ConstantValues.NONETWORK);
        } else {
            KnowledgeTagEntity bean = GsonTools.changeGsonToBean(result, KnowledgeTagEntity.class);
            if ("1".equals(bean.getStatus())) {
                for (int i = 0; i < bean.getData().size(); i++) {
                    data = bean.getData();

                    //defaultUserChannels.add(new ChannelItem(data.get(i).getId(), data.get(i).getName(), i + 2, 1));
                    defaultUserChannels.add(new ChannelItem(data.get(i).getId(), data.get(i).getName(), i, 1));
                }

                getTitleandId(SQLHelper.TABLE_KNOWLEDGE, 0);
                toCacheData(result, "knowledge_channel" + ConstantValues.versionCode, context);

            }

        }

    }


    // 本地缓存
    public static void toCacheData(String result, String name, Context context) {
        SharedPreferencesUtils.saveString(context, name, result);
    }

    // 处理缓存数据
    public void getCache() {

    }

}
