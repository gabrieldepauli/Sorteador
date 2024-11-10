package br.edu.ifsp.dmo1.sorteador.model

class Draw(private val border: Int = 0) {
    private lateinit var strategy: SorteioStrategy
    private val history = ArrayList<Int>()

    init {
        strategy = if (border == 0) DefaultLimit else DefinedLimit(border)
    }

    fun getNumber(): Int? {

        if (history.size >= strategy.getHighBorder() - strategy.getLowBorder() + 1) {
            return null
        }

        var number: Int
        do {
            number = strategy.nextNumber()
        } while (history.contains(number))

        history.add(number)
        return number
    }

    fun getHistory() = ArrayList(history)
    fun getLowBorder() = strategy.getLowBorder()
    fun getHighBorder() = strategy.getHighBorder()
}
