package com.user.cardapp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.user.cardapp.ui.theme.CardappTheme


@Composable
fun AddPaymentCard() {

    var nameText by remember { mutableStateOf(TextFieldValue()) }
    var cardNumber by remember { mutableStateOf(TextFieldValue()) }
    var expiryNumber by remember { mutableStateOf(TextFieldValue()) }
    var cvvNumber by remember { mutableStateOf(TextFieldValue()) }
    var zipCode by remember { mutableStateOf(TextFieldValue()) }
    var nickName by remember { mutableStateOf(TextFieldValue()) }

    var nameErrorMessage by remember { mutableStateOf("") }
    var cardNumberErrorMessage by remember { mutableStateOf("") }
    var expiryNumberErrorMessage by remember { mutableStateOf("") }
    var cvvNumberErrorMessage by remember { mutableStateOf("") }
    var zipCodeErrorMessage by remember { mutableStateOf("") }
    var nickNameErrorMessage by remember { mutableStateOf("") }

    val viewModel = viewModel<AddPaymentCardViewModel>()

    val localContext = LocalContext.current


    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        item {
            InputItem(
                textFieldValue = nameText,
                label = stringResource(id = R.string.card_holder_name),
                onTextChanged = {
                    if (it.text.length <= 40) {
                        nameText = it
                        nameErrorMessage = cardHolderNameValidation(it.getText())
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                errorMessage = nameErrorMessage
            )
        }

        item {
            InputItem(
                textFieldValue = cardNumber,
                label = stringResource(id = R.string.card_holder_number),
                keyboardType = KeyboardType.Number,
                onTextChanged = {
                   if (it.text.length <= 16) {
                        cardNumber = it
                        cardNumberErrorMessage = cardNumberValidation(it.getText())
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                visualTransformation = CreditCardFilter,
                errorMessage = cardNumberErrorMessage,
                leadingIcon = androidx.core.R.drawable.ic_call_answer_video
            )
        }

        item {

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                InputItem(
                    textFieldValue = expiryNumber,
                    label = stringResource(id = R.string.expiry_date),
                    keyboardType = KeyboardType.Number,
                    onTextChanged = {
                        if (it.text.length <= 4) {
                            expiryNumber = it
                            expiryNumberErrorMessage = expiryDateValidation(it.getText())
                        }
                    },
                    modifier = Modifier.weight(1f)
                        .padding(end = 8.dp),
                    visualTransformation = ExpiryDateFilter,
                    errorMessage = expiryNumberErrorMessage
                )

                InputItem(
                    textFieldValue = cvvNumber,
                    label = stringResource(id = R.string.cvv),
                    keyboardType = KeyboardType.Number,
                    onTextChanged = {
                        if (it.text.length <= 4) {
                            cvvNumber = it
                            cvvNumberErrorMessage = cvvValidation(it.getText())
                        }
                    },
                    modifier = Modifier.weight(1f)
                        .padding(end = 8.dp),
                    errorMessage = cvvNumberErrorMessage,
                    trailingIcon = androidx.core.R.drawable.ic_call_decline_low
                )

            }

        }

        item {
            InputItem(
                textFieldValue = zipCode,
                label = stringResource(id = R.string.zip_code),
                keyboardType = KeyboardType.Number,
                onTextChanged = {
                    if (it.text.length <= 5) {
                        zipCode = it
                        zipCodeErrorMessage = zipCodeValidation(it.getText())
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                errorMessage = zipCodeErrorMessage
            )
        }

        item {
            InputItem(
                textFieldValue = nickName,
                label = stringResource(id = R.string.nick_name),
                onTextChanged = {
                    if (it.text.length <= 15) {
                        nickName = it
                        nickNameErrorMessage = nickNameValidation(it.getText())
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                errorMessage = nickNameErrorMessage
            )
        }

        item {
            Button(
                onClick = {
                    viewModel.carValidation(
                        cardHolderName = nameText.getText(),
                        cardNumber = cardNumber.getText(),
                        expiryNumber = expiryNumber.getText(),
                        cvvNumber = cvvNumber.getText(),
                        zipCode = zipCode.getText(),
                        nickName = nickName.getText()
                    ) {
                        nameErrorMessage = ""
                        cardNumberErrorMessage = ""
                        expiryNumberErrorMessage = ""
                        cvvNumberErrorMessage = ""
                        zipCodeErrorMessage = ""
                        nickNameErrorMessage = ""

                        when (viewModel.toastMessage) {
                            CardValidationEnum.ALL_INPUT_VALID -> {
                                localContext.showToast("All Ok")
                                viewModel.resetToast()
                            }

                            CardValidationEnum.ENTER_CARD_HOLDER_NAME -> {
                                nameErrorMessage = "Please enter card holder name."
                                localContext.showToast(nameErrorMessage)
                                viewModel.resetToast()
                            }

                            CardValidationEnum.ENTER_VALID_CARD_HOLDER_NAME -> {
                                nameErrorMessage = "Please enter valid card holder name."
                                localContext.showToast(nameErrorMessage)
                                viewModel.resetToast()
                            }

                            CardValidationEnum.ENTER_CARD_NUMBER -> {
                                cardNumberErrorMessage = "Please enter card number."
                                localContext.showToast(cardNumberErrorMessage)
                                viewModel.resetToast()
                            }

                            CardValidationEnum.ENTER_VALID_CARD_NUMBER -> {
                                cardNumberErrorMessage = "Please enter valid card number."
                                localContext.showToast(cardNumberErrorMessage)
                                viewModel.resetToast()
                            }

                            CardValidationEnum.ENTER_EXPIRY_DATE -> {
                                expiryNumberErrorMessage = "Please enter expiry date."
                                localContext.showToast(expiryNumberErrorMessage)
                                viewModel.resetToast()
                            }

                            CardValidationEnum.ENTER_VALID_EXPIRY_DATE -> {
                                expiryNumberErrorMessage = "Please enter valid expiry date."
                                localContext.showToast(expiryNumberErrorMessage)
                                viewModel.resetToast()
                            }

                            CardValidationEnum.ENTER_CVV_NUMBER -> {
                                cvvNumberErrorMessage = "Please enter cvv number."
                                localContext.showToast(cvvNumberErrorMessage)
                                viewModel.resetToast()
                            }

                            CardValidationEnum.ENTER_VALID_CVV_NUMBER -> {
                                cvvNumberErrorMessage = "Please enter valid cvv number."
                                localContext.showToast(cvvNumberErrorMessage)
                                viewModel.resetToast()
                            }

                            CardValidationEnum.ENTER_ZIP_CODE -> {
                                zipCodeErrorMessage = "Please enter zip code."
                                localContext.showToast(zipCodeErrorMessage)
                                viewModel.resetToast()
                            }

                            CardValidationEnum.ENTER_VALID_ZIP_CODE -> {
                                zipCodeErrorMessage = "Please enter valid zip code."
                                localContext.showToast(zipCodeErrorMessage)
                                viewModel.resetToast()
                            }

                            CardValidationEnum.ENTER_NICK_NAME -> {
                                nickNameErrorMessage = "Please enter nick name."
                                localContext.showToast(nickNameErrorMessage)
                                viewModel.resetToast()
                            }

                            CardValidationEnum.ENTER_VALID_NICK_NAME -> {
                                nickNameErrorMessage = "Please enter valid nick name."
                                localContext.showToast(nickNameErrorMessage)
                                viewModel.resetToast()
                            }

                            CardValidationEnum.EMPTY -> {}
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.save),
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(horizontal = 30.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddPaymentPreview() {
    CardappTheme {
        AddPaymentCard()
    }
}