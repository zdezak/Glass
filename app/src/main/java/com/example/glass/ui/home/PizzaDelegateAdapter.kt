package com.example.glass.ui.home

import com.bumptech.glide.Glide
import com.example.glass.data.model.Pizza
import com.example.glass.databinding.ItemPizzaBinding
import com.livermor.delegateadapter.delegate.ViewBindingDelegateAdapter

class PizzaDelegateAdapter :
    ViewBindingDelegateAdapter<Pizza, ItemPizzaBinding>(ItemPizzaBinding::inflate) {
    override fun ItemPizzaBinding.onBind(item: Pizza) {
        namePizza.text = item.name
        description.text = item.description
        buttonBuy.text = "от ${item.price} р."
        Glide
            .with(fotoPizza)
            .load(item.image_url)
            .centerCrop()
            .into(fotoPizza)
    }

    override fun isForViewType(item: Any): Boolean = item is Pizza
    override fun Pizza.getItemId(): Any = id


}