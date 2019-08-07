package com.siddhesh.wardrobe.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.siddhesh.wardrobe.R
import com.siddhesh.wardrobe.model.FavouriteModel
import com.siddhesh.wardrobe.model.JeansModel
import com.siddhesh.wardrobe.model.TopModel
import com.siddhesh.wardrobe.viewModel.WardrobeViewModel
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView


class MainActivity : AppCompatActivity() {

    private lateinit var wardrobeViewModel: WardrobeViewModel
    private var clothType: Boolean = true
    private var alTops: ArrayList<TopModel> = ArrayList()
    private var alJeans: ArrayList<JeansModel> = ArrayList()
    private var alFavCombo: ArrayList<FavouriteModel> = ArrayList()

    lateinit var binding: com.siddhesh.wardrobe.databinding.ActivityMainBinding
    private lateinit var topPagerAdapter: ViewPagerAdapter
    private lateinit var jeansPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


//        setContentView(R.layout.activity_main)


        initializations()

    }

    private fun initializations() {


        wardrobeViewModel = WardrobeViewModel(application)



        alTops = wardrobeViewModel.getTops()
        alJeans = wardrobeViewModel.getJeans()
        alFavCombo = wardrobeViewModel.getFavCombo()


        topPagerAdapter = ViewPagerAdapter(this, alTops)

        binding.vpTop.adapter = topPagerAdapter
        binding.vpTop.setPageTransformer(true, PageTransformer())
        topPagerAdapter.notifyDataSetChanged()


        jeansPagerAdapter = ViewPagerAdapter(this, alJeans)

        binding.vpJeans.adapter = jeansPagerAdapter
        jeansPagerAdapter.notifyDataSetChanged()


        binding.fabTop.setOnClickListener {

            clothType=true
             onSelectImageClick() }
        binding.fabJeans.setOnClickListener {
            clothType=false

            onSelectImageClick() }


    }

    fun onSelectImageClick() {
        CropImage.activity()
            .setGuidelines(CropImageView.Guidelines.ON)
            .setActivityTitle("My Crop")
            .setCropShape(CropImageView.CropShape.RECTANGLE)
            .setCropMenuCropButtonTitle("Done")
            .setRequestedSize(400, 400)
            .setCropMenuCropButtonIcon(R.drawable.ic_add)
            .start(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        // handle result of CropImageActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
//                (findViewById(R.id.quick_start_cropped_image) as ImageView).setImageURI(result.uri)
                Toast.makeText(
                    this, "Cropping successful, Sample: " + result.uri, Toast.LENGTH_LONG
                )
                    .show()

                if (clothType) {
                    var topModel = TopModel()
                    topModel.image_path = result.uri.toString()

                    (wardrobeViewModel to this).first.addTop(topModel)


                    alTops =wardrobeViewModel.getTops()
                    topPagerAdapter.notifyDataSetChanged()

                } else {
                    var jeansModel = JeansModel()
                    jeansModel.image_path = result.uri.toString()

                    (wardrobeViewModel to this).first.addJeans(jeansModel)

                    alJeans =wardrobeViewModel.getJeans()
                    jeansPagerAdapter.notifyDataSetChanged()

                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.error, Toast.LENGTH_LONG).show()
            }
        }
    }
}
