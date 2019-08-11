package com.codepath.parseinstagram;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class InstaAdapter extends RecyclerView.Adapter<InstaAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;


    public InstaAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }


    @NonNull
    @Override
    public InstaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( context ).inflate( R.layout.item_post, viewGroup, false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull InstaAdapter.ViewHolder viewHolder, int i) {

        final Post post = posts.get(i);

        viewHolder.tvDescription.setText( post.KEY_DESCRIPTION );
        viewHolder.tvUser.setText( post.KEY_USER );
        Glide.with( context ).load( post.KEY_IMAGE ).into(viewHolder.ivPost);


    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void clear(){
        posts.clear();
        notifyDataSetChanged();
    }

    public void addPost(List<Post> postList){
        posts.addAll( postList );
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvDescription;
        public  TextView tvUser;
        public ImageView ivPost;


        public ViewHolder(@NonNull View itemView) {
            super( itemView );

            ivPost = itemView.findViewById( R.id.ivPost );
            tvUser = itemView.findViewById( R.id.tvUser );
            tvDescription = itemView.findViewById( R.id.tvDescription );
        }
    }
}
