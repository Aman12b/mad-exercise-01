/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package at.ac.fhcampuswien


class App {
    // Game logic for a number guessing game
    fun playNumberGame(digitsToGuess: Int = 4) {
        //TODO: build a menu which calls the functions and works with the return values
        val ran = generateRandomNonRepeatingNumber(digitsToGuess);

        while(true){
            println("guess number:")
            val input = readLine()
            var numb : Int? = null

            while (numb == null){
                numb = input?.toIntOrNull()
            }

            val response = checkUserInputAgainstGeneratedNumber(numb, ran)
            if(response.m == digitsToGuess){
                println("Well palyed you won!")
                break
            }
            println(response.toString())
        }
    }

    /**
     * Generates a non-repeating number of a specified length between 1-9.
     *
     * Note: The function is designed to generate a number where each digit is unique and does not repeat.
     * It is important to ensure that the length parameter does not exceed the maximum possible length
     * for non-repeating digits (which is 9 excluding 0 for base-10 numbers).
     *
     * @param length The length of the non-repeating number to be generated.
     *               This dictates how many digits the generated number will have.
     * @return An integer of generated non-repeating number.
     *         The generated number will have a number of digits equal to the specified length and will
     *         contain unique, non-repeating digits.
     * @throws IllegalArgumentException if the length is more than 9 or less than 1.
     */
    val generateRandomNonRepeatingNumber: (Int) -> Int = { length ->
        if(length <= 0 || length > 9){
            throw IllegalArgumentException();
        }
        val numbersList = (1..9).toList().shuffled()
        println(numbersList)
        numbersList.take(length).joinToString("").toInt()
    }

    /**
     * Compares the user's input integer against a generated number for a guessing game.
     * This function evaluates how many digits the user guessed correctly and how many of those
     * are in the correct position. The game generates number with non-repeating digits.
     *
     * Note: The input and the generated number must both be numbers.
     * If the inputs do not meet these criteria, an IllegalArgumentException is thrown.
     *
     * @param input The user's input integer. It should be a number with non-repeating digits.
     * @param generatedNumber The generated number with non-repeating digits to compare against.
     * @return [CompareResult] with two properties:
     *         1. `n`: The number of digits guessed correctly (regardless of their position).
     *         2. `m`: The number of digits guessed correctly and in the correct position.
     *         The result is formatted as "Output: m:n", where "m" and "n" represent the above values, respectively.
     * @throws IllegalArgumentException if the inputs do not have the same number of digits.
     */
    val checkUserInputAgainstGeneratedNumber: (Int, Int) -> CompareResult = { input, generatedNumber ->
        //TODO implement the function
        if(input.toString().isEmpty() || input.toString().length > 9 || generatedNumber.toString().length != input.toString().length) {
            throw IllegalArgumentException();
        }
        val castran = generatedNumber.toString()
        val castin = input.toString()
        var copycastin = castin;
        var n = 0
        var m = 0
        for (i in 0 until castran.length){
           if(castin[i] == castran[i]){
               m += 1
           }
            for (g in 0 until castin.length){
                if(castran[i] == copycastin[g]){
                    n += 1
                    copycastin = copycastin.replace(copycastin[g] ,'0');
                }
            }
        }
        CompareResult(n, m)   // return value is a placeholder
    }
}

fun main() {
    println("Hello World!")
    val app = App()
    app.playNumberGame()
    app.playNumberGame(6)
    // TODO: call the App.playNumberGame function with and without default arguments
}
