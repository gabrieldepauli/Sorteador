package br.edu.ifsp.dmo1.sorteador.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.dmo1.sorteador.R

class NumeroSorteadoAdapter(context: Context, private val sorteios: List<Int>) : ArrayAdapter<Int>(context, 0, sorteios) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_sorteio, parent, false)

        val txtPosicao = view.findViewById<TextView>(R.id.txtPosicao)
        val txtNumeroSorteado = view.findViewById<TextView>(R.id.txtNumeroSorteado)

        txtPosicao.text = "${position + 1}º sorteado ="
        txtNumeroSorteado.text = sorteios[position].toString()

        return view
    }
}