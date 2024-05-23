package com.kt.apps.media.taipeitour.ui.selectlanguage

import com.kt.apps.media.taipeitour.Constants
import com.kt.apps.media.taipeitour.R
import com.kt.apps.media.taipeitour.base.BaseAdapter
import com.kt.apps.media.taipeitour.databinding.ItemSelectLanguageBinding

class SelectLanguageAdapter() : BaseAdapter<String, ItemSelectLanguageBinding>() {
    var selectedLanguage: String? = null
    override val layoutRes: Int
        get() = R.layout.item_select_language

    override fun onBindItem(item: String, binding: ItemSelectLanguageBinding, position: Int) {
        binding.item = "${Constants.supportedLanguagesDisplay[item]} (${item})"
        binding.selectedLanguage = item == selectedLanguage
    }
}