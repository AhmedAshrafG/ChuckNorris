package side_projects.ahmedz.chucknorris

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


class SimpleBindingRecyclerAdapter<UIModel, Binding: ViewDataBinding>(
		@param:LayoutRes private val itemLayoutId: Int,
		private val variableId: Int,
		private var clickListener: ((UIModel) -> Unit)? = null,
		_items: List<UIModel> = listOf()
): RecyclerView.Adapter<SimpleBindingRecyclerAdapter.ViewHolder<Binding>>() {

	private var items: MutableList<UIModel> = _items.toMutableList()
		set(value) {
			this.items.clear()
			this.items.addAll(value)
			notifyDataSetChanged()
		}

	fun addItem(item: UIModel) {
		val lastPosition = items.size + 1
		this.items.add(item)
		notifyItemInserted(lastPosition)
	}

	fun addItems(newItems: List<UIModel>) {
		val lastPosition = items.size + 1
		this.items.addAll(newItems)
		notifyItemRangeInserted(lastPosition, newItems.size)
	}

	override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder<Binding> {
		val inflater = LayoutInflater.from(viewGroup.context)
		val binding = DataBindingUtil.inflate<Binding>(inflater, itemLayoutId, viewGroup, false)
		return ViewHolder(binding)
	}

	override fun onBindViewHolder(viewHolder: ViewHolder<Binding>, i: Int) {
		val model = items[i]
		viewHolder.binding.setVariable(variableId, model)
		viewHolder.itemView.setOnClickListener { clickListener?.invoke(model) }
	}

	override fun getItemCount() = items.size

	fun clear() {
		items.clear()
		notifyDataSetChanged()
	}

	class ViewHolder<Binding: ViewDataBinding>(val binding: Binding): RecyclerView.ViewHolder(binding.root)

}
