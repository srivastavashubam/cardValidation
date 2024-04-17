package com.user.cardapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AddPaymentCardViewModel : ViewModel() {

    // Mutable state variables
    var toastMessage by mutableStateOf(CardValidationEnum.EMPTY)


    fun resetToast() {
        toastMessage = CardValidationEnum.EMPTY
    }


    fun carValidation(
        cardHolderName: String?,
        cardNumber: String?,
        expiryNumber: String?,
        cvvNumber: String?,
        zipCode: String?,
        nickName: String?,
        callback: () -> Unit,
    ) {

       toastMessage = if (cardHolderName.isNullOrEmpty()) {
            CardValidationEnum.ENTER_CARD_HOLDER_NAME
        } else if (cardHolderNameValidation(cardHolderName).isNotEmpty()) {
            CardValidationEnum.ENTER_VALID_CARD_HOLDER_NAME
        }  else if (cardNumber.isNullOrEmpty()) {
            CardValidationEnum.ENTER_CARD_NUMBER
        } else if (cardNumberValidation(cardNumber).isNotEmpty()) {
            CardValidationEnum.ENTER_VALID_CARD_NUMBER
        } else if (expiryNumber.isNullOrEmpty()) {
            CardValidationEnum.ENTER_EXPIRY_DATE
        } else if (expiryDateValidation(expiryNumber).isNotEmpty()) {
            CardValidationEnum.ENTER_VALID_EXPIRY_DATE
        } else if (cvvNumber.isNullOrEmpty()) {
            CardValidationEnum.ENTER_CVV_NUMBER
        } else if (cvvValidation(cvvNumber).isNotEmpty()) {
            CardValidationEnum.ENTER_VALID_CVV_NUMBER
        } else if (zipCode.isNullOrEmpty()) {
            CardValidationEnum.ENTER_ZIP_CODE
        } else if (zipCodeValidation(zipCode).isNotEmpty()) {
            CardValidationEnum.ENTER_VALID_ZIP_CODE
        } else if (nickName.isNullOrEmpty()) {
            CardValidationEnum.ENTER_NICK_NAME
        }  else if (nickNameValidation(nickName).isNotEmpty()) {
            CardValidationEnum.ENTER_VALID_NICK_NAME
        } else {
            CardValidationEnum.ALL_INPUT_VALID
        }

        callback()
    }

}