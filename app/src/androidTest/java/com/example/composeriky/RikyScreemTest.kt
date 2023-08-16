package com.example.composeriky


import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import com.example.composeriky.UI.MyProgressBar
import com.example.composeriky.UI.RikyScreem
import com.example.composeriky.UI.RikyViewModel
import com.example.composeriky.domain.DoListRikyListUseCase
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


// si le das al play q esta en la clase hace todos sus test,
//si le das al play de un test especifico solo   hace ese
class RikyScreemTest {
    //Siempre se empieza con las signtes 2 líneas:
    @get:Rule
    val composeTestRule = createComposeRule()
    @Test
//minuscula
    fun whenMyProgressBar_thenProgressbarIs() {
        composeTestRule.setContent {
            //el componente a testear
            MyProgressBar()
        }
        //las acciones q quieres comprobar
        composeTestRule.onAllNodesWithTag("Progress")
    }



}


