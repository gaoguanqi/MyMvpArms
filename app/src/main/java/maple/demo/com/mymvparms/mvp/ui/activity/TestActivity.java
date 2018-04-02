package maple.demo.com.mymvparms.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import maple.demo.com.mymvparms.R;

public class TestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

//        android.support.v4.app.FragmentManager fm = this.getSupportFragmentManager();
//        android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
//        TestFragment fragment = TestFragment.getInstance();
//        ft.replace(R.id.fragment,fragment);
//        ft.commit();
    }
}
