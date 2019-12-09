package com.dineshredditsample.models;

import java.util.List;

/**
 * Created by $Dinesh on 12/7/2019.
 */
public class ModelRedditPopular {


    private Data data;
    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }
    public class Data {
        private List<Child> children = null;
        public List<Child> getChildren() {
            return children;
        }

        public void setChildren(List<Child> children) {
            this.children = children;
        }
        public class Child {
            private Datas data;
            public Datas getData() {
                return data;
            }
            public void setData(Datas data) {
                this.data = data;
            }
            public class Datas {
                String title;
                String subreddit_name_prefixed;
                String author;
                String selftext;
                Preview preview;
                Integer total_awards_received;
                String thumbnail;
                Secure_media secure_media;
                public Preview getPreview() {
                    return preview;
                }

                public void setPreview(Preview preview) {
                    this.preview = preview;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getSubreddit_name_prefixed() {
                    return subreddit_name_prefixed;
                }

                public void setSubreddit_name_prefixed(String subreddit_name_prefixed) {
                    this.subreddit_name_prefixed = subreddit_name_prefixed;
                }

                public String getAuthor() {
                    return author;
                }

                public void setAuthor(String author) {
                    this.author = author;
                }

                public String getSelftext() {
                    return selftext;
                }

                public void setSelftext(String selftext) {
                    this.selftext = selftext;
                }

                public Integer getTotal_awards_received() {
                    return total_awards_received;
                }

                public void setTotal_awards_received(Integer total_awards_received) {
                    this.total_awards_received = total_awards_received;
                }

                public String getThumbnail() {
                    return thumbnail;
                }

                public void setThumbnail(String thumbnail) {
                    this.thumbnail = thumbnail;
                }

                public Secure_media getSecure_media() {
                    return secure_media;
                }

                public void setSecure_media(Secure_media secure_media) {
                    this.secure_media = secure_media;
                }

                public class Secure_media {

                    public Reddit_video reddit_video;

                    public Reddit_video getReddit_video() {
                        return reddit_video;
                    }

                    public void setReddit_video(Reddit_video reddit_video) {
                        this.reddit_video = reddit_video;
                    }

                    public class Reddit_video {

                      public String fallback_url;

                        public String getFallback_url() {
                            return fallback_url;
                        }

                        public void setFallback_url(String fallback_url) {
                            this.fallback_url = fallback_url;
                        }
                    }

                }
                public class Preview {
                    public class Image {

                        private source source;

                        public Preview.source getSource() {
                            return source;
                        }

                        public void setSource(Preview.source source) {
                            this.source = source;
                        }
                    }

                    private List<Image> images = null;

                    public List<Image> getImages() {
                        return images;
                    }

                    public void setImages(List<Image> images) {
                        this.images = images;
                    }

                    public class source{


                        String url;

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }
                    }

                }

            }



        }
    }



}
