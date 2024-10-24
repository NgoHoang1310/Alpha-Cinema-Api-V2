package com.example.alpha_cinemas.service.impl;

import com.example.alpha_cinemas.dto.request.MovieRequest;
import com.example.alpha_cinemas.dto.request.PaginationRequest;
import com.example.alpha_cinemas.dto.response.MovieResponse;
import com.example.alpha_cinemas.dto.response.PaginationResponse;
import com.example.alpha_cinemas.enums.ApiErrorCode;
import com.example.alpha_cinemas.enums.Status;
import com.example.alpha_cinemas.exception.ApiException;
import com.example.alpha_cinemas.mapper.MovieMapper;
import com.example.alpha_cinemas.model.Category;
import com.example.alpha_cinemas.model.Movie;
import com.example.alpha_cinemas.repository.CategoryRepository;
import com.example.alpha_cinemas.repository.MovieRepository;
import com.example.alpha_cinemas.service.FirebaseStorageService;
import com.example.alpha_cinemas.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    @Value("${firebase.image-url}")
    private String imageUrl;

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final FirebaseStorageService firebaseStorageService;
    private final CategoryRepository categoryRepository;


    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper, FirebaseStorageService firebaseStorageService, CategoryRepository categoryRepository) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.firebaseStorageService = firebaseStorageService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public MovieResponse handleGetAMovie(Long id) {
        Movie movie = movieRepository.findMovieById(id);
        return movieMapper.toDto(movie);
    }

    @Override
    public Set<MovieResponse> handleGetMovies() {
        Set<Movie> movies = movieRepository.findMoviesByDestroyed(false);
        Set<MovieResponse> movieResponses = new HashSet<>();
        if (movies.isEmpty()) throw new ApiException(ApiErrorCode.NO_MOVIE);
        for (Movie movie : movies) {
            movieResponses.add(movieMapper.toDto(movie));
        }
        return movieResponses;
    }

    // hàm này sẽ trả về 1 object tùy chỉnh bằng cách sử dụng map
    @Override
    public Map<String, Object> handleGetMoviesByStatus(Status status, PaginationRequest paginationRequest) {
        Map<String, Object> object = new HashMap<>();
        // tạo pageable
        Pageable pageable = PageRequest.of(paginationRequest.getPage(), paginationRequest.getLimit(), Sort.by(paginationRequest.getSortBy()));
        // tương tác database
        Page<Movie> movies = movieRepository.findMoviesByDestroyedAndStatus(false, status, pageable);

        // tạo 1 list kiểu movieresponse để map dữ liệu kiểu movíe
        List<MovieResponse> movieResponses = new ArrayList<>();
        if (movies.getContent().isEmpty()) throw new ApiException(ApiErrorCode.NO_MOVIE);
        // thực hiện map dữ liệu
        for (Movie movie : movies.getContent()) {
            movieResponses.add(movieMapper.toDto(movie));
        }
        // tạo ra 2 object sử dụng map
        object.put("content", movieResponses);
        object.put("pagination", PaginationResponse
                .builder()
                .pageable(movies.getPageable())
                .totalPages(movies.getTotalPages())
                .totalElements(movies.getTotalElements())
                .first(movies.isFirst())
                .last(movies.isLast())
                .build());
        return object;
    }

    @Override
    public Set<MovieResponse> handleGetMoviesByStatusAndTheater
            (Status status, String theater, PaginationRequest paginationRequest) {
//        Map<String, Object> object = new HashMap<>();
//        // tạo pageable
//        Pageable pageable = PageRequest.of(paginationRequest.getPage(), paginationRequest.getLimit(), Sort.by(paginationRequest.getSortBy()));
//        // tương tác database
//        Page<Movie> movies = movieRepository
//                .findMoviesByDestroyedAndStatusAndTheater(false, status,theater);
//        System.out.println(movies);
//
//        // tạo 1 list kiểu movieresponse để map dữ liệu kiểu movíe
//        List<MovieResponse> movieResponses = new ArrayList<>();
//        if (movies.getContent().isEmpty()) throw new ApiException(ApiErrorCode.NO_MOVIE);
//        // thực hiện map dữ liệu
//        for (Movie movie : movies.getContent()) {
//            movieResponses.add(movieMapper.toDto(movie));
//        }
//        // tạo ra 2 object sử dụng map
//        object.put("content", movieResponses);
//        object.put("pagination", PaginationResponse
//                .builder()
//                .pageable(movies.getPageable())
//                .totalPages(movies.getTotalPages())
//                .totalElements(movies.getTotalElements())
//                .first(movies.isFirst())
//                .last(movies.isLast())
//                .build());
        return movieRepository.findMoviesByDestroyedAndStatusAndTheater(false, status.toString(),theater)
                .stream().map((movie -> movieMapper.toDto(movie))).collect(Collectors.toSet());
    }

    @Override
    public MovieResponse handleCreateMovie(MovieRequest payload) throws IOException {
        Movie movie = movieMapper.toEntity(payload);
        Set<Category> categories = new HashSet<>();
        String imgName = firebaseStorageService.save(payload.getFile(), "movies");
        String urlImage = firebaseStorageService.getImageUrl(imgName);
        if (urlImage.isEmpty()) throw new ApiException(ApiErrorCode.UPLOAD_FAILED);
        for (String category : payload.getCategory()) {
            categories.add(categoryRepository.findCategoryById(Long.valueOf(category)));
        }

        movie.setCategory(categories);
        movie.setThumbPath(urlImage);
        return movieMapper.toDto(movieRepository.save(movie));
    }

    @Override
    public MovieResponse handleUpdateMovie(Long id, MovieRequest payload) throws IOException {
        Movie movie = movieRepository.findMovieById(id);
        Set<Category> categories = new HashSet<>();

        for (String category : payload.getCategory()) {
            Category _category = categoryRepository.findCategoryById(Long.valueOf(category));
            if (_category != null)
                categories.add(_category);
        }

        if (movie == null) throw new ApiException(ApiErrorCode.MOVIE_NOT_FOUND);
        if (categories.isEmpty()) throw new ApiException(ApiErrorCode.CATEGORY_NOT_FOUND);

        String name = movie.getThumbPath().substring(imageUrl.length() - 2);
        if (!name.isEmpty()) {
            firebaseStorageService.delete(name);
        }

        String newImgName = firebaseStorageService.save(payload.getFile(), "movies");
        String newUrlImage = firebaseStorageService.getImageUrl(newImgName);
        if (newUrlImage.isEmpty()) throw new ApiException(ApiErrorCode.UPLOAD_FAILED);

        movie.setTitle(payload.getTitle());
        movie.setThumbPath(newUrlImage);
        movie.setDuration(payload.getDuration());
        movie.setHot(payload.isHot());
        movie.setCategory(categories);
        movie.setTrailer(payload.getTrailer());
        movie.setLimitation(payload.getLimitation());
        movie.setLanguage(payload.getLanguage());
        movie.setDirector(payload.getDirector());
        movie.setCast(payload.getCast());
        movie.setReleaseDate(payload.getReleaseDate());
        movieRepository.save(movie);

        return movieMapper.toDto(movie);
    }

    @Override
    public void handleSoftDeleteMovie(Long id) {
        Movie movie = movieRepository.findMovieById(id);
        if (movie == null) throw new ApiException(ApiErrorCode.MOVIE_NOT_FOUND);
        movie.setDestroyed(true);
        movieRepository.save(movie);
    }

    @Override
    public void handleHardDeleteMovie(Long id) throws IOException {
        Movie movie = movieRepository.findMovieById(id);
        String name = movie.getThumbPath().substring(imageUrl.length() - 2);
        if (!name.isEmpty()) {
            firebaseStorageService.delete(name);
        }
        movieRepository.deleteById(id);
    }

}
