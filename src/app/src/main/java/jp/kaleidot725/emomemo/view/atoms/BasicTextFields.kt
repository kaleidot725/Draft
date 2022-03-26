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
    fun DisplayLarge(
        text: String,
        onValueChange: (String) -> Unit,
        singleLine: Boolean = true,
        enabled: Boolean = true,
        modifier: Modifier = Modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            enabled = enabled,
            modifier = modifier,
            textStyle = MaterialTheme.typography.displayLarge
        )
    }

    @Composable
    fun DisplayMedium(
        text: String,
        onValueChange: (String) -> Unit,
        singleLine: Boolean = true,
        enabled: Boolean = true,
        modifier: Modifier = Modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            enabled = enabled,
            modifier = modifier,
            textStyle = MaterialTheme.typography.displayMedium
        )
    }

    @Composable
    fun DisplaySmall(
        text: String,
        onValueChange: (String) -> Unit,
        singleLine: Boolean = true,
        enabled: Boolean = true,
        modifier: Modifier = Modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            enabled = enabled,
            modifier = modifier,
            textStyle = MaterialTheme.typography.displaySmall
        )
    }

    @Composable
    fun HeadlineLarge(
        text: String,
        onValueChange: (String) -> Unit,
        singleLine: Boolean = true,
        enabled: Boolean = true,
        modifier: Modifier = Modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            enabled = enabled,
            modifier = modifier,
            textStyle = MaterialTheme.typography.headlineLarge
        )
    }

    @Composable
    fun HeadlineMedium(
        text: String,
        onValueChange: (String) -> Unit,
        singleLine: Boolean = true,
        enabled: Boolean = true,
        modifier: Modifier = Modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            enabled = enabled,
            modifier = modifier,
            textStyle = MaterialTheme.typography.headlineMedium
        )
    }

    @Composable
    fun HeadlineSmall(
        text: String,
        onValueChange: (String) -> Unit,
        singleLine: Boolean = true,
        enabled: Boolean = true,
        modifier: Modifier = Modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            enabled = enabled,
            modifier = modifier,
            textStyle = MaterialTheme.typography.headlineSmall
        )
    }

    @Composable
    fun TitleLarge(
        text: String,
        onValueChange: (String) -> Unit,
        singleLine: Boolean = true,
        enabled: Boolean = true,
        modifier: Modifier = Modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            enabled = enabled,
            modifier = modifier,
            textStyle = MaterialTheme.typography.titleLarge
        )
    }

    @Composable
    fun TitleMedium(
        text: String,
        onValueChange: (String) -> Unit,
        singleLine: Boolean = true,
        enabled: Boolean = true,
        modifier: Modifier = Modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            enabled = enabled,
            modifier = modifier,
            textStyle = MaterialTheme.typography.titleMedium
        )
    }

    @Composable
    fun TitleSmall(
        text: String,
        onValueChange: (String) -> Unit,
        singleLine: Boolean = true,
        enabled: Boolean = true,
        modifier: Modifier = Modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            enabled = enabled,
            modifier = modifier,
            textStyle = MaterialTheme.typography.titleSmall
        )
    }

    @Composable
    fun BodyLarge(
        text: String,
        onValueChange: (String) -> Unit,
        singleLine: Boolean = true,
        enabled: Boolean = true,
        modifier: Modifier = Modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            enabled = enabled,
            modifier = modifier,
            textStyle = MaterialTheme.typography.bodyLarge
        )
    }

    @Composable
    fun BodyMedium(
        text: String,
        onValueChange: (String) -> Unit,
        singleLine: Boolean = true,
        enabled: Boolean = true,
        modifier: Modifier = Modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            enabled = enabled,
            modifier = modifier,
            textStyle = MaterialTheme.typography.bodyMedium
        )
    }

    @Composable
    fun BodySmall(
        text: String,
        onValueChange: (String) -> Unit,
        singleLine: Boolean = true,
        enabled: Boolean = true,
        modifier: Modifier = Modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            enabled = enabled,
            modifier = modifier,
            textStyle = MaterialTheme.typography.bodySmall
        )
    }

    @Composable
    fun LabelLarge(
        text: String,
        onValueChange: (String) -> Unit,
        singleLine: Boolean = true,
        enabled: Boolean = true,
        modifier: Modifier = Modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            enabled = enabled,
            modifier = modifier,
            textStyle = MaterialTheme.typography.labelLarge
        )
    }

    @Composable
    fun LabelMedium(
        text: String,
        onValueChange: (String) -> Unit,
        singleLine: Boolean = true,
        enabled: Boolean = true,
        modifier: Modifier = Modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            enabled = enabled,
            modifier = modifier,
            textStyle = MaterialTheme.typography.labelMedium
        )
    }

    @Composable
    fun LabelSmall(
        text: String,
        onValueChange: (String) -> Unit,
        singleLine: Boolean = true,
        enabled: Boolean = true,
        modifier: Modifier = Modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            enabled = enabled,
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
        BasicTextFields.DisplayLarge(displayLarge, { displayLarge = it })
        BasicTextFields.DisplayMedium(displayMedium, { displayMedium = it })
        BasicTextFields.DisplaySmall(displaySmall, { displaySmall = it })
        BasicTextFields.HeadlineLarge(headlineLarge, { headlineLarge = it })
        BasicTextFields.HeadlineMedium(headlineMedium, { headlineMedium = it })
        BasicTextFields.HeadlineSmall(headlineSmall, { headlineSmall = it })
        BasicTextFields.TitleLarge(titleLarge, { titleLarge = it })
        BasicTextFields.TitleMedium(titleMedium, { titleMedium = it })
        BasicTextFields.TitleSmall(titleSmall, { titleSmall = it })
        BasicTextFields.BodyLarge(bodyLarge, { bodyLarge = it })
        BasicTextFields.BodyMedium(bodyMedium, { bodyMedium = it })
        BasicTextFields.BodySmall(bodySmall, { bodySmall = it })
        BasicTextFields.LabelLarge(labelLarge, { labelLarge = it })
        BasicTextFields.LabelMedium(labelMedium, { labelMedium = it })
        BasicTextFields.LabelSmall(labelSmall, { labelSmall = it })
    }
}
