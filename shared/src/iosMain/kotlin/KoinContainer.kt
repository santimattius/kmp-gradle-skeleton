import com.santimattius.kmp.skeleton.shared.di.sharedModule
import com.santimattius.kmp.skeleton.shared.ui.CharacterViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin


object KoinContainer : KoinComponent {

    private val _viewModel: CharacterViewModel by inject()

    fun start() {
        startKoin {
            modules(sharedModule())
        }
    }

    fun characterViewModel() = _viewModel
}