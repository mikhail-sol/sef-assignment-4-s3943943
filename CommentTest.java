import static org.junit.jupiter.api.Assertions.*; // junit 5 testing class
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class CommentTest {
    // declare instance variable of PostTest class
    // ref: https://www.tutorialspoint.com/what-are-class-variables-instance-variables-and-local-variables-in-java
    PostTest postTest;

    @BeforeEach
    public void setup() {

        // create an instance of PostTest class each run
        // ref: https://www.geeksforgeeks.org/getter-and-setter-in-java/
        // ref: https://www.codecademy.com/article/variable-scope-in-java
        postTest = new PostTest();

        // set default post variables
        postTest.setVariables();
    }

    // test comment word count (6 words) [pass]
    @Test
    public void testCommentWordCount_Valid() {
        // create comment object with default constructor values (postID = 1) (postEmergency[2] = "Ordinary") (postType[2] = "Easy")
        Comment comment = new Comment(postTest.getPostID(), postTest.getPostEmergency()[2], postTest.getPostTypes()[2]);
        // check if adding a new comment returns true or false (pass or fail)
        assertTrue(comment.addComment("This is a sentence of 6 words"));
        comment.writeToFile();       
    }

    // test comment word count (11 words) [fail]
    @Test
    public void testCommentWordCount_Invalid1() {
        Comment comment = new Comment(postTest.getPostID(), postTest.getPostEmergency()[2], postTest.getPostTypes()[2]);
        assertTrue(comment.addComment("This is a sentence of over 10 words and should fail"));
        comment.writeToFile();
    }    

    // test comment word count (3 words) [fail]
    @Test
    public void testCommentWordCount_Invalid2() {
        Comment comment = new Comment(postTest.getPostID(), postTest.getPostEmergency()[2], postTest.getPostTypes()[2]);
        assertTrue(comment.addComment("This is a"));
        comment.writeToFile();
    }    


    // first letter of first word should be capital [pass]
    @Test
    public void testIsFirstUpperCase_Valid() {
        Comment comment = new Comment(postTest.getPostID(), postTest.getPostEmergency()[2], postTest.getPostTypes()[2]);
        assertTrue(comment.addComment("This is a test sentence"));
        comment.writeToFile();
    }
    
    // first letter of first word should be capital [fail]
    @Test
    public void testIsFirstUpperCase_Invalid() {
        Comment comment = new Comment(postTest.getPostID(), postTest.getPostEmergency()[2], postTest.getPostTypes()[2]);
        assertTrue(comment.addComment("this is a test sentence"));
        comment.writeToFile();
    }    

    // test post can only have maximum 3 comments if postEmergency = "Ordinary" [pass]
    @Test
    public void testCommentLimit_Ord_Valid() {
        // create post object of postEmergency = "Ordinary" AND postType = "Difficult"
        Post post = new Post(postTest.getPostID(), postTest.getPostTitle(), postTest.getPostBody(), postTest.getPostTags(), postTest.getPostEmergency()[2], postTest.getPostTypes()[1]);
        post.addPost();
        post.writeToFile();
        
        // create comment object
        // ensure to pass postEmergency = "Ordinary" in this scenario
        Comment comment = new Comment(postTest.getPostID(), postTest.getPostEmergency()[2], postTest.getPostTypes()[0]);

        // add 3 comments (maximum allowed is 3)
        assertTrue(comment.addComment("This is a test comment (1)"));
        assertTrue(comment.addComment("This is a test comment (2)"));
        // final comment should pass here
        assertTrue(comment.addComment("This is a test comment (3)"));

        comment.writeToFile();
        
    }

    // test post can only have maximum 3 comments if postEmergency = "Ordinary" [fail]
    @Test
    public void testCommentLimit_Ord_Invalid() {
        // create post object of postEmergency = "Ordinary" AND postType = "Difficult"
        Post post = new Post(postTest.getPostID(), postTest.getPostTitle(), postTest.getPostBody(), postTest.getPostTags(), postTest.getPostEmergency()[2], postTest.getPostTypes()[1]);
        post.addPost();
        post.writeToFile();
        
        Comment comment = new Comment(postTest.getPostID(), postTest.getPostEmergency()[2], postTest.getPostTypes()[0]);

        // add 4 comments (maximum allowed is 3)
        assertTrue(comment.addComment("This is a test comment (1)"));
        assertTrue(comment.addComment("This is a test comment (2)"));
        assertTrue(comment.addComment("This is a test comment (3)"));
        // fail here, as attempting to add fourth comment
        assertTrue(comment.addComment("This is a test comment (4)"));

        comment.writeToFile();
    }    
}
