package com.kt.apps.media.taipeitour.ui.webview

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.kt.apps.media.taipeitour.Constants
import com.kt.apps.media.taipeitour.R
import com.kt.apps.media.taipeitour.base.BaseFragment
import com.kt.apps.media.taipeitour.databinding.FragmentWebViewBinding

class WebViewFragment : BaseFragment<FragmentWebViewBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_web_view

    override fun initView(savedInstanceState: Bundle?) {
        setSupportActionBar(binding.toolbar.toolbar)
        binding.toolbar.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.webView.settings.domStorageEnabled = true
        binding.webView.settings.javaScriptCanOpenWindowsAutomatically = true
        binding.webView.settings.setSupportMultipleWindows(true)
        binding.webView.settings.loadWithOverviewMode = true
        binding.webView.settings.useWideViewPort = true
        binding.webView.settings.setSupportZoom(true)
        binding.webView.webChromeClient = object : WebChromeClient() {

        }
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        if (savedInstanceState == null) {
            val url = arguments?.getString(EXTRA_PAGE_URL)
            binding.webView.loadUrl(url ?: Constants.BASE_URL)
            binding.toolbar.appbarTitle = arguments?.getString(EXTRA_PAGE_TITLE)
        }
    }

    override fun initAction(savedInstanceState: Bundle?) {
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        arguments?.let {
            outState.putString(EXTRA_PAGE_URL, it.getString(EXTRA_PAGE_URL))
            outState.putString(EXTRA_PAGE_TITLE, it.getString(EXTRA_PAGE_TITLE))
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            binding.toolbar.appbarTitle = it.getString(EXTRA_PAGE_TITLE)
            it.getString(EXTRA_PAGE_URL)?.let { url ->
                binding.webView.loadUrl(url)
            }
        }
    }

    companion object {
        private const val EXTRA_PAGE_TITLE = "extra:page_title"
        private const val EXTRA_PAGE_URL = "extra:page_url"
        fun newInstance(
            pageTitle: String, url: String
        ) = WebViewFragment().apply {
            arguments = Bundle().apply {
                putString(EXTRA_PAGE_TITLE, pageTitle)
                putString(EXTRA_PAGE_URL, url)
            }
        }
    }
}