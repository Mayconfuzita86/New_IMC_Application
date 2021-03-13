package com.dioinnovation.new_imc_app


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.dioinnovation.new_imc_app.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun setListeners() {
        alturaEDT?.doAfterTextChanged { text ->
            Toast.makeText(this, text.toString(), Toast.LENGTH_SHORT).show()
        }

        pesoEDT?.doOnTextChanged { text, _, _, _ ->
            //   titleTXT.text = text
        }

        buttonCALC?.setOnClickListener {
            calcularIMC(pesoEDT.text.toString(), alturaEDT.text.toString())
        }
    }

    private fun calcularIMC(peso: String, altura: String) {
        val peso = peso.toFloatOrNull()
        val altura = altura.toFloatOrNull()

        if (peso!= null && altura != null){
            val imc = peso / (altura * altura)

            if(imc < 18.5){
                result_imc.text = "Desnutrição"
            } else if (imc >= 18.5 && imc <= 24.9){
                result_imc.text = "Eutrofia"
            } else if (imc >= 25.0 && imc <= 29.9){
                result_imc.text = "Sobrepeso"
            } else if (imc >= 30.0 && imc <= 34.9){
                result_imc.text = "Obesidade Grau I"
            } else if (imc >= 35.0 && imc <= 39.9){
                result_imc.text = "Obesidade Grau II - Severa"
            } else if (imc >= 40.0 ){
                result_imc.text = "Obesidade Grau III - Mórbida"
            }

            textSeu.text = "IMC na faixa de:"
            titleTXT.text = "%.2f ".format(imc)
        }
    }
}