package com.demo.cityIno.model;

import java.util.List;

public class ResponseModel {

    private String title;
    private List<RowsBean> rows;

    public ResponseModel(String title, List<RowsBean> rows) {
        this.title = title;
        this.rows = rows;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {

        private String title;
        private String description;
        private String imageHref;

        public RowsBean(){

        }

        public RowsBean(String title, String description, String imageHref) {
            this.title = title;
            this.description = description;
            this.imageHref = imageHref;
        }



        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageHref() {
            return imageHref;
        }

        public void setImageHref(String imageHref) {
            this.imageHref = imageHref;
        }
    }
}
