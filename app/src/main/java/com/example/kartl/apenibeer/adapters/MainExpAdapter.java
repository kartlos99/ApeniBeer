package com.example.kartl.apenibeer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.kartl.apenibeer.R;
import com.example.kartl.apenibeer.models.Jgufi;
import com.example.kartl.apenibeer.models.Obieqti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kartl on 02.10.2017.
 */

public class MainExpAdapter extends BaseExpandableListAdapter {

    private ArrayList<Jgufi> headersList;
    private ArrayList<Jgufi> originalList;
    private Context context;

    public MainExpAdapter(ArrayList<Jgufi> incomeOriginalList, Context context) {
        this.originalList = new ArrayList<>();
        this.originalList.addAll(incomeOriginalList);
        this.headersList = new ArrayList<>();
        this.headersList.addAll(incomeOriginalList);
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return headersList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return headersList.get(groupPosition).getChilds().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return headersList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return headersList.get(groupPosition).getChilds().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {


        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.exp_groupitem_layout, null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.group_name);
        title.setText(headersList.get(groupPosition).getName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        Obieqti esObieqti = (Obieqti) this.getChild(groupPosition, childPosition);
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.exp_listitem_layout, null);
        }

        TextView dasaxeleba = (TextView) convertView.findViewById(R.id.obieqtis_name_view);
        TextView adress = (TextView) convertView.findViewById(R.id.obieqsis_adress_view);

        dasaxeleba.setText(esObieqti.getDasaxeleba());
        adress.setText(esObieqti.getAdress());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void filterData(String query){
        this.headersList.clear();

        if(query.isEmpty()){
            headersList.addAll(originalList);
        }else{
            for (Jgufi mimdinareJgufi : originalList){
                ArrayList<Obieqti> childList = mimdinareJgufi.getChilds();
                ArrayList<Obieqti> newList = new ArrayList<>();

                for (Obieqti mimdinareObieqti : childList){
                    if (mimdinareObieqti.getDasaxeleba().contains(query)){
                        newList.add(mimdinareObieqti);
                    }
                }
                if (newList.size()>0){
                    Jgufi axali_Jgufi = new Jgufi(mimdinareJgufi.getName(),newList);
                    headersList.add(axali_Jgufi);
                }
            }
        }

        notifyDataSetChanged();
    }
}
