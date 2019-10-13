package jmapps.strengthofwill.presentation.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import jmapps.strengthofwill.R

class BottomSheetAboutUs : BottomSheetDialogFragment() {

    private lateinit var rootAbout: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootAbout = inflater.inflate(R.layout.fragment_about_us, container, false)
        return rootAbout
    }
}