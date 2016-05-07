package com.example.trim.contentprovider;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/4/24.
 */
public class MessageAdapter extends AppBaseAdapter {

    private List<Message> mList = null;//定义 泛型list 列表，类似于数组
    private Context mContext = null;//当前上下文

    public MessageAdapter(Context context, List<Message> mList) {
        super(context, mList);
        this.mList = mList;
        this.mContext = context;
        MyLog.i("MessageAdapter Constructer");

        for(Message msg: mList){
            MyLog.i("Address: "+msg.getAddress());
            MyLog.i("Body: "+msg.getBody());
            MyLog.i("Time: "+msg.getTime());
        }
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.message_layout, parent, false);//子类具有父类的属性
        }else {

        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        TextView tvAddress = (TextView) convertView.findViewById(R.id.address);
        TextView tvBody = (TextView) convertView.findViewById(R.id.body);
        TextView tvTime = (TextView) convertView.findViewById(R.id.timer);

        imageView.setBackgroundColor(mContext.getResources().getColor(R.color.colorBlue));

        Message msg = mList.get(position);
        tvAddress.setText(msg.getAddress());
        tvBody.setText(msg.getBody());
        tvTime.setText(msg.getTime());

        return convertView;
    }
}
