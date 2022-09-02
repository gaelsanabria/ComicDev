package com.example.comicdev.ui.profile

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.comicdev.databinding.FragmentCameraGalleryDialogListDialogBinding

// TODO: Customize parameter argument names
const val ARG_ITEM_COUNT = "item_count"

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    CameraGalleryDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
class CameraGalleryDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentCameraGalleryDialogListDialogBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCameraGalleryDialogListDialogBinding.inflate(inflater, container, false)
        binding.selectGalleryButton.setOnClickListener {
            val pickPhoto = Intent(
                Intent.ACTION_PICK
            )
            pickPhoto.type = "image/*"
            startActivityForResult(pickPhoto, 1)
        }
        binding.selectCameraButton.setOnClickListener{
            val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePicture, 0)
        }
        binding.closeDialog.setOnClickListener(){
            dismiss()
        }

        return binding.root

    }

    companion object {

        // TODO: Customize parameters
        fun newInstance(itemCount: Int): CameraGalleryDialogFragment =
            CameraGalleryDialogFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ITEM_COUNT, itemCount)
                }
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}