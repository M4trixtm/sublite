package m4trixtm.sublite.features.subtitle.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.skydoves.whatif.whatIfNotNull
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import m4trixtm.sublite.R
import m4trixtm.sublite.core.platform.fragment.BaseFragment
import m4trixtm.sublite.core.toast.shortToast
import m4trixtm.sublite.databinding.FragmentHomeBinding

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeViewModel: HomeViewModel by viewModels()

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            popularAdapter = GroupieAdapter()
            mostDownloadAdapter = GroupieAdapter()
            latestAdapter = GroupieAdapter()
            viewModel = homeViewModel

            failureLayout.tryAgainButton.setOnClickListener { loadHome() }
        }

        homeViewModel.apply {
            selectedShow.collectOnLifecycleScope { show ->
                show.whatIfNotNull {
                    requireContext().shortToast(it.title)
                }
            }

            selectedSubtitle.collectOnLifecycleScope { subtitle ->
                subtitle.whatIfNotNull {
                    requireContext().shortToast(it.details.title)
                }
            }
        }

        loadHome()
    }

    private fun loadHome() = homeViewModel.loadHomePage()

    override fun onNetworkAvailable() {
        super.onNetworkAvailable()
        homeViewModel.reloadHomeIfNeeded()
    }
}