package jp.kaleidot725.emomemo.view.organisms.dropdown

import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import jp.kaleidot725.emomemo.data.entity.NotebookEntity
import jp.kaleidot725.emomemo.view.atoms.Texts

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NotebookDropdownMenu(
    label: String,
    notebooks: List<NotebookEntity>,
    selectedNotebook: NotebookEntity?,
    onSelect: (NotebookEntity) -> Unit,
    modifier: Modifier
) {
    var dropdownExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = dropdownExpanded,
        onExpandedChange = { dropdownExpanded = !dropdownExpanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedNotebook?.title ?: "",
            onValueChange = { },
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdownExpanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        ExposedDropdownMenu(expanded = dropdownExpanded, onDismissRequest = { dropdownExpanded = false }) {
            notebooks.forEach { notebook ->
                DropdownMenuItem(
                    onClick = {
                        onSelect(notebook)
                        dropdownExpanded = false
                    }
                ) {
                    Texts.LabelSmall(text = notebook.title)
                }
            }
        }
    }
}