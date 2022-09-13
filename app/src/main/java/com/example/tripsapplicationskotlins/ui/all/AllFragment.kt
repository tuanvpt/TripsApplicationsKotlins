import android.view.LayoutInflater
import androidx.lifecycle.ViewModelStoreOwner
import com.example.comic.utils.base.BaseFragment
import com.example.tripsapplicationskotlins.databinding.FragmentAllBinding
import com.example.tripsapplicationskotlins.ui.all.AllViewModel


class AllFragment : BaseFragment<FragmentAllBinding, AllViewModel>() {

    override fun inflateViewBinding(inflater: LayoutInflater) = FragmentAllBinding.inflate(inflater)

    override fun setUpView() {
    }


    companion object {
        fun newInstance() = AllFragment()
    }

    override fun getViewModelProviderOwner(): ViewModelStoreOwner {
        return this
    }

    override fun getViewModelClass(): Class<AllViewModel> {
        return AllViewModel::class.java
    }
}
