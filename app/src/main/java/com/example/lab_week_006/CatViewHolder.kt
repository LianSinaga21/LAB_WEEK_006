package com.example.lab_week_006

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_006.model.*

private const val FEMALE_SYMBOL = "\u2640"
private const val MALE_SYMBOL = "\u2642"
private const val UNKNOWN_SYMBOL = "?"

class CatViewHolder(
    private val containerView: View,
    private val imageLoader: ImageLoader,
    private val onClickListener: CatAdapter.OnClickListener
) : RecyclerView.ViewHolder(containerView) {

    private val catPhoto: ImageView =
        containerView.findViewById(R.id.cat_photo)
    private val catName: TextView =
        containerView.findViewById(R.id.cat_name)
    private val catBreed: TextView =
        containerView.findViewById(R.id.cat_breed)
    private val catBio: TextView =
        containerView.findViewById(R.id.cat_biography)
    private val catGender: TextView =
        containerView.findViewById(R.id.cat_gender)

    fun bindData(cat: CatModel) {
        containerView.setOnClickListener {
            onClickListener.onItemClick(cat)
        }

        imageLoader.loadImage(cat.imageUrl, catPhoto)
        catName.text = cat.name
        catBio.text = cat.biography

        catBreed.text = when (cat.breed) {
            CatBreed.AmericanCurl -> "American Curl"
            CatBreed.BalineseJavanese -> "Balinese-Javanese"
            CatBreed.ExoticShorthair -> "Exotic Shorthair"
        }

        catGender.text = when (cat.gender) {
            Gender.Female -> FEMALE_SYMBOL
            Gender.Male -> MALE_SYMBOL
            Gender.Unknown -> UNKNOWN_SYMBOL
        }
    }
}
