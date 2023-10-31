import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.laredoutebetatest.R
import com.example.laredoutebetatest.ui.viewmodel.DataViewModel

class TextInput : Fragment() {
    private var userTitle: String? = null
    private val myDataViewModel: DataViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_text_input, container, false)
        val titleEditText = rootView.findViewById<EditText>(R.id.titleEditText)

            userTitle = titleEditText.text.toString().trim()


        myDataViewModel.reviewFormData?.let { response ->

            val textField = response.fields?.find { it.id == "reviewtext" }
            if (textField != null) {

                val titleTextView = rootView.findViewById<TextView>(R.id.titleTextView)
                titleTextView.text = textField.title.toString()
            }
        }


        return rootView
    }
}
