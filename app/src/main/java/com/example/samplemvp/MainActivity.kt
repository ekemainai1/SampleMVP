package com.example.samplemvp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.samplemvp.contract.MainActivityMVP
import com.example.samplemvp.di.App
import com.example.samplemvp.ui.theme.SampleMVPTheme
import javax.inject.Inject

class MainActivity : ComponentActivity(), MainActivityMVP.View {

    @Inject
    lateinit var presenter: MainActivityMVP.Presenter

    private val _userName = MutableLiveData("")
    private val userName: LiveData<String> = _userName
    private fun onNameChange(input: String) {
        _userName.value = input
    }

    private val _userEmail = MutableLiveData("")
    private val userEmail: LiveData<String> = _userEmail
    private fun onEmailChange(input: String) {
        _userEmail.value = input
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            SampleMVPTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
        presenter.getCurrentUser()
    }

    override fun getName(): String {
        return userName.value.toString()
    }

    override fun getEmail(): String {
        return userEmail.value.toString()
    }

    override fun showInputError() {
        Toast.makeText(this, "First Name or last name cannot be empty",
            Toast.LENGTH_SHORT).show();
    }

    override fun setName(name: String) {
        onNameChange(name)
    }

    override fun setEmail(email: String) {
        onEmailChange(email)
    }

    override fun showUserSavedMessage() {
        Toast.makeText(this, "User saved successfully",
            Toast.LENGTH_SHORT).show();
    }

    // UI Design
    @Composable
    fun TextFieldName(modifier: Modifier) {
        val username = userName.observeAsState()
        Column(modifier.padding(16.dp)) {
            TextField(
                value = username.value!!,
                onValueChange = { onNameChange(it) }
            )
            Text("The textfield has this text: " + userName.value)
        }
    }

    @Composable
    fun TextFieldEmail(modifier: Modifier) {
        val useremail = userEmail.observeAsState()
        Column(modifier.padding(16.dp)) {
            TextField(
                value = useremail.value!!,
                onValueChange = { onEmailChange(it) }
            )
            Text("The textfield has this text: " + userEmail.value)
        }
    }

    @Composable
    fun ButtonLogin(modifier: Modifier) {
        Button(onClick = { presenter.loginButtonClicked() },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.Red
            ), modifier = modifier.fillMaxWidth()) {
            Text("Login", textAlign = TextAlign.Center,
                color = Color.White,
                style = TextStyle.Default)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Greeting() {

        ConstraintLayout(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)) {
            val (nameCom, emailCom, buttonCom) = createRefs()

            TextFieldName(modifier = Modifier
                .constrainAs(nameCom){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

            TextFieldEmail(modifier = Modifier
                .constrainAs(emailCom){
                    top.linkTo(nameCom.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

            ButtonLogin(modifier = Modifier
                .constrainAs(buttonCom){
                    top.linkTo(emailCom.bottom)
                    start.linkTo(parent.start, 20.dp)
                    end.linkTo(parent.end, margin = 20.dp)
                    bottom.linkTo(parent.bottom)
                })

        }


    }

}



