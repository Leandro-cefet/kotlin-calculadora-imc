package com.example.calculadoradeimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoradeimc.ui.theme.CalculadoraDeIMCTheme
import java.util.Locale
import kotlin.math.roundToInt


object LimitesIMC {
    const val LIMITE_ABAIXO_PESO = 18.5
    const val LIMITE_PESO_NORMAL = 24.9
    const val LIMITE_SOBREPESO = 29.9
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraDeIMCTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculadoraimcLayout()
                }
            }
        }
    }
}

@Composable
fun CalculadoraimcLayout() {

    var pesoInput by remember { mutableStateOf("") }
    var alturaInput by remember { mutableStateOf("") }
    var arredondar by remember { mutableStateOf(false) }


    var imcExibido by remember { mutableStateOf("--") }
    var classificacaoExibida by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Calcular IMC",
            fontSize = 24.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.Start)
        )


        EditNumberField(
            label = "Peso (kg)",
            value = pesoInput,
            onValueChange = { pesoInput = it },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )


        EditNumberField(
            label = "Altura (m)",
            value = alturaInput,
            onValueChange = { alturaInput = it },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )


        ArredondarRow(
            arredondar = arredondar,
            onArredondarChanged = { arredondar = it },
            modifier = Modifier.padding(bottom = 32.dp)
        )


        Button(
            onClick = {

                val peso = pesoInput.toDoubleOrNull() ?: 0.0
                val altura = alturaInput.toDoubleOrNull() ?: 0.0

                if (peso > 0.0 && altura > 0.0) {
                    val imc = calcularIMC(peso, altura)
                    classificacaoExibida = obterClassificacao(imc)


                    imcExibido = if (arredondar) {
                        imc.roundToInt().toString()
                    } else {
                        String.format(Locale.US, "%.2f", imc)
                    }
                } else {
                    imcExibido = "Dados Inválidos"
                    classificacaoExibida = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Calcular")
        }

        Spacer(modifier = Modifier.height(24.dp))


        Text(
            text = "IMC: $imcExibido",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )


        if (classificacaoExibida.isNotEmpty()) {
            Text(
                text = classificacaoExibida,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun EditNumberField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        modifier = modifier
    )
}


@Composable
fun ArredondarRow(
    arredondar: Boolean,
    onArredondarChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Arredondar resultado")
        Switch(
            checked = arredondar,
            onCheckedChange = onArredondarChanged,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        )
    }
}


fun calcularIMC(peso: Double, altura: Double): Double {
    if (altura == 0.0) return 0.0
    return peso / (altura * altura)
}


fun obterClassificacao(imc: Double): String {
    return when {
        imc < LimitesIMC.LIMITE_ABAIXO_PESO -> "Abaixo do peso"
        imc <= LimitesIMC.LIMITE_PESO_NORMAL -> "Peso normal"
        imc <= LimitesIMC.LIMITE_SOBREPESO -> "Sobrepeso"
        else -> "Obesidade"
    }
}


