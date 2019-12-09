package com.dineshredditsample.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dineshredditsample.R;
import com.dineshredditsample.adapter.CustomAdapter;
import com.dineshredditsample.customrecycle.AAH_CustomRecyclerView;
import com.dineshredditsample.helpers.ShoppingApplication;
import com.dineshredditsample.helpers.UtilsDefault;
import com.dineshredditsample.models.ModelRedditPopular;
import com.dineshredditsample.retrofit.API;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by $Dinesh on 12/7/2019.
 */
public class FragmentPopularList extends Fragment {

    ProgressDialog dialog;
    @Inject
    API api;
    AlertDialog alertDialog;

    @BindView(R.id.rv_home)
    AAH_CustomRecyclerView recyclerView;

    @Override
    public View
    onCreateView(LayoutInflater inflater, ViewGroup container,
                 Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_popular_list, container, false);
        ButterKnife.bind(this, rootView);
       // AndroidSupportInjection.inject(this);
        ShoppingApplication.getContext().getComponent().inject(this);

        dialog = new ProgressDialog(getActivity());

        getPopulars();
        return rootView;

    }

    public void shwProgress() {
        dialog.setMessage(getString(R.string.please_wait));
        dialog.setCancelable(false);

        dialog.show();
        ProgressBar progressbar = (ProgressBar) dialog.findViewById(android.R.id.progress);
        progressbar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#16172b"), android.graphics.PorterDuff.Mode.SRC_IN);
    }

    public void hideprogress() {


        dialog.hide();

    }

    public void alertShowError(String title) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setCancelable(true);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_login_required, null);
        alertDialogBuilder.setView(dialogView);
        TextView titletv = dialogView.findViewById(R.id.tv_title);

        Button btn_login = dialogView.findViewById(R.id.btn_login);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();

            }
        });


        alertDialog = alertDialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        alertDialog.show();
    }

    public void getPopulars() {
        if (!UtilsDefault.isOnline()) {
            alertShowError("");
            return;

        }
        shwProgress();
        //  Log.d("aviparms", "search: "+UtilsDefault.getSharedPreferenceValue(Constants.USER_ID)+search);


        api.getPopular("20").enqueue(new Callback<ModelRedditPopular>() {
            @Override
            public void onResponse(Call<ModelRedditPopular> call, Response<ModelRedditPopular> response) {
                hideprogress();
                if (response.body() != null) {
                    ModelRedditPopular data = response.body();



                    if (data.getData().getChildren() != null && data.getData().getChildren().size() != 0) {
                        //orderList.clear();
                       // CustomAdapter
                        CustomAdapter adapter=new CustomAdapter(data.getData().getChildren(),getActivity(),0);
                        recyclerView.stopVideos();
                        recyclerView.setActivity(getActivity());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.removeAllViewsInLayout();
                       // recyclerView.setPlayOnlyFirstVideo(true);
                        recyclerView.setAdapter(adapter);



                    } else {
                        Toast.makeText(getActivity(), getString(R.string.no_data_found), Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(getActivity(), getString(R.string.server_error), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelRedditPopular> call, Throwable t) {
                hideprogress();
                Log.d("failure", "onFailure: " + t.getMessage());
                Toast.makeText(getActivity(), getString(R.string.server_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
