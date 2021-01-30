package m4trixtm.sublite.features.subtitle.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.skydoves.whatif.whatIfNotNull
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import m4trixtm.sublite.R
import m4trixtm.sublite.core.platform.fragment.BaseFragment
import m4trixtm.sublite.core.toast.shortToast
import m4trixtm.sublite.databinding.FragmentSearchSubtitleBinding
import m4trixtm.sublite.features.subtitle.entity.Subtitle

@AndroidEntryPoint
class SearchSubtitleFragment :
    BaseFragment<FragmentSearchSubtitleBinding>(R.layout.fragment_search_subtitle) {

    private val viewModel: SearchSubtitleViewModel by viewModels()
    private var searchJob: Job? = null

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            adapter = GroupieAdapter()
            model = viewModel
            refreshLayout.setOnRefreshListener { search("${searchQuery.text}") }
            searchQuery.addTextChangedListener { search("$it") }
        }

        viewModel.clickedItem.collectOnLifecycleScope {
            it.whatIfNotNull { subtitle -> onItemClicked(subtitle) }
        }
    }

    private fun onItemClicked(item: Subtitle) = shortToast(item.details.movieName)

    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            delay(700)
            viewModel.search(query)
            searchJob = null
        }
    }
}