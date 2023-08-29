package com.tungngt.dev.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tungngt.dev.model.Person;

import java.util.ArrayList;
import java.util.List;


public class HomeFragmentViewModel extends ViewModel {
    public MutableLiveData<List<Person>> personList = new MutableLiveData<>(new ArrayList<>());
}
