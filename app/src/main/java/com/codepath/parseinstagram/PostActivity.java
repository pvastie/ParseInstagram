package com.codepath.parseinstagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity {


   // private ParseApplication application;
    private RecyclerView rvPost;
    private InstaAdapter adapter;
    private List<Post> posts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_post );

        // Find the recycleView
        rvPost = findViewById( R.id.rvPost);
        // initialize a list of tweet and adapter from the data source
        posts = new ArrayList<>( );
        adapter = new InstaAdapter( this, posts );
        //Recycle View setup: layout manager and setting the adapter
        rvPost.setLayoutManager( new LinearLayoutManager(this ) );
        rvPost.setAdapter( adapter );



    }

    private void addAllPost() {
        ParseQuery<Post> postParseQuery = new ParseQuery<Post>(Post.class);
        postParseQuery.include(Post.KEY_USER);
        postParseQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null){
                    Log.e("Error fetch","Error while fetching posts.");
                    e.printStackTrace();
                    return;
                }
                adapter.addPost(posts);
            }
        });
    }

}
