package me.wangolf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页广告
 * 
 * @author Administrator
 * 
 */
public class HomePageAdsEntity implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
    /**
     * status : 1
     * data : [{"icon":"http://www.wangolf.me/public/attachment/201506/10/20150710171030936493728.jpg","adv_id":"4","type":"1"},{"icon":"http://www.wangolf.me/public/attachment/201506/10/20150710171650645730926.jpg","adv_id":"6","type":"1"},{"icon":"http://www.wangolf.me/public/attachment/201506/10/20150710165854701712680.jpg","adv_id":"7","type":"1"},{"icon":"http://www.wangolf.me/public/attachment/201506/13/20150713164021972696371.jpg","adv_id":"197","type":"3"},{"icon":"http://www.wangolf.me/public/attachment/201507/06/150806095439856251387.jpg","adv_id":"275","type":"3"}]
     * info : 成功
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

    public static class DataEntity implements Serializable{
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
         * icon : http://www.wangolf.me/public/attachment/201506/10/20150710171030936493728.jpg
         * adv_id : 4
         * type : 1
         */
        private String icon;
        private String adv_id;
        private String type;

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setAdv_id(String adv_id) {
            this.adv_id = adv_id;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIcon() {
            return icon;
        }

        public String getAdv_id() {
            return adv_id;
        }

        public String getType() {
            return type;
        }
    }
	
//	private String logo;// "/public/attachment/201201/4f013fc452347_150x110.jpg"//图片地址
//	private String advid;// 广告ID(若是活动则代表活动ID，若是分类则代表分类ID)
//	private int type;// "0"//0代表具体商品广告1代表某活动广告
//	private String status;
//	private String info;
//	private List<HomePageAdsEntity> data;
//	private String shopid;
//	private String code;
//	private String name;
//
//	public String getLogo() {
//		return logo;
//	}
//
//	public void setLogo(String logo) {
//		this.logo = logo;
//	}
//
//	public String getAdvid() {
//		return advid;
//	}
//
//	public void setAdvid(String advid) {
//		this.advid = advid;
//	}
//
//	public int getType() {
//		return type;
//	}
//
//	public void setType(int type) {
//		this.type = type;
//	}
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
//	public List<HomePageAdsEntity> getData() {
//		return data;
//	}
//
//	public void setData(List<HomePageAdsEntity> data) {
//		this.data = data;
//	}
//
//	public String getShopid() {
//		return shopid;
//	}
//
//	public void setShopid(String shopid) {
//		this.shopid = shopid;
//	}
//
//	public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

}
