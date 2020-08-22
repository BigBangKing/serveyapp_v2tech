package com.example.serveyapp_rifatmahmud_v2tech.ui.fragments

import android.R.layout
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.serveyapp_rifatmahmud_v2tech.R
import com.example.serveyapp_rifatmahmud_v2tech.ViewModel.ServeyVM
import com.example.serveyapp_rifatmahmud_v2tech.data.pojo.schema2x


class SurveyFragment : Fragment() {

    private val model: ServeyVM by activityViewModels()
    private var i: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.servey_fragment, container, false)

        val tv: TextView = view.findViewById(R.id.textViewQuestion)
        val NextButton: TextView = view.findViewById(R.id.button2)
        val PrevButton: TextView = view.findViewById(R.id.button3)
        val button: Button = view.findViewById(R.id.button)
        val TypeHolder: LinearLayout = view.findViewById(R.id.TypeHolder)

        PrevButton.visibility = View.GONE
        NextButton.visibility = View.GONE

        var currentSurvey: schema2x? = null
        //var x: Array<schema2x>? = null
        model.surveys.observe(viewLifecycleOwner, Observer {
            //x = it
            //tv.text = (x as Array<out schema2x>?)?.get(0)?.question
            button.visibility = View.VISIBLE

        })

        model.showDoneButton.observe(viewLifecycleOwner, Observer {

            if (it == true) {
                button.visibility = View.VISIBLE
//                PrevButton.visibility = View.GONE
//                NextButton.visibility = View.GONE

                button.setText("Done Survey")
                button.setOnClickListener {
                    View.OnClickListener {
                        Log.d("test", "Done")
                    }
                }
            } else {
                if (button.visibility == View.VISIBLE)
                    button.visibility = View.INVISIBLE
            }
        })

        fun processView() {
            tv.text = currentSurvey?.question

            when (currentSurvey?.type) {

                "Checkbox" -> {
                    TypeHolder.removeAllViews()

                    val cs: String? = currentSurvey?.options ?: return

                    val strings = cs?.split(",")?.toList()

                    val rg = RadioGroup(context)
                    rg.orientation = RadioGroup.VERTICAL
                    if (strings != null) {
                       for(i in strings.indices){
                           val rb = RadioButton(context)
                           rb.text = strings[i]
                           rg.addView(rb)
                       }
                    }
                    TypeHolder.addView(rg)
                }

                "dropdown" -> {
                    TypeHolder.removeAllViews()

                    val cs: String? = currentSurvey?.options ?: return
                    val strings = cs?.split(",")?.toTypedArray()

                    val spinner = Spinner(view.context)

                    val spinnerArrayAdapter: ArrayAdapter<String>? = strings?.let {
                        ArrayAdapter<String>(
                            view.context, layout.simple_spinner_dropdown_item,
                            it
                        )
                    }

                    spinner.adapter = spinnerArrayAdapter

                    TypeHolder.addView(spinner)

                }
                "text" -> {
                    TypeHolder.removeAllViews()
                    val mEditText: EditText = EditText(context)
                    mEditText.hint = "Address"
                    TypeHolder.addView(mEditText)

                }
                "number" -> {
                    TypeHolder.removeAllViews()
                    val mEditText: EditText = EditText(context)
                    mEditText.inputType = InputType.TYPE_CLASS_NUMBER
                    mEditText.hint = "Phone Number (Optional)"

                    TypeHolder.addView(mEditText)

                }
                "multiple choice" -> {
                    TypeHolder.removeAllViews()
                    val cs: String? = currentSurvey?.options ?: return

                    val strings = cs?.split(",")?.toTypedArray()

                    if (strings != null) {
                        for (s in strings) {
                            val cb = CheckBox(view.context)
                            cb.text = s
                            cb.isChecked = false
                            TypeHolder.addView(cb)
                        }
                    }
                }
                else -> {
                    Log.d("test", "Error. Survey Type mismatch.")
                }

            }
        }

        button.setOnClickListener(View.OnClickListener {
            if (button.visibility == View.VISIBLE)
                button.visibility = View.GONE

            PrevButton.visibility = View.VISIBLE
            NextButton.visibility = View.VISIBLE

            model.populateSurvey()
            currentSurvey = model.firstSurvey
            processView()
        })

        NextButton.setOnClickListener(View.OnClickListener {
            currentSurvey = model.IncSurvey()
//            TypeHolder.removeAllViews()

            processView()

        })

        PrevButton.setOnClickListener(View.OnClickListener {
            currentSurvey = model.decSurvey()
            processView()

        })


        return view
    }


}