package com.user.cardapp

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.ui.text.input.TextFieldValue
import java.text.SimpleDateFormat
import java.util.Date
import java.util.regex.Pattern

fun TextFieldValue.getText() = this.text.trim()

fun Context.showToast(message: String) {
    if (message.isNotEmpty())
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


fun cardHolderNameValidation(name: String?): String {
    if (!name.isNullOrEmpty()) {
        val letter: Pattern = Pattern.compile("[^a-zA-z0-9.' -]")


        val hasLetter = name.let { letter.matcher(it) }

        val errorMessage = if (name.length < 2 || name.length > 40) {
            "Please enter valid card holder name"
        } else if (hasLetter?.find() == true) {
            "Please enter valid card holder name"
        } else {
            ""
        }

        return errorMessage
    } else {
        return ""
    }
}


fun cardNumberValidation(number: String?): String {
    if (!number.isNullOrEmpty()) {
        val letter: Pattern = Pattern.compile("[^0-9 ]")
        val hasLetter = number.let { letter.matcher(it) }

        val errorMessage = if (number.length < 15 || number.length > 16) {
            "Please enter valid card number"
        } else if (hasLetter.find()) {
            "Please enter valid card number"
        } else {
            ""
        }
        return errorMessage
    } else {
        return ""
    }
}

@SuppressLint("SimpleDateFormat")
fun expiryDateValidation(input: String) =
    if (input.isNotEmpty()) {
        if (input.length == 4) {
            if (input.subSequence(0, 2).toString().toInt() > 12 ) {
                "Please enter valid month."
            } else {
                val sdf = SimpleDateFormat("MM/yy")
                val currentDate = sdf.format(Date())
                if (input.subSequence(2, 4).toString().toInt() == currentDate.split("/")[1].toInt()) {
                    if (input.subSequence(0, 2).toString()
                            .toInt() < currentDate.split("/")[0].toInt() ){
                        "Please enter valid month."
                    } else {
                        ""
                    }
                } else if (input.subSequence(2, 4).toString()
                        .toInt() < currentDate.split("/")[1].toInt()
                ) {
                    "Please enter valid year."
                } else {
                    ""
                }
            }

        } else {
            "Please enter valid expiry date."
        }
    } else {
        ""
    }


@SuppressLint("SimpleDateFormat")
fun cvvValidation(input: String) =
    if (input.isNotEmpty()) {
        if (input.length < 3 || input.length > 4) {
            "Please enter valid cvv"
        } else {
            ""
        }
    } else {
        ""
    }


@SuppressLint("SimpleDateFormat")
fun zipCodeValidation(input: String) =
    if (input.isNotEmpty()) {
        if (input.length != 5) {
            "Please enter valid zip code"
        } else {
            ""
        }
    } else {
        ""
    }


@SuppressLint("SimpleDateFormat")
fun nickNameValidation(input: String?): String {

    if (!input.isNullOrEmpty()) {
        val letter: Pattern = Pattern.compile("[^a-zA-z0-9!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_` {|}~]")

        val hasLetter = input.let { letter.matcher(it) }

        val errorMessage = if (input.length > 15) {
            "Please enter valid card holder name"
        } else if (hasLetter.find()) {
            "Please enter valid card holder name"
        } else {
            ""
        }
        return errorMessage
    } else {
        return ""
    }

}