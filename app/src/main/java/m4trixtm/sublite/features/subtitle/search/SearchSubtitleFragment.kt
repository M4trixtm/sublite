package m4trixtm.sublite.features.subtitle.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.whatif.whatIfNotNull
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import m4trixtm.sublite.R
import m4trixtm.sublite.core.platform.fragment.BaseFragment
import m4trixtm.sublite.core.toast.shortToast
import m4trixtm.sublite.databinding.FragmentSearchSubtitleBinding
import m4trixtm.sublite.features.dialog.action.actionBottomSheet
import m4trixtm.sublite.features.subtitle.entity.Subtitle

@AndroidEntryPoint
class SearchSubtitleFragment :
    BaseFragment<FragmentSearchSubtitleBinding>(R.layout.fragment_search_subtitle) {

    private val viewModel: SearchSubtitleViewModel by viewModels()
    private var searchHandler = Handler(Looper.getMainLooper())

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            adapter = GroupieAdapter()
            selectedLanguagesAdapter = GroupieAdapter()
            model = viewModel

            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    RecyclerView.VERTICAL
                )
            )
            refreshLayout.setOnRefreshListener { search("${searchQuery.text}") }
            searchQuery.addTextChangedListener { search("$it") }
            filterIcon.setOnClickListener { toggleFilterLayout() }
            addLanguages.setOnClickListener { showLanguagesDialog() }
            collapseFilterLayout.setOnClickListener { toggleFilterLayout() }
        }

        viewModel.clickedItem.collectOnLifecycleScope {
            it.whatIfNotNull { subtitle -> onItemClicked(subtitle) }
        }
    }

    private fun onItemClicked(item: Subtitle) = shortToast(item.details.movieName)

    private fun toggleFilterLayout() = with(binding.filterLayout) {
        visibility = if (visibility != View.VISIBLE) View.VISIBLE else View.GONE
    }

    private fun showLanguagesDialog() = viewModel.languages.value?.let { languages ->
        actionBottomSheet {
            items = languages
        }.show()
    }

    private fun search(query: String) {
        searchHandler.removeCallbacksAndMessages(null)
        searchHandler.postDelayed({ viewModel.search(query) }, 700)
    }
}