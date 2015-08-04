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
import me.wangolf.bean.shop.CommodityEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.SharedPreferencesUtils;
import me.wangolf.utils.ToastUtils;

public class ShopChannelManage {
    public ShopChannelManage channelManage;
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
    private ArrayList<NewsTagsEntity> data;
    private int[] orderid = new int[20];
    private boolean iscache = true;
    private List<ChannelItem> defaultUserShopChannels;
    private ArrayList<ChannelItem> defaultOtherShopChannels;
    private boolean isFirst;

    public ShopChannelManage() {

    }

    public void initData(final Context context) {

        //初始化学院
        //initCollegeChannel(context);

        //初始化商城
       initShopChannel(context);
        defaultUserChannels = new ArrayList<ChannelItem>();
        defaultOtherChannels = new ArrayList<ChannelItem>();

    }

    // 获取学院分类
    public void initCollegeChannel(final Context context) {
        String cache = SharedPreferencesUtils.getString(context, "college_channel" + ConstantValues.versionCode);
        try {
            ServiceFactory.getCollegeEngineInstatice().getNewsTags(new IOAuthCallBack() {

                @Override
                public void getIOAuthCallBack(String result) {
                    toCacheData(result, "college_channel" + ConstantValues.versionCode, context);
                    iscache = false;
                    ProcessCollegedata(result, context);

                    // if (result.equals(ConstantValues.FAILURE)) {
                    // Toast.makeText(context, ConstantValues.NONETWORK,
                    // 0).show();
                    // } else {
                    // NewsTagsEntity bean = GsonTools.changeGsonToBean(result,
                    // NewsTagsEntity.class);
                    // if ("1".equals(bean.getStatus())) {
                    // // ConstantValues.title_name[0] = "教练";
                    // // ConstantValues.title_name[1] = "学院";
                    //
                    // for (int i = 0; i < bean.getData().size(); i++) {
                    // data = bean.getData();
                    // defaultUserChannels.add(new
                    // ChannelItem(data.get(i).getId(), data.get(i).getName(), i
                    // + 2, 1));
                    // // ConstantValues.title_name[i + 2] =
                    // // data.get(i).getName();
                    // // ConstantValues.title_id[i + 2] =
                    // // data.get(i).getId();
                    // }
                    // // ConstantValues.title_size =
                    // // bean.getData().size();
                    // getTitleandId();
                    // }
                    //
                    // }
                }
            });
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    //商城分类
    public void initShopChannel(final Context context) {
        try {
            ServiceFactory.getShopEngineInstatice().getCommodity(new IOAuthCallBack() {
                @Override
                public void getIOAuthCallBack(String result) {
                    ProcessShopdata(result, context);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTitleandId(String tableName, int flag) {
        isFirst=true;
        userChannelList = ((ArrayList<ChannelItem>) getManage(LocationApplication.getApp().getSQLHelper(), tableName).getUserChannel(tableName));
        ArrayList<String> titles = new ArrayList<String>();
        ArrayList<Integer> titleid = new ArrayList<Integer>();
        for (int i = 0; i < userChannelList.size(); i++) {
            titles.add(userChannelList.get(i).getName());
            titleid.add(userChannelList.get(i).getId());
        }

        String[] title = (String[]) titles.toArray(new String[0]);
        Integer[] id = (Integer[]) titleid.toArray(new Integer[0]);

                    ConstantValues.shop_title_name = title;
                    ConstantValues.shop_title_id = id;
                    ConstantValues.shop_title_size = userChannelList.size();

    }

    public ShopChannelManage(SQLHelper paramDBHelper, String tableName) {
        if (channelDao == null) {
            channelDao = new ChannelDao(paramDBHelper.getContext());
            deleteAllChannel(tableName);
        }
        // NavigateItemDao(paramDBHelper.getDao(NavigateItem.class));

    }

    /**
     * 初始化频道管理类
     *
     * @param
     * @throws android.database.SQLException
     */
    public ShopChannelManage getManage(SQLHelper dbHelper, String tableName) throws SQLException {
        if (channelManage == null)
            channelManage = new ShopChannelManage(dbHelper, tableName);
        return channelManage;
    }

    /**
     * 清除所有的频道
     */
    public void deleteAllChannel(String tableName) {

        if (!CheckUtils.checkEmpty(tableName))
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
       if(isFirst){
           initDefaultChannel(tableName);
           isFirst=false;
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
       deleteAllChannel(tableName);

        saveUserChannel(defaultUserChannels, tableName);
        saveOtherChannel(defaultOtherChannels, tableName);

    }


    public void ProcessCollegedata(String result, Context context) {
        //defaultUserChannels.clear();
        if (result.equals(ConstantValues.FAILURE)) {
            ToastUtils.showInfo(context, ConstantValues.NONETWORK);
        } else {
            NewsTagsEntity bean = GsonTools.changeGsonToBean(result, NewsTagsEntity.class);
            //defaultUserChannels.add(new ChannelItem(1, "教练", 1, 1));
            //defaultUserChannels.add(new ChannelItem(2, "学院", 2, 1));
            if ("1".equals(bean.getStatus())) {
                for (int i = 0; i < bean.getData().size(); i++) {
                    data = bean.getData();
                    //defaultUserChannels.add(new ChannelItem(data.get(i).getId(), data.get(i).getName(), i + 2, 1));
                    defaultUserChannels.add(new ChannelItem(data.get(i).getId(), data.get(i).getName(), i, 1));

                }

                getTitleandId(SQLHelper.TABLE_CHANNEL, 0);
            }

        }

    }

    public void ProcessShopdata(String result, Context context) {
       // defaultUserChannels.clear();
        if (result.equals(ConstantValues.FAILURE)) {
            ToastUtils.showInfo(context, ConstantValues.NONETWORK);
        } else {
            CommodityEntity bean = GsonTools.changeGsonToBean(result, CommodityEntity.class);
            if ("1".equals(bean.getStatus())) {
                for (int i = 0; i < bean.getData().size(); i++) {
                    ArrayList<CommodityEntity> data = bean.getData();

                    defaultUserChannels.add(new ChannelItem(data.get(i).getId(), data.get(i).getName(), i, 1));
                }

                //userChannelList = ((ArrayList<ChannelItem>) getManage(LocationApplication.getApp().getSQLHelper(), SQLHelper.TABLE_SHOP).getUserChannel(SQLHelper.TABLE_SHOP));
               getTitleandId(SQLHelper.TABLE_SHOP, 1);
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
