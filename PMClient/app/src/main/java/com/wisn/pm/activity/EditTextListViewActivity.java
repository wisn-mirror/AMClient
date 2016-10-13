package com.wisn.pm.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.wisn.pm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Create Date:</b> 2016/10/13<br>
 * <b>Author:</b> Wisn(吴贻顺)<br>
 * <b>Description:</b>
 * <p>
 * <br>
 */

public class EditTextListViewActivity extends BaseActivity {

    private ListView listView;
    private List<String> data;

    private BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewedittext);
        listView = (ListView) findViewById(R.id.list);
        data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add("data" + i);
        }
        adapter = new BaseAdapter() {


            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public Object getItem(int i) {
                return data.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }
            private int mTouchItemPosition = -1;
            @Override
            public View getView(final int i, View view, ViewGroup viewGroup) {
                ViewHolder vh = null;
                if (view != null) {
                    vh = (ViewHolder) view.getTag();
                } else {
                    view = View.inflate(EditTextListViewActivity.this, R.layout.item_edittext, null);
                    vh = new ViewHolder(view);
                    view.setTag(vh);
                }
                vh.editText.setTag(i);
                vh.textView.setText(data.get(i));
                final ViewHolder finalVh = vh;
                vh.editText.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent event) {
                        //注意，此处必须使用getTag的方式，不能将position定义为final，写成mTouchItemPosition = position
                        mTouchItemPosition = (Integer) view.getTag();
                        return false;
                    }
                });
               /* vh.editText.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },100);*/
                if (mTouchItemPosition == i) {
                    finalVh.editText.requestFocus();
                        finalVh.editText.setCursorVisible(true);
                    if (finalVh.editText.getText() != null && finalVh.editText.getText().toString().trim().length() > 0) {
                        finalVh.editText.setSelection(finalVh.editText.getText().length());
                    }
                } else {
                   // finalVh.editText.clearFocus();

                }
                return view;
            }
        };
        listView.setAdapter(adapter);
    }
    public static class ViewHolder {
        public View rootView;
        public TextView textView;
        public EditText editText;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.textView = (TextView) rootView.findViewById(R.id.textView);
            this.editText = (EditText) rootView.findViewById(R.id.editText);
        }

    }
}
