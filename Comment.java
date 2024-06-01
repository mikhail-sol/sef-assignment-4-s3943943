import java.util.ArrayList; // array handling class

import java.io.File; // file handling class
import java.io.FileWriter; // file handling class
import java.io.IOException; // handle errors class

public class Comment {
        private ArrayList<String> postComments = new ArrayList<>();

        int postID;
        String postEmergency;
        String postType;
    
        int commentCount = 0;

        // constructor to initalise values
        public Comment(int postID, String postEmergency, String postType) {
            this.postID = postID;
            this.postEmergency = postEmergency;
            this.postType = postType;
        }

        public boolean addComment(String comment) {

            // addComment validation

            // check comment length is not blank
            if (comment.length() == 0) {
                System.out.println("COMMENT ERROR: Cannot be blank");
                return false;
            }

            // condition 1: check word count is over 4 and NOT over 10
            // split the comment by any punctuaton, whitespace and void ' character using regex
            // ref: https://www.baeldung.com/java-word-counting
            final String[] commentBody = comment.split("[\\pP\s&&[^']]+");
            if (commentBody.length < 4 || commentBody.length > 10) {
                System.out.println("COMMENT ERROR: The word count of the comment is either < 4 OR > 10");
                return false;
            }

            // condition 1: check first character of comment is capital
            char character = comment.charAt(0);
            if (!(Character.isUpperCase(character))) {
                System.out.println("COMMENT ERROR: Lowercase letter detect as first character");
                return false;
            }
            
            // increment comment count
            commentCount++;
            
            // condition 2: maximum comment per Emergency/Category & general maximum comment
            // if postEmergency is Ordinary OR postType is Easy AND the comment count exceeds 3, do not comment
            if (((postEmergency == "Ordinary") || (postType == "Easy")) && commentCount == 4) {
                System.out.println("COMMENT ERRROR: (postEmergency is Ordinary OR postType is Easy) AND comments exceed 3");
                return false;
            }
            // if postEmergency or postType is neither the above categories AND comment count exceeds 5, do not add comment
            else if (commentCount == 6) {
                System.out.println("COMMENT ERROR: Maximum number of comments is exceeds 5");
                return false;
            }

            // if validation passes, add comment to array return true
            postComments.add(comment);
            return true;
        }

    // create file
    // https://www.w3schools.com/java/java_class_methods.asp
    // ref: https://www.w3schools.com/java/java_files_create.asp
    public boolean createFile() {
        try {
            File postFile = new File("comments-postid-" + postID + ".txt");
            if (postFile.createNewFile()) {
                System.out.println("Comment created " + postFile.getName());
            }
            else {
                System.out.println("A comment with that ID combination already exists.");
                return false;
            }
        } 
        // if any errors during file creation
        catch (IOException e) {
            System.out.println("SYSTEM ERROR: An error has occured.");
            // show location where error occured
            // ref: https://docs.oracle.com/javase/6/docs/api/java/lang/Throwable.html#printStackTrace()
            e.printStackTrace();
            return false;
        } 
        return true;
    }

    public void writeToFile() {
        try {

            FileWriter myWriter = new FileWriter("comments-postid-" + postID + ".txt");

            // ensure each line is on a new one https://stackoverflow.com/questions/41789470/files-write-appending-new-lines-in-a-text-file
            String lineSeperator = System.getProperty("line.separator");
            
            // write comment content
            myWriter.write("PostID: " + postID);

            myWriter.write(lineSeperator);

            for (String comment : postComments) {
                myWriter.write(comment);
                myWriter.write(lineSeperator);
            }

            myWriter.close();
            System.out.println("SYSTEM INFO: File was written to!");
        }
        // if any errors writing to file
        catch (IOException e) {
            System.out.println("SYSTEM ERROR: An error has occured.");
            e.printStackTrace();
        }
    }
}

