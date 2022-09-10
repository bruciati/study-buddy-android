package com.brc.studybuddy.presentation.groups.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brc.studybuddy.data.model.Group


@Composable
fun IconText(
    icon: ImageVector,
    text: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = text)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text, style = MaterialTheme.typography.body1)
    }
}

@Preview
@Composable
fun PreviewIconText() = IconText(icon = Icons.Default.Icecream, text = "Testo!")

@Composable
fun NormalGroupItem(
    group: Group
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = group.title,
                style = MaterialTheme.typography.h6,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(Modifier.height(16.dp))
            IconText(icon = Icons.Default.MilitaryTech, text = "Owner Name")
        }
    }
}

@Composable
fun LatestGroupItem(group: Group) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .requiredWidth(264.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Color.Red)
                )
                Spacer(Modifier.width(4.dp))
                Text("Live Now")
            }
            Spacer(Modifier.height(28.dp))
            Text(
                text = group.title,
                style = MaterialTheme.typography.h6,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(Modifier.height(16.dp))
            Row {
                Text(
                    text = "By ",
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = "Piermenti Sfracellozzi",
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            Spacer(Modifier.height(8.dp))
            Row {
                Text(text = "8", style = MaterialTheme.typography.body1)
                Text(text = " participants", style = MaterialTheme.typography.body1)
            }
        }
    }
}


@Preview
@Composable
fun PreviewNormalGroupItem() {
    NormalGroupItem(
        Group(0, "Titolo molto lungo per verificare che funzioni", "Descrizione interessante")
    )
}