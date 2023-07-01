
import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val questionID:String,
    val question:String,
    val awnserA: Awnser,
    val awnserB: Awnser,
    val awnserC: Awnser,
)

@Serializable
data class Awnser (
        val awnser:String,
        val rightOrWrong:Boolean,
        )