import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

//класс искючения
class PostNotFoundException(massage: String) : Exception() {
    override val message: String?
        get() = "No such post (("
}

//класс коммент
data class Comment(
    val commentId: Int,
    val postId: Int,
    val authorId: Int,
    var date: String,
    var text: String
)

// это коллекция постов
object WallService {
    internal var list: MutableList<Post> = emptyArray<Post>().toMutableList()

    //создаем список комментов
    val commentsList = mutableListOf<Comment>()

    fun addComment(comment: Comment) {
        for (thispost in list) {
            if (comment.postId == thispost.id) {
                commentsList.add(comment)
                println("Добавлено")
                break
            } else {
                throw PostNotFoundException("0")
            }
        }
    }

    fun add(post: Post): MutableList<Post> {
        var newPost: Post = post
        if (!list.isEmpty()) {
            //post.id = list.last().id + 1
            newPost = post.copy(id = list.last().id + 1)
        } else {
            newPost.id = 1
        }
        list.add(newPost)
        return list
    }

    fun update(post: Post): Boolean {
        list.forEach() {
            if (it.id == post.id) {
                it.text = post.text
                return true
            }
        }
        return false
    }
}


fun printArr(list: MutableList<Post>) {
    for (post in list) {
        println(post.toString())
    }
}

fun main() {
    // сначала add
    // специально присвоил идиотские id пусть метод сам разберется как выстроить их подряд
    val first = Post(
        id = 1,
        ownerId = 2,
        text = "курс по Kotlin читается отвратительно",
        attachment = Attachment(1,0,0,0,0)
    )
    //println(first.toString())
    val second = Post(
        id = 3,
        ownerId = 3,
        text = "действительно отвратительно",
        attachment = Attachment(1,0,0,0,1)
    )

    val some = Post(
        id = 8,
        ownerId = 5,
        text = "просто ужасно",
        attachment = Attachment(0,0,2,0,0)
    )
    WallService.add(first)
    WallService.add(second)
    WallService.add(some)
    printArr(WallService.list)

    // теперь update
    // создаем Post c id = 2 (такой id есть в системе), его и будем изменять
    // даем ему все другие данные, но поменяются только нужные
    // поэтому для id=2  ответом будет true
    val newPost = Post(
        id = 2,
        ownerId = 6,
        text = "да, я тоже ничего не понимаю",
        attachment = Attachment(1,0,0,0,1)
    )
    val answer = WallService.update(newPost)
    println(answer)
    println("_______________________")
    printArr(WallService.list)

    //печать комментов
    fun printComments(commentList: MutableList<Comment>) {
        println("_______________________")
        println("Добавленные комменты...")
        for (comment in commentList) {
            println(comment.text)
        }
    }

    //создаем объекты Comment
    val comment1 = Comment(1, 1, 1, "12.01.2022", "классный пост")
    val comment2 = Comment(2, 20, 1, "13.01.2022", "ужасный пост")

    //пытаемся добавить оба. Добавится только 1, потому что поста с id = 20 не существует
    try {
        WallService.addComment(comment1)
        WallService.addComment( comment2)
    } catch (e: PostNotFoundException) {
        println(e.message)
    }
    printComments(WallService.commentsList)
}

//печать аатачей
fun printAttaches(){
    for(i in allAttaches.attachList){
        println("____________________")
        println(i)
    }
}








