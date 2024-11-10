package br.edu.ifsp.dmo1.sorteador.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo1.sorteador.model.Draw
import br.edu.ifsp.dmo1.sorteador.R
import br.edu.ifsp.dmo1.sorteador.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var draw = Draw()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setClickListener()
    }

    override fun onClick(v: View) {

        when (v) {
            binding.buttonUseLimit -> {
                val limit: Int = try {
                    binding.editLimit.text.toString().toInt()
                } catch (e: NumberFormatException) {
                    -1
                }

                draw = if (limit > 1) Draw(limit) else Draw()
                updateUI()
            }

            binding.buttonDraw -> {
                val nextNumber = draw.getNumber()
                if (nextNumber != null) {
                    // Exibe o número sorteado
                    binding.textviewExit.text = nextNumber.toString()
                    updateListview()
                } else {

                    Toast.makeText(this, "Todos os números do intervalo foram sorteados!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun setClickListener() {
        binding.buttonDraw.setOnClickListener(this)
        binding.buttonUseLimit.setOnClickListener(this)
    }

    private fun updateUI() {
        val str = String.format("Intervalo de 1 à %,d.", draw.getHighBorder())

        binding.textviewInterval.text = str
        binding.editLimit.text.clear()
        binding.textviewExit.text = getString(R.string.inicie_o_sorteio)

        updateListview()
    }

    private fun updateListview() {
        val adapter = NumeroSorteadoAdapter(this, draw.getHistory())
        binding.listviewDraw.adapter = adapter
    }
}
