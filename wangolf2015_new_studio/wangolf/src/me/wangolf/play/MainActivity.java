package me.wangolf.play;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import me.wangolf.ConstantValues;
import me.wangolf.bean.college.InfoDetailEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.GsonTools;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	// 自定义VideoView
	private FullScreenVideoView mVideo;
	@ViewInject(R.id.common_back)
	private Button common_back; // 后退
	@ViewInject(R.id.common_title)
	private TextView common_title;// 标题
	@ViewInject(R.id.common_bt)
	private TextView common_bt;// 分享
	// 头部View
	private View mTopView;
	@ViewInject(R.id.chzz_news_content_webview)
	private WebView mWebView;
	@ViewInject(R.id.common_bar)
	private RelativeLayout common_bar;// 标题
	@ViewInject(R.id.ll_web)
	private LinearLayout ll_web;// 内容
	@ViewInject(R.id.upper_layout)
	private RelativeLayout upper_layout;
	// 底部View
	private View mBottomView;
	// 视频播放拖动条
	private SeekBar mSeekBar;
	private ImageView mPlay;
	private TextView mPlayTime;
	private TextView mDurationTime;
	private String knowid;

	// 音频管理器
	private AudioManager mAudioManager;

	// 屏幕宽高
	private float width;
	private float height;

	// 视频播放时间
	private int playTime;

	private String videoUrl = "http://www.wangolf.me/public/video/0821-17【高球课堂】起扑击球指导.mp4";
	// 自动隐藏顶部和底部View的时间
	private static final int HIDE_TIME = 5000;

	// 声音调节Toast
	private VolumnController volumnController;

	// 原始屏幕亮度
	private int orginalLight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		volumnController = new VolumnController(this);
		mVideo = (FullScreenVideoView) findViewById(R.id.videoview);
		mPlayTime = (TextView) findViewById(R.id.play_time);
		mDurationTime = (TextView) findViewById(R.id.total_time);
		mPlay = (ImageView) findViewById(R.id.play_btn);
		mSeekBar = (SeekBar) findViewById(R.id.seekbar);
		mTopView = findViewById(R.id.top_layout);
		mBottomView = findViewById(R.id.bottom_layout);

		mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		width = DensityUtil.getWidthInPx(this);
		height = DensityUtil.getHeightInPx(this);
		threshold = DensityUtil.dip2px(this, 18);
		orginalLight = LightnessController.getLightness(this);
		mPlay.setOnClickListener(this);
		mSeekBar.setOnSeekBarChangeListener(mSeekBarChangeListener);
		knowid = getIntent().getStringExtra("knowid");
		common_title.setText(ConstantValues.CONTENT_TITLE);
		// common_bt.setText(ConstantValues.SHARE);
		common_back.setVisibility(0);
		common_back.setOnClickListener(this);

		playVideo();
		getData();
		w = upper_layout.getLayoutParams().width;
		h = upper_layout.getLayoutParams().height;
		System.out.println(w + "***" + h);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			// height = DensityUtil.getWidthInPx(this);
			// width = DensityUtil.getHeightInPx(this);
			RelativeLayout.LayoutParams layoutParam = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			upper_layout.setLayoutParams(layoutParam);
			common_bar.setVisibility(8);
			ll_web.setVisibility(8);

		} else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

			RelativeLayout.LayoutParams layoutParam = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, h);
			upper_layout.setLayoutParams(layoutParam);
			common_bar.setVisibility(0);
			ll_web.setVisibility(0);
			//System.out.println(w + "***" + h);
			// width = DensityUtil.getWidthInPx(this);
			// height = DensityUtil.getHeightInPx(this);

		}
		super.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onPause() {
		super.onPause();
		LightnessController.setLightness(this, orginalLight);
	}

	private OnSeekBarChangeListener mSeekBarChangeListener = new OnSeekBarChangeListener() {

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			mHandler.postDelayed(hideRunnable, HIDE_TIME);
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			mHandler.removeCallbacks(hideRunnable);
		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			if (fromUser) {
				int time = progress * mVideo.getDuration() / 100;
				mVideo.seekTo(time);
			}
		}
	};

	private void backward(float delataX) {
		int current = mVideo.getCurrentPosition();
		int backwardTime = (int) (delataX / width * mVideo.getDuration());
		int currentTime = current - backwardTime;
		mVideo.seekTo(currentTime);
		mSeekBar.setProgress(currentTime * 100 / mVideo.getDuration());
		mPlayTime.setText(formatTime(currentTime));
	}

	private void forward(float delataX) {
		int current = mVideo.getCurrentPosition();
		int forwardTime = (int) (delataX / width * mVideo.getDuration());
		int currentTime = current + forwardTime;
		mVideo.seekTo(currentTime);
		mSeekBar.setProgress(currentTime * 100 / mVideo.getDuration());
		mPlayTime.setText(formatTime(currentTime));
	}

	private void volumeDown(float delatY) {
		int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		int current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		int down = (int) (delatY / height * max * 3);
		int volume = Math.max(current - down, 0);
		mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
		int transformatVolume = volume * 100 / max;
		volumnController.show(transformatVolume);
	}

	private void volumeUp(float delatY) {
		int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		int current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		int up = (int) ((delatY / height) * max * 3);
		int volume = Math.min(current + up, max);
		mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
		int transformatVolume = volume * 100 / max;
		volumnController.show(transformatVolume);
	}

	private void lightDown(float delatY) {
		int down = (int) (delatY / height * 255 * 3);
		int transformatLight = LightnessController.getLightness(this) - down;
		LightnessController.setLightness(this, transformatLight);
	}

	private void lightUp(float delatY) {
		int up = (int) (delatY / height * 255 * 3);
		int transformatLight = LightnessController.getLightness(this) + up;
		LightnessController.setLightness(this, transformatLight);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mHandler.removeMessages(0);
		mHandler.removeCallbacksAndMessages(null);
	}

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				if (mVideo.getCurrentPosition() > 0) {
					mPlayTime.setText(formatTime(mVideo.getCurrentPosition()));
					int progress = mVideo.getCurrentPosition() * 100 / mVideo.getDuration();
					mSeekBar.setProgress(progress);
					if (mVideo.getCurrentPosition() > mVideo.getDuration() - 100) {
						mPlayTime.setText("00:00");
						mSeekBar.setProgress(0);
					}
					mSeekBar.setSecondaryProgress(mVideo.getBufferPercentage());
				} else {
					mPlayTime.setText("00:00");
					mSeekBar.setProgress(0);
				}

				break;
			case 2:
				showOrHide();
				break;

			default:
				break;
			}
		}
	};

	private void playVideo() {
		mVideo.setVideoPath(videoUrl);
		mVideo.requestFocus();
		mVideo.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				mVideo.setVideoWidth(mp.getVideoWidth());
				mVideo.setVideoHeight(mp.getVideoHeight());

				mVideo.start();
				if (playTime != 0) {
					mVideo.seekTo(playTime);
				}

				mHandler.removeCallbacks(hideRunnable);
				mHandler.postDelayed(hideRunnable, HIDE_TIME);
				mDurationTime.setText(formatTime(mVideo.getDuration()));
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {

					@Override
					public void run() {
						mHandler.sendEmptyMessage(1);
					}
				}, 0, 1000);
			}
		});
		mVideo.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				mPlay.setImageResource(R.drawable.video_btn_down);
				mPlayTime.setText("00:00");
				mSeekBar.setProgress(0);
			}
		});
		mVideo.setOnTouchListener(mTouchListener);
	}

	private Runnable hideRunnable = new Runnable() {

		@Override
		public void run() {
			showOrHide();
		}
	};

	@SuppressLint("SimpleDateFormat")
	private String formatTime(long time) {
		DateFormat formatter = new SimpleDateFormat("mm:ss");
		return formatter.format(new Date(time));
	}

	private float mLastMotionX;
	private float mLastMotionY;
	private int startX;
	private int startY;
	private int threshold;
	private boolean isClick = true;

	private OnTouchListener mTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			final float x = event.getX();
			final float y = event.getY();

			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mLastMotionX = x;
				mLastMotionY = y;
				startX = (int) x;
				startY = (int) y;
				break;
			case MotionEvent.ACTION_MOVE:
				float deltaX = x - mLastMotionX;
				float deltaY = y - mLastMotionY;
				float absDeltaX = Math.abs(deltaX);
				float absDeltaY = Math.abs(deltaY);
				// 声音调节标识
				boolean isAdjustAudio = false;
				if (absDeltaX > threshold && absDeltaY > threshold) {
					if (absDeltaX < absDeltaY) {
						isAdjustAudio = true;
					} else {
						isAdjustAudio = false;
					}
				} else if (absDeltaX < threshold && absDeltaY > threshold) {
					isAdjustAudio = true;
				} else if (absDeltaX > threshold && absDeltaY < threshold) {
					isAdjustAudio = false;
				} else {
					return true;
				}
				if (isAdjustAudio) {
					if (x < width / 2) {
						if (deltaY > 0) {
							lightDown(absDeltaY);
						} else if (deltaY < 0) {
							lightUp(absDeltaY);
						}
					} else {
						if (deltaY > 0) {
							volumeDown(absDeltaY);
						} else if (deltaY < 0) {
							volumeUp(absDeltaY);
						}
					}

				} else {
					if (deltaX > 0) {
						forward(absDeltaX);
					} else if (deltaX < 0) {
						backward(absDeltaX);
					}
				}
				mLastMotionX = x;
				mLastMotionY = y;
				break;
			case MotionEvent.ACTION_UP:
				if (Math.abs(x - startX) > threshold || Math.abs(y - startY) > threshold) {
					isClick = false;
				}
				mLastMotionX = 0;
				mLastMotionY = 0;
				startX = (int) 0;
				if (isClick) {
					showOrHide();
				}
				isClick = true;
				break;

			default:
				break;
			}
			return true;
		}

	};

	private int w;

	private int h;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.play_btn:
			if (mVideo.isPlaying()) {
				mVideo.pause();
				mPlay.setImageResource(R.drawable.video_btn_down);
			} else {
				mVideo.start();
				mPlay.setImageResource(R.drawable.video_btn_on);
			}
			break;
		case R.id.common_back:
			finish();
			break;
		default:
			break;
		}
	}

	private void showOrHide() {
		if (mTopView.getVisibility() == View.VISIBLE) {
			mTopView.clearAnimation();
			Animation animation = AnimationUtils.loadAnimation(this, R.anim.option_leave_from_top);
			animation.setAnimationListener(new AnimationImp() {
				@Override
				public void onAnimationEnd(Animation animation) {
					super.onAnimationEnd(animation);
					mTopView.setVisibility(View.GONE);
				}
			});
			mTopView.startAnimation(animation);

			mBottomView.clearAnimation();
			Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.option_leave_from_bottom);
			animation1.setAnimationListener(new AnimationImp() {
				@Override
				public void onAnimationEnd(Animation animation) {
					super.onAnimationEnd(animation);
					mBottomView.setVisibility(View.GONE);
				}
			});
			mBottomView.startAnimation(animation1);
		} else {
			mTopView.setVisibility(View.VISIBLE);
			mTopView.clearAnimation();
			Animation animation = AnimationUtils.loadAnimation(this, R.anim.option_entry_from_top);
			mTopView.startAnimation(animation);

			mBottomView.setVisibility(View.VISIBLE);
			mBottomView.clearAnimation();
			Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.option_entry_from_bottom);
			mBottomView.startAnimation(animation1);
			mHandler.removeCallbacks(hideRunnable);
			mHandler.postDelayed(hideRunnable, HIDE_TIME);
		}
	}

	private class AnimationImp implements AnimationListener {

		@Override
		public void onAnimationEnd(Animation animation) {

		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}

		@Override
		public void onAnimationStart(Animation animation) {
		}

	}

	public void getData() {
		try {
			ServiceFactory.getCollegeEngineInstatice().getInfoDetail(knowid, new IOAuthCallBack() {

				@Override
				public void getIOAuthCallBack(String result) {
					if (result.equals(ConstantValues.FAILURE)) {
						Toast.makeText(getApplicationContext(), "请确保已联网", 0).show();
					} else {
						InfoDetailEntity bean = GsonTools.changeGsonToBean(result, InfoDetailEntity.class);
						ArrayList<InfoDetailEntity> info = bean.getData();
						loadNewsData(info.get(0).getContent());
						videoUrl = info.get(0).getVideo();
						// mEventHandler.sendEmptyMessage(EVENT_PLAY);
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadNewsData(String info) {
		WebSettings setting = mWebView.getSettings();
		// setting.setUseWideViewPort(true);
		setting.setJavaScriptEnabled(true);
		// setting.setSupportZoom(true);
		// setting.setBuiltInZoomControls(true);
		// setting.setJavaScriptCanOpenWindowsAutomatically(true);
		// setting.setLoadWithOverviewMode(true);
		// chzz_news_no_data_image.setVisibility(8);
		mWebView.loadData(info, "text/html; charset=UTF-8", null);

	}
}
