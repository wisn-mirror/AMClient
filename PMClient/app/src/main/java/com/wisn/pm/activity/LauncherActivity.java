package com.wisn.pm.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.wisn.pm.ApkInfo;
import com.wisn.pm.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <b>Create Date:</b> 2016/9/21<br>
 * <b>Author:</b> Wisn(吴贻顺)<br>
 * <b>Description:</b>
 * 左面应用
 * <br>
 */

public class LauncherActivity extends Activity {

    private List<ResolveInfo> mApps;
    private List<ApkInfo>   ApkInfoList;
    GridView mGrid;
    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ResolveInfo info = mApps.get(position);

            //该应用的包名
            String pkg = info.activityInfo.packageName;
            //应用的主activity类
            String cls = info.activityInfo.name;

            ComponentName componet = new ComponentName(pkg, cls);

            Intent i = new Intent();
            i.setComponent(componet);
            startActivity(i);
        }

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadApps();
        setContentView(R.layout.activity_launcher);
        mGrid = (GridView) findViewById(R.id.apps_list);
        mGrid.setAdapter(new AppsAdapter());
        mGrid.setOnItemClickListener(listener);
    }


    private void loadApps() {
        PackageManager pm = getPackageManager();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mApps = getPackageManager().queryIntentActivities(mainIntent, 0);
        ApkInfoList=new ArrayList<>();
        Iterator<ResolveInfo> iterator = mApps.iterator();
        while(iterator.hasNext()){
            ResolveInfo next = iterator.next();
            ApkInfoList.add(new ApkInfo(next.activityInfo.loadIcon(pm),next.loadLabel(pm).toString()));
        }
    }

    public class AppsAdapter extends BaseAdapter {
        public AppsAdapter() {

        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh = null;
            if (convertView != null) {
                vh = (ViewHolder) convertView.getTag();
            } else {
                convertView = View.inflate(LauncherActivity.this, R.layout.item_launcher, null);
                vh = new ViewHolder(convertView);
                convertView.setTag(vh);
            }
            ApkInfo info = ApkInfoList.get(position);
            // vh.app_icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
            vh.app_icon.setImageDrawable(info.drawable);
            vh.app_name.setText(String.valueOf(info.apkName));
            return convertView;
        }

        public final int getCount() {
            return ApkInfoList.size();
        }

        public final Object getItem(int position) {
            return ApkInfoList.get(position);
        }

        public final long getItemId(int position) {
            return position;
        }
    }

    private class ViewHolder {
        ImageView app_icon;
        TextView app_name;

        public ViewHolder(View convertView) {
            app_icon = (ImageView) convertView.findViewById(R.id.app_icon);
            app_name = (TextView) convertView.findViewById(R.id.app_name);
        }
    }
}
