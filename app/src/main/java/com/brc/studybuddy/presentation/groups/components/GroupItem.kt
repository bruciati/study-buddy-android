package com.brc.studybuddy.presentation.groups.components

import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.brc.studybuddy.domain.model.Group

@Composable
fun GroupItem(
    group: Group
) {
    Surface(
        color = Color.Transparent,
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(0.dp, 8.dp, 0.dp, 0.dp))
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            elevation = 8.dp,
            color = MaterialTheme.colors.surface,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(Modifier.padding(8.dp)) {
                Text(text = group.title, style = MaterialTheme.typography.body1)
                Spacer(Modifier.height(8.dp))
                Text(text = group.id.toString(), style = MaterialTheme.typography.body2)
            }
        }

    }
}