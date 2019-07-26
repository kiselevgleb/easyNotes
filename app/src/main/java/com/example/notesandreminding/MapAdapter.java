package com.example.notesandreminding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MapAdapter extends BaseAdapter {
    public ArrayList mData;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy hh:mm");

    public MapAdapter(ArrayList list) {
        mData = list;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Map.Entry<Long, Note> getItem(int position) {
        return (Map.Entry) mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        } else {
            result = convertView;
        }

        Map.Entry<Long, Note> item = getItem(position);
        String tit = item.getValue().getTitle();
        String text = item.getValue().getText();
        int checkBox = item.getValue().getCheckBox();
        Long date = item.getValue().getDeadline();
        String timeString = " ";
        if (checkBox == 1) {
            Calendar d = Calendar.getInstance();
            d.setTimeInMillis(date);
            Date convertDate = d.getTime();
            timeString = formatter.format(convertDate);
        }


        TextView title = ((TextView) result.findViewById(R.id.title));
        title.setText(tit);
        if (title.getText().length() == 0) {
            title.setVisibility(View.GONE);
        }
        TextView subtitle = ((TextView) result.findViewById(R.id.subtitle));
        subtitle.setText(text);
        if (subtitle.getText().length() == 0) {
            subtitle.setVisibility(View.GONE);
        }
        TextView textdate = ((TextView) result.findViewById(R.id.textdate));
        textdate.setText(timeString);
        if (textdate.getText() == " ") {
            textdate.setVisibility(View.GONE);
        }
        return result;
    }
}