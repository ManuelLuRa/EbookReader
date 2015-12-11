package com.xsoft.android.ebookreader.presentation.presenter;

import android.support.annotation.NonNull;

import com.xsoft.android.ebookreader.domain.GetEBookList;
import com.xsoft.android.ebookreader.domain.entities.EBook;
import com.xsoft.android.ebookreader.domain.exception.ErrorBundle;
import com.xsoft.android.ebookreader.presentation.exception.ErrorMessageFactory;
import com.xsoft.android.ebookreader.presentation.view.EBookListView;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

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
public class EBookListPresenter implements Presenter {

    private EBookListView mEBookListView;
    private GetEBookList mGetEBookList;

    @Inject EBookListPresenter (@Named("ebookList") GetEBookList getEBookList){
        this.mGetEBookList = getEBookList;
    }

    public void setView(@NonNull EBookListView view) { this.mEBookListView = view; }


    @Override
    public void resume() {}

    @Override
    public void pause() {}

    @Override
    public void destroy() {}

    /**
     * Initializes the presenter by start retrieving the ebook list.
     */
    public void initialize() {
        this.loadUserList();
    }

    /**
     * Loads all ebooks.
     */
    private void loadUserList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getEBookList();
    }

    private void getEBookList(){
        this.showViewLoading();
        mGetEBookList.execute(new GetEBookList.Callback(){
            @Override public void onGetEBooksList(Collection<EBook> eBooksCollection){
                hideViewLoading();
                showEBooksCollectionInView(eBooksCollection);
            }

            @Override public void onConnectionError() {
                hideViewLoading();
                showConnectionError();
                showViewRetry();
            }
        });
    }
    public void onEBookClicked(EBook eBook) {
        this.mEBookListView.viewEBook(eBook);
    }

    private void showViewLoading() {
        this.mEBookListView.showLoading();
    }

    private void hideViewLoading() {
        this.mEBookListView.hideLoading();
    }

    private void showViewRetry() {
        this.mEBookListView.showRetry();
    }

    private void hideViewRetry() {
        this.mEBookListView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.mEBookListView.getContext(),
                errorBundle.getException());
        this.mEBookListView.showError(errorMessage);
    }

    private void showConnectionError(){this.mEBookListView.showConnectionError();}

    private void showEBooksCollectionInView(Collection<EBook> eBooksCollection) {
        this.mEBookListView.renderEBookList(eBooksCollection);
    }

}
