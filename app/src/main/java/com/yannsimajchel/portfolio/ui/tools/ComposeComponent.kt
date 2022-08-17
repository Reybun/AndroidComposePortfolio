package com.yannsimajchel.portfolio.ui.tools

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.yannsimajchel.portfolio.R

@Composable
fun ExpandableCardExperience(
    header: String,
    subtitle: String,
    date: String,
    description: List<String>,
    skills: List<String>,
    imageRes: Int,
) {
    var expand by remember { mutableStateOf(false) } // Expand State
    val rotationState by animateFloatAsState(if (expand) 180f else 0f) // Rotation State
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .animateContentSize( // Animation
                animationSpec = tween(
                    durationMillis = 200, // Animation Speed
                )
            )
            .noRippleClickable {
                expand = !expand
            },
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(vertical = 24.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween // Control the header Alignment over here.
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Company logo",
                    modifier = Modifier
                        .padding(end = 24.dp)
                        .size(50.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Column(modifier = Modifier.weight(.9f)) {
                    Text(
                        text = header,
                        color = if (expand) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface, // Header Color
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = subtitle,
                        fontSize = 16.sp,
                        color = if (expand) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface,
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_down_24),
                    colorFilter = ColorFilter.tint(if (expand) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface), // Icon Color
                    contentDescription = "Drop Down Arrow",
                    modifier = Modifier
                        .rotate(rotationState)
                        .weight(.1f)
                        .noRippleClickable {
                            expand = !expand
                        },
                )
            }
            if (expand) {
                Text(
                    text = date,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(vertical = 6.dp, horizontal = 24.dp)
                )
                LazyRow(modifier = Modifier.padding(vertical = 6.dp)) {
                    itemsIndexed(skills) { index, skill ->
                        Box(modifier = Modifier
                            .padding(
                                start = if (index == 0) 24.dp else 6.dp,
                                end = if (index == skills.lastIndex) 24.dp else 0.dp,
                            )
                        ) {
                            ChipSkill(text = skill)
                        }
                    }
                }
                Box(modifier = Modifier.padding(vertical = 6.dp, horizontal = 24.dp)) {
                    BulletList(
                        textList = description,
                        bulletColor = MaterialTheme.colors.primary,
                        textColor = MaterialTheme.colors.onSurface,
                    )
                }
            }
        }
    }
    Divider(
        color = Color.LightGray,
        thickness = .5.dp
    )
}

/**
 *
 */
@Composable
fun ExpandableCardSkill(
    header: String,
    skills: List<String>,
    imageRes: Int,
) {
    var expand by remember { mutableStateOf(false) } // Expand State
    val rotationState by animateFloatAsState(if (expand) 180f else 0f) // Rotation State
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .animateContentSize( // Animation
                animationSpec = tween(
                    durationMillis = 200, // Animation Speed
                )
            )
            .noRippleClickable {
                expand = !expand
            },
        shape = MaterialTheme.shapes.large
    ) {
        Row {

        }
        Column(modifier = Modifier.padding(vertical = 24.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween // Control the header Alignment over here.
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    colorFilter = ColorFilter.tint(if (expand) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface),
                    contentDescription = "Skill icon",
                    modifier = Modifier
                        .padding(end = 24.dp)
                        .size(24.dp)
                )
                Text(
                    text = header,
                    color = MaterialTheme.colors.onSurface, // Header Color
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(.9f)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_down_24),
                    colorFilter = ColorFilter.tint(if (expand) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface), // Icon Color
                    contentDescription = "Drop Down Arrow",
                    modifier = Modifier
                        .rotate(rotationState)
                        .weight(.1f)
                        .noRippleClickable {
                            expand = !expand
                        },
                )
            }
            if (expand) {
                FlowRow(
                    mainAxisSize = SizeMode.Expand,
                    modifier = Modifier.padding(top = 12.dp,
                        start = 24.dp,
                        end = 24.dp,
                        bottom = 6.dp),
                    mainAxisSpacing = 8.dp,
                ) {
                    skills.forEach {
                        ChipSkill(text = it)
                    }
                }
            }
        }
    }
    Divider(
        color = Color.LightGray,
        thickness = .5.dp
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipSkill(
    text: String,
) {
    Chip(
        modifier = Modifier.clickable(enabled = false,
            indication = null,
            interactionSource = remember { MutableInteractionSource() }) {},
        onClick = { /* Do something! */ },
        border = BorderStroke(
            ChipDefaults.OutlinedBorderSize,
            MaterialTheme.colors.primary
        ),
        colors = ChipDefaults.chipColors(
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.primary
        )
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
        )
    }
}

@Composable
fun BulletList(
    textList: List<String>,
    bulletColor: Color,
    textColor: Color,
) {
    Column() {
        textList.forEach {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start) {
                Canvas(modifier = Modifier
                    .padding(end = 12.dp)
                    .size(6.dp)) {
                    drawCircle(bulletColor)
                }
                Text(
                    text = it,
                    color = textColor, // Description Color
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                )
            }
        }
    }
}