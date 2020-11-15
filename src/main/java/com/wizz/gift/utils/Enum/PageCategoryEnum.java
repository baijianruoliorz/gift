package com.wizz.gift.utils.Enum;

/**
 * 页面类别枚举类
 * @author 郭树耸
 * @version 1.0
 * @date 2020/3/28 13:16
 */
public enum PageCategoryEnum {

    COVER_PAGE(0,"封面页"),
    CONTENT_PAGE(1,"目录页"),
    TRANSITION_PAGE(2,"过渡页"),
    TEXT_PAGE(3,"正文页"),
    PICTURE_PAGE(4,"图文页"),
    TABLE_PAGE(5,"表格页"),
    END_PAGE(6,"结束页");


    /**
     * 页面类型id
     */
    private Integer pageCategoryId;

    /**
     * 页面类型描述
     */
    private String pageCategoryDetail;

    PageCategoryEnum(Integer pageCategoryId, String pageCategoryDetail) {
        this.pageCategoryId = pageCategoryId;
        this.pageCategoryDetail = pageCategoryDetail;
    }

    public Integer getPageCategoryId() {
        return this.pageCategoryId;
    }

    public void setPageCategoryId(Integer pageCategoryId) {
        this.pageCategoryId = pageCategoryId;
    }

    public String getPageCategoryDetail() {
        return this.pageCategoryDetail;
    }

    public void setPageCategoryDetail(String pageCategoryDetail) {
        this.pageCategoryDetail = pageCategoryDetail;
    }
}
