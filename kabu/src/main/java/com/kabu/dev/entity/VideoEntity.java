package com.kabu.dev.entity;

import java.util.Date;

public class VideoEntity {
	private Long videoCd;
	
    private String path;
    
    private String videoName;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

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

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

     
}