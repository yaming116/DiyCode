package me.shenfan.retrodiycode;

import android.app.ProgressDialog;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

/**
 * Created by Sun on 2016/7/19.
 */
public class AuthFragment extends Fragment {

    private WebView mWebView;
    private ProgressBar progressBar;
    private ProgressDialog pd;
    private String authCode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auth, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FrameLayout layout = (FrameLayout) view.findViewById(R.id.auth_container);

        mWebView = new WebView(getContext().getApplicationContext());
        progressBar = (ProgressBar) view.findViewById(R.id.auth_progressBar);

        if (layout != null) {
            layout.addView(mWebView, 0, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }

        mWebView.getSettings().getJavaScriptEnabled();
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (progressBar != null) {
                    progressBar.setProgress(newProgress);
                    if (newProgress != 100) {
                        progressBar.setVisibility(View.VISIBLE);
                    } else {
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("test", url);
                if (url.startsWith(DiyCodeConstants.AUTHORIZATION_HTTPS_URL)) {
                    try {
                        authCode = url.substring(url.lastIndexOf("/") + 1);
                        //Generate URL for requesting Access Token
                        // String accessTokenUrl = AuthenticationService.getAccessTokenUrl(auth_code = code);
                        //We make the request in a AsyncTask
                        mWebView.setVisibility(View.INVISIBLE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (url.equals(DiyCodeConstants.BASE_URL + "/")) {
                    url = DiyCodeConstants.getAuthorizationUrl();
                }
                view.loadUrl(url);
                return true;
            }


            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        mWebView.loadUrl(DiyCodeConstants.getAuthorizationUrl());
    }

    @Override
    public void onResume() {
        super.onResume();


    }
}
