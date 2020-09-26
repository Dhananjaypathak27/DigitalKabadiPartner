package in.xparticle.digitalkabadipartner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    WebView webView;
    ImageButton mBackArrow;
    private ViewTreeObserver.OnScrollChangedListener mOnScrollChangedListener;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mBackArrow = findViewById(R.id.back_arrow);
        webView = findViewById(R.id.profileWebView1);
        swipeRefreshLayout = findViewById(R.id.profileSwipeRefresh1);
        profileWebViewRefreshing();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(ProfileActivity.this,"done",Toast.LENGTH_SHORT).show();
                profileWebViewRefreshing();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        mBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void profileWebViewRefreshing(){

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://digitalkabadi.in/pickupboy/profile.php");
        webView.setWebViewClient(new myWebclient());
    }
    public class myWebclient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView wv, String url) {
            if(url.startsWith("tel:") || url.startsWith("whatsapp:") ) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                return true;
            }
            return false;
        }
    }
}