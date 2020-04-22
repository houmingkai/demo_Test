package com.example.demo.util;

import com.example.fund02.controller.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**  
* @ClassName: JyrUtils  
* @Description: 获取股票交易日工具类  
* @author 张嘉恒 zhangjiaheng@myhexin.com  
* @date 2017年9月25日  
*    
*/
public class JyrUtils {

	private static final Logger logger = LoggerFactory.getLogger(JyrUtils.class);
	
	/**
	 * 获取到传入日期当年的所有交易日
	 * @param date
	 * @return
	 */
	public static List<String> getThisYearJyr(Date date) {
    	int year = Integer.parseInt(DateUtils.formatDate(date, "yyyyMMdd").substring(0, 4));
        List<String> monthdays = new ArrayList<String>();
        String url = "http://www.10jqka.com.cn/includes/jyr";
        url = url + "/" + year + ".txt";
        String text = HttpUtil.reqForGet(url);// text 形如 a:200:{i:0;s:4:"0105";i:1;s:4:"0106";}
        // www.10jqka.com.cn/includes/jyr/2017.txt
        if (StringUtils.isNotBlank(text)) {// 正常返回
            text = text.substring(text.indexOf("{") + 1, text.lastIndexOf("}") - 1);
            String[] days = text.split(";");
            for (int i = 0; i < days.length; i++) {
                if ((i & 1) == 1) {
                    String monthday = days[i].split(":")[2];// monthday 形如 "0105"
                    monthday = year + monthday.substring(1, monthday.length() - 1);// 去掉 "
                    monthdays.add(monthday);
                }
            }
        }else{
        	logger.error("请求同花顺获取交易日接口出错！");
        }
        return monthdays;
    }
	/**
	 * 是否是交易日
	 * @param date
	 * @return
	 */
	public static boolean isJYR(Date date){
    	String now = DateUtils.formatDate(date, "yyyyMMdd");
    	return getThisYearJyr(date).contains(now);
    }
	public static boolean isNotJYR(Date date){
    	return !isJYR(date);
    }
	
	/**
	 * 获取下个交易日
	 * @param date
	 * @return
	 */
	public static Date nextJYR(Date date){
		Date nextday = DateUtil.addDays(date, 1);
		while(isNotJYR(nextday)){
			nextday = DateUtil.addDays(nextday, 1);
		}
		return nextday;
	}
	
	/**
	 * 获取上个交易日
	 * @param date
	 * @return
	 */
	public static Date lastJYR(Date date){
		Date lastday = DateUtil.addDays(date, -1);
		while(isNotJYR(lastday)){
			lastday = DateUtil.addDays(lastday, -1);
		}
		return lastday;
	}
	
}
