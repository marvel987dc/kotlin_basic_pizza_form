🍕 Pizza Order Form App

A simple Android app built with Jetpack Compose that simulates placing a pizza order.
Users can enter order details, choose pizza size, toppings, and delivery mode. The app validates input and displays an order summary.

🚀 Features

📋 Form Inputs

Order ID (numbers only, required)

Customer Name (required)

Email ID (must contain @, required)

📦 Pizza Selection

Choose from 4 sizes: Small, Medium, Large, Extra Large

Multiple toppings available: Pepperoni, Mushroom, Onions, Sausage, Olives, Extra Cheese

🚚 Delivery Options

Pick-Up

Home Delivery

✅ Validation

No empty fields allowed

Order ID must contain only numbers

Email must contain @

At least one topping must be selected

Delivery option required

📜 Summary

Displays all entered details once the order is valid.

🎨 UI

Built entirely with Jetpack Compose (Material 3)

Scrollable layout for smaller screens

🖼️ Screenshots

(Add your emulator/device screenshots here)

🛠️ Tech Stack

Kotlin

Jetpack Compose (Material 3)

AndroidX Activity + Core

Gradle Build System

📂 Project Structure
app/
 ├── java/com/example/juanbarrero_comp304_001_lab01_ex03/
 │   ├── MainActivity.kt       # Entry point
 │   └── PizzaOrderForm.kt     # UI Composable with validation & summary
 └── res/
     ├── layout/               # Compose-managed UI
     └── values/               # Themes, colors, strings

▶️ How to Run

Clone the repo:

git clone https://github.com/marvel987dc/kotlin_basic_pizza_form.git


Open in Android Studio (Ladybug or later).

Sync Gradle.

Run on an emulator or physical device (Android 8.0+ recommended).

📌 Future Improvements

Add price calculation based on size and toppings.

Save orders in a database (Room).

Implement API call for placing real orders.

Add UI tests with Compose Test.
