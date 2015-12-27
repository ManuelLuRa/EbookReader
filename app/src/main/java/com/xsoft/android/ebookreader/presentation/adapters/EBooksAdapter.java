package com.xsoft.android.ebookreader.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xsoft.android.ebookreader.R;
import com.xsoft.android.ebookreader.domain.entities.EBook;

import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (C) 2015 Manuel Luque Ramos.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class EBooksAdapter extends RecyclerView.Adapter<EBooksAdapter.EBookViewHolder> {

    public interface OnItemClickListener {
        void onEbookItemClicked(EBook eBook);
    }

    private List<EBook> eBooksCollection;
    private final LayoutInflater layoutInflater;

    private OnItemClickListener onItemClickListener;

    public EBooksAdapter(Context context, Collection<EBook> eBookCollection) {
        this.validateEBooksCollection(eBookCollection);
        this.layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.eBooksCollection = (List<EBook>) eBookCollection;
    }

    @Override public int getItemCount() {
        return (this.eBooksCollection != null) ? this.eBooksCollection.size() : 0;
    }

    @Override public EBookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = this.layoutInflater.inflate(R.layout.row_ebook, parent, false);
        EBookViewHolder eBookViewHolder = new EBookViewHolder(view);

        return eBookViewHolder;
    }

    @Override public void onBindViewHolder(EBookViewHolder holder, final int position) {
        final EBook eBook = this.eBooksCollection.get(position);
        holder.textViewTitle.setText(eBook.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (EBooksAdapter.this.onItemClickListener != null) {
                    EBooksAdapter.this.onItemClickListener.onEbookItemClicked(eBook);
                }
            }
        });
    }

    @Override public long getItemId(int position) {
        return position;
    }

    public void setEbooksCollection(Collection<EBook> eBookCollection) {
        this.validateEBooksCollection(eBookCollection);
        this.eBooksCollection = (List<EBook>) eBookCollection;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void validateEBooksCollection(Collection<EBook> eBookCollection) {
        if (eBookCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    static class EBookViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.title)
        TextView textViewTitle;

        public EBookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
