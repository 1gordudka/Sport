package com.fjdjf.sporttt.data.local

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource
import sport.composeapp.generated.resources.Res
import sport.composeapp.generated.resources.calendar
import sport.composeapp.generated.resources.pic1
import sport.composeapp.generated.resources.pic2
import sport.composeapp.generated.resources.pic3
import sport.composeapp.generated.resources.pic4
import sport.composeapp.generated.resources.pic5
import sport.composeapp.generated.resources.pic6
import sport.composeapp.generated.resources.pic7

sealed class InfoItem {

    data class Fact(
        val pic: DrawableResource,
        val name: String,
        val description: String,
    ) : InfoItem()

    data class Tip(
        val color: Color,
        val name: String,
        val description: String,
        val level: String,
    ) : InfoItem()
}

val sportInfoList = listOf<InfoItem>(
    // Facts
    InfoItem.Fact(
        pic = Res.drawable.pic1,
        name = "Fact 1: Marathon Origin",
        description = "The marathon race commemorates the run of the Greek soldier Pheidippides from Marathon to Athens to announce victory over the Persians."
    ),
    InfoItem.Fact(
        pic = Res.drawable.pic2,
        name = "Fact 2: First Olympics",
        description = "The first modern Olympic Games were held in Athens, Greece, in 1896."
    ),
    InfoItem.Fact(
        pic = Res.drawable.pic3,
        name = "Fact 3: Fastest Sport",
        description = "Badminton is the fastest racket sport, with shuttlecock speeds reaching over 200 mph."
    ),
    InfoItem.Fact(
        pic = Res.drawable.pic4,
        name = "Fact 4: Chess as a Sport",
        description = "Chess is recognized as a sport by the International Olympic Committee."
    ),
    InfoItem.Fact(
        pic = Res.drawable.pic5,
        name = "Fact 5: Longest Match",
        description = "The longest tennis match lasted 11 hours and 5 minutes, played between John Isner and Nicolas Mahut in 2010."
    ),
    InfoItem.Fact(
        pic = Res.drawable.pic6,
        name = "Fact 6: Most Popular Sport",
        description = "Soccer (football) is the most popular sport in the world, with over 4 billion fans."
    ),
    InfoItem.Fact(
        pic = Res.drawable.pic7,
        name = "Fact 7: Basketball Invention",
        description = "Basketball was invented by Dr. James Naismith in 1891 as a way to keep athletes active indoors during winter."
    ),

    // Tips
    InfoItem.Tip(
        color = Color.Green,
        name = "Tip 1: Stay Hydrated",
        description = "Always drink water before, during, and after exercise to stay hydrated.",
        level = "Beginner"
    ),
    InfoItem.Tip(
        color = Color.Blue,
        name = "Tip 2: Warm-Up",
        description = "Spend at least 10 minutes warming up to prevent injuries and improve performance.",
        level = "Beginner"
    ),
    InfoItem.Tip(
        color = Color.Yellow,
        name = "Tip 3: Set Goals",
        description = "Set realistic and achievable fitness goals to stay motivated.",
        level = "Intermediate"
    ),
    InfoItem.Tip(
        color = Color.Red,
        name = "Tip 4: Rest Days",
        description = "Incorporate rest days into your routine to allow your body to recover.",
        level = "Intermediate"
    ),
    InfoItem.Tip(
        color = Color.Green,
        name = "Tip 5: Proper Nutrition",
        description = "Eat a balanced diet rich in proteins, carbs, and healthy fats to fuel your workouts.",
        level = "Advanced"
    ),
    InfoItem.Tip(
        color = Color.Blue,
        name = "Tip 6: Cross-Training",
        description = "Engage in different types of exercises to improve overall fitness and avoid boredom.",
        level = "Intermediate"
    ),
    InfoItem.Tip(
        color = Color.Yellow,
        name = "Tip 7: Track Progress",
        description = "Keep a journal or use an app to track your workouts and progress.",
        level = "Beginner"
    ),
    InfoItem.Tip(
        color = Color.Red,
        name = "Tip 8: Stretch After Workouts",
        description = "Stretching after exercise helps improve flexibility and reduce muscle soreness.",
        level = "Beginner"
    ),
    InfoItem.Tip(
        color = Color.Green,
        name = "Tip 9: Listen to Your Body",
        description = "Avoid overtraining and pay attention to signs of fatigue or injury.",
        level = "Advanced"
    ),
    InfoItem.Tip(
        color = Color.Blue,
        name = "Tip 10: Get Enough Sleep",
        description = "Aim for 7-9 hours of sleep per night to support recovery and performance.",
        level = "Intermediate"
    )
)