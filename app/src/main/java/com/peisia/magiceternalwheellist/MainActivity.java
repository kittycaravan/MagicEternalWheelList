package com.peisia.magiceternalwheellist;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    LinearLayoutManager lm;
    AdapterRecycler ar;
    ArrayList<RecyclerItem> alReal = new ArrayList<>();
    ArrayList<RecyclerItem> alShow = new ArrayList<>();
    ImageView top,bot;
    TextView log1,log2,log3,log4;
    int realIndexForShowTop;
    int realIndexForShowBot;
    int mTotalLength;
    int mCurrentSelectedIndex = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log1 = findViewById(R.id.log_1);
        log2 = findViewById(R.id.log_2);
        log3 = findViewById(R.id.log_3);
        log4 = findViewById(R.id.log_4);
        top = findViewById(R.id.top);
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ////    무한궤도    ////
                reOrder(true);  //재배열 머리에 삽입
            }
        });
        bot = findViewById(R.id.bot);
        bot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ////    무한궤도    ////
                reOrder(false); //재배열 꼬리에 삽입
            }
        });
        alShow.add(new RecyclerItem("m1"));
        alShow.add(new RecyclerItem("m2"));
        alShow.add(new RecyclerItem("m3"));
        alShow.add(new RecyclerItem("m4"));
        alShow.add(new RecyclerItem("m5"));

        alReal.add(new RecyclerItem("m1"));
        alReal.add(new RecyclerItem("m2"));
        alReal.add(new RecyclerItem("m3"));
        alReal.add(new RecyclerItem("m4"));
        alReal.add(new RecyclerItem("m5"));
        alReal.add(new RecyclerItem("m6"));

        realIndexForShowTop = 0;
        realIndexForShowBot = 4;

        mTotalLength = alReal.size();
        rv = findViewById(R.id.rv);
        lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lm);
        ar = new AdapterRecycler(alReal);
        rv.setAdapter(ar);
    }

    private void reOrder(boolean isReverse){
        if(isReverse) {
            mCurrentSelectedIndex--;
            if(mCurrentSelectedIndex == -1){
                mCurrentSelectedIndex = alReal.size() - 1;
            }
            realIndexForShowTop--;
            if(realIndexForShowTop == -1){
                realIndexForShowTop = alReal.size() - 1;
            }
            realIndexForShowBot--;
            if(realIndexForShowBot == -1){
                realIndexForShowBot = alReal.size() - 1;
            }
            alShow.remove(4);   // 보이는 리스트의 마지막꺼 제거하고
            alShow.add(0, alReal.get(realIndexForShowTop));   // 보이는 리스트의 첫 index 에 실 데이터의 보이는 리스트용 첫 index 값을 add.
        } else {
            mCurrentSelectedIndex++;
            if(mCurrentSelectedIndex == alReal.size()){
                mCurrentSelectedIndex = 0;
            }
            realIndexForShowTop++;
            if(realIndexForShowTop == alReal.size()){
                realIndexForShowTop = 0;
            }
            realIndexForShowBot++;
            if(realIndexForShowBot == alReal.size()){
                realIndexForShowBot = 0;
            }
            alShow.remove(0);   // 보이는 리스트의 맨 처음꺼 제거하고
            alShow.add(alReal.get(realIndexForShowBot));   // 보이는 리스트의 마지막 4 index 에 실 데이터의 보이는 리스트용 마지막 index 값을 add.
        }
        ar.setItems(alShow);    //어댑터에 재 반영
        ar.notifyDataSetChanged();  //어댑터 다시 그리기
        ////    로그    ////
        log4.setText("mCurrentSelectedIndex : "+mCurrentSelectedIndex);
        log3.setText("realIndexForShowTop : "+realIndexForShowTop);
        log2.setText("realIndexForShowBot : "+realIndexForShowBot);
        log1.setText("alReal size:"+ alReal.size());
    }
}