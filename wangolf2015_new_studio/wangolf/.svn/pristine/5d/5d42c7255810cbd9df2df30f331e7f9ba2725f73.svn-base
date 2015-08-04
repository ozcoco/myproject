package me.wangolf.community;

import java.util.ArrayList;
import java.util.HashMap;

import me.wangolf.adapter.AlbumGridViewAdapter;
import me.wangolf.utils.CommonDefine;
import me.wangolf.utils.ToastUtils;
import me.wangolf.utils.Xutils;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.ImageColumns;
import android.provider.MediaStore.MediaColumns;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.meigao.mgolf.R;

public class AlbumActivity extends AbsActivity {

    private GridView gridView;
    private ArrayList<String> dataList;
    private HashMap<String, ImageView> hashMap = new HashMap<String, ImageView>();
    private ArrayList<String> selectedDataList = new ArrayList<String>();
    private ProgressBar progressBar;
    private AlbumGridViewAdapter gridImageAdapter;
    private LinearLayout selectedImageLayout;
    private TextView okButton;
    private HorizontalScrollView scrollview;
    private String editContent;
    private String imgLocation;
    private boolean booleanExtra;
    private int flag;
    private int imagesize;
    private static final String[] STORE_IMAGES = {
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.LATITUDE,
            MediaStore.Images.Media.LONGITUDE,
            MediaStore.Images.Media._ID
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_album);
        loadPic();

        // updateList();
        // booleanExtra = mActThis.getIntent().getBooleanExtra("album", false);
        updateList(getIntent());
    }

    @SuppressWarnings("unchecked")
    private void updateList(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            ArrayList<String> selList1 = (ArrayList<String>) bundle.getSerializable("dataList");
            ArrayList<String> pathList = bundle.getStringArrayList("listPath");
            ArrayList<String> selList2 = bundle.getStringArrayList("selectedDataList");
            editContent = bundle.getString("editContent");
            imgLocation = bundle.getString("name");
            flag = bundle.getInt("flag");
            imagesize = bundle.getInt("imagesize");

            if (pathList != null) {
                dataList = pathList;
            }

            if (selList2 != null) {
                selectedDataList = selList2;
            } else if (selList1 != null) {
                selectedDataList = selList1;
            }
            booleanExtra = bundle.getBoolean("album");
        }
        init();
        initListener();
    }

    @SuppressWarnings("deprecation")
    private void loadPic() {
        final String[] columns = {BaseColumns._ID, ImageColumns.MINI_THUMB_MAGIC, MediaColumns.DATA, ImageColumns.BUCKET_DISPLAY_NAME,
                ImageColumns.BUCKET_ID};
        final String orderBy = MediaColumns.DATE_ADDED;

        //Cursor imagecursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy + " DESC" );
        Cursor imagecursor = MediaStore.Images.Media.query(getContentResolver(),
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns);
        this.dataList = new ArrayList<String>();

        for (int i = 0; i < imagecursor.getCount(); i++) {
            imagecursor.moveToPosition(i);
            int dataColumnIndex = imagecursor.getColumnIndexOrThrow(MediaColumns.DATA);
            dataList.add(imagecursor.getString(dataColumnIndex));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1 && data != null) {
            updateList(data);
        }
    }

    private void init() {
        TextView cancelBT = (TextView) findViewById(R.id.cancel_button);
        cancelBT.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                AlbumActivity.this.finish();
            }
        });
        TextView chanceBT = (TextView) findViewById(R.id.chance_button);
        chanceBT.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActThis, AlbumChanceActivity.class);
                intent.putExtra("selectedDataList", selectedDataList);
                intent.putExtra("album", booleanExtra);
                startActivityForResult(intent, 0);
                overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
                // AlbumActivity.this.finish();
            }
        });
        TextView mImgLocation = (TextView) findViewById(R.id.imageLocation);
        if (!TextUtils.isEmpty(imgLocation)) {
            mImgLocation.setText(imgLocation);
        } else {
            mImgLocation.setText("相册");
        }
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        gridView = (GridView) findViewById(R.id.myGrid);

        gridImageAdapter = new AlbumGridViewAdapter(this, dataList, selectedDataList, loader, options);
        gridView.setAdapter(gridImageAdapter);
        selectedImageLayout = (LinearLayout) findViewById(R.id.selected_image_layout);
        okButton = (TextView) findViewById(R.id.ok_button);
        scrollview = (HorizontalScrollView) findViewById(R.id.scrollview);

        initSelectImage();
    }

    private void initSelectImage() {
        if (selectedDataList == null)
            return;
        selectedImageLayout.removeAllViews();
        for (final String path : selectedDataList) {
            ImageView imageView = (ImageView) LayoutInflater.from(AlbumActivity.this).inflate(R.layout.choose_imageview, selectedImageLayout, false);
            selectedImageLayout.addView(imageView);
            hashMap.put(path, imageView);
            // DisplayImageOptions options = new
            // DisplayImageOptions.Builder().showImageOnLoading(R.drawable.pic_loading).cacheInMemory(true).cacheOnDisk(true).build();
           //==== loader.displayImage("file://" + path, imageView, options);
            Xutils.getBitmap(AlbumActivity.this,imageView,path);
            imageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    removePath(path);
                    gridImageAdapter.notifyDataSetChanged();
                }
            });
        }
        okButton.setText("完成(" + selectedDataList.size() + ")");
    }

    private void initListener() {

        gridImageAdapter.setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(final CheckBox toggleButton, int position, final String path, boolean isChecked) {
                if (flag == 1 && selectedDataList.size() >= 1) {
                    // 来自头像
                    toggleButton.setChecked(false);
                    if (!removePath(path)) {
                        Toast.makeText(AlbumActivity.this, "只能选择1张图片", Toast.LENGTH_SHORT).show();
                    }
                    return;
                } else if (flag == 0 && selectedDataList.size() >= (10 - imagesize)) {
                    // 来自相册
                    toggleButton.setChecked(false);
                    if (!removePath(path)) {
                        Toast.makeText(AlbumActivity.this, "只能选择" + (10 - imagesize) + "张图片", Toast.LENGTH_SHORT).show();
                    }
                    return;
                } else {
                    // 来自社区
                    if (selectedDataList.size() >= 10) {
                        toggleButton.setChecked(false);
                        if (!removePath(path)) {
                            Toast.makeText(AlbumActivity.this, "只能选择10张图片", Toast.LENGTH_SHORT).show();
                        }
                        return;
                    }
                }
                if (isChecked) {
                    if (!hashMap.containsKey(path)) {
                        ImageView imageView = (ImageView) LayoutInflater.from(AlbumActivity.this).inflate(R.layout.choose_imageview,
                                selectedImageLayout, false);
                        selectedImageLayout.addView(imageView);
                        imageView.postDelayed(new Runnable() {

                            @Override
                            public void run() {

                                int off = selectedImageLayout.getMeasuredWidth() - scrollview.getWidth();
                                if (off > 0) {
                                    scrollview.smoothScrollTo(off, 0);
                                }
                            }
                        }, 100);

                        hashMap.put(path, imageView);
                        selectedDataList.add(path);
                        // DisplayImageOptions options = new
                        // DisplayImageOptions.Builder().showStubImage(R.drawable.group_item_pic_bg).cacheInMemory(true).cacheOnDisc(true).build();
                       //=== loader.displayImage("file://" + path, imageView, options);
                        Xutils.getBitmap(AlbumActivity.this,imageView,path);
                        imageView.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                toggleButton.setChecked(false);
                                removePath(path);
                            }
                        });
                        okButton.setText("完成(" + selectedDataList.size() + ")");
                    }
                } else {
                    removePath(path);
                }

            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent;

                if(selectedDataList.size()<=0){
                    ToastUtils.showInfo(AlbumActivity.this,"选择不能为空");
                    return;
                }
                if (booleanExtra) {
                    intent = new Intent();
                    intent.putExtra("datalist", selectedDataList);
                    setResult(RESULT_OK, intent);
                } else {
                    // intent = new Intent(mActThis, AlbumEditActivity.class);
                    intent = new Intent();
                    // Bundle bundle = new Bundle();
                    // bundle.putStringArrayList("dataList", selectedDataList);
                    // bundle.putString("editContent", editContent);
                    // intent.putExtras(bundle);

                    intent.putStringArrayListExtra("dataList", selectedDataList);
                    setResult(CommonDefine.TAKE_PICTURE_FROM_GALLERY, intent);

                    // startActivity(intent);
                }

                System.gc();
                mActThis.finish();
            }
        });
    }

    private boolean removePath(String path) {
        if (hashMap.containsKey(path)) {
            selectedImageLayout.removeView(hashMap.get(path));
            hashMap.remove(path);
            removeOneData(selectedDataList, path);
            okButton.setText("完成(" + selectedDataList.size() + ")");
            return true;
        } else {
            return false;
        }
    }

    private void removeOneData(ArrayList<String> arrayList, String s) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).equals(s)) {
                arrayList.remove(i);
                return;
            }
        }
    }

}
