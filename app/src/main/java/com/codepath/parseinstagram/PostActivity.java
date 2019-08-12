package com.codepath.parseinstagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class PostActivity<client> extends AppCompatActivity {


   // private ParseApplication application;
    private RecyclerView rvPost;
    private InstaAdapter adapter;
    private List<Post> posts;
    private MenuItem miCompose;

    private static final String POST_URL = "https:vastie-instagram.herokuapp.com/parse/classes/Post";


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

        addAllPost();


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

        AsyncHttpClient client = new AsyncHttpClient(  );
        client.get( POST_URL, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray postJsonArray = response.getJSONArray("results");
                    adapter.notifyDataSetChanged();
                    Log.d("smile", posts.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    public void onComposeAction (MenuItem mi){
        miCompose = findViewById( R.id.miPost );
        miCompose.setOnMenuItemClickListener( new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i = new Intent( PostActivity.this, MainActivity.class );
                startActivity( i );
                return true;
            }
        } );

    }

}
