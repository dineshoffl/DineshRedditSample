package com.dineshredditsample.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dineshredditsample.R;
import com.dineshredditsample.adapter.CustomAdapter;
import com.dineshredditsample.customrecycle.AAH_CustomRecyclerView;
import com.dineshredditsample.helpers.Constants;
import com.dineshredditsample.helpers.UtilsDefault;
import com.dineshredditsample.models.ModelRedditPopular;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by $Dinesh on 12/8/2019.
 */
public class FragmentFavourites extends Fragment {
    TextView nodata;
    AAH_CustomRecyclerView recyclerView;

    @Override
    public void onDetach() {
        super.onDetach();
        recyclerView.stopVideos();
    }


    @Override
    public View
    onCreateView(LayoutInflater inflater, ViewGroup container,
                 Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_popular_list, container, false);
        ButterKnife.bind(this, rootView);
        nodata=rootView.findViewById(R.id.nodata);
        recyclerView=rootView.findViewById(R.id.rv_home);
        recyclerView.stopVideos();

        recyclerView.removeAllViewsInLayout();


        if (UtilsDefault.getSharedPreferenceValue(Constants.list)!=null){
            Type listType = new TypeToken<List<ModelRedditPopular.Data.Child>>() {}.getType();
            Gson gson = new Gson();
            List<ModelRedditPopular.Data.Child> list = gson.fromJson(UtilsDefault.getSharedPreferenceValue(Constants.list),
                    listType);

            CustomAdapter adapter=new CustomAdapter(list,getActivity(),1);
            recyclerView.setActivity(getActivity());
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setPlayOnlyFirstVideo(true);
            recyclerView.setAdapter(adapter);
            nodata.setVisibility(View.GONE);
            if (list.size()==0){
                nodata.setVisibility(View.VISIBLE);
            }
        }
        else {
            nodata.setVisibility(View.VISIBLE);
        }

        return rootView;
    }
}
