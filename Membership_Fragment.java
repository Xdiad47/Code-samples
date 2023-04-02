package tech.kushmanda.luckydays.View.ViewController.UserFragmentViews;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tech.kushmanda.luckydays.R;


public class Membership_Fragment extends Fragment {


    private static final int NUM_PAGES = 2;
    private ViewPager2 viewPager2;
    private FragmentStateAdapter pagerAdapter;

    TextView back_to_profile;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view;

        view = inflater.inflate(R.layout.membership_fragment_, container, false);


        viewPager2 = view.findViewById(R.id.viewpager_membership);
        back_to_profile = view.findViewById(R.id.back_to_profile);


        back_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getActivity().onBackPressed();
                Intent intent = new Intent(getActivity(),FragmentController.class);
                startActivity(intent);
                getActivity().finish();

            }
        });


        pagerAdapter = new ScreenSliderPageAdapter(getActivity());
        viewPager2.setAdapter(pagerAdapter);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                // The position parameter indicates which fragment is currently being displayed
                Log.d("ViewPager", "Selected fragment position: " + position);

                // Pass the current fragment position to the fragments using bundle
//                Bundle bundle = new Bundle();
//                bundle.putInt("POSITION", position);
//
//                // Set the bundle to the fragments
//                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("f" + position);
//                if (fragment != null) {
//                    fragment.setArguments(bundle);
//                }

            }
        });

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);
        //viewPager2.setOffscreenPageLimit(1);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);





        return view;
    }

    private class ScreenSliderPageAdapter extends FragmentStateAdapter {
        public ScreenSliderPageAdapter(FragmentActivity activity) {
            super(activity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position){

                case 0:
                    return new MonthlySubscription_Fragment();
                case 1:
                    return new LifetimeSubscription_Fragment(); //try to change here and try different code for view pager
                default:
                    return null;


            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}