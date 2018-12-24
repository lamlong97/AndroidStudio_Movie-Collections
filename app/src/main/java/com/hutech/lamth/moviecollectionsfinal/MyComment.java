package com.hutech.lamth.moviecollectionsfinal;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.hutech.lamth.moviecollectionsfinal.Objects.Comment;

public class MyComment extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText edtComment;
    String mCurrentUserId;

    ImageView imageViewSend;
    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener  authStateListener;
    FirebaseRecyclerOptions<Comment> options;
    FirebaseRecyclerAdapter<Comment, RecyclerComment> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comment);
        AnhXa();

        imageViewSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendComment();
            }
        });

        UpdateComment();
    }
    private void AnhXa() {
        imageViewSend = findViewById(R.id.ImgSendComment);
        edtComment = findViewById(R.id.edtComment);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser().getUid().isEmpty())
            Toast.makeText(this, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
        else
            mCurrentUserId = mAuth.getCurrentUser().getUid();
    }

    private void UpdateComment() {
        options = new FirebaseRecyclerOptions.Builder<Comment>()
                .setQuery(mRootRef.child("Comments"), Comment.class).build();

        adapter = new FirebaseRecyclerAdapter<Comment, RecyclerComment>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RecyclerComment holder, int position, @NonNull Comment model) {
                holder.txtName.setText(model.getName());
                holder.txtContent.setText(model.getContent());

            }

            @NonNull
            @Override
            public RecyclerComment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_items, parent, false);
                return new RecyclerComment(itemView);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }

    private void SendComment(){
        String Comment = edtComment.getText().toString();

        if(!Comment.isEmpty()){

            Comment mComment = new Comment(mAuth.getCurrentUser().getEmail(),Comment, String.valueOf(ServerValue.TIMESTAMP));

            mRootRef.child("Comments").push().setValue(mComment);
            adapter.notifyDataSetChanged();
            edtComment.setText("");

        }else{
            Toast.makeText(this, "Bình luận không thể để trống", Toast.LENGTH_SHORT).show();
        }

    }
}
