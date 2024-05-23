package com.kt.apps.media.taipeitour.base

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.kt.apps.media.taipeitour.Constants
import com.kt.apps.media.taipeitour.data.settings.LanguageSettings
import java.util.Locale
import javax.inject.Inject


abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    abstract val layoutRes: Int

    protected lateinit var binding: T

    @Inject
    lateinit var languageSettings: LanguageSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateLocal()
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, layoutRes)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initView(savedInstanceState)
        initAction(savedInstanceState)
    }

    private fun updateLocal() {
        val languageCode = Constants.supportedLanguagesCode[languageSettings.getLanguage()]
            ?: languageSettings.getLanguage()
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
    }

    abstract fun initView(savedInstanceState: Bundle?)
    abstract fun initAction(savedInstanceState: Bundle?)
}