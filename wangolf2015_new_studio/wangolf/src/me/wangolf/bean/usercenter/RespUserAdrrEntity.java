package me.wangolf.bean.usercenter;

import java.io.Serializable;
import java.util.List;

public class RespUserAdrrEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

    /**
     * status : 1
     * data :[]
     * info : 成功
     */
    private int status;
    private List<DataEntity> data;
    private String info;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public String getInfo() {
        return info;
    }

    
    
    public static class DataEntity implements Serializable
    {
        /**
		 * 
		 */
    	
		private static final long serialVersionUID = 1L;
		/**
         * id : 156
         * city_id : 257
         * zip : 33
         * area_id : 2156
         * city_name : 营口市
         * province_name : 辽宁省
         * province_id : 18
         * address : 那
         * consignee : 咯
         * area_name : 站前区
         * user_id : 52
         * type : 1
         * mobile : 5
         */
        private String id;
        private String city_id;
        private String zip;
        private String area_id;
        private String city_name;
        private String province_name;
        private String province_id;
        private String address;
        private String consignee;
        private String area_name;
        private String user_id;
        private String type;
        private String mobile;

        public void setId(String id) {
            this.id = id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getId() {
            return id;
        }

        public String getCity_id() {
            return city_id;
        }

        public String getZip() {
            return zip;
        }

        public String getArea_id() {
            return area_id;
        }

        public String getCity_name() {
            return city_name;
        }

        public String getProvince_name() {
            return province_name;
        }

        public String getProvince_id() {
            return province_id;
        }

        public String getAddress() {
            return address;
        }

        public String getConsignee() {
            return consignee;
        }

        public String getArea_name() {
            return area_name;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getType() {
            return type;
        }

        public String getMobile() {
            return mobile;
        }
    }
	
	
	
//	private int id;
//	private String rid1;//所属国家ID
//	private String rid2;//所属省份ID
//	private String rid3;//所属市ID
//	private String rid4;//所属区/县ID
//	
//	private String address;//具体地址
//	private String mobile;//联系号码
//	private String zip;//邮编
//	private String consignee;//联系人
//	private String type;//区分是否默认地址
//	
//	
//	
//	private String country;//国家
//	private String province;//省份
//	private String city;//所属市
//	private String xian;//所属区/县
//	private String status;
//	private String info;
//	private ArrayList<RespUserAdrrEntity> data;
//	public String getRid1() {
//		return rid1;
//	}
//	public void setRid1(String rid1) {
//		this.rid1 = rid1;
//	}
//	public String getRid2() {
//		return rid2;
//	}
//	public void setRid2(String rid2) {
//		this.rid2 = rid2;
//	}
//	public String getRid3() {
//		return rid3;
//	}
//	public void setRid3(String rid3) {
//		this.rid3 = rid3;
//	}
//	public String getRid4() {
//		return rid4;
//	}
//	public void setRid4(String rid4) {
//		this.rid4 = rid4;
//	}
//	public String getAddress() {
//		return address;
//	}
//	public void setAddress(String address) {
//		this.address = address;
//	}
//	public String getMobile() {
//		return mobile;
//	}
//	public void setMobile(String mobile) {
//		this.mobile = mobile;
//	}
//	public String getZip() {
//		return zip;
//	}
//	public void setZip(String zip) {
//		this.zip = zip;
//	}
//	public String getConsignee() {
//		return consignee;
//	}
//	public void setConsignee(String consignee) {
//		this.consignee = consignee;
//	}
//	public String getCountry() {
//		return country;
//	}
//	public void setCountry(String country) {
//		this.country = country;
//	}
//	public String getProvince() {
//		return province;
//	}
//	public void setProvince(String province) {
//		this.province = province;
//	}
//	public String getCity() {
//		return city;
//	}
//	public void setCity(String city) {
//		this.city = city;
//	}
//	public String getXian() {
//		return xian;
//	}
//	public void setXian(String xian) {
//		this.xian = xian;
//	}
//
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getStatus() {
//		return status;
//	}
//	public void setStatus(String status) {
//		this.status = status;
//	}
//	public String getInfo() {
//		return info;
//	}
//	public void setInfo(String info) {
//		this.info = info;
//	}
//	public ArrayList<RespUserAdrrEntity> getData() {
//		return data;
//	}
//	public void setData(ArrayList<RespUserAdrrEntity> data) {
//		this.data = data;
//	}
//	
}
