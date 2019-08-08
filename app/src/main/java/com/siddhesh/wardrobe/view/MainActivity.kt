package com.siddhesh.wardrobe.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.siddhesh.wardrobe.R
import com.siddhesh.wardrobe.model.FavouriteModel
import com.siddhesh.wardrobe.model.JeansModel
import com.siddhesh.wardrobe.model.TopModel
import com.siddhesh.wardrobe.viewModel.WardrobeViewModel
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

import java.util.*
import kotlin.collections.ArrayList




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

        initializations()

    }

    private fun initializations() {


        wardrobeViewModel = WardrobeViewModel(application)



        alTops = wardrobeViewModel.getTops()
        alJeans = wardrobeViewModel.getJeans()
        alFavCombo = wardrobeViewModel.getFavCombo()

        if (alTops != null && alTops.isEmpty()) {
            binding.tvTopMsg.visibility = View.VISIBLE
            binding.ivLeftTop.visibility = View.GONE
            binding.ivRightTop.visibility = View.GONE
        } else {
            binding.tvTopMsg.visibility = View.GONE
            binding.ivLeftTop.visibility = View.VISIBLE
            binding.ivRightTop.visibility = View.VISIBLE

        }

        if (alJeans != null && alJeans.isEmpty()) {
            binding.tvJeansMsg.visibility = View.VISIBLE
            binding.ivLeftJeans.visibility = View.GONE
            binding.ivRightJeans.visibility = View.GONE
        } else {
            binding.tvJeansMsg.visibility = View.GONE

            binding.ivLeftJeans.visibility = View.VISIBLE
            binding.ivRightJeans.visibility = View.VISIBLE
        }


        if (alTops != null && !alTops.isEmpty() && alJeans != null && !alJeans.isEmpty()) {
            binding.ivFav.visibility = View.VISIBLE
            binding.ivShuffle.visibility = View.VISIBLE

            if (wardrobeViewModel.checkFavCombo(alTops[0].topId, alJeans[0].jeansId)>0) {
                binding.ivFav.setImageDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_selected_fav))
            } else {
                binding.ivFav.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.ic_unselected_fav
                    )
                )

            }
        } else {
            binding.ivFav.visibility = View.GONE
            binding.ivShuffle.visibility = View.GONE
        }



        Log.d("Siddhesh", "Init fav: " + alFavCombo)

        topPagerAdapter = ViewPagerAdapter(this, alTops, 0)

        binding.vpTop.adapter = topPagerAdapter
        binding.vpTop.setPageTransformer(true, PageTransformer())
        topPagerAdapter.notifyDataSetChanged()


        jeansPagerAdapter = ViewPagerAdapter(this, alJeans, 1)

        binding.vpJeans.adapter = jeansPagerAdapter
        binding.vpJeans.setPageTransformer(true, PageTransformer())

        jeansPagerAdapter.notifyDataSetChanged()




        binding.fabTop.setOnClickListener {

            clothType = true
            onSelectImageClick()
        }
        binding.fabJeans.setOnClickListener {
            clothType = false

            onSelectImageClick()
        }

        binding.ivLeftTop.setOnClickListener {
            if (binding.vpTop.currentItem > 0) {
                binding.vpTop.setCurrentItem(binding.vpTop.currentItem - 1, true)
            }


        }
        binding.ivRightTop.setOnClickListener {
            if (binding.vpTop.currentItem < alTops.size - 1) {
                binding.vpTop.setCurrentItem(binding.vpTop.currentItem + 1, true)
            }


        }
        binding.ivLeftJeans.setOnClickListener {
            if (binding.vpJeans.currentItem > 0) {
                binding.vpJeans.setCurrentItem(binding.vpJeans.currentItem - 1, true)
            }


        }
        binding.ivRightJeans.setOnClickListener {
            if (binding.vpJeans.currentItem < alTops.size - 1) {
                binding.vpJeans.setCurrentItem(binding.vpJeans.currentItem + 1, true)
            }
        }


        binding.ivShuffle.setOnClickListener {


            binding.vpTop.setCurrentItem((Random().nextInt(alTops.size) ), true)
            binding.vpJeans.setCurrentItem((Random().nextInt(alJeans.size) ), true)

//            alTops.shuffle(Random())
//            alJeans.shuffle(Random())
//
//            topPagerAdapter.notifyDataSetChanged()
//            jeansPagerAdapter.notifyDataSetChanged()




        }



        binding.vpTop.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {



                if (alTops != null && !alTops.isEmpty() && alJeans != null && !alJeans.isEmpty()) {
                    if (wardrobeViewModel.checkFavCombo(alTops[position].topId, alJeans[binding.vpJeans.currentItem].jeansId)>0) {
                        binding.ivFav.setImageDrawable(
                            ContextCompat.getDrawable(
                                this@MainActivity,
                                com.siddhesh.wardrobe.R.drawable.ic_selected_fav
                            )
                        )
                    } else {
                        binding.ivFav.setImageDrawable(
                            ContextCompat.getDrawable(
                                this@MainActivity,
                                R.drawable.ic_unselected_fav
                            )
                        )

                    }
                }


            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {}

            override fun onPageScrollStateChanged(pos: Int) {}
        })
        binding.vpJeans.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {

                if (alTops != null && !alTops.isEmpty() && alJeans != null && !alJeans.isEmpty()) {
                    if (wardrobeViewModel.checkFavCombo(alTops[binding.vpTop.currentItem].topId, alJeans[position].jeansId)>0) {
                        binding.ivFav.setImageDrawable(
                            ContextCompat.getDrawable(
                                this@MainActivity,
                                R.drawable.ic_selected_fav
                            )
                        )
                    } else {
                        binding.ivFav.setImageDrawable(
                            ContextCompat.getDrawable(
                                this@MainActivity,
                                R.drawable.ic_unselected_fav
                            )
                        )

                    }
                }

            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {}

            override fun onPageScrollStateChanged(pos: Int) {}
        })
        binding.ivFav.setOnClickListener {
            val favouriteModel = FavouriteModel()
            favouriteModel.topId = alTops.get(binding.vpTop.currentItem).topId
            favouriteModel.jeansId = alJeans.get(binding.vpJeans.currentItem).jeansId
            val builder = AlertDialog.Builder(this)


            if (alTops != null && !alTops.isEmpty() && alJeans != null && !alJeans.isEmpty()) {
                var favId=wardrobeViewModel.checkFavCombo(alTops[binding.vpTop.currentItem].topId, alJeans[binding.vpJeans.currentItem].jeansId)

                if (favId<=0) {

                    builder.setTitle(getString(R.string.add_to_fav_list))
                    builder.setMessage(getString(R.string.add_to_fav_question))


                    builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                        if (wardrobeViewModel.selectFavCombo(favouriteModel)) {
                            binding.ivFav.setImageDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_selected_fav))

                        } else
                            Toast.makeText(
                                this,
                                getString(R.string.combination_already_exist),
                                Toast.LENGTH_SHORT
                            ).show()


                    }



                } else {
                    builder.setTitle(getString(R.string.remove_from_fav))
                    builder.setMessage(getString(R.string.remove_from_fav_question))


                    builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                        wardrobeViewModel.removeFavCombo(favId)
                        binding.ivFav.setImageDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_unselected_fav))




                    }

                }
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->

                dialog.dismiss()
            }

            builder.show()



//            if (wardrobeViewModel.selectFavCombo(favouriteModel)) {
//                binding.ivFav.setImageDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_selected_fav))
//
//            } else
//                Toast.makeText(
//                    this,
//                    "This Combonation is already exist... \n Choose Different Combination",
//                    Toast.LENGTH_SHORT
//                ).show()


        }

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
                    val topModel = TopModel()
                    topModel.image_path = result.uri.toString()

                    wardrobeViewModel.addTop(topModel)


                    alTops.clear()
                    alTops.addAll(wardrobeViewModel.getTops())
                    topPagerAdapter.notifyDataSetChanged()


                    binding.vpTop.setCurrentItem(alTops.size - 1, true)

                    if (alTops != null && alTops.isEmpty()) {
                        binding.tvTopMsg.visibility = View.VISIBLE
                        binding.ivLeftTop.visibility = View.GONE
                        binding.ivRightTop.visibility = View.GONE
                    } else {
                        binding.tvTopMsg.visibility = View.GONE
                        binding.ivLeftTop.visibility = View.VISIBLE
                        binding.ivRightTop.visibility = View.VISIBLE

                    }


                } else {
                    val jeansModel = JeansModel()
                    jeansModel.image_path = result.uri.toString()

                    wardrobeViewModel.addJeans(jeansModel)

                    alJeans.clear()
                    alJeans.addAll(wardrobeViewModel.getJeans())
                    jeansPagerAdapter.notifyDataSetChanged()

                    binding.vpJeans.setCurrentItem(alJeans.size - 1, true)

                    if (alJeans != null && alJeans.isEmpty()) {
                        binding.tvJeansMsg.visibility = View.VISIBLE
                        binding.ivLeftJeans.visibility = View.GONE
                        binding.ivRightJeans.visibility = View.GONE
                    } else {
                        binding.tvJeansMsg.visibility = View.GONE

                        binding.ivLeftJeans.visibility = View.VISIBLE
                        binding.ivRightJeans.visibility = View.VISIBLE
                    }


                }
                if (alTops != null && !alTops.isEmpty() && alJeans != null && !alJeans.isEmpty()) {
                    binding.ivFav.visibility = View.VISIBLE
                    binding.ivShuffle.visibility = View.VISIBLE
                } else {
                    binding.ivFav.visibility = View.GONE
                    binding.ivShuffle.visibility = View.GONE
                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.error, Toast.LENGTH_LONG).show()
            }
        }
    }
}
