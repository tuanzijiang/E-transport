package com.example.dell2.e_transport;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.Toast;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import application.E_Trans_Application;
import collector.BaseActivity;
import collector.CommonRequest;
import collector.CommonResponse;
import collector.Constant;
import collector.HttpPostTask;
import collector.LoadingDialogUtil;
import collector.PictureUtils;
import collector.ResponseHandler;
import entity.User;


/**
 * Created by wangyan on 2017/6/6.
 */

public class UserAvatarActivity extends BaseActivity implements View.OnClickListener {
    private ImageView header_front_1;
    private ImageView header_back_1;
    private ImageView avatar;
    private TextView title_name;
    private Button choosePhoto;
    private Button takePhoto;
    private Button cancel;
    private Dialog dialog;
    private View inflate;
    private File mPhotoFile;
    private static final int REQUEST_PHOTO = 2;
    private E_Trans_Application app;
    private boolean isAlbum = false;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_avatar);
        init();
    }

    private void init() {
        /*实例化*/
        header_front_1 = (ImageView) findViewById(R.id.header_front_1);
        header_back_1 = (ImageView) findViewById(R.id.header_back_1);
        title_name = (TextView) findViewById(R.id.title_name);
        avatar = (ImageView) findViewById(R.id.iv_personal_icon);
        /*初始化标题*/
        header_front_1.setImageResource(R.drawable.last_white);
        header_back_1.setImageResource(R.drawable.more);
        title_name.setText("头像");
         /*响应事件*/
        header_front_1.setOnClickListener(this);
        header_back_1.setOnClickListener(this);
        /*信息初始化*/
        initInfo();
    }

    public void show(View view) {
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        inflate = LayoutInflater.from(this).inflate(R.layout.activity_photo_popup, null);
        choosePhoto = (Button) inflate.findViewById(R.id.choosePhoto);
        takePhoto = (Button) inflate.findViewById(R.id.takePhoto);
        cancel = (Button) inflate.findViewById(R.id.btn_cancel);
        choosePhoto.setOnClickListener(this);
        takePhoto.setOnClickListener(this);
        cancel.setOnClickListener(this);
        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.header_front_1:
                BitmapFactory.Options options = new BitmapFactory.Options();
                if (isAlbum)
                    options.inSampleSize = 1;
                else
                    options.inSampleSize = 5;
                Bitmap bitmap = BitmapFactory.decodeFile(mPhotoFile.getPath(), options);
                String string = null;
                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 30, bStream);
                byte[] bytes = bStream.toByteArray();
                string = Base64.encodeToString(bytes, Base64.DEFAULT);
                setAvatar(string);
                break;
            case R.id.header_back_1:
                show(view);
                break;
            case R.id.takePhoto:
                // Toast.makeText(this,"点击了拍照",Toast.LENGTH_SHORT).show();
                isAlbum = false;
                final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mPhotoFile = new File(getExternalFilesDir("avatar").getPath() + "/avatar.jpg");
                if (mPhotoFile == null) {
                    Log.d("Fa", "filefail");
                }
                if (!mPhotoFile.exists()) {
                    Log.e("TAG", "Directory not created");
                } else {
                    try {
                        mPhotoFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.e("TAG", "Directory created");
                }
                boolean canTakePhoto = mPhotoFile != null && captureImage.resolveActivity(getPackageManager()) != null;
                if (canTakePhoto) {
                    Uri uri = Uri.fromFile(mPhotoFile);
                    captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(captureImage, REQUEST_PHOTO);
                }

                break;
            case R.id.choosePhoto:
                isAlbum=true;
                mPhotoFile = new File(getExternalFilesDir("avatar").getPath() + "/avatar.jpg");
                if (mPhotoFile == null) {
                    Log.d("Fa", "filefail");
                }
                if (!mPhotoFile.exists()) {
                    Log.e("TAG", "Directory not created");
                } else {
                    try {
                        mPhotoFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.e("TAG", "Directory created");
                }
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 2);
                break;
            case R.id.btn_cancel:
                dialog.dismiss();
            default:
                break;
        }
    }

    /**
     * （待填）
     * 设置头像加载
     */
    public void initInfo() {
        avatar.setImageBitmap(PictureUtils.getBitmap(Constant.avatarPath, 1000, 1000));
    }

    private void setAvatar(String avatar) {
        CommonRequest request = new CommonRequest();
        request.setRequestCode("avatar");
        request.addRequestParam("param", avatar);
        app = (E_Trans_Application) getApplication();
        User user = app.getUser();
        String userName = user.getUserEmail();
        String phoneNumber = user.getUserTel();
        request.addRequestParam("userName", userName);
        request.addRequestParam("phoneNumber", phoneNumber);
        HttpPostTask myTask = sendHttpPostRequest(Constant.SETTING_URL, request, new ResponseHandler() {
            @Override
            public CommonResponse success(CommonResponse response) {
                Log.e("SETTING", "S");
                LoadingDialogUtil.cancelLoading();
                Toast.makeText(UserAvatarActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                finish();
                return response;
            }

            @Override
            public CommonResponse fail(String failCode, String failMsg) {
                LoadingDialogUtil.cancelLoading();
                Toast.makeText(UserAvatarActivity.this, "设置失败", Toast.LENGTH_SHORT).show();
                finish();
                return null;
            }
        }, true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor actualimagecursor = managedQuery(uri, proj, null, null, null);
            int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            String img_path = actualimagecursor.getString(actual_image_column_index);
            BitmapFactory.Options options = new BitmapFactory.Options();
            File tempFile = new File(img_path);
            try {
                FileInputStream inputStream = new FileInputStream(tempFile);
                int size = -1;
                try {
                    size = inputStream.available();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (size < 1024 * 100) {
                    options.inSampleSize = 1;
                } else {
                    options.inSampleSize = 5;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Log.d("Uri", uri.getPath());
            Bitmap bitmap = BitmapFactory.decodeFile(img_path, options);
            String string = null;
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 30, bStream);
            byte[] bytes = bStream.toByteArray();
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(mPhotoFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (out != null) {
                try {
                    out.write(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
