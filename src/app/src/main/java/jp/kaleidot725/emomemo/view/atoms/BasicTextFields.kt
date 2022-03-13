package jp.kaleidot725.emomemo.view.atoms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

object BasicTextFields {
    @Composable
    fun DisplayLarge(text: String, onValueChange: (String) -> Unit, singleLine: Boolean = true, modifier: Modifier = Modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            modifier = modifier,
            textStyle = MaterialTheme.typography.displayLarge
        )
    }

    @Composable
    fun DisplayMedium(text: String, onValueChange: (String) -> Unit, singleLine: Boolean = true, modifier: Modifier = Modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            modifier = modifier,
            textStyle = MaterialTheme.typography.displayMedium
        )
    }

    @Composable
    fun DisplaySmall(text: String, onValueChange: (String) -> Unit, singleLine: Boolean = true, modifier: Modifier = Modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            modifier = modifier,
            textStyle = MaterialTheme.typography.displaySmall
        )
    }

    @Composable
    fun HeadlineLarge(text: String, onValueChange: (String) -> Unit, singleLine: Boolean = true, modifier: Modifier = Modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            modifier = modifier,
            textStyle = MaterialTheme.typography.headlineLarge
        )
    }

    @Composable
    fun HeadlineMedium(text: String, onValueChange: (String) -> Unit, singleLine: Boolean = true, modifier: Modifier = Modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            modifier = modifier,
            textStyle = MaterialTheme.typography.headlineMedium
        )
    }

    @Composable
    fun HeadlineSmall(text: String, onValueChange: (String) -> Unit, singleLine: Boolean = true, modifier: Modifier = Modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            modifier = modifier,
            textStyle = MaterialTheme.typography.headlineSmall
        )
    }

    @Composable
    fun TitleLarge(text: String, onValueChange: (String) -> Unit, singleLine: Boolean = true, modifier: Modifier = Modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            modifier = modifier,
            textStyle = MaterialTheme.typography.titleLarge
        )
    }

    @Composable
    fun TitleMedium(text: String, onValueChange: (String) -> Unit, singleLine: Boolean = true, modifier: Modifier = Modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            modifier = modifier,
            textStyle = MaterialTheme.typography.titleMedium
        )
    }

    @Composable
    fun TitleSmall(text: String, onValueChange: (String) -> Unit, singleLine: Boolean = true, modifier: Modifier = Modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            modifier = modifier,
            textStyle = MaterialTheme.typography.titleSmall
        )
    }

    @Composable
    fun BodyLarge(text: String, onValueChange: (String) -> Unit, singleLine: Boolean = true, modifier: Modifier = Modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            modifier = modifier,
            textStyle = MaterialTheme.typography.bodyLarge
        )
    }

    @Composable
    fun BodyMedium(text: String, onValueChange: (String) -> Unit, singleLine: Boolean = true, modifier: Modifier = Modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            modifier = modifier,
            textStyle = MaterialTheme.typography.bodyMedium
        )
    }

    @Composable
    fun BodySmall(text: String, onValueChange: (String) -> Unit, singleLine: Boolean = true, modifier: Modifier = Modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            modifier = modifier,
            textStyle = MaterialTheme.typography.bodySmall
        )
    }

    @Composable
    fun LabelLarge(text: String, onValueChange: (String) -> Unit, singleLine: Boolean = true, modifier: Modifier = Modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            modifier = modifier,
            textStyle = MaterialTheme.typography.labelLarge
        )
    }

    @Composable
    fun LabelMedium(text: String, onValueChange: (String) -> Unit, singleLine: Boolean = true, modifier: Modifier = Modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            modifier = modifier,
            textStyle = MaterialTheme.typography.labelMedium
        )
    }

    @Composable
    fun LabelSmall(text: String, onValueChange: (String) -> Unit, singleLine: Boolean = true, modifier: Modifier = Modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            modifier = modifier,
            textStyle = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview
@Composable
private fun Texts_Preview() {
    var displayLarge by remember { mutableStateOf("DisplayLarge") }
    var displayMedium by remember { mutableStateOf("DisplayMedium") }
    var displaySmall by remember { mutableStateOf("DisplaySmall") }
    var headlineLarge by remember { mutableStateOf("HeadlineLarge") }
    var headlineMedium by remember { mutableStateOf("HeadlineMedium") }
    var headlineSmall by remember { mutableStateOf("HeadlineSmall") }
    var titleLarge by remember { mutableStateOf("TitleLarge") }
    var titleMedium by remember { mutableStateOf("TitleMedium") }
    var titleSmall by remember { mutableStateOf("TitleSmall") }
    var bodyLarge by remember { mutableStateOf("BodyLarge") }
    var bodyMedium by remember { mutableStateOf("BodyMedium") }
    var bodySmall by remember { mutableStateOf("BodySmall") }
    var labelLarge by remember { mutableStateOf("LabelLarge") }
    var labelMedium by remember { mutableStateOf("LabelMedium") }
    var labelSmall by remember { mutableStateOf("LabelSmall") }

    Column {
        TextFields.DisplayLarge(displayLarge, { displayLarge = it })
        TextFields.DisplayMedium(displayMedium, { displayMedium = it })
        TextFields.DisplaySmall(displaySmall, { displaySmall = it })
        TextFields.HeadlineLarge(headlineLarge, { headlineLarge = it })
        TextFields.HeadlineMedium(headlineMedium, { headlineMedium = it })
        TextFields.HeadlineSmall(headlineSmall, { headlineSmall = it })
        TextFields.TitleLarge(titleLarge, { titleLarge = it })
        TextFields.TitleMedium(titleMedium, { titleMedium = it })
        TextFields.TitleSmall(titleSmall, { titleSmall = it })
        TextFields.BodyLarge(bodyLarge, { bodyLarge = it })
        TextFields.BodyMedium(bodyMedium, { bodyMedium = it })
        TextFields.BodySmall(bodySmall, { bodySmall = it })
        TextFields.LabelLarge(labelLarge, { labelLarge = it })
        TextFields.LabelMedium(labelMedium, { labelMedium = it })
        TextFields.LabelSmall(labelSmall, { labelSmall = it })
    }
}
