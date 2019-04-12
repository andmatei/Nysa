package nysa.nysa_20.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import nysa.nysa_20.R;
import nysa.nysa_20.model.Toolbar_MainActivity;
import nysa.nysa_20.model.adaptors.MainActivityPagerAdaptor;

public class MainActivity extends AppCompatActivity {
    private static ViewPager trackPager;
    private static MainActivityPagerAdaptor trackAdapter;
    private static Toolbar_MainActivity toolbar_mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

    }

    private   void initComponents(){
        trackAdapter = new MainActivityPagerAdaptor(getSupportFragmentManager());
        trackPager = findViewById(R.id.trackPager);
        toolbar_mainActivity = findViewById(R.id.toolbarMainActivity);

        trackPager.setAdapter(trackAdapter);

        prepareViewPager();

    }

    private void prepareViewPager() {
        trackPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                toolbar_mainActivity.updateToolbarImageResorces(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public static void setCurrentTab(int position){
        trackPager.setCurrentItem(position);
        toolbar_mainActivity.updateToolbarImageResorces(position);
    }


}
