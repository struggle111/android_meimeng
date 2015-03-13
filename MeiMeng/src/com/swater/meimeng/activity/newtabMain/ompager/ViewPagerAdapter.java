package com.swater.meimeng.activity.newtabMain.ompager;


import java.util.HashMap;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.swater.meimeng.activity.adapterGeneral.vo.UserSearchVo;
import com.swater.meimeng.commbase.MeiMengApp;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * @author frankiewei
 * 相册的适配器.
 */
public class ViewPagerAdapter extends PagerAdapter {

	/**
	 * 上下文
	 */
	private Context mContext;
	
	private LinkedList<UserSearchVo> mJsonArray;
//	private JSONArray mJsonArray;
	
	/**
	 * Hashmap保存相片的位置以及ItemView.
	 */
	private HashMap<Integer, ViewPagerItemView> mHashMap;
	MeiMengApp app;
	
	public ViewPagerAdapter(MeiMengApp app,Context context,LinkedList<UserSearchVo> arrays) {
		this.mContext = context;
		this.mJsonArray = arrays;
		this.app=app;
		mHashMap = new HashMap<Integer, ViewPagerItemView>();
	}
	
	//这里进行回收，当我们左右滑动的时候，会把早期的图片回收掉.
	@Override
	public void destroyItem(View container, int position, Object object) {
		ViewPagerItemView itemView = (ViewPagerItemView)object;
		itemView.release();
//		itemView.recycle();
	}
	
	@Override
	public void finishUpdate(View view) {

	}

	//这里返回相册有多少条,和BaseAdapter一样.
	@Override
	public int getCount() {
		return mJsonArray.size();
	}

	//这里就是初始化ViewPagerItemView.如果ViewPagerItemView已经存在,
	//重新reload，不存在new一个并且填充数据.
	@Override
	public Object instantiateItem(View container, int position) {	
		ViewPagerItemView itemView;
		if(mHashMap.containsKey(position)){
			itemView = mHashMap.get(position);
//			itemView.reload();
		}else{
			itemView = new ViewPagerItemView(mContext,app);
			try {
				UserSearchVo dataObj = (UserSearchVo) mJsonArray.get(position);
				itemView.setData(dataObj,this.mContext);
			} catch (Exception e) {
				e.printStackTrace();
			}
			mHashMap.put(position, itemView);
			((ViewPager) container).addView(itemView);
		}
//		Log.d("-----可使用内存--KB--》》-"+Runtime.getRuntime().freeMemory()/1000+"---KB--", "---kb--");
//		Log.d("-----可使用内存-MB---》》-"+Runtime.getRuntime().freeMemory()/1000/1000+"MB", "---kb--");
		
		return itemView;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {
		
	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(View view) {

	}
}
