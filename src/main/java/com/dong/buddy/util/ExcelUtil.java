package com.dong.buddy.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;

public class ExcelUtil
{
    public static void noModelMultipleSheet(String exlcelPath) throws FileNotFoundException
    {
        InputStream inputStream = new FileInputStream(exlcelPath);
        try
        {
            ExcelReader reader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null,
                    new AnalysisEventListener<List<String>>()
                    {
                        @Override
                        public void invoke(List<String> object, AnalysisContext context)
                        {
                            System.out.println("当前sheet:" + context.getCurrentSheet().getSheetNo() + " 当前行："
                                    + context.getCurrentRowNum() + " data:" + object);
                        }

                        @Override
                        public void doAfterAllAnalysed(AnalysisContext context)
                        {

                        }
                    });

            reader.read();
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        finally
        {
            try
            {
                inputStream.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        noModelMultipleSheet("D:\\doc\\mytask.xlsx");
    }
}