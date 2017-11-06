package lvtv.chemeneon.com.mylistview;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView ls;
    private ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ls = (ListView) findViewById(R.id.lv);
        for (int i = 0; i < 10; i++) {
            data.add("data" + i);
        }
        ls.setAdapter(new MyAdapter(data));
        ls.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getApplication(), "Item" + position, Toast.LENGTH_SHORT).show();

    }

    private class MyAdapter extends BaseAdapter {
        private ArrayList<String> mData;
        public boolean isClick = false;
        public View myconvertView;

        public MyAdapter(ArrayList<String> data) {
            this.mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            isClick = false;
            MyHolder myHolder = null;
            myHolder = new MyHolder();
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.my_lvitem, null);
                myHolder.tv = (TextView) convertView.findViewById(R.id.tv);
                myHolder.tvf = (TextViewFixTouchConsume) convertView.findViewById(R.id.tvf);
                convertView.setTag(myHolder);
            } else {
                myHolder = (MyHolder) convertView.getTag();
            }
//            myHolder.tv.setText("Hello world!");

            /**
             * 本文所提的另一种方案：（思想也可用于RecyclerView）
             */

//            StringBuilder sb = new StringBuilder();
//            sb.append("举个栗子").append("(300003)").append("你好，童话镇 - 陈一发儿 ").toString();
//
//            SpannableStringBuilder ssb = new SpannableStringBuilder(sb);
//            ssb.setSpan(new ClickableSpan() {
//                @Override
//                public void onClick(View widget) {
//                    isClick = true;
//                    Toast.makeText(getApplication(), "跳转到详情页...", Toast.LENGTH_SHORT).show();
////                    Intent intent = new Intent(MainActivity.this, NewActivity.class);
////                    startActivity(intent);
//
//                }
//
//                @Override
//                public void updateDrawState(TextPaint ds) {
//                    ds.setColor(Color.RED);
//                    ds.setUnderlineText(true);
//                }
//            }, 0, ("举个栗子" + "(300003)").length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
//
//            myHolder.tv.setText(ssb);
//            myHolder.tv.setMovementMethod(LinkMovementMethod.getInstance());
//
//            myHolder.tv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (!isClick) {
//                        onItemClick(null, v, position, 0);
//                    } else {
//                        isClick = false;
//                    }
//                }
//            });


            /**
             * stackoverflow的解决方案1
             */
            StringBuilder sb1 = new StringBuilder();
            sb1.append("stackoverflow的解决方案：").append("https://stackoverflow.com/questions/8558732/listview-textview-with-linkmovementmethod-makes-list-item-unclickable ").toString();

            SpannableStringBuilder ssb1 = new SpannableStringBuilder(sb1);
            ssb1.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    Toast.makeText(getApplication(), "跳转到新闻页...", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setColor(Color.BLUE);
                    ds.setUnderlineText(true);
                }
            }, 0, ("stackoverflow的解决方案：").length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

            myHolder.tvf.setText(ssb1);
            myHolder.tvf.setMovementMethod(TextViewFixTouchConsume.LocalLinkMovementMethod.getInstance());

            /**
             * stackoverflow的解决方案2
             */
//            myHolder.tvf.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    boolean ret = false;
//                    CharSequence text = ((TextView) v).getText();
//                    Spannable stext = Spannable.Factory.getInstance().newSpannable(text);
//                    TextView widget = (TextView) v;
//                    int action = event.getAction();
//
//                    if (action == MotionEvent.ACTION_UP ||
//                            action == MotionEvent.ACTION_DOWN) {
//                        int x = (int) event.getX();
//                        int y = (int) event.getY();
//
//                        x -= widget.getTotalPaddingLeft();
//                        y -= widget.getTotalPaddingTop();
//
//                        x += widget.getScrollX();
//                        y += widget.getScrollY();
//
//                        Layout layout = widget.getLayout();
//                        int line = layout.getLineForVertical(y);
//                        int off = layout.getOffsetForHorizontal(line, x);
//
//                        ClickableSpan[] link = stext.getSpans(off, off, ClickableSpan.class);
//
//                        if (link.length != 0) {
//                            if (action == MotionEvent.ACTION_UP) {
//                                link[0].onClick(widget);
//                            }
//                            ret = true;
//                        }
//                    }
//                    return ret;
//                }
//            });

            return convertView;
        }
    }

    class MyHolder {
        public TextView tv;
        public TextViewFixTouchConsume tvf;

    }
}
