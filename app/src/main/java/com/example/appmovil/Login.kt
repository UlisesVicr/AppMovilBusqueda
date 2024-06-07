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

class Login : AppCompatActivity() {
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var imageViewPasswordVisibility: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextEmail = findViewById(R.id.emailET)
        editTextPassword = findViewById(R.id.passwordET)
        imageViewPasswordVisibility = findViewById(R.id.passwordIcon)
    }

    fun RegisterInClicked(view: View) {
        startActivity(Intent(this, Register::class.java))
    }

    fun validarDatos(view: View) {
        val email = editTextEmail.text.toString()
        val contraseña = editTextPassword.text.toString()
        val patternEmail = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

        if (email.isBlank()) {
            Toast.makeText(this, "Por favor, ingresa tu correo electrónico", Toast.LENGTH_SHORT).show()
            return
        }

        if (contraseña.isBlank()) {
            Toast.makeText(this, "Por favor, ingresa tu contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        if (!(contraseña.length in 8..16)) {
            Toast.makeText(this, "La contraseña debe tener entre 8 y 16 caracteres", Toast.LENGTH_SHORT).show()
            return
        }

        if (!patternEmail.matcher(email).matches()) {
            Toast.makeText(this, "Por favor, ingresa un correo electrónico válido", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "Su login a sido exitoso", Toast.LENGTH_LONG).show()

    }

    fun togglePasswordVisibility(view: View) {
        toggleVisibility(editTextPassword, imageViewPasswordVisibility)
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
