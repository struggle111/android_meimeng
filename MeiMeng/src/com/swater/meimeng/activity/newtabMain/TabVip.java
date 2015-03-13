package com.swater.meimeng.activity.newtabMain;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import com.meimeng.app.R;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;
import com.swater.meimeng.activity.user.UserGallery;
import com.swater.meimeng.activity.user.UserSetHome;
import com.swater.meimeng.commbase.DiagPopView;
import com.swater.meimeng.commbase.MeiMengApp;
import com.swater.meimeng.commbase.TabBaseTemplate;
import com.swater.meimeng.commbase.DiagPopView.Diag_ok_no;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class TabVip extends TabBaseTemplate implements Diag_ok_no {

    private ImageView imageView1;

    private ImageView imageView2;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        t_context = this;
        setContentView(R.layout.fragment_vip);
        TempRun();
    }

    @Override
    public void iniView() {
        bindMenuClick();
        showTitle("尊贵服务");
        findViewById(R.id.btn_vip_call)
                .setOnClickListener(this);

        imageView1 = (ImageView) findViewById(R.id.fragment_vip_image_view1);

        imageView2 = (ImageView) findViewById(R.id.fragment_vip_image_view2);

//        Bitmap bitmap =  convertToBitmap();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.meimeng_service);
        int bitmapHeight = bitmap.getHeight();
        int bitmapWidth = bitmap.getWidth();

        Bitmap bitmap1 = CutBitmap(bitmap, 0, bitmapHeight / 2, bitmapWidth);
        imageView1.setImageBitmap(bitmap1);

        Bitmap bitmap2 = CutBitmap(bitmap, bitmapHeight / 2, bitmapHeight / 2, bitmapWidth);
        imageView2.setImageBitmap(bitmap2);

        if (bitmap != null && !bitmap.isRecycled())
         {
            bitmap.recycle();
            bitmap = null;
         }
    }

    public Bitmap convertToBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;

        options.inSampleSize = 1 * 1024 * 1024;

        options.inJustDecodeBounds = false;

        options.inDither = false;

        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        options.inJustDecodeBounds = false;

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.meimeng_service,options);

        return bitmap;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    /**
     * 剪切图片
     *
     * @param bitmap
     * @param startY
     * @param numY
     * @param width
     * @return
     */
    private Bitmap CutBitmap(Bitmap bitmap, int startY, int numY, int width) {
        if (bitmap == null) {
            return null;
        }

        Bitmap b = Bitmap.createBitmap(bitmap, 0, startY, width, numY, null, false);


        return b;
    }



    @Override
    public void bindClick() {

    }

    @Override
    public void onHeader_ItemClick(View v) {
        judgeTypeMyfocus(v, TabVip.this);
        switch (v.getId()) {

            case R.id.gallery_imv:
            case R.id.lay_person: {
                if (MeiMengApp.isVisitor){
                    MeiMengApp.setVisitorDialogView(TabVip.this);
                }else {
                    jumpOtherActivity(UserSetHome.class);
                }
            }

            break;
            // case R.id.gallery_imv: {
            // jumpOtherActivity(UserGallery.class);
            //
            // }
            // break;

            default:
                break;
        }
    }

    DiagPopView diagpopview = null;

    void popCall() {
        if (diagpopview == null) {
            diagpopview = new DiagPopView(t_context, "拨打服务热线" + "400-8783-520");
            diagpopview.setLeftMsg("确定");
            diagpopview.setOnHeaderItemClick(this);
        }
        diagpopview.showAsDropDown(findViewById(R.id.fragment_vip_image_view1));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_img_btn:
            case R.id.home_left_btn: {
                this.LeftClickPop(findViewById(R.id.topNav));
            }

            break;
            case R.id.btn_vip_call: {
                popCall();
            }
            break;

            default:
                break;
        }

    }

    void callTel() {

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
                + "4008783520"));
        TabVip.this.startActivity(intent);// 内部类
    }

    @Override
    public void btn_ok_no(View v) {
        if (diagpopview != null) {
            diagpopview.dismiss();
        }
        if (v.getId() == R.id.app_yes) {
            callTel();

        }

    }

}
