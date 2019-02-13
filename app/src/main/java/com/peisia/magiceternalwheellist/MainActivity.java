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
    private static final int SHOW_LENGTH = 5;
    RecyclerView mRv;
    LinearLayoutManager mLlm;
    AdapterRecycler mAr;
    ArrayList<RecyclerItem> mAlVirtual = new ArrayList<>();
    ArrayList<RecyclerItem> mAlShow = new ArrayList<>();
    ArrayList<RecyclerItem> mAlReal = new ArrayList<>();
    ImageView mImgTop, mImgBot;
    TextView mTvLog1, mTvLog2, mTvLog3, mTvLog4;
    int mRealIndexForShowTop = 0;
    int mRealIndexForShowBot = 4;
    int mRealListSize;
    int mCurrentSelectedIndex = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvLog1 = findViewById(R.id.log_1);
        mTvLog2 = findViewById(R.id.log_2);
        mTvLog3 = findViewById(R.id.log_3);
        mTvLog4 = findViewById(R.id.log_4);
        mImgTop = findViewById(R.id.top);
        ////    실 데이터 입력(시작)  ////
        mAlReal.add(new RecyclerItem("m1", "http://vcast.co.kr/testimage/chimage1.png"));
        mAlReal.add(new RecyclerItem("m2", "http://vcast.co.kr/testimage/chimage2.png"));
        mAlReal.add(new RecyclerItem("m3", "http://vcast.co.kr/testimage/chimage3.png"));
        mAlReal.add(new RecyclerItem("m4", "http://vcast.co.kr/testimage/chimage4.png"));
        mAlReal.add(new RecyclerItem("m5", "http://vcast.co.kr/testimage/chimage5.png"));
        mAlReal.add(new RecyclerItem("m6", "http://vcast.co.kr/testimage/chimage6.png"));
        ////    실 데이터 입력(끝)  ////
        mRealListSize = mAlReal.size(); // 실 데이터 수 기록.
        ////    실 데이터 리스트의 수에 따른 예외 처리
        if(mRealListSize > 0){  // 중요 조건 : 실 데이터 수가 0개 초과만 처리
            ////    클릭 리스너 등록(시작)   ////
            mImgTop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reOrder(true);  // 무한궤도 처리 : 재배열 머리에 삽입
                }
            });
            mImgBot = findViewById(R.id.bot);
            mImgBot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reOrder(false); // 무한궤도 처리 : 재배열 꼬리에 삽입
                }
            });
            ////    클릭 리스너 등록(끝)   ////
            if(mRealListSize == 1) {        // 실 데이터 리스트 수가 1개 일 때 : 0,1,3,4 index 에는 item view 를 gone 처리 하기위한 데이터를 넣고, 2 index에만 1개뿐인 데이터를 넣음.
                mAlVirtual.add(new RecyclerItem("gone", ""));
                mAlVirtual.add(new RecyclerItem("gone", ""));
                mAlVirtual.add(mAlReal.get(0));
                mAlVirtual.add(new RecyclerItem("gone", ""));
                mAlVirtual.add(new RecyclerItem("gone", ""));
            } else if(mRealListSize == 2){  // 실 데이터 리스트 수가 2개 일 때 : 실 데이터 세트 3개를 가상 리스트에 더해준다.
                mAlVirtual.add(mAlReal.get(0));
                mAlVirtual.add(mAlReal.get(1));
                mAlVirtual.add(mAlReal.get(0));
                mAlVirtual.add(mAlReal.get(1));
                mAlVirtual.add(mAlReal.get(0));
                mAlVirtual.add(mAlReal.get(1));
            } else if(mRealListSize == 3){  // 실 데이터 리스트 수가 3개 일 때 : index 0,1 에는 실 데이터의 마지막 두 값을넣고, 그 다음 실데이터 한 세트를 넣고, 마지막에 실데이터의 맨 첫값을 넣는다.
                mAlVirtual.add(mAlReal.get(mRealListSize-2));
                mAlVirtual.add(mAlReal.get(mRealListSize-1));
                for(int i=0;i<mRealListSize;i++){
                    mAlVirtual.add(mAlReal.get(i));
                }
                mAlVirtual.add(mAlReal.get(0));
            } else if(mRealListSize == 4){  // 실 데이터 리스트 수가 4개 일 때 : index 0,1 에는 실 데이터의 마지막 두 값을넣고, 그 다음 실데이터 한 세트를 넣고, 마지막에 실데이터의 맨 첫값, 두번째 값을 넣는다.
                mAlVirtual.add(mAlReal.get(mRealListSize-2));
                mAlVirtual.add(mAlReal.get(mRealListSize-1));
                for(int i=0;i<mRealListSize;i++){
                    mAlVirtual.add(mAlReal.get(i));
                }
                mAlVirtual.add(mAlReal.get(0));
                mAlVirtual.add(mAlReal.get(1));
            } else {                        // 실 데이터 리스트 수가 5개 이상인 나머지 경우 : index 0,1 에는 실 데이터의 마지막 두 값을넣고, 그 다음 실데이터 한 세트를 마지막 2개 값을 제외하고 넣는다.
                mAlVirtual.add(mAlReal.get(mRealListSize-2));
                mAlVirtual.add(mAlReal.get(mRealListSize-1));
                for(int i=0;i<mRealListSize - 2;i++){
                    mAlVirtual.add(mAlReal.get(i));
                }
            }
            for (int i = 0; i < SHOW_LENGTH; i++) { // 보이는 리스트에 SHOW_LENGTH 개 세팅.
                mAlShow.add(mAlVirtual.get(i));
            }
            mAr = new AdapterRecycler(mAlVirtual, this);  // 어댑터에 반영(가상리스트)
            mRv = findViewById(R.id.rv);
            mLlm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRv.setLayoutManager(mLlm);
            mRv.setAdapter(mAr);
        }
    }
    private void reOrder(boolean isReverse){
        if(mRealListSize > 1){   // 실 리스트 아이템 수가 1개 이상일 때만 작동함.
            if(isReverse) {
                mCurrentSelectedIndex--;
                if(mCurrentSelectedIndex == -1){
                    mCurrentSelectedIndex = mAlVirtual.size() - 1;
                }
                mRealIndexForShowTop--;
                if(mRealIndexForShowTop == -1){
                    mRealIndexForShowTop = mAlVirtual.size() - 1;
                }
                mRealIndexForShowBot--;
                if(mRealIndexForShowBot == -1){
                    mRealIndexForShowBot = mAlVirtual.size() - 1;
                }
                mAlShow.remove(4);   // 보이는 리스트의 마지막꺼 제거하고
                mAlShow.add(0, mAlVirtual.get(mRealIndexForShowTop));   // 보이는 리스트의 첫 index 에 실 데이터의 보이는 리스트용 첫 index 값을 add.
            } else {
                mCurrentSelectedIndex++;
                if(mCurrentSelectedIndex == mAlVirtual.size()){
                    mCurrentSelectedIndex = 0;
                }
                mRealIndexForShowTop++;
                if(mRealIndexForShowTop == mAlVirtual.size()){
                    mRealIndexForShowTop = 0;
                }
                mRealIndexForShowBot++;
                if(mRealIndexForShowBot == mAlVirtual.size()){
                    mRealIndexForShowBot = 0;
                }
                mAlShow.remove(0);   // 보이는 리스트의 맨 처음꺼 제거하고
                mAlShow.add(mAlVirtual.get(mRealIndexForShowBot));   // 보이는 리스트의 마지막 4 index 에 실 데이터의 보이는 리스트용 마지막 index 값을 add.
            }
            mAr.setItems(mAlShow);    //어댑터에 재 반영
            mAr.notifyDataSetChanged();  //어댑터 다시 그리기
            ////    로그    ////
            mTvLog4.setText("mRealIndexForShowTop : "+ mRealIndexForShowTop);
            mTvLog3.setText("mCurrentSelectedIndex : "+mCurrentSelectedIndex);
            mTvLog2.setText("mRealIndexForShowBot : "+ mRealIndexForShowBot);
            mTvLog1.setText("mAlVirtual size:"+ mAlVirtual.size());
        }
    }
}