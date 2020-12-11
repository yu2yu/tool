package com.yy.tool.excel.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @version 1.0
 * @description:
 * @author: yy
 * @date: 2020/11/25
 */
@Data
@ToString
@EqualsAndHashCode(callSuper=true)
public class ExcelEntity extends ExcelBaseEntity implements Comparable<ExcelEntity>{
    /**
     * 排序顺序
     */
    private int index = 0;

    /**
     * 是否是复杂表头
     */
    private boolean isComplexHead = false;

    /**
     * 是否需要纵向合并，也就是有list的属性时
     */
    private boolean needMerge = false;

    /**
     * 父表头的名称
     */
    private String groupName;
    private List<ExcelEntity> list;

    @Override
    public int compareTo(ExcelEntity o) {
        return this.index - o.index;
    }
}
