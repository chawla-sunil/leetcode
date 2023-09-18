
public class BetterPlace2 {
//    Create an architecture for a social networking app's newsfeed
//    Concentrate on Database Schema, API Design & Database Indexes
//
//    List of APIs:
//            1. List the posts of people I follow with the count of likes
//            2. Like a post
//            3. Unlike a post
//
//    1. URL - /getNewsFeed(String userId)
//    Method - GET
//    Response Body - [
//      {
//        postId,
//                creatorDP,
//                creatorName,
//                userId,
//                Timestamp,
//                Likes,
//                isLiked,
//                postText
//      }
//    ]
//
//    2. URL - /likePost
//    Method - Post
//
//
//    3. URL - /unlikePost
//    Method - Post
//
//
//
//
//            User
//    Id name mobile email, dp,
//    Index => id
//
//            Post
//    postId creatorId, text, timestamp, likes
//    Index => creatorId, postId
//
//    userId -> follows,  followingId
//
//            Follow
//    Id, userId, followingId
//    Index => userId
//
//
//            Like
//    Id, postId, liker
//
//
//    My Answer => 
//
//1. URL - /getNewsFeed(String userId, int skip, int limit) {
//
//// cache
//
//        List<String> userIds = getFollowers(userId);
//        List<Posts> posts = getPostsByCreatorIds(userIds, skip, limit);
//==============; like logic;
//        convertToResponse(posts, userIds, userId) {
//
//        }
//// caching Redis
//    }
//
//
//    convertToResponse(posts,userIds) {
//
//        List<Like> likes = getLikes(posts, userId);
//        List<Users> userData = getUsersData(posts.filter( e => e.creatorId));
//[
//        {
//            postId,
//                    creatorDP,
//                    creatorName,
//                    userId,
//                    Timestamp,
//                    Likes,
//                    isLiked,
//                    postText
//        }
//]
//    }
//
//
//
//
//2. URL - /likePost(String, userId, String postId) {
//        Method - Post
//
//        KafkaQueue(userId, postId, LIKE);
//        Boolean exists = PostService.increaseLikes(postId);
//        updateLikes(postId, userId);
//
//
//    }
//
//3. URL - /unlikePost(String, userId, String postId) {
//        Method - Post
//
//        KafkaQueue(userId, postId, UNLIKE);
//
//    }

}
