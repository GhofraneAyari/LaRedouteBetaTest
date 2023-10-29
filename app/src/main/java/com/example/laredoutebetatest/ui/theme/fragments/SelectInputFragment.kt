import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.laredoutebetatest.data.model.ReviewFormResponse

class SelectInputFragment : Fragment() {
//    private var selectedValue: String? = null
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val rootView = inflater.inflate(R.layout.fragment_select_input, container, false)
//
//        val spinner = rootView.findViewById<Spinner>(R.id.selectSpinner)
//        val options = getSelectOptions() // You should implement this method
//
//        // Create an ArrayAdapter using the options from the API response
//        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, options)
//
//        // Set the adapter to the spinner
//        spinner.adapter = adapter
//
//        // Set a listener to capture the selected value
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                selectedValue = options[position]
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // Handle when nothing is selected, if needed
//            }
//        }
//
//        // Implement your logic to handle button click to proceed to the next step
//
//        return rootView
//    }
//
//    private fun getSelectOptions(): List<String> {
//        // Extract the options from the API response
//        val reviewForm = // Deserialize the JSON response into ReviewFormResponse
//        // (You can use a library like Gson for this)
//
//        val selectField = reviewForm.fields?.find { it.type == "SelectInput" }
//
//        return selectField?.options?.map { it.name ?: "" } ?: emptyList()
//    }
}
