package com.wisn.pm.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    private List<Data> data;

    private BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewedittext);
        listView = (ListView) findViewById(R.id.list);
        data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add(new Data("data" + i,i));
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
                vh.textView.setText(data.get(i).title);
                vh.editText.setText(String.valueOf(data.get(i).pos));
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
                    finalVh.editText.clearFocus();

                }
                final ViewHolder finalVh1 = vh;
                vh.editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        String count =editable.toString().trim();
                        // log.e(TAG, "修改后：mTouchItemPosition" + mTouchItemPosition + "position" + position);
                        int tag = (int) finalVh1.editText.getTag();
                        if (count == null || count.length() == 0||tag!=i)
                            return;
                        int inputCount = 0;
                        try {
                            inputCount = Integer.parseInt(count);
                            finalVh1.textView.setText(editable.toString());
                            data.get(i).pos=inputCount;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
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
    public class  Data{
        public  String  title;
        public  int  pos;

        public Data(String title, int pos) {
            this.title = title;
            this.pos = pos;
        }
    }
}
