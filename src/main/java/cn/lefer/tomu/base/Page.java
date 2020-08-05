package cn.lefer.tomu.base;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/7/4
 * @Description : 分页封装
 */
@ToString
@Getter
public class Page<T> {
    private static final long serialVersionUID = 1L;
    private final int pageNum;
    private final int pageSize;
    private final int startRow;
    private final int endRow;
    private final long total;
    private final int pages;
    private final List<T> data;

    private Page(Builder<T> builder) {
        this.pageNum = builder.pageNum;
        this.pageSize = builder.pageSize;
        this.startRow = (pageNum - 1) * pageSize + 1;
        this.endRow = pageNum * pageSize;
        this.total = builder.total;
        this.pages = (int) Math.ceil(builder.total * 1.0 / builder.pageSize);
        this.data = builder.data;
    }

    public static class Builder<T> {
        private int pageNum;
        private int pageSize;
        private long total;
        private List<T> data;

        public Builder<T> pageNum(int pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        public Builder<T> pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder<T> total(long total) {
            this.total = total;
            return this;
        }

        public Builder<T> data(List<T> data) {
            this.data = data;
            return this;
        }

        public Page<T> build() {
            return new Page<>(this);
        }
    }
}
