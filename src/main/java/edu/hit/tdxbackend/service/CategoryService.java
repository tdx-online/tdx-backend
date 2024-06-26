package edu.hit.tdxbackend.service;

public interface CategoryService {
    /*
     * 获取首页分类
     */
    Object categoryAndProduct();

    /*
     * 后台获取所有分类
     * @return 所有分类, 包括id, name
     */
    Object listAllCategories();

    /*
     * 删除商品分类
     */
    boolean deleteCategory(int i);

    /*
     * 添加商品分类
     */
    int addCategory(String categoryName);

    /*
     * 根据分类cid获取商品内容
     */
    Object cidCategoryProperty(int i);
}
