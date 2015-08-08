package me.wangolf.factory;

import me.wangolf.service.IBallService;
import me.wangolf.service.ICollegeService;
import me.wangolf.service.ICommunityService;
import me.wangolf.service.IEventService;
import me.wangolf.service.IIndexService;
import me.wangolf.service.IKnowledgeService;
import me.wangolf.service.IPracService;
import me.wangolf.service.IShopService;
import me.wangolf.service.IUserService;
import me.wangolf.service.impl.BallServiceImpl;
import me.wangolf.service.impl.CollegeServiceImpl;
import me.wangolf.service.impl.CommunityServiceImpl;
import me.wangolf.service.impl.EventServiceImpl;
import me.wangolf.service.impl.IndexServiceImpl;
import me.wangolf.service.impl.KnowledgeServiceImpl;
import me.wangolf.service.impl.PracServiceImpl;
import me.wangolf.service.impl.ShopServiceImpl;
import me.wangolf.service.impl.UserServiceImpl;

public class ServiceFactory {
	/**
	 * 用户相关
	 * 
	 * @return
	 */
	public static IUserService getIUserEngineInstatice()
	{
		return new UserServiceImpl();
	}

	/**
	 * 练习场相关
	 * 
	 * @return
	 */
	public static IPracService getPracEngineInstatice()
	{
		return new PracServiceImpl();

	}

	/**
	 * 商城相关
	 */
	public static IShopService getShopEngineInstatice()
	{
		return new ShopServiceImpl();
	}

	/**
	 * 学院相关
	 */
	public static ICollegeService getCollegeEngineInstatice() 
	{
		return new CollegeServiceImpl();
	}

	/**
	 * 球场相关
	 */
	public static IBallService getBallEngineInstatice() {
		return new BallServiceImpl();
	}

	/**
	 * 活动相关
	 * 
	 * @return
	 */
	public static IEventService getEventEngineInstatice() {
		return new EventServiceImpl();
	}

	/**
	 * 首页相关
	 * 
	 * @return
	 */
	public static IIndexService getIndexEngineInstatice() {
		return new IndexServiceImpl();
	}

	/**
	 * 社区相关
	 * 
	 * @return
	 */
	public static ICommunityService getCommunityEngineInstatice() {
		return new CommunityServiceImpl();
	}

    /**
     * 高球常识
     * @return
     */
    public static IKnowledgeService getKnowledgeEngineInstatice(){
        return  new KnowledgeServiceImpl();
    }
}
