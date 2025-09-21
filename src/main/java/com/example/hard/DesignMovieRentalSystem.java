package com.example.hard;

import java.util.*;

public class DesignMovieRentalSystem {
    class MovieRentingSystem {
        private Set<Movie> rentedMovies;
        private Set<Movie> notRentedMovies;
        private Map<Integer, Set<Movie>> movieToShops; // key: movieId, value: shops có movie đó
        private Map<String, Movie> movieMap; // key: shopId + "_" + movieId

        private int cmp(Movie a, Movie b) {
            if (a.price != b.price)
                return a.price - b.price;
            if (a.shop != b.shop)
                return a.shop - b.shop;
            return a.id - b.id;
        }

        public MovieRentingSystem(int n, int[][] entries) {
            notRentedMovies = new TreeSet<>(this::cmp);
            rentedMovies = new TreeSet<>(this::cmp);
            movieToShops = new HashMap<>();
            movieMap = new HashMap<>();

            for (int[] entry : entries) {
                Movie movie = new Movie(entry[2], entry[0], entry[1]);
                notRentedMovies.add(movie);
                movieMap.put(entry[0] + "_" + entry[1], movie);
                movieToShops
                        .computeIfAbsent(entry[1], k -> new TreeSet<>(this::cmp))
                        .add(movie);
            }
        }

        public List<Integer> search(int movieId) {
            List<Integer> result = new ArrayList<>();
            if (!movieToShops.containsKey(movieId)) return result;
            Iterator<Movie> it = movieToShops.get(movieId).iterator();
            while (it.hasNext() && result.size() < 5) {
                Movie m = it.next();
                if (!rentedMovies.contains(m)) {
                    result.add(m.shop);
                }
            }
            return result;
        }

        public void rent(int shop, int movieId) {
            Movie m = movieMap.get(shop + "_" + movieId);
            notRentedMovies.remove(m);
            rentedMovies.add(m);
            movieToShops.get(movieId).remove(m);
        }

        public void drop(int shop, int movieId) {
            Movie m = movieMap.get(shop + "_" + movieId);
            notRentedMovies.add(m);
            rentedMovies.remove(m);
            movieToShops.get(movieId).add(m);
        }

        public List<List<Integer>> report() {
            List<List<Integer>> result = new ArrayList<>();
            Iterator<Movie> it = rentedMovies.iterator();
            while (it.hasNext() && result.size() < 5) {
                Movie m = it.next();
                List<Integer> term = new ArrayList<>();
                term.add(m.shop);
                term.add(m.id);
                result.add(term);
            }
            return result;
        }

        class Movie {
            int price, shop, id;

            public Movie(int price, int shop, int id) {
                this.price = price;
                this.shop = shop;
                this.id = id;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o)
                    return true;
                if (o == null || getClass() != o.getClass())
                    return false;
                Movie movie = (Movie) o;
                return shop == movie.shop && id == movie.id;
            }

            @Override
            public int hashCode() {
                return Objects.hash(shop, id);
            }
        }
    }

/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * MovieRentingSystem obj = new MovieRentingSystem(n, entries);
 * List<Integer> param_1 = obj.search(movie);
 * obj.rent(shop,movie);
 * obj.drop(shop,movie);
 * List<List<Integer>> param_4 = obj.report();
 */
}
