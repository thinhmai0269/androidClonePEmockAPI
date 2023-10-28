package com.example.callapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {
    Context content;
    int layout;
    List<Trainee> TraineeList;

    public SinhVienAdapter(Context content, int layout, List<Trainee> traineeList) {
        this.content = content;
        this.layout = layout;
        TraineeList = traineeList;
    }

    @Override
    public int getCount() {
        return TraineeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater =(LayoutInflater)
                content.getSystemService(content.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);
        //
        TextView nameSV = view.findViewById(R.id.NameSV);
        TextView sexSV = view.findViewById(R.id.SexSV);
        TextView phoneSV = view.findViewById(R.id.PhoneSV);
        TextView emailSV = view.findViewById(R.id.EmailSV);
        //
        Trainee trainee = TraineeList.get(i);
        nameSV.setText(trainee.getName());
        sexSV.setText(trainee.getGender());
        phoneSV.setText(trainee.getPhone());
        emailSV.setText(trainee.getEmail());
        //
        return view;
    }
}
