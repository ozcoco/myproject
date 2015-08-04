/*
 * Copyright (C) 2010 The MobileSecurePay Project
 * All right reserved.
 * author: shiqun.shi@alipay.com
 * 
 *  提示：如何获取安全校验码和合作身份者id
 *  1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *  2.点击“商家服务”(https://b.alipay.com/order/myorder.htm)
 *  3.点击“查询合作者身份(pid)”、“查询安全校验码(key)”
 */

package me.wangolf.alipay;

//
// 请参考 Android平台安全支付服务(msp)应用开发接口(4.2 RSA算法签名)部分，并使用压缩包中的openssl RSA密钥生成工具，生成一套RSA公私钥。
// 这里签名时，只需要使用生成的RSA私钥。
// Note: 为安全起见，使用RSA私钥进行签名的操作过程，应该尽量放到商家服务器端去进行。
public final class Keys {

	//合作身份者id，以2088开头的16位纯数字
	public static final String DEFAULT_PARTNER = "2088511494848981";

	//收款支付宝账号
	public static final String DEFAULT_SELLER = "mgolfapp@gmail.com";

	//商户私钥，自助生成
	public static final String PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ8cdfrCIQjfhXKlQOEt6FLARfc6Nk4FCHJkF45uERmxLxUoFAHnVlQYAaradgKIV0mb3lUllaDU2GGNHnQ+FbMzcSo6NVmH3G23nkkq0xGTy+Qu7a2/AbP0HOVFIGJBD7fdxyZ++8vfGaiHY7RY8G9XuMFHi+hsmMtxZM3FO0cfAgMBAAECgYBJ2BupVY+fXp3Ap5GE8JlyTmoqY4nwi8Vv0aRdJhgppGIFn6QpfgRC4yFi+iDq6/wW3ZBCVD8tdZs3KxeHO4k19sPUrWFMzGxGSp4BD+yJQG0mIGaVaclG9ddqym5HQgLxvxXqBLISazK6V31I2FR13JQZcqHLW1mSEmedxoFjgQJBAMn0lssQnq+Q9PIzn/ngCDet5yiRKgw4Lvi/EqZVMCy1RJyqfvZTb0GYdN9M5A52oNYPu4WkvpcjARAPZY3QFisCQQDJsLrhjyo7mhgF4q9Ew/X+VfRKzKRrSmN1HkFM1yqrocqiribWqYOOg3HJSZRztM46eQfa/atoEePdrvVcumzdAkAq7EI0bJbdh7iX1aMVr6jvdJWhyKCgMjzBf5LOKWLwzaIEjjQn1Hnb/jQw1Z3O+SWb+YIImIgthTIPoMYbyvM7AkA6/7K7NqjxBHOQjS4eHIDCJJywoMpcBEexw+bwNmTqDgr7qZv84Vt1fef0LMz7R+Gn4y20Fs5kRA93Eq39sR8VAkEAo2HAcntFOnuUyUT5NZw9Todjidnn90rzqDLq11UbEjhABEDmQyigmjNt8cscgdaZzWXvVdnCijpMKFQfQ0lv5Q==";

	public static final String PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbTkUEkX/SJrZZojrf5hbch+U+hmjk/cBVAItQjvWa4nXYaTvN3vXhCiwaqJs4LbimYmNj6gpUQzQs8LvmTF8pkenH5KbjAnDswM1/R9GcrJTip+JHW0nTsyVOxc10KEQI8Q48hHYJDIYdaaJ+HC54212fbtjpesseAlm65bDn/QIDAQAB";

}
