package com.siddhesh.wardrobe.view

import android.content.Context
import android.net.Uri
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.siddhesh.wardrobe.R
import com.siddhesh.wardrobe.model.BaseModel
import com.siddhesh.wardrobe.model.JeansModel
import com.siddhesh.wardrobe.model.TopModel


class ViewPagerAdapter(
    private val context: Context,
    private val clothAl: List<BaseModel>,
    private val check: Int
) : PagerAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
//        return imageModelArrayList.size


        return clothAl.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {

        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.item_view_pager, view, false) as ViewGroup

        val ivPackages = layout.findViewById(R.id.iv_cloth) as ImageView

        if (check == 0) {
            ivPackages.setImageURI(Uri.parse((clothAl.get(position) as TopModel).image_path))

        } else
            ivPackages.setImageURI(Uri.parse((clothAl.get(position) as JeansModel).image_path))



        view.addView(layout)
        return layout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}

    override fun saveState(): Parcelable? {
        return null
    }

}