import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun addComment() {
        //arrange
        val first = Post(
            id = 1,
            ownerId = 2,
            text = "курс по Kotlin читается отвратительно",
            attachment = Attachment(1,0,0,0,0)
        )
        WallService.add(first)
        val comment1 = Comment(1, 1, 1, "12.01.2022", "классный пост")
        val theCommentsSize0 = WallService.commentsList.size
        //act
        WallService.addComment(comment1)
        val theCommentsSize1 = WallService.commentsList.size
        //assert
        assertEquals(theCommentsSize1 > theCommentsSize0, true)
    }

    @Test
    fun add() {
        //arrange
        val thePostsSize = WallService.list.size
        val first = Post(
            id = 1,
            ownerId = 2,
            text = "курс по Kotlin читается отвратительно",
            attachment = Attachment(1,0,0,0,0)
        )
        //act111
        WallService.add(first)
        val thePostsSize1 = WallService.list.size

        //assert
        println("1")
        assertEquals(thePostsSize1 > thePostsSize, true)
    }

    @Test
    fun update() {
        //arrange
        val first = Post(
            id = 1,
            ownerId = 2,
            text = "курс по Kotlin читается отвратительно",
            attachment = Attachment(1,0,0,0,0)
        )
        val second = Post(
            id = 3,
            ownerId = 3,
            text = "действительно отвратительно",
            attachment = Attachment(1,0,0,0,1)
        )
        WallService.add(first)
        WallService.add(second)

        //act
        val newPost = Post(
            id = 2,
            ownerId = 6,
            text = "да, я тоже ничего не понимаю",
            attachment = Attachment(1,0,0,0,1)
        )
        val answer = WallService.update(newPost)

        //assert
        assertEquals(WallService.list[1].text == "да, я тоже ничего не понимаю", true)
    }
}