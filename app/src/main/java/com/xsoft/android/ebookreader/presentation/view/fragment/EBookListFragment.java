package com.xsoft.android.ebookreader.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.xsoft.android.ebookreader.R;
import com.xsoft.android.ebookreader.dependencies.presentation.components.UserComponent;
import com.xsoft.android.ebookreader.domain.entities.EBook;
import com.xsoft.android.ebookreader.presentation.adapters.EBooksAdapter;
import com.xsoft.android.ebookreader.presentation.adapters.EbookLayoutManager;
import com.xsoft.android.ebookreader.presentation.presenter.EBookListPresenter;
import com.xsoft.android.ebookreader.presentation.view.EBookListView;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
public class EBookListFragment extends BaseFragment implements EBookListView {

    public interface EBookListListener {
        void onEBookClicked(final EBook eBook);
    }

    @Inject
    EBookListPresenter eBookListPresenter;

    @Bind(R.id.rv_ebooks) RecyclerView rv_ebooks;
    @Bind(R.id.rl_progress) RelativeLayout rl_progress;
    @Bind(R.id.rl_retry) RelativeLayout rl_retry;
    @Bind(R.id.bt_retry) Button bt_retry;

    @OnClick(R.id.bt_retry) void onButtonRetryClick() { EBookListFragment.this.loadEBookList(); }

    private EBooksAdapter eBooksAdapter;
    private EbookLayoutManager eBookLayoutManager;



    private EBookListListener userListListener;

    public EBookListFragment() { super(); }

    public static EBookListFragment newInstance() {
        EBookListFragment eBookListFragment = new EBookListFragment();
        return eBookListFragment;
    }

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof EBookListListener) {
            this.userListListener = (EBookListListener) activity;
        }
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_ebook_list, container, false);
        ButterKnife.bind(this, fragmentView);
        setupUI();

        return fragmentView;
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initialize();
        this.loadEBookList();
    }

    @Override public void onResume() {
        super.onResume();
        this.eBookListPresenter.resume();
    }

    @Override public void onPause() {
        super.onPause();
        this.eBookListPresenter.pause();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        this.eBookListPresenter.destroy();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initialize() {
        this.getComponent(UserComponent.class).inject(this);
        this.eBookListPresenter.setView(this);
    }

    private void setupUI() {
        this.eBookLayoutManager = new EbookLayoutManager(getActivity());
        this.rv_ebooks.setLayoutManager(eBookLayoutManager);

        this.eBooksAdapter = new EBooksAdapter(getActivity(), new ArrayList<EBook>());
        this.eBooksAdapter.setOnItemClickListener(onItemClickListener);
        this.rv_ebooks.setAdapter(eBooksAdapter);
    }

    private EBooksAdapter.OnItemClickListener onItemClickListener =
            new EBooksAdapter.OnItemClickListener() {
                @Override
                public void onEbookItemClicked(EBook eBook) {
                    if (EBookListFragment.this.eBookListPresenter != null && eBook != null) {
                        EBookListFragment.this.eBookListPresenter.onEBookClicked(eBook);
                    }
                }
            };


    private void loadEBookList() {
        this.eBookListPresenter.initialize();
    }

    @Override
    public void renderEBookList(Collection<EBook> userModelCollection) {

    }

    @Override
    public void viewEBook(EBook eBook) {

    }

    @Override public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override public void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override public void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override public void showConnectionError() { this.showToastMessage(this.getContext().getString(R.string.connection_error)); }

    @Override public Context getContext() {
        return this.getActivity().getApplicationContext();
    }
}
