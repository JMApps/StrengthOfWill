package jmapps.strengthofwill.presentation.ui.favorites

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jmapps.strengthofwill.R

class ViewHolderFavorites(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val tvParagraph: TextView = itemView.findViewById(R.id.tvParagraph)
    val tvFavoriteTitle: TextView = itemView.findViewById(R.id.tvFavoriteTitle)

    fun findItemClick(onItemFavoriteClick: AdapterFavorites.OnItemFavoriteClick, idPosition: Int) {
        itemView.setOnClickListener {
            onItemFavoriteClick.onItemClick(idPosition)
        }
    }
}