package com.example.olympiabardis.booklistingproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by olympiabardis on 1/2/17.
 */

public class BookAdapter extends ArrayAdapter<Book> {
    private ArrayList<Book> mBooksList;

    public BookAdapter(Context context, ArrayList<Book> booksList){
        super(context, 0, booksList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Book book = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView tvTitle = (TextView) convertView.findViewById(R.id.title);
        tvTitle.setText(book.getTitle());

        TextView tvSubtitle = (TextView) convertView.findViewById(R.id.subtitle);
        tvSubtitle.setText(book.getSubtitle());

        TextView tvAuthors = (TextView) convertView.findViewById(R.id.authors);
        tvAuthors.setText(book.getAuthors());

        TextView tvPublishedDate = (TextView) convertView.findViewById(R.id.published_date);
        tvPublishedDate.setText(book.getPublishedDate());


        return convertView;
    }
}
