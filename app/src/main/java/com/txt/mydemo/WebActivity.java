package com.txt.mydemo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class WebActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = (WebView) findViewById(R.id.web);
        webView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
//                setNight();
            }
        });
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                setNight();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                setNight();
                Log.d("web", "onPageFinished");
//                setTranslate();
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl("https://www.thestartmagazine.com/article/2e5dbab5-64bf-451d-a6fc-4cae6252ea69?ref=TWVpWnUtU0RLJSQlSHlYNFBrZmVIRmRtMUNoN1lGNEw3MTBod004ODU5N0olJCUxMzU3OTg2NDI%3D&recommendationId=TIME_BL&theme=template6");
//        webView.loadUrl("file:///android_res/" + "raw/js.html");

        webView.loadUrl("http://v-res.in.meizu.com/news/article/103.html");

        webView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.i("web", "onScrollChange");
            }
        });

        final EditText editText = (EditText) findViewById(R.id.edit_web);
        Button button = (Button) findViewById(R.id.btn_web);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(editText.getText().toString());
            }
        });
    }

    public void setNight(){
        /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            webView.evaluateJavascript("document.body.style.backgroundColor=\"black\";document.body.style.color=\"white\";", null);
        } else {
            webView.loadUrl("javascript:document.html.style.backgroundColor=\"black\";document.html.style.color=\"white\";");
        }*/

        String css="javascript: (function() {\n" +
                "  \n" +
                "    css = document.createElement('link');\n" +
                "    css.id = 'xxx_browser_2014';\n" +
                "    css.rel = 'stylesheet';\n" +
                "    css.href = 'data:text/css,html,body,applet,object,h1,h2,h3,h4,h5,h6,blockquote,pre,abbr,acronym,address,big,cite,code,del,dfn,em,font,img,ins,kbd,q,p,s,samp,small,strike,strong,sub,sup,tt,var,b,u,i,center,dl,dt,dd,ol,ul,li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,th,td{background:rgba(0,0,0,0) !important;color:#fff !important;border-color:#A0A0A0 !important;}div,input,button,textarea,select,option,optgroup{background-color:#000 !important;color:#fff !important;border-color:#A0A0A0 !important;}a,a *{color:#ffffff !important; text-decoration:none !important;font-weight:bold !important;background-color:rgba(0,0,0,0) !important;}a:active,a:hover,a:active *,a:hover *{color:#1F72D0 !important;background-color:rgba(0,0,0,0) !important;}p,span{font color:#FF0000 !important;color:#ffffff !important;background-color:rgba(0,0,0,0) !important;}html{-webkit-filter: contrast(50%);}';\n" +
                "    document.getElementsByTagName('head')[0].appendChild(css);\n" +
                "  \n" +
                "})();";
        webView.loadUrl(css);
    }

    public void setTranslate(){
        String translate = "javascript: doGet(e) {\n" +
                "\n" +
                "  var sourceText = ''\n" +
                "  if (e.parameter.q){\n" +
                "    sourceText = e.parameter.q;\n" +
                "  }\n" +
                "  \n" +
                "  var sourceLang = 'auto';\n" +
                "  if (e.parameter.source){\n" +
                "    sourceLang = e.parameter.source;\n" +
                "  }\n" +
                "\n" +
                "  var targetLang = 'ja';\n" +
                "  if (e.parameter.target){\n" +
                "    targetLang = e.parameter.target;\n" +
                "  }\n" +
                "  \n" +
                "  /* Option 1 */\n" +
                "  \n" +
                "  var translatedText = LanguageApp.translate(sourceText, sourceLang, targetLang)\n" +
                "  \n" +
                "  /* Option 2 */  \n" +
                "  \n" +
                "  var url = \"https://translate.googleapis.com/translate_a/single?client=gtx&sl=\" \n" +
                "            + sourceLang + \"&tl=\" + targetLang + \"&dt=t&q=\" + encodeURI(sourceText);\n" +
                "  \n" +
                "  var result = JSON.parse(UrlFetchApp.fetch(url).getContentText());\n" +
                "  \n" +
                "  translatedText = result[0][0][0];\n" +
                "  \n" +
                "  var json = {\n" +
                "    'sourceText' : sourceText,\n" +
                "    'translatedText' : translatedText\n" +
                "  };\n" +
                "  \n" +
                "  // set JSONP callback\n" +
                "  var callback = 'callback';\n" +
                "  if(e.parameter.callback){\n" +
                "    callback = e.parameter.callback\n" +
                "  }\n" +
                "  \n" +
                "  // return JSONP\n" +
                "  return ContentService\n" +
                "           .createTextOutput(callback + '(' + JSON.stringify(json) + ')')\n" +
                "           .setMimeType(ContentService.MimeType.JAVASCRIPT);\n" +
                "}";
        webView.loadUrl(translate);

        webView.loadUrl("file:///android_res/" + "raw/js.html");
    }
}
