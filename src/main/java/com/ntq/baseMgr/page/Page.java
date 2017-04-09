package com.ntq.baseMgr.page;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>@description:分页封装 </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.page
 * @className:
 * @author: shuangyang
 * @date: 17-4-2 下午12:28
 */
@Setter
@Getter
public class Page<T> {
    private int pageNo = 1;//页码，默认是第一页
    private int pageSize = 15;//每页显示的记录数，默认是15
    private int totalRecord;//总记录数
    private int totalPage;//总页数
    private List<T> results;//对应的当前页记录
    private Map<String, Object> params = new HashMap<>();//其他的参数我们把它分装成一个Map对象
    private boolean success;//成功或者状态


    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        //在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
        int totalPage = totalRecord%pageSize==0 ? totalRecord/pageSize : totalRecord/pageSize + 1;
        this.setTotalPage(totalPage);
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Page [pageNo=").append(pageNo).append(", pageSize=")
                .append(pageSize).append(", results=").append(results).append(
                ", totalPage=").append(totalPage).append(
                ", totalRecord=").append(totalRecord).append("]");
        return builder.toString();
    }

}

