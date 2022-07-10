package com.othadd.chopmore

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.othadd.chopmore.databinding.FragmentLandingBinding

class LandingFragment : Fragment() {

    private val sharedViewModel: ChopmoreViewModel by activityViewModels {
        ChopmoreViewModelFactory()
    }

    private lateinit var binding: FragmentLandingBinding
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var cakesAdapter: CakesAdapter
    private lateinit var scrollItemsAdapter: ScrollItemsAdapter

    private var previouslyAnimatedView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLandingBinding.inflate(inflater, container, false)
        categoriesAdapter =
            CategoriesAdapter().also { it.updateItemList(sharedViewModel.categoriesList) }
        cakesAdapter = CakesAdapter().also { it.updateItemList(sharedViewModel.cakesList) }
        scrollItemsAdapter =
            ScrollItemsAdapter().also { it.updateItemList(sharedViewModel.scrollItemList) }

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            landingFragment = this@LandingFragment
            categoriesRecyclerView.adapter = categoriesAdapter
            cakesRecyclerView.adapter = cakesAdapter
            cakesScrollRecyclerView.adapter = scrollItemsAdapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cakesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                runScrollAnimation()
            }
        })
    }

    private fun runScrollAnimation() {

        val focusedItemPosition =
            (binding.cakesRecyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()

        val focusedItemView =
            (binding.cakesRecyclerView.layoutManager as LinearLayoutManager).findViewByPosition(
                focusedItemPosition
            )
                ?: return

        if (focusedItemView == previouslyAnimatedView)
            return

        previouslyAnimatedView?.let { animate(it, focusedItemView) } ?: animateExpansion(focusedItemView)

        sharedViewModel.updateScrollItems(focusedItemPosition)
        scrollItemsAdapter.updateItemList(sharedViewModel.scrollItemList)

        previouslyAnimatedView = focusedItemView
    }

    private fun animateExpansion(item: View): ObjectAnimator {
        val movePropertyValueHolder = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f, 1.1f)
        val transparencyValueHolder = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f, 1.1f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(
            item,
            movePropertyValueHolder,
            transparencyValueHolder
        )
        animator.duration = 100
        return animator
    }

    private fun animateShrink(item: View): ObjectAnimator {
        val movePropertyValueHolder = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.1f, 1.0f)
        val transparencyValueHolder = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.1f, 1.0f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(
            item,
            movePropertyValueHolder,
            transparencyValueHolder
        )
        animator.duration = 100
        return animator
    }

    private fun animate(previouslySelected: View, newSelectedView: View) {
        val expandAnimator = animateExpansion(newSelectedView)
        val shrinkAnimator = animateShrink(previouslySelected)
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(expandAnimator, shrinkAnimator)
        animatorSet.start()
    }
}