package com.example.appmovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import java.util.regex.Pattern

class Register : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextConfirmPassword: EditText
    private lateinit var imageViewPasswordVisibility: ImageView
    private lateinit var imageViewConfirmPasswordVisibility: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editTextName = findViewById(R.id.nameET)
        editTextEmail = findViewById(R.id.emailET)
        editTextPassword = findViewById(R.id.passwordET)
        editTextConfirmPassword = findViewById(R.id.cPasswordET)
        imageViewPasswordVisibility = findViewById(R.id.passwordIcon)
        imageViewConfirmPasswordVisibility = findViewById(R.id.cPasswordIcon)
    }

    fun signInClicked(view: View) {
        startActivity(Intent(this, Login::class.java))
    }

    fun validarDatos(view: View) {
        val nombre = editTextName.text.toString()
        val correo = editTextEmail.text.toString()
        val contraseña = editTextPassword.text.toString()
        val confirmacionContraseña = editTextConfirmPassword.text.toString()
        val patternName = Pattern.compile("^[a-zA-Z ]+\$")
        val patternEmail = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

        if (nombre.isBlank()) {
            Toast.makeText(this, "Por favor, ingresa tu nombre", Toast.LENGTH_SHORT).show()
            return
        }

        if (correo.isBlank()) {
            Toast.makeText(this, "Por favor, ingresa tu correo electrónico", Toast.LENGTH_SHORT).show()
            return
        }

        if (contraseña.isBlank()) {
            Toast.makeText(this, "Por favor, ingresa tu contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        if (confirmacionContraseña.isBlank()) {
            Toast.makeText(this, "Por favor, confirma tu contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        if (!patternName.matcher(nombre).matches()){
            Toast.makeText(this, "Por favor, ingresa un nombre válido (solo letras)", Toast.LENGTH_SHORT).show()
            return
        }

        if (!(contraseña.length in 8..16)) {
            Toast.makeText(this, "La contraseña debe tener entre 8 y 16 caracteres", Toast.LENGTH_SHORT).show()
            return
        }

        if (contraseña != confirmacionContraseña) {
            Toast.makeText(this, "La contraseña y la confirmación de contraseña no coinciden", Toast.LENGTH_SHORT).show()
            return
        }

        if (!patternEmail.matcher(correo).matches()) {
            Toast.makeText(this, "Por favor, ingresa un correo electrónico válido", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "Su registro a sido exitoso", Toast.LENGTH_LONG).show()
    }

    fun togglePasswordVisibility(view: View) {
        toggleVisibility(editTextPassword, imageViewPasswordVisibility)
    }

    fun toggleConfirmPasswordVisibility(view: View) {
        toggleVisibility(editTextConfirmPassword, imageViewConfirmPasswordVisibility)
    }

    private fun toggleVisibility(editText: EditText, imageView: ImageView) {
        if (editText.inputType == (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            editText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            imageView.setImageResource(R.drawable.visible_password)
        } else {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            imageView.setImageResource(R.drawable.hide_password)
        }
        // Mueve el cursor al final del texto después de cambiar el tipo de entrada para mantener la posición del cursor.
        editText.setSelection(editText.text.length)
    }
}