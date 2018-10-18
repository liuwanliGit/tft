package com.tft.forum.post.bean;

/**
 * <br>类描述：帖子
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/28 10:33
 *
 * @ClassName Post
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class Post {

    private int id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return post.id == this.id;
    }

    @Override
    public int hashCode() {

        return (int)Math.random()*100;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
