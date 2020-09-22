package in.xparticle.digitalkabadipartner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ImageView profilePhoto;
    SwipeRefreshLayout swipeRefreshLayout;
    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        bottomNav = findViewById(R.id.bottomNavigation);
        profilePhoto = findViewById(R.id.profile);
//        bottomNav.setOnNavigationItemSelectedListener(navListener);
        intent_function();


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                intent_function();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
            }
        });
    }
    public void intent_function(){

        Toast.makeText(MainActivity.this,"refresh Successful",Toast.LENGTH_SHORT).show();
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;

                switch (item.getItemId()){
                    case R.id.nav_home:
                        selectedFragment =new HomeFragment();
                        break;
                    case R.id.nav_scrape_price:
                        selectedFragment = new ScrapePriceFragment();
                        break;
                }
                assert selectedFragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();

                return true;
            }
        });
    }






//    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
//        new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment selectedFragment = null;
//
//                switch (item.getItemId()){
//                    case R.id.nav_home:
//                        selectedFragment =new HomeFragment();
//                        break;
//                    case R.id.nav_scrape_price:
//                        selectedFragment = new ScrapePriceFragment();
//                        break;
//                }
//                assert selectedFragment != null;
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        selectedFragment).commit();
//                return true;
//            }
//        };
}