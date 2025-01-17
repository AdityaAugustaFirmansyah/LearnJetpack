package com.aditya.jetpack.model;

public class ModelDetailMovie {
    private String original_language;

    private String imdb_id;

    private String video;

    private String title;

    private String backdrop_path;

    private String revenue;

    private String answerYes = "Yes";

    private String answerNo="No";

    private ModelGenre[] genres;

    private String popularity;

    private Production_countries[] production_countries;

    private String id;

    private String vote_count;

    private String budget;

    private String overview;

    private String original_title;

    private String runtime;

    private String poster_path;

    private Spoken_languages[] spoken_languages;

    private Production_companies[] production_companies;

    private String release_date;

    private String vote_average;

    private String tagline;

    private boolean adult;

    private String homepage;

    private String status;

    public String getAnswerYes() {
        return answerYes;
    }

    public String getAnswerNo() {
        return answerNo;
    }

    public String getOriginal_language ()
    {
        return original_language;
    }

    public void setOriginal_language (String original_language)
    {
        this.original_language = original_language;
    }

    public String getImdb_id ()
    {
        return imdb_id;
    }

    public void setImdb_id (String imdb_id)
    {
        this.imdb_id = imdb_id;
    }

    public String getVideo ()
    {
        return video;
    }

    public void setVideo (String video)
    {
        this.video = video;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getBackdrop_path ()
    {
        return backdrop_path;
    }

    public void setBackdrop_path (String backdrop_path)
    {
        this.backdrop_path = backdrop_path;
    }

    public String getRevenue ()
    {
        return revenue;
    }

    public void setRevenue (String revenue)
    {
        this.revenue = revenue;
    }

    public ModelGenre[] getGenres ()
    {
        return genres;
    }

    public void setGenres (ModelGenre[] genres)
    {
        this.genres = genres;
    }

    public String getPopularity ()
    {
        return popularity;
    }

    public void setPopularity (String popularity)
    {
        this.popularity = popularity;
    }

    public Production_countries[] getProduction_countries ()
    {
        return production_countries;
    }

    public void setProduction_countries (Production_countries[] production_countries)
    {
        this.production_countries = production_countries;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getVote_count ()
    {
        return vote_count;
    }

    public void setVote_count (String vote_count)
    {
        this.vote_count = vote_count;
    }

    public String getBudget ()
    {
        return budget;
    }

    public void setBudget (String budget)
    {
        this.budget = budget;
    }

    public String getOverview ()
    {
        return overview;
    }

    public void setOverview (String overview)
    {
        this.overview = overview;
    }

    public String getOriginal_title ()
    {
        return original_title;
    }

    public void setOriginal_title (String original_title)
    {
        this.original_title = original_title;
    }

    public String getRuntime ()
    {
        return runtime;
    }

    public void setRuntime (String runtime)
    {
        this.runtime = runtime;
    }

    public String getPoster_path ()
    {
        return poster_path;
    }

    public void setPoster_path (String poster_path)
    {
        this.poster_path = poster_path;
    }

    public Spoken_languages[] getSpoken_languages ()
    {
        return spoken_languages;
    }

    public void setSpoken_languages (Spoken_languages[] spoken_languages)
    {
        this.spoken_languages = spoken_languages;
    }

    public Production_companies[] getProduction_companies ()
    {
        return production_companies;
    }

    public void setProduction_companies (Production_companies[] production_companies)
    {
        this.production_companies = production_companies;
    }

    public String getRelease_date ()
    {
        return release_date;
    }

    public void setRelease_date (String release_date)
    {
        this.release_date = release_date;
    }

    public String getVote_average ()
    {
        return vote_average;
    }

    public void setVote_average (String vote_average)
    {
        this.vote_average = vote_average;
    }

    public String getTagline ()
    {
        return tagline;
    }

    public void setTagline (String tagline)
    {
        this.tagline = tagline;
    }

    public boolean getAdult ()
    {
        return adult;
    }

    public void setAdult (boolean adult)
    {
        this.adult = adult;
    }

    public String getHomepage ()
    {
        return homepage;
    }

    public void setHomepage (String homepage)
    {
        this.homepage = homepage;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [original_language = "+original_language+", imdb_id = "+imdb_id+", video = "+video+", title = "+title+", backdrop_path = "+backdrop_path+", revenue = "+revenue+", genres = "+genres+", popularity = "+popularity+", production_countries = "+production_countries+", id = "+id+", vote_count = "+vote_count+", budget = "+budget+", overview = "+overview+", original_title = "+original_title+", runtime = "+runtime+", poster_path = "+poster_path+", spoken_languages = "+spoken_languages+", production_companies = "+production_companies+", release_date = "+release_date+", vote_average = "+vote_average+", tagline = "+tagline+", adult = "+adult+", homepage = "+homepage+", status = "+status+"]";
    }

    private static class Spoken_languages {
        private String name;

        private String iso_639_1;

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
        }

        public String getIso_639_1 ()
        {
            return iso_639_1;
        }

        public void setIso_639_1 (String iso_639_1)
        {
            this.iso_639_1 = iso_639_1;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [name = "+name+", iso_639_1 = "+iso_639_1+"]";
        }
    }

    private static class Production_companies {
        private String logo_path;

        private String name;

        private String id;

        private String origin_country;

        public String getLogo_path ()
        {
            return logo_path;
        }

        public void setLogo_path (String logo_path)
        {
            this.logo_path = logo_path;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getOrigin_country ()
        {
            return origin_country;
        }

        public void setOrigin_country (String origin_country)
        {
            this.origin_country = origin_country;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [logo_path = "+logo_path+", name = "+name+", id = "+id+", origin_country = "+origin_country+"]";
        }
    }

    private static class Production_countries {
        private String iso_3166_1;

        private String name;

        public String getIso_3166_1 ()
        {
            return iso_3166_1;
        }

        public void setIso_3166_1 (String iso_3166_1)
        {
            this.iso_3166_1 = iso_3166_1;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [iso_3166_1 = "+iso_3166_1+", name = "+name+"]";
        }
    }
}
