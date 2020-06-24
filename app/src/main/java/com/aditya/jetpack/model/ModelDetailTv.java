package com.aditya.jetpack.model;

public class ModelDetailTv {
    private String original_language;

    private String number_of_episodes;

    private Networks[] networks;

    private String type;

    private String backdrop_path;

    private ModelGenre[] genres;

    private String popularity;

    private String id;

    private String number_of_seasons;

    private String vote_count;

    private String first_air_date;

    private String overview;

    private Seasons[] seasons;

    private String[] languages;

    private Created_by[] created_by;

    private Last_episode_to_air last_episode_to_air;

    private String poster_path;

    private String[] origin_country;

    private Production_companies[] production_companies;

    private String original_name;

    private String vote_average;

    private String name;

    private String[] episode_run_time;

    private String in_production;

    private String last_air_date;

    private String homepage;

    private String status;

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getNumber_of_episodes() {
        return number_of_episodes;
    }

    public void setNumber_of_episodes(String number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public Networks[] getNetworks() {
        return networks;
    }

    public void setNetworks(Networks[] networks) {
        this.networks = networks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public ModelGenre[] getGenres() {
        return genres;
    }

    public void setGenres(ModelGenre[] genres) {
        this.genres = genres;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(String number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Seasons[] getSeasons() {
        return seasons;
    }

    public void setSeasons(Seasons[] seasons) {
        this.seasons = seasons;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public Created_by[] getCreated_by() {
        return created_by;
    }

    public void setCreated_by(Created_by[] created_by) {
        this.created_by = created_by;
    }

    public Last_episode_to_air getLast_episode_to_air() {
        return last_episode_to_air;
    }

    public void setLast_episode_to_air(Last_episode_to_air last_episode_to_air) {
        this.last_episode_to_air = last_episode_to_air;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String[] getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String[] origin_country) {
        this.origin_country = origin_country;
    }

    public Production_companies[] getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(Production_companies[] production_companies) {
        this.production_companies = production_companies;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getEpisode_run_time() {
        return episode_run_time;
    }

    public void setEpisode_run_time(String[] episode_run_time) {
        this.episode_run_time = episode_run_time;
    }

    public String getIn_production() {
        return in_production;
    }

    public void setIn_production(String in_production) {
        this.in_production = in_production;
    }

    public String getLast_air_date() {
        return last_air_date;
    }

    public void setLast_air_date(String last_air_date) {
        this.last_air_date = last_air_date;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class Seasons
    {

        private String overview;

        private String episode_count;

        private String name;

        private String season_number;

        private String id;

        public String getOverview ()
        {
            return overview;
        }

        public void setOverview (String overview)
        {
            this.overview = overview;
        }

        public String getEpisode_count ()
        {
            return episode_count;
        }

        public void setEpisode_count (String episode_count)
        {
            this.episode_count = episode_count;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
        }

        public String getSeason_number ()
        {
            return season_number;
        }

        public void setSeason_number (String season_number)
        {
            this.season_number = season_number;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [air_date = "+", overview = "+overview+", episode_count = "+episode_count+", name = "+name+", season_number = "+season_number+", id = "+id+", poster_path = "+poster_path+"]";
        }
    }

    public class Production_companies
    {
        private String name;

        private String id;

        private String origin_country;

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
            return "ClassPojo [logo_path = "+", name = "+name+", id = "+id+", origin_country = "+origin_country+"]";
        }
    }

    public class Networks
    {
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

    public class Last_episode_to_air
    {
        private String production_code;

        private String air_date;

        private String overview;

        private String episode_number;

        private String show_id;

        private String vote_average;

        private String name;

        private String season_number;

        private String id;

        private String still_path;

        private String vote_count;

        public String getProduction_code ()
        {
            return production_code;
        }

        public void setProduction_code (String production_code)
        {
            this.production_code = production_code;
        }

        public String getAir_date ()
        {
            return air_date;
        }

        public void setAir_date (String air_date)
        {
            this.air_date = air_date;
        }

        public String getOverview ()
        {
            return overview;
        }

        public void setOverview (String overview)
        {
            this.overview = overview;
        }

        public String getEpisode_number ()
        {
            return episode_number;
        }

        public void setEpisode_number (String episode_number)
        {
            this.episode_number = episode_number;
        }

        public String getShow_id ()
        {
            return show_id;
        }

        public void setShow_id (String show_id)
        {
            this.show_id = show_id;
        }

        public String getVote_average ()
        {
            return vote_average;
        }

        public void setVote_average (String vote_average)
        {
            this.vote_average = vote_average;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
        }

        public String getSeason_number ()
        {
            return season_number;
        }

        public void setSeason_number (String season_number)
        {
            this.season_number = season_number;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getStill_path ()
        {
            return still_path;
        }

        public void setStill_path (String still_path)
        {
            this.still_path = still_path;
        }

        public String getVote_count ()
        {
            return vote_count;
        }

        public void setVote_count (String vote_count)
        {
            this.vote_count = vote_count;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [production_code = "+production_code+", air_date = "+air_date+", overview = "+overview+", episode_number = "+episode_number+", show_id = "+show_id+", vote_average = "+vote_average+", name = "+name+", season_number = "+season_number+", id = "+id+", still_path = "+still_path+", vote_count = "+vote_count+"]";
        }
    }

    public class Created_by
    {
        private String gender;

        private String credit_id;

        private String name;

        private String id;

        public String getGender ()
        {
            return gender;
        }

        public void setGender (String gender)
        {
            this.gender = gender;
        }

        public String getCredit_id ()
        {
            return credit_id;
        }

        public void setCredit_id (String credit_id)
        {
            this.credit_id = credit_id;
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

        @Override
        public String toString()
        {
            return "ClassPojo [gender = "+gender+", credit_id = "+credit_id+", name = "+name+", profile_path = "+", id = "+id+"]";
        }
    }
}
