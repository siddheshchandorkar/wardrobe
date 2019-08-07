package com.siddhesh.wardrobe.utils.HorizontalLinearLayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.siddhesh.wardrobe.R;


public class PagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {
    public final static float BIG_SCALE = 1f;
    public final static float SMALL_SCALE = 0.9f;
    public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
    public int FIRST_PAGE = 1;
    private Context context;
    private FragmentManager fragmentManager;
    private float scale;
    private Fragment fragment;

    private IViewPager iViewPager;
//    private ItemFragment itemFragment;
    private boolean pageSelect = false;


    public PagerAdapter(IViewPager iViewPager, Context context, Fragment whatsUpStatusDetailsFragment, FragmentManager fm, int selectedPage) {
        super(fm);
        this.fragmentManager = fm;
        this.context = context;
        this.fragment = whatsUpStatusDetailsFragment;

        FIRST_PAGE = selectedPage;
        this.iViewPager = iViewPager;


    }

    public Object instantiateItem(ViewGroup collection, int position) {

        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_view_pager, collection, false);
        collection.addView(layout);
        return layout;
    }

  /*  @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        if (position >= getCount()) {
            FragmentManager manager = ((Fragment) object).getFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.remove((Fragment) object);
            trans.commit();
        }
    }*/

   /* @Override
    public Fragment getItem(int position) {
        // make the first pager bigger than others
        itemFragment = null;
        try {
            if (position == FIRST_PAGE)
                scale = BIG_SCALE;
            else
                scale = SMALL_SCALE;

            position = position % alWhatsUpStatus.size();
            itemFragment = (ItemFragment) ItemFragment.newInstance(context, position, scale, alWhatsUpStatus);
            itemFragment.setListener(iViewPager);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return itemFragment;
    }


    @Override
    public int getCount() {
        int counts = 0;
        try {
            counts = alWhatsUpStatus.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counts;
    }

    @Override
    public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {
        try {
            if (positionOffset >= 0f && positionOffset <= 1f) {
                if (position < (alWhatsUpStatus.size() - 1)) {
                    RecyclerViewHorizontalLayout cur = getRootView(position);
                    RecyclerViewHorizontalLayout next = getRootView(position + 1);
                    cur.setScaleBoth(BIG_SCALE - DIFF_SCALE * positionOffset);
                    next.setScaleBoth(SMALL_SCALE + DIFF_SCALE * positionOffset);
                } else {
                    RecyclerViewHorizontalLayout cur = getRootView(position);
                    cur.setScaleBoth(BIG_SCALE - DIFF_SCALE * positionOffset);

                }
//                }
               *//* if (alWhatsUpStatus.size() > 1) {
                    RecyclerViewHorizontalLayout cur = getRootView(position);


                    cur.setScaleBoth(BIG_SCALE - DIFF_SCALE * positionOffset);
                    if(position!=alWhatsUpStatus.size()){
                        RecyclerViewHorizontalLayout next = getRootView(position + 1);
                        next.setScaleBoth(SMALL_SCALE + DIFF_SCALE * positionOffset);


                    }

                 } else if (alWhatsUpStatus.size() == 1) {
                    RecyclerViewHorizontalLayout cur = getRootView(0);
//                    RecyclerViewHorizontalLayout next = getRootView(position + 1);

                    cur.setScaleBoth(BIG_SCALE - DIFF_SCALE * positionOffset);
//                    next.setScaleBoth(SMALL_SCALE + DIFF_SCALE * positionOffset);
                }
*//*

            }
            if (positionOffset == 0.0) {
                if (alWhatsUpStatus.size() == 1 || position == 0) {
                    itemFragment = (ItemFragment) fragmentManager.findFragmentByTag(this.getFragmentTag(position));
                    Utils.log("Siddhesh Check onPageSelected " + position + ":" + itemFragment);

                    if (itemFragment != null) {
                        itemFragment.imageView.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View view) {


                                if (alWhatsUpStatus.get(position).isSelected()) {
                                    alWhatsUpStatus.get(position).setSelected(false);
                                    itemFragment.llSelectedContainer.setVisibility(View.GONE);
                                    pageSelect = false;

                                } else {
                                    alWhatsUpStatus.get(position).setSelected(true);
                                    itemFragment.llSelectedContainer.setVisibility(View.VISIBLE);
                                    pageSelect = true;

                                }
                                iViewPager.onViewPagerScrolled(position, itemFragment.imageView);

                                notifyDataSetChanged();
                                return true;
                            }


                        });
                        itemFragment.imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Utils.log("Siddhesh Check setOnClickListener:" + position);

                                if (pageSelect) {
                                    if (alWhatsUpStatus.get(position).isSelected()) {
                                        alWhatsUpStatus.get(position).setSelected(false);
                                        itemFragment.llSelectedContainer.setVisibility(View.GONE);
                                        pageSelect = false;

                                        for (WhatsUpStatusVO.WhatsUpStatus whatsUpStatus : alWhatsUpStatus) {
                                            if (whatsUpStatus.isSelected()) {
                                                pageSelect = true;

                                            }
                                        }
                                    } else {
                                        alWhatsUpStatus.get(position).setSelected(true);
                                        itemFragment.llSelectedContainer.setVisibility(View.VISIBLE);

                                    }

//                        iViewPager.onViewPagerScrolled(position, itemFragment.imageView);
                                }

                                iViewPager.onViewPagerScrolled(position, itemFragment.imageView);
                                notifyDataSetChanged();
                            }
                        });
                        iViewPager.onViewPagerScrolled(position, itemFragment.imageView);

                    }
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPageSelected(final int position) {

        itemFragment = (ItemFragment) fragmentManager.findFragmentByTag(this.getFragmentTag(position));
        Utils.log("Siddhesh Check onPageSelected " + position + ":" + itemFragment);

//        if (itemFragment != null)
//            iViewPager.onViewPagerScrolled(position, itemFragment.imageView);

        if (itemFragment != null) {
            if (itemFragment.imageView != null) // fabric #1534 solved
                itemFragment.imageView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        Utils.log("Siddhesh Check setOnLongClickListener:" + position);
                        if (alWhatsUpStatus.get(position).getCaption().contains(Constant.THYROCARE_STACK_KEY))
                            iViewPager.onViewPagerLongClick(alWhatsUpStatus.get(position).getSection_name() + " : " + alWhatsUpStatus.get(position).getSection_name());
                        if (alWhatsUpStatus.get(position).isSelected()) {
                            alWhatsUpStatus.get(position).setSelected(false);
                            itemFragment.llSelectedContainer.setVisibility(View.GONE);
                            pageSelect = false;

                        } else {
                            alWhatsUpStatus.get(position).setSelected(true);
                            itemFragment.llSelectedContainer.setVisibility(View.VISIBLE);
                            pageSelect = true;

                        }
                        iViewPager.onViewPagerScrolled(position, itemFragment.imageView);

                        notifyDataSetChanged();
                        return true;
                    }


                });
            if (itemFragment.imageView != null) // Jira #SAA-642 solved
                itemFragment.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Utils.log("Siddhesh Check setOnClickListener:" + position + " " + pageSelect);

                        if (alWhatsUpStatus.get(position).getCaption().contains(Constant.THYROCARE_STACK_KEY))
                            iViewPager.onViewPagerClick(alWhatsUpStatus.get(position).getSection_name() + " : " + alWhatsUpStatus.get(position).getSection_name());
                        if (pageSelect) {
                            if (alWhatsUpStatus.get(position).isSelected()) {
                                alWhatsUpStatus.get(position).setSelected(false);
                                itemFragment.llSelectedContainer.setVisibility(View.GONE);
                                pageSelect = false;

                                for (WhatsUpStatusVO.WhatsUpStatus whatsUpStatus : alWhatsUpStatus) {
                                    if (whatsUpStatus.isSelected()) {
                                        pageSelect = true;

                                    }
                                }
                            } else {
                                alWhatsUpStatus.get(position).setSelected(true);
                                itemFragment.llSelectedContainer.setVisibility(View.VISIBLE);

                            }

//                        iViewPager.onViewPagerScrolled(position, itemFragment.imageView);
                        }

                        iViewPager.onViewPagerScrolled(position, itemFragment.imageView);
                        notifyDataSetChanged();
                    }
                });
            iViewPager.onViewPagerScrolled(position, itemFragment.imageView);

        }

    }*/

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
//
//    @SuppressWarnings("ConstantConditions")
//    private RecyclerViewHorizontalLayout getRootView(int position) {
//        return (RecyclerViewHorizontalLayout) fragmentManager.findFragmentByTag(this.getFragmentTag(position)).getView().findViewById(R.id.);
//    }

    private String getFragmentTag(int position) {
        return "";
//        return "android:switcher:" + ((WhatsUpStatusDetailsFragment) fragment).pager.getId() + ":" + position;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    public interface IViewPager {
        void onViewPagerScrolled(int position, View view);

        void onViewPagerClick(String storyName);

        void onViewPagerLongClick(String storyName);
    }
}