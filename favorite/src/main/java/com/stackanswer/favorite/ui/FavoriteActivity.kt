package com.stackanswer.favorite.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.stackanswer.favorite.R
import com.stackanswer.favorite.databinding.ActivityFavoriteBinding
import com.stackanswer.favorite.injection.favoriteModule
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        val fragment: Fragment
        fragment = FavoriteFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_layout_fav, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.containerLayoutFav.removeAllViewsInLayout()
        binding.rootView.removeAllViewsInLayout()

        if (binding.root.parent != null) {
            (binding.root.parent as ViewGroup).removeView(binding.root)
        }
    }
}