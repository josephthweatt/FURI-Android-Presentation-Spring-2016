package com.example.family.furi;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Family on 12/30/2015.
   --------------------------------
    this class will add multiple adapters to a single ListView, for more information,
    read the blog it was taken from,
    http://jsharkey.org/blog/2008/08/18/separating-lists-with-headers-in-android-09/
 */

public class SeparatedListAdapter extends BaseAdapter {

    public final Map<String,Adapter> sections = new LinkedHashMap<String,Adapter>();
    public final ArrayAdapter<String> headers;
    public final static int TYPE_SECTION_HEADER = 0;

    public SeparatedListAdapter(Context context, int layout) {
        headers = new ArrayAdapter<String>(context, layout);
    }

    public void addSection(String section, Adapter adapter) {
        this.headers.add(section);
        this.sections.put(section, adapter);
    }

    public Object getItem(int position) {
        for(Object section : this.sections.keySet()) {
            Adapter adapter = sections.get(section);
            int size = adapter.getCount() + 1;

            // check if position inside this section   
            if(position == 0) return section;
            if(position < size) return adapter.getItem(position - 1);

            // otherwise jump into next section  
            position -= size;
        }
        return null;
    }

    public int getCount() {
        // total together all sections, plus one for each section header  
        int total = 0;
        for(Adapter adapter : this.sections.values())
            total += adapter.getCount() + 1;
        return total;
    }

    public int getViewTypeCount() {
        // assume that headers count as one, then total all sections  
        int total = 1;
        for(Adapter adapter : this.sections.values())
            total += adapter.getViewTypeCount();
        return total;
    }

    public int getItemViewType(int position) {
        int type = 1;
        for(Object section : this.sections.keySet()) {
            Adapter adapter = sections.get(section);
            int size = adapter.getCount() + 1;

            // check if position inside this section   
            if(position == 0) return TYPE_SECTION_HEADER;
            if(position < size) return type + adapter.getItemViewType(position - 1);

            // otherwise jump into next section  
            position -= size;
            type += adapter.getViewTypeCount();
        }
        return -1;
    }

    public boolean areAllItemsSelectable() {
        return false;
    }

    public boolean isEnabled(int position) {
        return (getItemViewType(position) != TYPE_SECTION_HEADER);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int sectionnum = 0;

        for(Object section : this.sections.keySet()) {
            Adapter adapter = sections.get(section);
            int size = adapter.getCount() + 1;

            // check if position inside this section   
            if(position == 0) {
                View headerView = headers.getView(sectionnum, convertView, parent);
                Drawable border = ContextCompat.getDrawable(
                        headerView.getContext(), R.drawable.std_border_header);
                headerView.setBackground(border);
                return headerView;
            }
            if(position < size) {
                View view = adapter.getView(position - 1, convertView, parent);
                if (position % 2 == 0) {
                    int color = ContextCompat.getColor(view.getContext(), R.color.colorSecondary);
                    view.setBackgroundColor(color);
                }
                return view;
            }

            // otherwise jump into next section  
            position -= size;
            sectionnum++;
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}  