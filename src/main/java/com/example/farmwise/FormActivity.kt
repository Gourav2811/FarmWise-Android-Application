package com.example.farmwise

import ai.onnxruntime.*
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmwise.ui.theme.FarmWiseTheme

class FormActivity : ComponentActivity() {

    private lateinit var ortEnvironment: OrtEnvironment
    private var ortSession: OrtSession? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            ortEnvironment = OrtEnvironment.getEnvironment()
            val inputStream = assets.open("random_forest.onnx")
            val modelBytes = inputStream.readBytes()
            inputStream.close()

            val sessionOptions = OrtSession.SessionOptions()
            ortSession = ortEnvironment.createSession(modelBytes, sessionOptions)

            Log.d("ONNX", "Model loaded successfully")

            ortSession?.inputNames?.forEach {
                Log.d("ONNX_INPUT", "Input name: $it")
            }
            ortSession?.outputNames?.forEach {
                Log.d("ONNX_OUTPUT", "Output name: $it")
            }

        } catch (e: Exception) {
            Log.e("ONNX", "Model loading failed: ${e.message}")
        }

        setContent {
            FarmWiseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CropFormUI(
                        modifier = Modifier.padding(innerPadding),
                        ortEnvironment = ortEnvironment,
                        ortSession = ortSession
                    )
                }
            }
        }
    }
}

@Composable
fun CropFormUI(
    modifier: Modifier = Modifier,
    ortEnvironment: OrtEnvironment?,
    ortSession: OrtSession?
) {
    var nitrogen by remember { mutableStateOf("") }
    var phosphorus by remember { mutableStateOf("") }
    var potassium by remember { mutableStateOf("") }
    var temperature by remember { mutableStateOf("") }
    var humidity by remember { mutableStateOf("") }
    var ph by remember { mutableStateOf("") }
    var rainfall by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("Recommended Crop: ") }

    val cropLabels = listOf(
        "apple", "banana", "blackgram", "chickpea", "coconut", "coffee", "cotton",
        "grapes", "jute", "kidneybeans", "lentil", "maize", "mango", "mothbeans",
        "mungbean", "muskmelon", "orange", "papaya", "pigeonpeas", "pomegranate",
        "rice", "watermelon"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "FarmWise \uD83C\uDF31",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        val fieldModifier = Modifier.fillMaxWidth()
        OutlinedTextField(value = nitrogen, onValueChange = { nitrogen = it }, label = { Text("Nitrogen (N)") }, modifier = fieldModifier)
        OutlinedTextField(value = phosphorus, onValueChange = { phosphorus = it }, label = { Text("Phosphorus (P)") }, modifier = fieldModifier)
        OutlinedTextField(value = potassium, onValueChange = { potassium = it }, label = { Text("Potassium (K)") }, modifier = fieldModifier)
        OutlinedTextField(value = temperature, onValueChange = { temperature = it }, label = { Text("Temperature (\u00B0C)") }, modifier = fieldModifier)
        OutlinedTextField(value = humidity, onValueChange = { humidity = it }, label = { Text("Humidity (%)") }, modifier = fieldModifier)
        OutlinedTextField(value = ph, onValueChange = { ph = it }, label = { Text("pH Level") }, modifier = fieldModifier)
        OutlinedTextField(value = rainfall, onValueChange = { rainfall = it }, label = { Text("Rainfall (mm)") }, modifier = fieldModifier)

        Button(
            onClick = {
                try {
                    if (ortEnvironment == null || ortSession == null) {
                        result = "Model not loaded properly."
                        return@Button
                    }

                    val inputs = floatArrayOf(
                        nitrogen.toFloatOrNull() ?: 0f,
                        phosphorus.toFloatOrNull() ?: 0f,
                        potassium.toFloatOrNull() ?: 0f,
                        temperature.toFloatOrNull() ?: 0f,
                        humidity.toFloatOrNull() ?: 0f,
                        ph.toFloatOrNull() ?: 0f,
                        rainfall.toFloatOrNull() ?: 0f
                    )

                    val inputName = ortSession.inputNames.firstOrNull()
                    val outputName = ortSession.outputNames.firstOrNull()

                    if (inputName == null || outputName == null) {
                        result = "Model input/output name missing."
                        return@Button
                    }

                    val inputTensor = OnnxTensor.createTensor(
                        ortEnvironment,
                        inputs.reshape(longArrayOf(1, inputs.size.toLong()))
                    )

                    val output = ortSession.run(mapOf(inputName to inputTensor))
                    val outputValue = output[outputName]

                    var labelIndex: Int? = null

                    if (outputValue is OnnxValue) {
                        val rawValue = outputValue.value
                        Log.d("ONNX", "Raw output: $rawValue")

                        labelIndex = when (rawValue) {
                            is LongArray -> rawValue.firstOrNull()?.toInt()
                            is IntArray -> rawValue.firstOrNull()
                            is Array<*> -> {
                                val firstElem = rawValue.firstOrNull()
                                if (firstElem is LongArray) {
                                    firstElem.firstOrNull()?.toInt()
                                } else if (firstElem is IntArray) {
                                    firstElem.firstOrNull()
                                } else if (firstElem is Number) {
                                    firstElem.toInt()
                                } else null
                            }
                            else -> null
                        }

                    }

                    result = if (labelIndex != null && labelIndex in cropLabels.indices) {
                        "Recommended Crop: ${cropLabels[labelIndex]}"
                    } else {
                        "Prediction failed: Output index not found or out of bounds"
                    }

                } catch (e: Exception) {
                    result = "Prediction failed: ${e.message}"
                    Log.e("ONNX", "Prediction error", e)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Get Crop Recommendation")
        }

        Text(
            text = result,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 24.dp)
        )
    }
}

fun FloatArray.reshape(shape: LongArray): Array<FloatArray> {
    require(this.size == shape[1].toInt()) { "Shape mismatch!" }
    return arrayOf(this)
}
