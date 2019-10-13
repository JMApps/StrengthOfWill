package jmapps.strengthofwill.presentation.ui.chapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jmapps.strengthofwill.R

class ViewHolderChapters(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val tvParagraph: TextView = itemView.findViewById(R.id.tvParagraph)
    val tvChapterTitle: TextView = itemView.findViewById(R.id.tvChapterTitle)

    fun findItemClick(onItemChapterClick: AdapterChapters.OnItemChapterClick, idPosition: Int) {
        itemView.setOnClickListener {
            onItemChapterClick.onItemClick(idPosition)
        }
    }
}