package com.example.sheettraining.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sheettraining.Model.Coin
import com.example.sheettraining.R


class CoinRecyclerView(private val coinList: ArrayList<Coin>, val coinClickInterface: CoinClickInterface,) : RecyclerView.Adapter<CoinRecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinRecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerviewitems, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CoinRecyclerView.ViewHolder, position: Int) {
        val coin: Coin = coinList[position]
        holder.moeda.text = coin.moeda
        holder.data.text = coin.data
        holder.valor.text = coin.valor.toString()


        holder.itemView.setOnClickListener {

            coinClickInterface.onCoinClick(coinList.get(position))
        }
    }

    override fun getItemCount(): Int {
        return coinList.size
    }


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val moeda : TextView = itemView.findViewById(R.id.idTVCoin)
        val data : TextView = itemView.findViewById(R.id.idTVDate)
        val valor : TextView = itemView.findViewById(R.id.idTVValor)

    }

    fun updateList(newList: List<Coin>) {

        coinList.clear()

        coinList.addAll(newList)

        notifyDataSetChanged()
    }

    interface CoinClickInterface {
        fun onCoinClick(coin: Coin)
    }
}