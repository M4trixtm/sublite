package m4trixtm.sublite.features.subtitle.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import m4trixtm.sublite.R
import m4trixtm.sublite.core.platform.fragment.BaseFragment
import m4trixtm.sublite.databinding.FragmentSearchSubtitleBinding

@AndroidEntryPoint
class SearchSubtitleFragment :
    BaseFragment<FragmentSearchSubtitleBinding>(R.layout.fragment_search_subtitle) {

    private val viewModel: SearchSubtitleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            adapter = GroupieAdapter()
            model = viewModel

            searchQuery.addTextChangedListener {
                viewModel.search(it.toString())
            }
        }
    }
}