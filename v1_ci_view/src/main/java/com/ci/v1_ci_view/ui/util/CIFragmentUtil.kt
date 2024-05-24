package com.ci.v1_ci_view.ui.util

import androidx.appcompat.app.AppCompatActivity
import com.ci.v1_ci_view.ui.view.fragment.CIFragment

class CIFragmentUtil {
    companion object {
        /** display new fragment and hide now fragment */
        fun switchFragment(
            activity: AppCompatActivity,
            fragmentLayoutItemId: Int,
            newFragment: CIFragment,
            fragmentTag: String
        ) {
            val fragmentManager = activity.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            //預計隱藏的fragment
            val oldFragment :CIFragment? = fragmentManager.fragments
                .firstOrNull { it is CIFragment && it.isVisible } as? CIFragment
            //預計使用的Fragment
            var nowFragment = fragmentManager.findFragmentByTag(fragmentTag) as? CIFragment
            if (nowFragment == null){
                nowFragment = newFragment
            }
            if (!nowFragment.isAdded){
                fragmentTransaction.add(fragmentLayoutItemId,nowFragment,fragmentTag)
            }
            // hide
            if (oldFragment != null){
                fragmentTransaction.hide(oldFragment)
            }
            // display
            fragmentTransaction.show(newFragment)

            // run
            fragmentTransaction.commit()
        }
    }
}