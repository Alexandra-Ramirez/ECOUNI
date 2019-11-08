package pe.ecouni.ecouniapp.presentation.view.ui.info;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

import pe.ecouni.ecouniapp.R;
import pe.ecouni.ecouniapp.presentation.model.RankingItem;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_info_1, R.string.tab_info_2};
    private final Context mContext;

    private RankingFragment rankingFragment;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            if(rankingFragment==null){
                rankingFragment = RankingFragment.newInstance();
            }
            return rankingFragment;
        }else{
            return LocalizacFragment.newInstance();
        }
    }

    public void mostrarRanking(List<RankingItem> items){
        if(rankingFragment!=null)
            rankingFragment.mostrarRanking(items);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}