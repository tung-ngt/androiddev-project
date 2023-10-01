package com.tungngt.dev.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tungngt.dev.data.repository.SearchRepository;
import com.tungngt.dev.viewmodel.SearchViewModel;

public class SearchViewModelFactory implements ViewModelProvider.Factory {
    private final SearchRepository searchRepository;

    public SearchViewModelFactory(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SearchViewModel(searchRepository);
    }
}
