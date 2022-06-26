package jp.kaleidot725.draft.view.atoms

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

object Texts {
    @Composable
    fun DisplayLarge(text: String, maxLines: Int = Int.MAX_VALUE, modifier: Modifier = Modifier) {
        Text(
            text = text,
            maxLines = maxLines,
            modifier = modifier,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.displayLarge
        )
    }

    @Composable
    fun DisplayMedium(text: String, maxLines: Int = Int.MAX_VALUE, modifier: Modifier = Modifier) {
        Text(
            text = text,
            maxLines = maxLines,
            modifier = modifier,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.displayMedium
        )
    }

    @Composable
    fun DisplaySmall(text: String, maxLines: Int = Int.MAX_VALUE, modifier: Modifier = Modifier) {
        Text(
            text = text,
            maxLines = maxLines,
            modifier = modifier,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.displaySmall
        )
    }

    @Composable
    fun HeadlineLarge(text: String, maxLines: Int = Int.MAX_VALUE, modifier: Modifier = Modifier) {
        Text(
            text = text,
            maxLines = maxLines,
            modifier = modifier,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.headlineLarge
        )
    }

    @Composable
    fun HeadlineMedium(text: String, maxLines: Int = Int.MAX_VALUE, modifier: Modifier = Modifier) {
        Text(
            text = text,
            maxLines = maxLines,
            modifier = modifier,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.headlineMedium
        )
    }

    @Composable
    fun HeadlineSmall(text: String, maxLines: Int = Int.MAX_VALUE, modifier: Modifier = Modifier) {
        Text(
            text = text,
            maxLines = maxLines,
            modifier = modifier,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.headlineSmall
        )
    }

    @Composable
    fun TitleLarge(text: String, maxLines: Int = Int.MAX_VALUE, modifier: Modifier = Modifier) {
        Text(
            text = text,
            maxLines = maxLines,
            modifier = modifier,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleLarge
        )
    }

    @Composable
    fun TitleMedium(text: String, maxLines: Int = Int.MAX_VALUE, modifier: Modifier = Modifier) {
        Text(
            text = text,
            maxLines = maxLines,
            modifier = modifier,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium,
        )
    }

    @Composable
    fun TitleSmall(text: String, maxLines: Int = Int.MAX_VALUE, modifier: Modifier = Modifier) {
        Text(
            text = text,
            maxLines = maxLines,
            modifier = modifier,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleSmall
        )
    }

    @Composable
    fun BodyLarge(text: String, maxLines: Int = Int.MAX_VALUE, modifier: Modifier = Modifier) {
        Text(
            text = text,
            maxLines = maxLines,
            modifier = modifier,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge
        )
    }

    @Composable
    fun BodyMedium(text: String, maxLines: Int = Int.MAX_VALUE, modifier: Modifier = Modifier) {
        Text(
            text = text,
            maxLines = maxLines,
            modifier = modifier,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium
        )
    }

    @Composable
    fun BodySmall(text: String, maxLines: Int = Int.MAX_VALUE, modifier: Modifier = Modifier) {
        Text(
            text = text,
            maxLines = maxLines,
            modifier = modifier,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall
        )
    }

    @Composable
    fun LabelLarge(text: String, maxLines: Int = Int.MAX_VALUE, modifier: Modifier = Modifier) {
        Text(
            text = text,
            maxLines = maxLines,
            modifier = modifier,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.labelLarge
        )
    }

    @Composable
    fun LabelMedium(text: String, maxLines: Int = Int.MAX_VALUE, modifier: Modifier = Modifier) {
        Text(
            text = text,
            maxLines = maxLines,
            modifier = modifier,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.labelMedium
        )
    }

    @Composable
    fun LabelSmall(text: String, maxLines: Int = Int.MAX_VALUE, modifier: Modifier = Modifier) {
        Text(
            text = text,
            maxLines = maxLines,
            modifier = modifier,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview
@Composable
private fun Texts_Preview() {
    Column {
        Texts.DisplayLarge("DisplayLarge")
        Texts.DisplayMedium("DisplayMedium")
        Texts.DisplaySmall("DisplaySmall")
        Texts.HeadlineLarge("HeadlineLarge")
        Texts.HeadlineMedium("HeadlineMedium")
        Texts.HeadlineSmall("HeadlineSmall")
        Texts.TitleLarge("TitleLarge")
        Texts.TitleMedium("TitleMedium")
        Texts.TitleSmall("TitleSmall")
        Texts.BodyLarge("BodyLarge")
        Texts.BodyMedium("BodyMedium")
        Texts.BodySmall("BodySmall")
        Texts.LabelLarge("LabelLarge")
        Texts.LabelMedium("LabelMedium")
        Texts.LabelSmall("LabelSmall")
    }
}
