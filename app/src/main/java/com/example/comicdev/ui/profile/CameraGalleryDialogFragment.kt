package com.example.comicdev.ui.profile

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        dismiss()
    }

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
            requireActivity().startActivityForResult(pickPhoto, 1)
        }
        binding.selectCameraButton.setOnClickListener{
            val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            requireActivity().startActivityForResult(takePicture, 0)
        }
        binding.closeDialog.setOnClickListener{
            dismiss()
        }

        return binding.root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}