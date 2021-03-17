package com.example.mystudyproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<JSONObject> {
int listLayout;
ArrayList<JSONObject> userList;
Context context;
        public ListViewAdapter(Context context, int listLayout, int field, ArrayList<JSONObject> userList) {
            super(context, listLayout, field, userList);
            this.context = context;
            this.listLayout = listLayout;
            this.userList = userList;

        }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View listViewItem = inflater.inflate(listLayout, null, false);
    TextView charcode = listViewItem.findViewById(R.id.textViewCharCode);
    TextView name = listViewItem.findViewById(R.id.textViewName);
    TextView value = listViewItem.findViewById(R.id.textViewValue);
    TextView previous = listViewItem.findViewById(R.id.textViewPrevious);
    try {
        charcode.setText (userList.get(position).getString("CharCode"));
        name.setText(userList.get(position).getString("Name"));
        value.setText (userList.get(position).getString("Value"));
       // previous.setText (userList.get(position).getString("Previous"));
      String q = (userList.get(position).getString("Previous"));
      String w = (userList.get(position).getString("Value"));
 if (Double.parseDouble(q) > Double.parseDouble(w)) {
     previous.setText("-"+String.format("%.3f",(Double.parseDouble(q) - Double.parseDouble(w))));


 } else {

     previous.setText("+"+String.format("%.3f",(Double.parseDouble(w) - Double.parseDouble(q))));
 }
    }catch (JSONException je){
        je.printStackTrace();
    }
    return listViewItem;
}

}
