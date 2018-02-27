package test.com.tsapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class WebTranslateActivity extends AppCompatActivity {
    String transStr = "http://translate.google.com/translate?ie=UTF8&sl=auto&tl=ru&u=";
    WebView webView;
    String lastNotTransUrl;

    String getJs() {
        String js = "var newscript = document.createElement(\"script\");";
        js += "newscript.src=\"http://translate.google.com/translate_a/element.js?cb=googleTranslateElementInit\";";
        js += "document.body.appendChild(newscript);";
        return js;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_translate);
        webView = (WebView) findViewById(R.id.web);
        webView.getSettings().setJavaScriptEnabled(true);
        final EditText editText = (EditText) findViewById(R.id.edt);
        Button load = (Button) findViewById(R.id.load);
        Button translate = (Button) findViewById(R.id.translate);
        final ProgressBar bar = (ProgressBar) findViewById(R.id.pb);
        webView.loadUrl("https://www.google.com/");

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(editText.getText().toString());
            }
        });

        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastNotTransUrl = webView.getUrl();
                webView.loadUrl(transStr + webView.getUrl());
            }
        });

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("web", "url: " + url);
//                webView.loadUrl("javascript:" + getJs());
//                webView.loadUrl("javascript:" + "function googleTranslateElementInit() {  \n" +
//                        "  new google.translate.TranslateElement({  \n" +
//                        "    pageLanguage: 'cn',  \n" +
//                        "    autoDisplay: false,  \n" +
//                        "    gaTrack: true,  \n" +
//                        "    gaId: 'UA-20464626-1',  \n" +
//                        "    layout: google.translate.TranslateElement.InlineLayout.SIMPLE  \n" +
//                        "  }, 'google_translate_element');  \n" +
//                        "}");
                bar.setProgress(0);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("web", "view.url: " + view.getUrl());
                Log.i("web", "url : " + url);
                if(view.getUrl().equals(url))
                    return false;
                view.loadUrl(url);
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                bar.setProgress(newProgress);
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            WebBackForwardList list = webView.copyBackForwardList();
            Log.d("web", "list: " + list.getSize());
            int index = list.getCurrentIndex();
            if(list != null && list.getCurrentItem() != null && list.getItemAtIndex(index).getUrl().contains(transStr)){
                webView.loadUrl(lastNotTransUrl);
                return true;
            }
            webView.goBack();
            return true;
        }
        finish();
        return super.onKeyDown(keyCode, event);
    }
}
