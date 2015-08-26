package me.wangolf.bean.usercenter;

import java.util.List;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年1月26日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年1月26日
 * 
 * 描述 ： 返回的状态
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
public class RegistEntity {
	

    /**
     * status : 1
     * data : [{"is_effect":"0","recommend_amount":"5","self_info":"恭喜您获得10元注册礼包！","avatar":"","recommend_info":"推荐的好友也会获取丰厚的礼包哟！","id":"1","nick_name":"","user_name":"m6183423255","self_amount":"10","account":"10","gender":"1","my_intro":"","user_id":"2687","mobile":"15796643255"}]
     * info : 登录成功
     */
    private String status;
    private List<DataEntity> data;
    private String info;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public String getInfo() {
        return info;
    }

    public static class DataEntity {
        /**
         * is_effect : 0
         * recommend_amount : 5
         * self_info : 恭喜您获得10元注册礼包！
         * avatar :
         * recommend_info : 推荐的好友也会获取丰厚的礼包哟！
         * id : 1
         * nick_name :
         * user_name : m6183423255
         * self_amount : 10
         * account : 10
         * gender : 1
         * my_intro :
         * user_id : 2687
         * mobile : 15796643255
         */
        private String is_effect;
        private String recommend_amount;
        private String self_info;
        private String avatar;
        private String recommend_info;
        private String id;
        private String nick_name;
        private String user_name;
        private String self_amount;
        private String account;
        private String gender;
        private String my_intro;
        private String user_id;
        private String mobile;

        public void setIs_effect(String is_effect) {
            this.is_effect = is_effect;
        }

        public void setRecommend_amount(String recommend_amount) {
            this.recommend_amount = recommend_amount;
        }

        public void setSelf_info(String self_info) {
            this.self_info = self_info;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setRecommend_info(String recommend_info) {
            this.recommend_info = recommend_info;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public void setSelf_amount(String self_amount) {
            this.self_amount = self_amount;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setMy_intro(String my_intro) {
            this.my_intro = my_intro;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getIs_effect() {
            return is_effect;
        }

        public String getRecommend_amount() {
            return recommend_amount;
        }

        public String getSelf_info() {
            return self_info;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getRecommend_info() {
            return recommend_info;
        }

        public String getId() {
            return id;
        }

        public String getNick_name() {
            return nick_name;
        }

        public String getUser_name() {
            return user_name;
        }

        public String getSelf_amount() {
            return self_amount;
        }

        public String getAccount() {
            return account;
        }

        public String getGender() {
            return gender;
        }

        public String getMy_intro() {
            return my_intro;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getMobile() {
            return mobile;
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
//	private String status;
//	private String info;
//	private ArrayList<RegistEntity> data;
//	private String owninfo;
//	private  String otherinfo;
//	private String ownprice;
//	private int regstatus;//0代表要显示注册礼物1代表不需要显示注册礼物
//
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public String getInfo() {
//		return info;
//	}
//
//	public void setInfo(String info) {
//		this.info = info;
//	}
//
//	public ArrayList<RegistEntity> getData() {
//		return data;
//	}
//
//	public void setData(ArrayList<RegistEntity> data) {
//		this.data = data;
//	}
//
//	public String getOtherinfo() {
//		return otherinfo;
//	}
//
//	public void setOtherinfo(String otherinfo) {
//		this.otherinfo = otherinfo;
//	}
//
//	public String getOwninfo() {
//		return owninfo;
//	}
//
//	public void setOwninfo(String owninfo) {
//		this.owninfo = owninfo;
//	}
//
//	public String getOwnprice() {
//		return ownprice;
//	}
//
//	public void setOwnprice(String ownprice) {
//		this.ownprice = ownprice;
//	}
//
//	public int getRegstatus() {
//		return regstatus;
//	}
//
//	public void setRegstatus(int regstatus) {
//		this.regstatus = regstatus;
//	}
}
