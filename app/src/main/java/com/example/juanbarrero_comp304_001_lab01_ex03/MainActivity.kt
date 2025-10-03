package com.example.juanbarrero_comp304_001_lab01_ex03

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Checkbox
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.juanbarrero_comp304_001_lab01_ex03.ui.theme.JuanBarrero_COMP304001_Lab01_ex03Theme
import kotlin.math.exp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JuanBarrero_COMP304001_Lab01_ex03Theme {
            PizzaOrderForm()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizzaOrderForm() {
    var orderId by remember { mutableStateOf(TextFieldValue("")) }
    var customerName by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }

    // Pizza size dropdown
    val pizzaSizes = listOf("Small", "Medium", "Large", "Extra Large")
    var expanded by remember { mutableStateOf(false) }
    var selectedSize by remember { mutableStateOf(pizzaSizes[0]) }

    val toppings = listOf("Pepperoni", "Mushroom", "Onions", "Sausage", "Olives", "Extra Cheese")
    var selectedToppings by remember { mutableStateOf(setOf<String>()) }

    val deliveryOptions = listOf("PickUp", "Home Delivery")
    var selectedDeliveryOption by remember { mutableStateOf(deliveryOptions[0]) }

    var showSumary by remember { mutableStateOf(false) }

    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),   // 👈 makes it scrollable
        verticalArrangement = Arrangement.Top
    )  {

        Text(
            text = "Pizza Order Form",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier // Make the component span the full width
                .background(MaterialTheme.colorScheme.primaryContainer) // Add a background color
                .fillMaxWidth()
                .padding(16.dp), // Add padding *inside* the background
            color = MaterialTheme.colorScheme.onPrimaryContainer, // Use a color that's readable on the container
            textAlign = TextAlign.Center // Center the text within the colored background
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Order ID
        TextField(
            value = orderId,
            onValueChange = { orderId = it },
            label = { Text("Order ID") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (orderId.text.isEmpty()) {

        }
        // Customer Name
        TextField(
            value = customerName,
            onValueChange = { customerName = it },
            label = { Text("Customer Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Email
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email ID") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        //Pizza Size dropdown
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            TextField(
                value = selectedSize,
                onValueChange = {},
                readOnly = true,
                label = { Text("Pizza Size") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(), // Optional: for default dropdown colors
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()

            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                pizzaSizes.forEach { size ->
                    DropdownMenuItem(
                        text = { Text(text = size) },
                        onClick = {
                            selectedSize = size
                            expanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        //Toppings
        Text("Select Toppings: ")
        toppings.forEach { topping ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = topping in selectedToppings,
                    onCheckedChange = { isChecked ->
                        selectedToppings = if (isChecked) {
                            selectedToppings + topping
                        } else {
                            selectedToppings - topping
                        }
                    }
                )
                Text(topping)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text("Delivery Mode:")
        deliveryOptions.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .selectable(
                        selected = (option == selectedDeliveryOption),
                        onClick = { selectedDeliveryOption = option }
                    )
                    .padding(2.dp)
            )

            {
                RadioButton(
                    selected = (option == selectedDeliveryOption),
                    onClick = { selectedDeliveryOption = option }
                )
                Text(option)

            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                when{
                    orderId.text.isEmpty() -> {
                        errorMessage = "Order ID is required"
                        showSumary = false
                    }
                    !orderId.text.all { it.isDigit() } -> {   // 👈 NEW validation
                        errorMessage = "Order ID must contain only numbers"
                        showSumary = false
                    }
                    customerName.text.isEmpty() -> {
                        errorMessage = "Customer Name is required"
                        showSumary = false
                    }
                    email.text.isEmpty() -> {
                        errorMessage = "Email is required"
                        showSumary = false
                    }
                    email.text.isBlank() || !email.text.contains("@") -> {
                        errorMessage = "Enter a valid Email ID (must contain @)"
                        showSumary = false
                    }

                    selectedSize.isEmpty() -> {
                        errorMessage = "Pizza size is required"
                        showSumary = false
                    }
                    selectedToppings.isEmpty() -> {
                        errorMessage = "Toppings are required"
                        showSumary = false
                    }
                    selectedDeliveryOption.isEmpty() -> {
                        errorMessage = "Delivery option is required"
                        showSumary = false
                    }
                    else -> {
                        errorMessage = ""
                        showSumary = true
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit Order")
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }


        if(showSumary){
            Text("Order Summary")
            Text("Order ID: ${orderId.text}")
            Text("Customer Name: ${customerName.text}")
            Text("Email:  ${email.text}")
            Text("Pizza Size: $selectedSize")
            Text("Toppings: ${selectedToppings.joinToString(", ")}")
            Text("Delivery Mode: $selectedDeliveryOption")
        }
    }
        }



