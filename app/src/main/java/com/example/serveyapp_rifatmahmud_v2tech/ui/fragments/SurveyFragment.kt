package com.example.serveyapp_rifatmahmud_v2tech.ui.fragments

import android.R.layout
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextUtils
import android.text.TextWatcher
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
    private lateinit var tempString: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.servey_fragment, container, false)
        tempString = ""
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
                    model.showValues()
                }
            } else {
                if (button.visibility == View.VISIBLE)
                    button.visibility = View.INVISIBLE
            }
        })

        fun RequiredCheckedListener() {

            if (PrevButton.visibility == View.INVISIBLE || PrevButton.visibility == View.GONE)
                PrevButton.visibility = View.VISIBLE
            if (NextButton.visibility == View.INVISIBLE || NextButton.visibility == View.GONE)
                NextButton.visibility = View.VISIBLE

        }

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
                        for (i in strings.indices) {
                            val rb = RadioButton(context)
                            rb.text = strings[i]
                            rg.addView(rb)
                        }
                    }
                    TypeHolder.addView(rg)


                    rg.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
                        RequiredCheckedListener()

                        val checkedRadioButton =
                            radioGroup?.findViewById(radioGroup.checkedRadioButtonId) as? RadioButton

                        checkedRadioButton?.let {

                            if (checkedRadioButton.isChecked)
                                tempString = checkedRadioButton?.text as String
                        }
                        /* var id: Int = rg.checkedRadioButtonId
                         if (id!=-1){ // If any radio button checked from radio group
                             // Get the instance of radio button using id
                             val radio:RadioButton = findViewById(id)

                         }else{
                             // If no radio button checked in this radio group

                         }*/

                    })
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

                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {

                            PrevButton.visibility = View.INVISIBLE
                            NextButton.visibility = View.INVISIBLE


                        }

                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            tempString += parent?.getItemAtPosition(position).toString();
                            RequiredCheckedListener()
                        }

                    }

                }
                "text" -> {
                    TypeHolder.removeAllViews()
                    val mEditText = EditText(context)
                    mEditText.hint = "Address"
                    TypeHolder.addView(mEditText)

                    mEditText.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(
                            s: CharSequence?,
                            start: Int,
                            count: Int,
                            after: Int
                        ) {

                        }

                        override fun onTextChanged(
                            s: CharSequence?,
                            start: Int,
                            before: Int,
                            count: Int
                        ) {
                        }

                        override fun afterTextChanged(s: Editable?) {
                            if (TextUtils.isEmpty(mEditText.text)) {

                            } else {
                                RequiredCheckedListener()

                                tempString = mEditText.text.toString()

                            }
                        }

                    })

                }
                "number" -> {
                    TypeHolder.removeAllViews()
                    val mEditText: EditText = EditText(context)
                    mEditText.inputType = InputType.TYPE_CLASS_NUMBER
                    mEditText.hint = "Phone Number (Optional)"

                    TypeHolder.addView(mEditText)

                    mEditText.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(
                            s: CharSequence?,
                            start: Int,
                            count: Int,
                            after: Int
                        ) {

                        }

                        override fun onTextChanged(
                            s: CharSequence?,
                            start: Int,
                            before: Int,
                            count: Int
                        ) {
                        }

                        override fun afterTextChanged(s: Editable?) {
                            if (TextUtils.isEmpty(mEditText.text)) {

                            } else {
                                RequiredCheckedListener()
                                tempString = mEditText.text.toString()

                            }
                        }

                    })

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
                            cb.setOnClickListener(View.OnClickListener {
                                RequiredCheckedListener()

                                if (cb.isChecked)
                                    tempString += cb.text
                                else {
                                    if (tempString != null) {
//                                        tempString.chars().allMatch { cb.text }
                                    }
                                }

                            })
                        }
                    }


                }
                else -> {
                    Log.d("test", "Error. Survey Type mismatch.")
                }

            }

            if (currentSurvey?.required!!) {

                PrevButton.visibility = View.GONE
                NextButton.visibility = View.GONE


            } else {

                PrevButton.visibility = View.VISIBLE
                NextButton.visibility = View.VISIBLE
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

        NextButton.setOnClickListener {
            currentSurvey = model.IncSurvey()

            processView()

            model.addValues(currentSurvey?.question + " Feedback: " + tempString)

        }

        PrevButton.setOnClickListener(View.OnClickListener {
            currentSurvey = model.decSurvey()
            processView()

            model.addValues(currentSurvey?.question + " Feedback: " + tempString)

        })


        return view
    }


}