package com.example.movieapp.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EpisodeRequest {
    @NotEmpty(message = "Tên tập phim không được để trống")
    private String name;

    @NotNull(message = "Thời lượng không được để trống")
    private Integer duration;

    @NotNull(message = "Thứ tự hiển thị không được để trống")
    private Integer displayOrder;

    @NotEmpty(message = "Video URL không được để trống")
    private String videoUrl;

    @NotNull(message = "Trạng thái không được để trống")
    private Boolean status;

    @NotNull(message = "movieId không được để trống")
    private Integer movieId;
}
