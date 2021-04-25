package com.kabu.dev.dto;

public class KabuVideoOutDto {
	
	private Long videoCd;
	
    private String path;
    
    private String videoName;

	public Long getVideoCd() {
		return videoCd;
	}

	public void setVideoCd(Long videoCd) {
		this.videoCd = videoCd;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
    
}
