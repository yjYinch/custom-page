package zyj;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : yj zhang
 * @since : 2021/11/3 16:03
 */

public class PageHelper<T> {
    private Integer pageNum;

    private Integer pageSize;

    private Integer pageTotal;

    private List<T> pageList;

    private PageHelper() {
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public List<T> getPageList() {
        return pageList;
    }

    public PageHelper(List<T> toPageList, Integer pageNum, Integer pageSize) throws Exception {
        if (toPageList == null || toPageList.size() == 0) {
            throw new Exception("The list to be paged is null or empty");
        }
        if (pageNum == null || pageSize == null || pageNum < 1 || pageSize < 1) {
            throw new Exception("The pageNum or pageSize is null or < 1");
        }
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pageTotal = toPageList.size();
        this.pageList = toPageList;
    }

    public List<T> startPage() throws Exception {
        // start index
        int startIndex = (pageNum - 1) * pageSize;

        // get remain list, pageTotal - (startIndex -1) * pageSize
        int remainSize;
        if (startIndex == 0) {
            remainSize = pageTotal;
        } else {
            remainSize = pageTotal - (startIndex / pageSize) * pageSize;
        }

        int maxStartIndex = (pageTotal / pageSize) * pageSize;

        startIndex = Math.min(startIndex, maxStartIndex);

        // end index
        int endIndex;

        // judge the list remain size more than page size
        if ((remainSize - pageSize) > 0) {
            endIndex = startIndex + pageSize - 1;
        } else {
            endIndex = startIndex + remainSize - 1;
        }
        return pageList.subList(startIndex, endIndex + 1);
    }

    public static void main(String[] args) throws Exception {
        List<Integer> list = new ArrayList<>(50);
        for (int i = 0; i < 50; i++) {
            list.add(i);
        }
        PageHelper<Integer> doublePageHelper = new PageHelper<>(list, 1, 10);
        List<Integer> page = doublePageHelper.startPage();
        System.out.println(page);
    }
}
