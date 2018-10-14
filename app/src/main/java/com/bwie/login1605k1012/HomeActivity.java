package com.bwie.login1605k1012;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    public static final int FLAG = 123;

    private ViewPager vpBanner;

    private List<String> imgList;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == FLAG) {
                int current = vpBanner.getCurrentItem();
                if (current < imgList.size() - 1) {
                    current++;
                } else {
                    current = 0;
                }
                vpBanner.setCurrentItem(current);
                sendEmptyMessageDelayed(FLAG, 2000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        vpBanner = findViewById(R.id.vp_banner);

        imgList = new ArrayList<>();
        imgList.add("http://01.imgmini.eastday.com/mobile/20180512/20180512072505_0fe08f494e7c090764244e3581b3e5ca_5_mwpm_03200403.jpg");
        imgList.add("http://01.imgmini.eastday.com/mobile/20180512/20180512072505_0fe08f494e7c090764244e3581b3e5ca_1_mwpm_03200403.jpg");
        imgList.add("http://06.imgmini.eastday.com/mobile/20180512/20180512_38f5183808987be3783b180740d12a2a_cover_mwpm_03200403.jpg");


        vpBanner.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView img = new ImageView(HomeActivity.this);
                Glide.with(HomeActivity.this).load(imgList.get(position)).into(img);
                container.addView(img);
                return img;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });

        handler.sendEmptyMessageDelayed(FLAG, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

}
