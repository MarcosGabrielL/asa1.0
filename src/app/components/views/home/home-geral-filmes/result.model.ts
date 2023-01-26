export interface Result{
     id?: number;
     poster_path : String;
     adult: boolean;
     overview: String;
     release_date: String;
     genre_ids: number[];
     original_title: String;
     original_language: String;
     title: String;
     backdrop_path: String;
     popularity: number;
     vote_count: number;
     video: boolean;
     vote_average: number;
}

export interface ResultSeries{
     id?: number;
     poster_path : String;
     adult: boolean;
     overview: String;
     release_date: String;
     genre_ids: number[];
     original_title: String;
     original_name: String;
     original_language: String;
     name: String;
     backdrop_path: String;
     popularity: number;
     vote_count: number;
     video: boolean;
     vote_average: number;
}