package com.huohuzhihui.web.controller.account;

import java.util.List;

import com.huohuzhihui.common.constant.CardConstants;
import com.huohuzhihui.common.constant.UserConstants;
import com.huohuzhihui.common.core.domain.entity.Card;
import com.huohuzhihui.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.huohuzhihui.common.annotation.Log;
import com.huohuzhihui.common.core.controller.BaseController;
import com.huohuzhihui.common.core.domain.AjaxResult;
import com.huohuzhihui.common.enums.BusinessType;
import com.huohuzhihui.account.service.IAccCardService;
import com.huohuzhihui.common.utils.poi.ExcelUtil;
import com.huohuzhihui.common.core.page.TableDataInfo;

/**
 * 账户下的卡Controller
 * 
 * @author zylu
 * @date 2020-11-14
 */
@RestController
@RequestMapping("/account/card")
public class AccCardController extends BaseController
{
    @Autowired
    private IAccCardService accCardService;

    /**
     * 查询账户下的卡列表
     */
    @PreAuthorize("@ss.hasPermi('account:card:list')")
    @GetMapping("/list")
    public TableDataInfo list(Card accCard)
    {
        startPage();
        List<Card> list = accCardService.selectAccCardList(accCard);
        return getDataTable(list);
    }

    /**
     * 导出账户下的卡列表
     */
    @PreAuthorize("@ss.hasPermi('account:card:export')")
    @Log(title = "账户下的卡", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Card accCard)
    {
        List<Card> list = accCardService.selectAccCardList(accCard);
        ExcelUtil<Card> util = new ExcelUtil<Card>(Card.class);
        return util.exportExcel(list, "card");
    }

    /**
     * 获取账户下的卡详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:card:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(accCardService.selectAccCardById(id));
    }

    /**
     * 新增账户下的卡
     */
    @PreAuthorize("@ss.hasPermi('account:card:add')")
    @Log(title = "开卡", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Card accCard)
    {
        if (CardConstants.NOT_UNIQUE.equals(accCardService.checkCardNoUnique(accCard.getCardNo())))
        {
            return AjaxResult.error("开卡失败，卡号'"+accCard.getCardNo()+"'已存在");
        }
        accCard.setCreateBy(SecurityUtils.getUsername());
        return toAjax(accCardService.insertAccCard(accCard));
    }

    /**
     * 修改账户下的卡
     */
    @PreAuthorize("@ss.hasPermi('account:card:edit')")
    @Log(title = "编辑卡", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Card accCard)
    {
        accCard.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(accCardService.updateAccCard(accCard));
    }

    /**
     * 删除账户下的卡
     */
    @PreAuthorize("@ss.hasPermi('account:card:remove')")
    @Log(title = "账户下的卡", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(accCardService.deleteAccCardByIds(ids));
    }


    /**
     * 补卡
     * @param accCard 卡对象
     * @return
     */
    @PreAuthorize("@ss.hasPermi('account:card:replace')")
    @Log(title = "补卡", businessType = BusinessType.INSERT)
    @PostMapping(value = "/replace")
    @ResponseBody
    public AjaxResult replace(@RequestBody Card accCard)
    {
        if (CardConstants.NOT_UNIQUE.equals(accCardService.checkCardNoUnique(accCard.getCardNo())))
        {
            return AjaxResult.error("补卡失败，卡号'"+accCard.getCardNo()+"'已存在");
        }
        accCard.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(accCardService.replaceAccCard(accCard));
    }


    /**
     * 批量挂失卡
     * @param ids 卡ID集合
     * @return
     */
    @PreAuthorize("@ss.hasPermi('account:card:loss')")
    @Log(title = "挂失卡", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/loss")
    @ResponseBody
    public AjaxResult loss( Long[] ids)
    {
        return toAjax(accCardService.lossAccCardByIds(ids));
    }

    /**
     * 批量解挂卡
     * @param ids 卡ID集合
     * @return
     */
    @PreAuthorize("@ss.hasPermi('account:card:unloss')")
    @Log(title = "解挂卡", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/unloss")
    @ResponseBody
    public AjaxResult unloss( Long[] ids)
    {
        return toAjax(accCardService.unlossAccCardByIds(ids));
    }

    /**
     * 批量注销卡
     * @param ids 卡ID集合
     * @return
     */
    @PreAuthorize("@ss.hasPermi('account:card:cancel')")
    @Log(title = "注销卡", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/cancel")
    @ResponseBody
    public AjaxResult cancel( Long[] ids)
    {
        return toAjax(accCardService.cancelAccCardByIds(ids));
    }
}
