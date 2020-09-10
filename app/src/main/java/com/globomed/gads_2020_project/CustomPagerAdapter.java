package com.globomed.gads_2020_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater inflater;
    ViewGroup layout;
    RecyclerView recyclerView_learners;
    RecyclerView recyclerView_skill;
    public CustomPagerAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        ModelObject modelObject=ModelObject.values()[position];
        inflater = LayoutInflater.from(collection.getRootView().getContext());
        layout = (ViewGroup) inflater.inflate(modelObject.getLayoutResId(), collection, false);
        collection.addView(layout);
        recyclerView_learners=collection.findViewById(R.id.recyclerview_learners);
         recyclerView_skill=collection.findViewById(R.id.recyclerview_skill);
        if(recyclerView_learners!=null){
            GetLearnersAndSkillIqs getter = RetrofitGet.getRetrofit().create(GetLearnersAndSkillIqs.class);
            Call<List<DataModelLearners>> call = getter.getLearners();
            call.enqueue(new Callback<List<DataModelLearners>>() {
                @Override
                public void onResponse(Call<List<DataModelLearners>> call, Response<List<DataModelLearners>> response) {
                    generateLearnersDataList(response.body());
                }

                @Override
                public void onFailure(Call<List<DataModelLearners>> call, Throwable t) {
                }
            });
            if(recyclerView_skill!=null){
                GetLearnersAndSkillIqs getter_ = RetrofitGet.getRetrofit().create(GetLearnersAndSkillIqs.class);
                Call<List<DataModelSkill>> call_ = getter_.getSkillIqs();
                call_.enqueue(new Callback<List<DataModelSkill>>() {
                    @Override
                    public void onResponse(Call<List<DataModelSkill>> call_, Response<List<DataModelSkill>> response) {
                        generateSKillsDataList(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<DataModelSkill>> call_, Throwable t) {
                    }
                });
            }
        }
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, @NonNull Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return ModelObject.values().length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        ModelObject customPagerEnum = ModelObject.values()[position];
        return mContext.getString(customPagerEnum.getTitleResId());
    }


    private void generateLearnersDataList(List<DataModelLearners> learners) {
        RecyclerViewAdapterLearners adapter = new RecyclerViewAdapterLearners(mContext,learners);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView_learners.setLayoutManager(layoutManager);
        recyclerView_learners.setAdapter(adapter);
    }
    private void generateSKillsDataList(List<DataModelSkill> skillIqs) {
        RecyclerViewAdapterSkill adapter = new RecyclerViewAdapterSkill(mContext,skillIqs);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView_skill.setLayoutManager(layoutManager);
        recyclerView_skill.setAdapter(adapter);
    }
}
