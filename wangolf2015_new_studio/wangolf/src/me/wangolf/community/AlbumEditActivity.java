package me.wangolf.community;

/**
 * 相机，相册选择  发表
 */

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import me.wangolf.ConstantValues;
import me.wangolf.adapter.CommunitySendTagAdapter;
import me.wangolf.adapter.GridImageAdapter;
import me.wangolf.bean.InfoEntity;
import me.wangolf.bean.community.CommunityTagEntity;
import me.wangolf.bean.community.ImgInfoEntity;
import me.wangolf.factory.ServiceFactory;
import me.wangolf.service.IOAuthCallBack;
import me.wangolf.utils.CheckUtils;
import me.wangolf.utils.CommonDefine;
import me.wangolf.utils.DialogUtil;
import me.wangolf.utils.FileUtils;
import me.wangolf.utils.GsonTools;
import me.wangolf.utils.ImageUtils;
import me.wangolf.utils.ToastUtils;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.meigao.mgolf.R;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AlbumEditActivity extends AbsActivity implements OnClickListener, TextWatcher {
    @ViewInject(R.id.common_back)
    private TextView common_back;
    @ViewInject(R.id.common_title)
    private TextView common_title;
    @ViewInject(R.id.common_bt)
    private TextView common_bt;
    @ViewInject(R.id.c_d_img)
    private RelativeLayout mBut_img;
    @ViewInject(R.id.c_s_title)
    private EditText mEtitle;
    @ViewInject(R.id.c_s_content)
    private EditText mEcontent;
    @ViewInject(R.id.c_d_ed)
    private TextView mAddress;
    @ViewInject(R.id.lsit_tag)
    private RelativeLayout mTag;//标签
    @ViewInject(R.id.tv_tag)
    private TextView mtv_Tag;
    private EditText mETGroupPhotoContent;
    private String locationMsg;
    String objectKey = null;
    private GridView gridView;
    private ArrayList<String> dataList;
    private GridImageAdapter gridImageAdapter;
    private ArrayList<String> tDataList;
    private String photoContent;
    private String intranetID;
    private String cameraImagePath = "";
    private int finishCount = -1;
    private StringBuilder builder;
    private Uri uri;
    private String user_id; // 用户ID
    private String content;// 帖内容
    private String title;// 帖标题
    private String address;// 发帖地址
    private StringBuffer img;
    private String img_list;// 图片地址列表
    private int mFlag_img;// 图片上传标记
    private Dialog dialog;
    private String tags_name;//标签名称(若多选则以空格隔开,卢tags_name=高尔夫 活动)
    private CommunitySendTagAdapter tagAdapter;
    private String tags_id;//标签ID(若多选则以半角逗号隔开，如tags_id=1,3) ;//标签ID(若多选则以半角逗号隔开，如tags_id=1,3)
    private String longitude;
    private String latitude;
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ac_album_edit);
        ViewUtils.inject(this);
        mBut_img.setOnClickListener(this);
        dataList = new ArrayList<String>();
        init();
        initListener();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            path = extras.getString("path");
            tDataList = (ArrayList<String>) extras.getSerializable("dataList");
            editContent = extras.getString("editContent");
        }
        if (path != null) {
            dataList.add(path);
            if (dataList.size() < 10) {
                dataList.add("camera_default");
            }
            gridImageAdapter.notifyDataSetChanged();
        }
        if (tDataList != null) {
            for (int i = 0; i < tDataList.size(); i++) {
                String string = tDataList.get(i);
                dataList.add(string);
            }
            if (dataList.size() < 10) {
                dataList.add("camera_default");
            }
            gridImageAdapter.notifyDataSetChanged();
        }

    }

    // 初始化数据
    private void init() {
        dialog = DialogUtil.getDialog(this);
        common_title.setText("发帖");
        common_bt.setText("发表");
        common_back.setVisibility(View.VISIBLE);
        common_bt.setVisibility(View.VISIBLE);
        common_back.setOnClickListener(this);
        common_bt.setOnClickListener(this);
        mAddress.setOnClickListener(this);
        mEtitle.addTextChangedListener(this);
        mTag.setOnClickListener(this);
        gridView = (GridView) findViewById(R.id.gridview_image);
        // dataList.add("camera_default");
        if (gridImageAdapter == null) {
            gridImageAdapter = new GridImageAdapter(this, dataList, loader, options);
        } else {
            gridImageAdapter.notifyDataSetChanged();
        }
        gridView.setAdapter(gridImageAdapter);
        if (tagAdapter == null) {
            tagAdapter = new CommunitySendTagAdapter(this);
        } else {
            tagAdapter.notifyDataSetChanged();
        }

    }


    // *发帖
    public void sendData() {
        try {
            ServiceFactory.getCommunityEngineInstatice().sendPosts(user_id, content, title, address,longitude,latitude, tags_id, tags_name, img_list, new IOAuthCallBack() {

                @Override
                public void getIOAuthCallBack(String result) {
                    if (result.equals(ConstantValues.FAILURE)) {
                        ToastUtils.showInfo(AlbumEditActivity.this, ConstantValues.NONETWORK);
                    } else {
                        InfoEntity bean = GsonTools.changeGsonToBean(result, InfoEntity.class);
                        if ("1".equals(bean.getStatus())) {
                            ToastUtils.showInfo(AlbumEditActivity.this, bean.getInfo());
                            // 更新标记
                            ConstantValues.isSendPosts = true;
                            setResult(105);
                            finish();
                        } else {
                            ToastUtils.showInfo(AlbumEditActivity.this, bean.getInfo());
                        }
                        dialog.cancel();
                    }
                }
            });
        } catch (Exception e) {
            dialog.cancel();
            e.printStackTrace();
        }
    }

    // ***图片上传
    public void loadPostsImg(ArrayList<String> avatar_file, int i) {
        ArrayList<String> images = ImageUtils.compressImages(avatar_file);//压缩图片
        try {
            ServiceFactory.getCommunityEngineInstatice().loadPostsImg(images, i, new IOAuthCallBack() {

                @Override
                public void getIOAuthCallBack(String result) {

                    if (result.equals(ConstantValues.FAILURE)) {
                        ToastUtils.showInfo(AlbumEditActivity.this, ConstantValues.NONETWORK);
                    } else {

                        ImgInfoEntity bean = GsonTools.changeGsonToBean(result, ImgInfoEntity.class);
                        if ("1".equals(bean.getStatus())) {
                            img_list = bean.getData().get(0).getLogo();
                            FileUtils.clearImage();//上传完成 清空图片
                            sendData();// 发帖

                        } else {
                            ToastUtils.showInfo(AlbumEditActivity.this, bean.getInfo());
                        }
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // gridview图片查看
    private void initListener() {
        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String path = dataList.get(position);
                if (path.contains("default") && position == dataList.size() - 1 && dataList.size() - 1 != 10) {
                    Intent selectpir = new Intent(AlbumEditActivity.this, SelectPicPopupWindow.class);
                    selectpir.putStringArrayListExtra("dataList", getIntentArrayList(dataList));
                    startActivityForResult(selectpir, 100);
                } else {
                    Intent intent = new Intent(mActThis, ImageDelActivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("path", dataList.get(position));
                    startActivityForResult(intent, CommonDefine.DELETE_IMAGE);
                }
            }
        });
    }

    private OnClickListener mCancelListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            final Dialog dialog = new Dialog(mActThis, R.style.dialog);
            View inflate = View.inflate(mActThis, R.layout.dialog_del, null);
            TextView dialogTitle = (TextView) inflate.findViewById(R.id.dialog_title);
            dialogTitle.setText("放弃此次编辑？");
            TextView dialogCancel = (TextView) inflate.findViewById(R.id.del_cancel);
            dialogCancel.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            TextView dialogConfirm = (TextView) inflate.findViewById(R.id.confirm_del);
            dialogConfirm.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    AlbumEditActivity.this.finish();
                }
            });
            dialog.setContentView(inflate);
            dialog.show();
        }
    };

    private String editContent;
    private String path;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            final Dialog dialog = new Dialog(this, R.style.dialog);
            View inflate = View.inflate(this, R.layout.dialog_del, null);
            TextView dialogTitle = (TextView) inflate.findViewById(R.id.dialog_title);
            dialogTitle.setText("放弃此次编辑？");
            TextView dialogCancel = (TextView) inflate.findViewById(R.id.del_cancel);
            dialogCancel.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            TextView dialogConfirm = (TextView) inflate.findViewById(R.id.confirm_del);
            dialogConfirm.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            dialog.setContentView(inflate);
            dialog.show();
        }
        return super.onKeyDown(keyCode, event);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data == null) {
            return;
        }
        switch (resultCode) {
            // case CommonDefine.TAKE_PICTURE_FROM_CAMERA:
            case -1:
                gridView.setVisibility(View.VISIBLE);
                String cameraImagePath = data.getStringExtra("cameraImagePath");
                for (int i = 0; i < dataList.size(); i++) {
                    String path = dataList.get(i);

                    if (path.contains("default")) {
                        dataList.remove(dataList.size() - 1);
                    }
                }
                dataList.add(cameraImagePath);
                if (dataList.size() < 10) {
                    dataList.add("camera_default");
                }
                gridImageAdapter.notifyDataSetChanged();
                break;
            case CommonDefine.TAKE_PICTURE_FROM_GALLERY:
                dataList.clear();
                gridView.setVisibility(View.VISIBLE);
                Bundle bundle2 = data.getExtras();
                tDataList = (ArrayList<String>) bundle2.getSerializable("dataList");
                if (tDataList != null) {
                    for (int i = 0; i < tDataList.size(); i++) {
                        String string = tDataList.get(i);
                        dataList.add(string);
                    }
                    if (dataList.size() < 9) {
                        dataList.add("camera_default");
                    }
                    gridImageAdapter.setDataList(dataList);
                    gridImageAdapter.notifyDataSetChanged();
                }
                break;
            case CommonDefine.DELETE_IMAGE:
                int position = data.getIntExtra("position", -1);
                dataList.remove(position);
                if (dataList.size() < 10) {
                    dataList.add(dataList.size(), "camera_default");
                    for (int i = 0; i < dataList.size(); i++) {
                        String path = dataList.get(i);
                        if (path.contains("default")) {
                            dataList.remove(dataList.size() - 2);
                        }
                    }
                }
                if (dataList.size() <= 1) {
                    gridView.setVisibility(View.GONE);
                }
                gridImageAdapter.notifyDataSetChanged();
                break;
            case 1006:
                if (data != null) {
                    tags_id = data.getStringExtra("tags_id");
                    tags_name = data.getStringExtra("tags_name");
                    mtv_Tag.setText(tags_name);
                   // Log.i("wangolf", "当前为多选模式\n选中的条数："+tags_id+tags_name);//如果选中了前四条，打印：100，101，102，103;由前面的adapter getItemId()返回的
                }
                break;
            case 1005:
                mAddress.setText(data.getStringExtra("rengName"));
                longitude=data.getStringExtra("longitude");
                latitude=data.getStringExtra("latitude");
                break;
            default:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    // 选择相册，相机
    private void showSelectImageDialog() {
        final Dialog picAddDialog = new Dialog(mActThis, R.style.dialog);
        View picAddInflate = View.inflate(mActThis, R.layout.item_dialog_camera, null);
        TextView camera = (TextView) picAddInflate.findViewById(R.id.camera);
        camera.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {// 选择相机
                Intent cameraIntent = new Intent();
                cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.addCategory(Intent.CATEGORY_DEFAULT);
                // 根据文件地址创建文件
                File file = new File(CommonDefine.FILE_PATH);
                if (file.exists()) {
                    file.delete();
                }
                uri = Uri.fromFile(file);
                // 设置系统相机拍摄照片完成后图片文件的存放地址
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                // 开启系统拍照的Activity
                startActivityForResult(cameraIntent, CommonDefine.TAKE_PICTURE_FROM_CAMERA);
                picAddDialog.dismiss();
            }
        });
        TextView mapStroge = (TextView) picAddInflate.findViewById(R.id.mapstorage);
        mapStroge.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {// 选择图库
                Intent intent = new Intent(mActThis, AlbumActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("dataList", getIntentArrayList(dataList));
                bundle.putString("editContent", "");
                intent.putExtras(bundle);
                startActivityForResult(intent, CommonDefine.TAKE_PICTURE_FROM_GALLERY);
                picAddDialog.dismiss();
                // AlbumEditActivity.this.finish();
            }
        });
        TextView cancel = (TextView) picAddInflate.findViewById(R.id.cancel);
        cancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                picAddDialog.dismiss();
            }
        });
        picAddDialog.setContentView(picAddInflate);
        picAddDialog.show();

    }

    private ArrayList<String> getIntentArrayList(ArrayList<String> dataList) {

        ArrayList<String> tDataList = new ArrayList<String>();

        for (String s : dataList) {
            if (!s.contains("default")) {
                tDataList.add(s);
            }
        }
        return tDataList;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.c_d_img:
                Intent selectpir = new Intent(this, SelectPicPopupWindow.class);
                selectpir.putStringArrayListExtra("dataList", getIntentArrayList(dataList));
                startActivityForResult(selectpir, 100);
                break;
            case R.id.common_back:
                finish();
                break;
            case R.id.common_bt:
                toSendPost();
                break;
            case R.id.c_d_ed:
                Intent loactionadd= new Intent(this,CommunitySendPostsAddressActivity.class);
                startActivityForResult(loactionadd,1005);
                break;
            case R.id.lsit_tag:
                Intent tag = new Intent(this, CommunitySendPostsTagActivity.class);
                startActivityForResult(tag, 1006);
                break;
            default:
                break;
        }
    }

    // 发帖
    public void toSendPost() {
        address = mAddress.getText().toString();
        content = mEcontent.getText().toString();
        if (CheckUtils.checkEmpty(mEtitle.getText().toString().trim())) {
            ToastUtils.showInfo(this, "请填写标题");
            return;
        }
        if(CheckUtils.checkEmpty(address)){
            ToastUtils.showInfo(this, "请选择所在位置");
            return;
        }
        if(CheckUtils.checkEmpty(tags_name)){
            ToastUtils.showInfo(this, "请选择标签");
            return;
        }
        if(CheckUtils.checkEmpty(content)&dataList.size()==0){
            ToastUtils.showInfo(this, "帖子内容或图片不能为空");
            return;
        }

        user_id = ConstantValues.UID;
        title = mEtitle.getText().toString();


        if (address.equals("不显示所在位置")|address.equals("所在位置")) {
            address = "";
            longitude=ConstantValues.LONGITUDE;
            latitude =ConstantValues.LATITUDE;
        }
        dialog.show();
        // 上传
        if (dataList != null) {
            // mFlag_img = tDataList.size();
            // mFlag_img =dataList.size();
            img = new StringBuffer();
            loadPostsImg(dataList, dataList.size() - 1);
            // img_list = img.toString();
            // sendData();
        } else {
            sendData();
        }
    }

    //标是输入	@Override
    @Override
    public void afterTextChanged(Editable arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        if (mEtitle.getText().toString().trim().length() >= 34) {
            ToastUtils.showInfo(this, "标题不能超34个字！");
            return;
        }

    }


}
