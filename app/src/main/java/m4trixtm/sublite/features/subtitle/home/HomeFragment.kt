package m4trixtm.sublite.features.subtitle.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.skydoves.whatif.whatIfNotNull
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import m4trixtm.sublite.R
import m4trixtm.sublite.core.platform.fragment.BaseFragment
import m4trixtm.sublite.core.toast.shortToast
import m4trixtm.sublite.databinding.FragmentHomeBinding

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
        }

        homeViewModel.selectedShow.collectOnLifecycleScope { show ->
            show.whatIfNotNull {
                requireContext().shortToast(it.title)
            }
        }

        homeViewModel.selectedSubtitle.collectOnLifecycleScope { subtitle ->
            subtitle.whatIfNotNull {
                requireContext().shortToast(it.details.title)
            }
        }

        homeViewModel.loadHomePage()
    }
}