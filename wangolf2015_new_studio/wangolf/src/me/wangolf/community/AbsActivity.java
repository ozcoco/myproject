package me.wangolf.community;

import com.meigao.mgolf.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.umeng.analytics.MobclickAgent;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class AbsActivity extends FragmentActivity {
    protected AbsActivity mActThis = null;
    protected ImageLoader loader;
    protected DisplayImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActThis = this;

        loader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder().imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565)
                .showImageOnLoading(R.drawable.pic_loading).cacheInMemory(true).cacheOnDisc(true).build();

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        MobclickAgent.onResume(this); // 统计时长
    }

}
