package com.fjdjf.sporttt.ui.screens.game

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fjdjf.sporttt.ui.components.BaseScreen
import com.fjdjf.sporttt.ui.components.noRippleClickable
import com.fjdjf.sporttt.ui.components.red
import org.jetbrains.compose.resources.painterResource
import sport.composeapp.generated.resources.Res
import sport.composeapp.generated.resources.field

@Composable
fun GameScreen(
    new: () -> Unit
) {

    BaseScreen("Offside Checker"){
        GameBoard { new() }
    }
}

@Composable
fun GameBoard(
    new: () -> Unit
) {

    var step by remember { mutableStateOf(0) }
    var isOffside by remember { mutableStateOf(false) }
    var isGameOver by remember { mutableStateOf(false) }

    // Состояния для точек и линий
    val history = remember { mutableStateListOf<GameState>() }
    var currentState by remember { mutableStateOf(GameState()) }

    var lineThickness by remember { mutableStateOf(0.5f) }
    var dotRadius by remember { mutableStateOf(0.5f) }

    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    Box(Modifier.fillMaxSize()){

        Column (
            Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            if (isGameOver){
                InactiveTopBar(isOffside)
            }else{
                ActiveTopBar(steps[step])
            }
            Spacer(Modifier.weight(1f))
            GameField(scale, step, lineThickness, dotRadius,  history.toList(), currentState, changeState = {
                currentState = it
            })
            Spacer(Modifier.weight(1f))
            BottomGameBar(!isGameOver, {
                if(step != 0){
                    history.remove(history.last())
                    step --
                }
            }, {
                when(step){
                    0, 1 -> if(currentState.firstLineY != null ) history.add(currentState)
                    2 -> if (currentState.attackerBodyY != null) history.add(currentState)
                    3 -> if (currentState.secondLineY != null) history.add(currentState)
                    4 -> if (currentState.defenderFeetY != null) history.add(currentState)
                    5 -> if (currentState.defenderBodyY != null) history.add(currentState)
                    6 -> if (currentState.attackerFeetY != null) history.add(currentState)
                }
                currentState = GameState()
                if (step < 6) {
                    if (history.size == step + 1){
                        step++
                    }
                } else {
                    isOffside = checkOffside(history)
                    isGameOver = true
                }
            }, {
                new()
            }) {
                new()
            }
        }
    }


}

@Composable
fun GameField(
    scale: Float,
    step: Int,
    lineThickness: Float,
    dotRadius: Float,
    history: List<GameState>,
    currentState: GameState,
    changeState: (GameState) -> Unit,
) {
    val lineColors = listOf(
        Color.Red, Color.Red, // Первая линия
        Color.Blue,           // Тело атакующего
        Color.Green,          // Вторая линия
        Color.Magenta,        // Ноги защитника
        Color.Cyan,           // Тело защитника
        Color.Yellow          // Ноги атакующего
    )

    var offset by remember { mutableStateOf(Offset.Zero) }
    Box(contentAlignment = Alignment.Center){

        Image(
            painterResource(Res.drawable.field), "",
            Modifier
                .size(250.dp, 400.dp)
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offset.x,
                    translationY = offset.y
                )
        )

        // Обработчик нажатий на поле
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .pointerInput(step) {
                    detectTapGestures { offset ->
                        when (step) {
                            0, 1 -> changeState(
                                currentState.copy(
                                    firstLineY = offset.y,
                                    x = offset.x
                                )
                            )

                            2 -> changeState(
                                currentState.copy(
                                    attackerBodyY = offset.y,
                                    x = offset.x
                                )
                            )

                            3 -> changeState(
                                currentState.copy(
                                    secondLineY = offset.y,
                                    x = offset.x
                                )
                            )

                            4 -> changeState(
                                currentState.copy(
                                    defenderFeetY = offset.y,
                                    x = offset.x
                                )
                            )

                            5 -> changeState(
                                currentState.copy(
                                    defenderBodyY = offset.y,
                                    x = offset.x
                                )
                            )

                            6 -> changeState(
                                currentState.copy(
                                    attackerFeetY = offset.y,
                                    x = offset.x
                                )
                            )
                        }
                    }
                }
        ) {
            // Отрисовка всех элементов из истории
            history.forEach { state ->
                drawState(lineThickness, dotRadius,state, lineColors)
            }

            // Отрисовка текущего временного элемента
            drawState(lineThickness, dotRadius, currentState, lineColors, isTemporary = true)
        }
    }

}

// Модель состояния для каждого шага
data class GameState(
    val firstLineY: Float? = null,
    val attackerBodyY: Float? = null,
    val secondLineY: Float? = null,
    val defenderFeetY: Float? = null,
    val defenderBodyY: Float? = null,
    val attackerFeetY: Float? = null,
    val x: Float? = null
) {
    fun isStepValid(step: Int): Boolean = when (step) {
        0, 1 -> firstLineY != null
        2 -> attackerBodyY != null
        3 -> secondLineY != null
        4 -> defenderFeetY != null
        5 -> defenderBodyY != null
        6 -> attackerFeetY != null
        else -> false
    }
}

// Функция отрисовки состояния
private fun DrawScope.drawState(
    lineThickness: Float,
    dotRadius: Float,
    state: GameState,
    colors: List<Color>,
    isTemporary: Boolean = false
) {
    // Первая линия
    state.firstLineY?.let { y ->
        drawLine(
            color = colors[0],
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = 10*lineThickness,
            pathEffect = if (isTemporary) PathEffect.dashPathEffect(floatArrayOf(20f, 20f)) else null
        )
    }

    // Остальные элементы
    listOf(
        state.attackerBodyY to colors[2],
        state.secondLineY to colors[3],
        state.defenderFeetY to colors[4],
        state.defenderBodyY to colors[4],
        state.attackerFeetY to colors[6]
    ).forEach { (y, color) ->
        y?.let {
            drawLine(
                color = color,
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 10*lineThickness,
                pathEffect = if (isTemporary) PathEffect.dashPathEffect(floatArrayOf(20f, 20f)) else null
            )
            drawCircle(
                color = color,
                center = Offset(state.x!!, it),
                radius = 30*dotRadius,
                style = if (isTemporary) Stroke(2f) else Fill
            )
        }
    }
}

// Логика проверки оффсайда
private fun checkOffside(history: List<GameState>): Boolean {
    val state = history.last()



    // Получаем координаты линий и точек
    val firstLineY = history[0].firstLineY ?: return false // Линия мяча
    val secondLineY = history[3].secondLineY ?: return false // Линия защиты
    val attackerBodyY = history[2].attackerBodyY ?: return false // Тело атакующего
    val attackerFeetY = state.attackerFeetY ?: return false // Ноги атакующего

    // Определяем минимальную позицию атакующего (самую выступающую часть)
    val attackerMinY = minOf(attackerBodyY, attackerFeetY)

    // Проверяем оффсайд
    return attackerMinY < secondLineY && attackerMinY < firstLineY
}

@Composable
fun BottomGameBar(
    isPlay: Boolean,
    undo: () -> Unit,
    confirm: () -> Unit,
    reset: () -> Unit,
    new: () -> Unit
) {

    Row (
        Modifier
            .padding(bottom = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        if (isPlay){
            ActionButton("Undo", undo)
            ActionButton("Confirm", confirm)
            ActionButton("Reset", reset)
        }else{
            ActionButton("New game", new)
        }
    }
}

@Composable
fun InactiveTopBar(
    isOffside: Boolean,
) {

    Column(
        Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isOffside){
            Text("Offside", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = red)
        }else{
            Text("Not offside", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White)
        }
    }
}

@Composable
fun ActiveTopBar(
    hint: String
) {

    Column(
        Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White.copy(alpha = 0.2f))
                .padding(8.dp)
        ){
            Text(hint, fontSize = 16.sp, color = Color.White, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun ActionButton(
    text: String,
    click: () -> Unit
) {

    Box(
        modifier = Modifier.clip(RoundedCornerShape(20.dp)).background(Color.Black)
            .border(1.dp, Color.White, RoundedCornerShape(20.dp)).padding(16.dp).noRippleClickable { click() },
        contentAlignment = Alignment.Center
    ){
        Text(text, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.White)
    }
}