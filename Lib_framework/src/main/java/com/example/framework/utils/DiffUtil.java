package com.example.framework.utils;

import java.util.ArrayList;

/**
 * function: 类功能描述
 * describe:详细描述
 * email：yangchaozhi@outlook.com
 *
 * @author vinko on 2017/03/31.
 */

public class DiffUtil extends android.support.v7.util.DiffUtil.Callback{

    public DiffUtil() {
        super();
    }

    @Override
    public int getOldListSize() {
        return 0;
    }

    @Override
    public int getNewListSize() {
        return 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
}
