package me.wangolf.test;

import org.apache.commons.codec.digest.DigestUtils;

import android.test.AndroidTestCase;

import me.wangolf.bean.usercenter.User;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;

public class IUserServiceTest extends AndroidTestCase {

	public void testGetUserLoginInfo() {
		User u = new User();
		u.setUsername("15013576013");
		u.setPassword(DigestUtils.md5Hex("123789"));
		try {
			ServiceFactory.getIUserEngineInstatice().UserLogin(u,
					new IOAuthCallBack() {
						@Override
						public void getIOAuthCallBack(String result) {
							// TODO Auto-generated method stub
							System.out.println(result);
						}
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
