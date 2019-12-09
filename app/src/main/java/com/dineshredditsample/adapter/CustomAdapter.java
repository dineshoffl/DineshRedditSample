package com.dineshredditsample.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.dineshredditsample.R;
import com.dineshredditsample.helpers.Constants;
import com.dineshredditsample.helpers.UtilsDefault;
import com.dineshredditsample.models.ModelRedditPopular;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by $Dinesh on 12/9/2019.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<ModelRedditPopular.Data.Child> list;
    Context context;

    int type;

    String Imageurl="";

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_author, tv_postby, tv_awards, content_title, content, fav;

        ImageView imageView;

        boolean isMuted; //to mute/un-mute video (optional)

        public MyViewHolder(View x) {
            super(x);
            tv_author = x.findViewById(R.id.tv_author);
            fav = x.findViewById(R.id.fav);
            tv_postby = x.findViewById(R.id.tv_postby);
            tv_awards = x.findViewById(R.id.tv_awards);
            content_title = x.findViewById(R.id.content_title);
            content = x.findViewById(R.id.content);

            imageView = x.findViewById(R.id.image);
            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ModelRedditPopular.Data.Child clickedData = list.get(getAdapterPosition());
                    Type listType = new TypeToken<List<ModelRedditPopular.Data.Child>>() {
                    }.getType();
                    Gson gson = new Gson();

                    if (type == 0) {
                        if (UtilsDefault.getSharedPreferenceValue(Constants.list) != null) {
                            List<ModelRedditPopular.Data.Child> list = gson.fromJson(UtilsDefault.getSharedPreferenceValue(Constants.list),
                                    listType);
                            list.add(clickedData);
                            String json = gson.toJson(list, listType);
                            UtilsDefault.updateSharedPreference(Constants.list, json);
                            Toast.makeText(context, "Added to favourite.", Toast.LENGTH_SHORT).show();
                        } else {
                            List<ModelRedditPopular.Data.Child> target = new ArrayList<>();
                            target.add(clickedData);
                            String json = gson.toJson(target, listType);
                            UtilsDefault.updateSharedPreference(Constants.list, json);
                        }
                    } else {
                        if (UtilsDefault.getSharedPreferenceValue(Constants.list) != null) {
                            List<ModelRedditPopular.Data.Child> savedList = gson.fromJson(UtilsDefault.getSharedPreferenceValue(Constants.list),
                                    listType);

                            savedList.remove(getAdapterPosition());
                            list.remove(getAdapterPosition());

                            String json = gson.toJson(savedList, listType);
                            UtilsDefault.updateSharedPreference(Constants.list, json);
                            notifyItemRemoved(getAdapterPosition());
                            Toast.makeText(context, "Removed successfully", Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            });
        }

    }

    public CustomAdapter(List<ModelRedditPopular.Data.Child> list_urls, Context context, int type) {
        this.list = list_urls;
        this.context = context;
        this.type = type;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_popular, parent, false);

        return new CustomAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (type == 1) {
            holder.fav.setText("Remove");
        }
        holder.imageView.setVisibility(View.GONE);
        holder.content_title.setVisibility(View.GONE);
        holder.content.setVisibility(View.GONE);
        holder.tv_author.setText(list.get(position).getData().getSubreddit_name_prefixed());
        holder.tv_postby.setText("Posted by " + list.get(position).getData().
                getAuthor());
        if (list.get(position).getData().getTotal_awards_received() != null) {
            holder.tv_awards.setText(list.get(position).getData().
                    getTotal_awards_received() + " Awards");
        }
        if (list.get(position).getData().getTitle() != null) {
            holder.content_title.setVisibility(View.VISIBLE);
            holder.content_title.setText(list.get(position).getData().getTitle());
        }
        if (list.get(position).getData().getSelftext() != null) {
            holder.content.setVisibility(View.VISIBLE);
            holder.content.setText(list.get(position).getData().getSelftext());
        }


        //todo
        //optional - true by default

        //to play pause videos manually (optional)


        if (list.get(position).getData().getPreview() != null &&
                list.get(position).getData().getPreview().getImages() != null &&
                list.get(position).getData().getPreview().getImages().size() != 0 &&
                list.get(position).getData().getPreview().getImages().get(0).getSource().getUrl() != null) {
            if (list.get(position).getData().getPreview().getImages().get(0).getSource().getUrl().contains("amp;")) {
                String imgurls = list.get(position).getData().getPreview().getImages().get(0).getSource().getUrl().replace("amp;", "");
                Imageurl=imgurls;
                Log.d("orginal", "onBindViewHolder: " + list.get(position).
                        getData().getPreview().getImages().get(0).getSource().getUrl());
            } else {
                Imageurl=list.get(position).getData().getPreview().getImages().get(0).getSource().getUrl();

            }

        } else {
            Imageurl=list.get(position).getData().getThumbnail();
           // holder.setImageUrl(list.get(position).getData().getThumbnail());
        }
        if (Imageurl != null && !Imageurl.equals("") && Imageurl.contains(".jpg") || Imageurl.contains(".png")) {
            Log.d("datass", "onBindViewHolder: " + Imageurl);
            holder.imageView.setVisibility(View.VISIBLE);


            Picasso.with(context).load(Imageurl).

                    into(holder.imageView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        BitmapDrawable drawable = (BitmapDrawable) holder.imageView.getDrawable();
                                        Bitmap bitmap = drawable.getBitmap();
                                        int bitmapwidth = 0;
                                        int bitmpheight = 0;
                                        bitmapwidth = bitmap.getWidth();
                                        bitmpheight = bitmap.getHeight();
                                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.imageView.getLayoutParams();
                                        if (bitmapwidth == 0 || bitmpheight == 0) {
                                            return;
                                        }
                                        int newwidth = holder.imageView.getWidth();
                                        int newheight = newwidth * bitmpheight / bitmapwidth;
                                        params.height = newheight;
                                        holder.imageView.setLayoutParams(params);

                                    } catch (Exception e) {

                                    }

                                }
                            }, 100);


                        }

                        @Override
                        public void onError() {

                        }


                    });

        }


//        if (list.get(position).getData().getSecure_media()!=null&&
//                list.get(position).getData().getSecure_media().getReddit_video()!=null){
//            ((MyViewHolder) holder).data.setVisibility(View.VISIBLE);
//           // holder.getAAH_ImageView().setVisibility(View.VISIBLE);
//            Log.d("imageuu", "onBindViewHolder: "+list.get(position).getData().getSecure_media()
//                    .getReddit_video().getFallback_url());
//
//            holder.setVideoUrl(list.get(position).getData().getSecure_media()
//                    .getReddit_video().getFallback_url()+".mp4");
//           // ((MyViewHolder) holder).img_vol.setVisibility(View.VISIBLE);
//            ((MyViewHolder) holder).img_playback.setVisibility(View.VISIBLE);
//        }
//        else {
//            ((MyViewHolder) holder).img_vol.setVisibility(View.GONE);
//            ((MyViewHolder) holder).img_playback.setVisibility(View.GONE);
//        }


        // holder.setLooping(true);
        //load image/thumbnail into imageview


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}