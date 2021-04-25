package com.kabu.dev.entity;

import java.util.Date;

public class StockEntity {
    private String stockId;

    private String stockName;

    private String industry;

    private String total;

    private String dividendYield;

    private String dividendPershare;

    private String per;

    private String pbr;

    private String eps;

    private String bps;

    private String unit;

    private String delflg;

    private Date createtime;

    private String createuser;

    private String updateuser;

    private Date updatetime;

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId == null ? null : stockId.trim();
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName == null ? null : stockName.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total == null ? null : total.trim();
    }

    public String getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(String dividendYield) {
        this.dividendYield = dividendYield == null ? null : dividendYield.trim();
    }

    public String getDividendPershare() {
        return dividendPershare;
    }

    public void setDividendPershare(String dividendPershare) {
        this.dividendPershare = dividendPershare == null ? null : dividendPershare.trim();
    }

    public String getPer() {
        return per;
    }

    public void setPer(String per) {
        this.per = per == null ? null : per.trim();
    }

    public String getPbr() {
        return pbr;
    }

    public void setPbr(String pbr) {
        this.pbr = pbr == null ? null : pbr.trim();
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps == null ? null : eps.trim();
    }

    public String getBps() {
        return bps;
    }

    public void setBps(String bps) {
        this.bps = bps == null ? null : bps.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getDelflg() {
        return delflg;
    }

    public void setDelflg(String delflg) {
        this.delflg = delflg == null ? null : delflg.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}