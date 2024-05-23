package com.kt.apps.media.taipeitour.ui.selectlanguage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.kt.apps.media.taipeitour.Constants
import com.kt.apps.media.taipeitour.data.settings.LanguageSettings
import com.kt.apps.media.taipeitour.databinding.FragmentSelectLanguageBinding
import com.kt.apps.media.taipeitour.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SelectLanguageFragment() : BottomSheetDialogFragment() {
    @Inject
    lateinit var languageSettings: LanguageSettings
    private var _binding: FragmentSelectLanguageBinding? = null
    private val adapter by lazy {
        SelectLanguageAdapter()
    }
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectLanguageBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.selectedLanguage = languageSettings.getLanguage()
        _binding!!.recyclerView.adapter = adapter
        _binding!!.recyclerView.addItemDecoration(
            MaterialDividerItemDecoration(
                requireContext(),
                MaterialDividerItemDecoration.VERTICAL
            )
        )
        adapter.onRefresh(Constants.supportedLanguages)
        adapter.itemClickListener = { item, _ ->
            mainViewModel.onChangeLanguage(item)
            requireActivity().recreate()
            dismiss()
        }
    }
}