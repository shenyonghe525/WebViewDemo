package com.syh.webviewdemo;

import com.networkbench.agent.impl.NBSAppAgent;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private WebView webView;
	private Button backBtn, refreshBtn;
	private TextView titleView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		NBSAppAgent.setLicenseKey("50ec84b7bd81479aaf073cd7e7e413bb").withLocationServiceEnabled(true).start(this);
		webView = (WebView) findViewById(R.id.webView1);
		webView.loadUrl("http://www.baidu.com");
		backBtn = (Button) findViewById(R.id.back);
		refreshBtn = (Button) findViewById(R.id.refresh);
		titleView = (TextView) findViewById(R.id.textView1);

		webView.setWebChromeClient(new WebChromeClient() {

			@Override
			public void onReceivedTitle(WebView view, String title) {
				titleView.setText(title);
				super.onReceivedTitle(view, title);
			}
		});

		webView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return super.shouldOverrideUrlLoading(view, url);
			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				// TODO Auto-generated method stub
				super.onReceivedError(view, errorCode, description, failingUrl);
				// 鍔犺浇鑷畾涔塰tml閿欒椤�				webView.loadUrl("file:///android_asset/www/index.html");

			}

		});
		// 璁剧疆webview鐨勪笅杞芥帴鍙�		webView.setDownloadListener(new MyDownload());
		backBtn.setOnClickListener(this);
		refreshBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;

		case R.id.refresh:
			webView.reload();
			break;
		}
	}

	class MyDownload implements DownloadListener {

		@Override
		public void onDownloadStart(String url, String userAgent,
				String contentDisposition, String mimetype, long contentLength) {
			// TODO Auto-generated method stub
			System.out.println("url-------->" + url);
			if (url.endsWith(".apk")) {
				Uri uri = Uri.parse(url);
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		}

	}
}
