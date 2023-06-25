package com.smartdev.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smartdev.lemonadeapp.ui.theme.LemonadeAppTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun StepsUIDesign(modifier: Modifier) {

    var currentIndex by remember { mutableStateOf(1) }
    var squeezeTapCount by remember { mutableStateOf(2) }

    var step_text=when(currentIndex)
    {
        1->R.string.step1_text
        2->R.string.step2_text
        3->R.string.step3_text
        4->R.string.step4_text
        else->R.string.step1_text
    }
    var imageResource=when(currentIndex)
    {
        1->R.drawable.lemon_tree
        2->R.drawable.lemon_squeeze
        3->R.drawable.lemon_drink
        4->R.drawable.lemon_restart
        else->R.drawable.lemon_tree
    }
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id =imageResource),
            modifier = Modifier
                .size(300.dp)
                .clickable {

                    when(currentIndex)
                    {
                        1->{
                            squeezeTapCount=(1..6).random()
                            currentIndex += 1
                        }
                        2->{
                            if(squeezeTapCount==0)
                            {
                                currentIndex += 1
                            }
                            else
                            {
                                squeezeTapCount--
                            }
                        }
                        3->{
                            currentIndex += 1
                        }
                        4->{
                            currentIndex = 1
                        }
                    }


                },
            contentDescription ="1" )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(id = step_text),
        fontSize = 20.sp,
            color = Color.White
            )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeAppTheme {
        StepsUIDesign(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .wrapContentSize(Alignment.Center))
    }
}