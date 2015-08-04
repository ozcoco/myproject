package me.wangolf.service;

import android.widget.EditText;

/**
 * ============================================================
 * 新增高球常识
 * ============================================================
 */
public interface IKnowledgeService extends BaseService {


    /**
     * 1 高尔夫常识标签
     *
     * @param iOAuthCallBack
     * @throws Exception
     */
    public void getGeneralKnowledgeTags(IOAuthCallBack iOAuthCallBack) throws Exception;

    /**
     * 新手资讯根据标签显示列表(新版)
     *
     * @param tags_id        tags_id
     * @param iOAuthCallBack
     * @throws Exception
     */
    public void getKnowledgeList(String tags_id, int page, int number, IOAuthCallBack iOAuthCallBack) throws Exception;

    /**
     * 2资讯详情
     * @param knowid
     * @param iOAuthCallBack
     * @throws Exception
     */
    public void getInformation(String knowid, IOAuthCallBack iOAuthCallBack) throws Exception;
}
