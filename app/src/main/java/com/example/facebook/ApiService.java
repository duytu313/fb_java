package com.example.facebook;

import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Call<AuthResponse> login(
            @Field("usernameOrEmail") String usernameOrEmail,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register")
    Call<AuthResponse> register(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email
    );

    @Multipart
    @POST("createPost")
    Call<PostResponse> createPost(
            @Part("userId") RequestBody userId,
            @Part("content") RequestBody content,
            @Part MultipartBody.Part image
    );

    @GET("posts")
    Call<PostsResponse> getPosts();

    @FormUrlEncoded
    @POST("likePost")
    Call<LikeResponse> likePost(
            @Field("postId") String postId,
            @Field("userId") String userId
    );

    @FormUrlEncoded
    @POST("commentPost")
    Call<CommentResponse> commentPost(
            @Field("postId") String postId,
            @Field("userId") String userId,
            @Field("comment") String comment
    );

    @GET("comments/{postId}")
    Call<CommentsResponse> getComments(
            @Path("postId") String postId
    );
}
