package com.example.jingbin.cloudreader.viewmodel.wan;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.jingbin.cloudreader.bean.wanandroid.TreeBean;
import com.example.jingbin.cloudreader.http.HttpClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * @author jingbin
 * @data 2018/12/3
 * @Description wanandroid知识体系的ViewModel
 */

public class TreeViewModel extends AndroidViewModel {


    public TreeViewModel(@NonNull Application application) {
        super(application);
    }

    @SuppressLint("CheckResult")
    public MutableLiveData<TreeBean> getTree() {
        final MutableLiveData<TreeBean> data = new MutableLiveData<>();
        HttpClient.Builder.getWanAndroidServer().getTree()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TreeBean>() {
                    @Override
                    public void accept(TreeBean treeBean) throws Exception {
                        data.setValue(treeBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        data.setValue(null);
                    }
                });
        return data;
    }
}
