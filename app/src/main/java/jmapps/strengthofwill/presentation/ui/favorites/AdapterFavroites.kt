package jmapps.strengthofwill.presentation.ui.favorites

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jmapps.strengthofwill.R

class AdapterFavorites(
    private val modelFavorites: MutableList<ModelFavorites>,
    private val onItemFavoriteClick: OnItemFavoriteClick) :
    RecyclerView.Adapter<ViewHolderFavorites>() {

    interface OnItemFavoriteClick {
        fun onItemClick(idPosition: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFavorites {
        return ViewHolderFavorites(
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return modelFavorites.size
    }

    override fun onBindViewHolder(holder: ViewHolderFavorites, position: Int) {

        val strFavoriteId = modelFavorites[position].strIdPosition
        val strParagraph = modelFavorites[position].strParagraph
        val strFavoriteTitle = modelFavorites[position].strFavoriteTitle

        holder.tvParagraph.text = strParagraph
        holder.tvFavoriteTitle.text = Html.fromHtml(strFavoriteTitle)
        holder.findItemClick(onItemFavoriteClick, strFavoriteId!!.toInt() - 1)
    }
}