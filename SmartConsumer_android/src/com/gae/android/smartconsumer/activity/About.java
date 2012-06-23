package com.gae.android.smartconsumer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class About extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        tbxAbout = (TextView)findViewById(R.id.tbxabout);
        StringBuilder text = new StringBuilder();
        text.append("Trường Đại Học Công Nghệ Thông Tin\n");
        text.append("Khoa Công Nghệ Phần Mềm\n");
        text.append("SmartConsumer Android\n");
        text.append("GVHD : ThS. Nguyễn Đăng Khoa\n");
        text.append("Thành viên nhóm : \n");
        text.append("Cù Duy Khoa - 08520181\n");
        text.append("Phạm Trung Nguyên - 08520259\n");
        tbxAbout.setText(text.toString());
    }
    private TextView tbxAbout;
}
