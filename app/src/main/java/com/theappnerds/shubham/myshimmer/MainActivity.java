package com.theappnerds.shubham.myshimmer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mRecyclerViewLayoutManager;
    private List<BookItem> mBookListItems;
    private BookAdapter mBookAdapter;

    private ShimmerFrameLayout mShimmerFrameLayout;
    private RequestQueue mRequestQueue;

    //JSON file URL
    //Go to following link to see structure of json array
    private static final String URL = "https://api.myjson.com/bins/13be4e";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRequestQueue = Volley.newRequestQueue(this);
        mRecyclerView = findViewById(R.id.recycler_view);
        mShimmerFrameLayout = findViewById(R.id.shimmer_view_container);

        mBookListItems = new ArrayList<>();
        mBookAdapter = new BookAdapter(this, mBookListItems);
        mRecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(mRecyclerViewLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mBookAdapter);

        fetchBookList();

    }

    private void fetchBookList() {
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        if (response == null) {
                            Toast.makeText(MainActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
                        }

                        //Create new List to collect the JSON response data of Type BookItem
                        List<BookItem> bookItems = new Gson()
                                .fromJson(response.toString(), new TypeToken<List<BookItem>>() {
                                }
                                        .getType());

                        //add Books to final mBookItemList list
                        mBookListItems.clear();
                        mBookListItems.addAll(bookItems);

                        //refresh recyclerView
                        mBookAdapter.notifyDataSetChanged();

                        //shimmerAnimation stop and hide
                        mShimmerFrameLayout.stopShimmerAnimation();
                        mShimmerFrameLayout.setVisibility(View.GONE);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        mRequestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerFrameLayout.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        mShimmerFrameLayout.stopShimmerAnimation();
        super.onPause();
    }
}

