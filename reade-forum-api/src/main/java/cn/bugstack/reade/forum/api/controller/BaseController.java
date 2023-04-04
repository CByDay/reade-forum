//package cn.bugstack.reade.forum.api.controller;
//
//
//import com.github.pagehelper.PageHelper;
//import com.platform.bean.AjaxResult;
//import com.platform.bean.PageDomain;
//import com.platform.common.component.CookieUtils;
//import com.platform.common.constant.Constants;
//import com.platform.common.util.DateUtils;
//import com.platform.common.util.ServletUtils;
//import com.platform.common.util.StringUtils;
//import com.platform.repository.id.IdGeneratorWorker;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.InitBinder;
//
//import javax.annotation.Resource;
//import java.beans.PropertyEditorSupport;
//import java.util.Date;
//
///**
// * @author liuli
// */
//public class BaseController {
////    public Logger log = LoggerFactory.getLogger(this.getClass());
//    @Autowired
//    protected CookieUtils cookieUtils;
//    @Resource
//    public IdGeneratorWorker idGeneratorWorker;
//
//    @InitBinder
//    public void initBinder(WebDataBinder binder)
//    {
//        // Date 类型转换
//        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
//        {
//            @Override
//            public void setAsText(String text)
//            {
//                setValue(DateUtils.parseDate(text));
//            }
//        });
//    }
//
//
//
//    /**
//     * 设置请求分页数据
//     */
//    protected void startPage()
//    {
//        PageDomain pageDomain = new PageDomain();
//        pageDomain.setPageIndex(ServletUtils.getParameterToInt(Constants.PAGE_INDEX));
//        pageDomain.setPageSize(ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
//        pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
//        pageDomain.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));
//        Integer pageNum = pageDomain.getPageIndex();
//        //默认分页值
//        if (pageNum == null){
//            pageNum = 1;
//        }
//        Integer pageSize = pageDomain.getPageSize();
//        if (pageSize == null){
//            pageSize =20;
//        }
//        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
//        {
//            String orderBy = pageDomain.getOrderBy();
//            PageHelper.startPage(pageNum, pageSize, orderBy);
//        }
//    }
//
//    /**
//     * 响应返回结果
//     *
//     * @param rows 影响行数
//     * @return 操作结果
//     */
//    protected AjaxResult toAjax(int rows)
//    {
//        return rows > 0 ? success() : error();
//    }
//
//    /**
//     * 响应返回结果
//     *
//     * @param result 结果
//     * @return 操作结果
//     */
//    protected AjaxResult toAjax(boolean result)
//    {
//        return result ? success() : error();
//    }
//
//    /**
//     * 返回成功
//     */
//    public AjaxResult success()
//    {
//        return AjaxResult.success();
//    }
//
//    /**
//     * 返回失败消息
//     */
//    public AjaxResult error()
//    {
//        return AjaxResult.error();
//    }
//
//    /**
//     * 返回成功消息
//     */
//    public AjaxResult success(String message)
//    {
//        return AjaxResult.success(message);
//    }
//
//    /**
//     * 返回失败消息
//     */
//    public AjaxResult error(String message)
//    {
//        return AjaxResult.error(message);
//    }
//
//    /**
//     * 返回错误码消息
//     */
//    public AjaxResult error(int code, String message)
//    {
//        return AjaxResult.error(code, message);
//    }
//
//    /**
//     * 页面跳转
//     */
//    public String redirect(String url)
//    {
//        return StringUtils.format("redirect:{}", url);
//    }
//
//}
