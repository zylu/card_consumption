package com.huohuzhihui.web.controller.api;

import com.huohuzhihui.account.service.IAccUserAccountService;
import com.huohuzhihui.common.annotation.Log;
import com.huohuzhihui.common.core.controller.BaseController;
import com.huohuzhihui.common.core.domain.AjaxResult;
import com.huohuzhihui.common.core.domain.entity.SysUser;
import com.huohuzhihui.common.core.page.TableDataInfo;
import com.huohuzhihui.common.enums.BusinessType;
import com.huohuzhihui.common.utils.poi.ExcelUtil;
import com.huohuzhihui.merchant.domain.MerCost;
import com.huohuzhihui.merchant.domain.MerDevice;
import com.huohuzhihui.merchant.exception.PosException;
import com.huohuzhihui.merchant.service.IMerCostService;
import com.huohuzhihui.merchant.service.IMerDeviceService;
import com.huohuzhihui.merchant.vo.*;
import com.huohuzhihui.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.huohuzhihui.common.annotation.Log;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author mzy
 * @email 1992lcg@163.com
 * @date ${datetime}
 */

@Controller
@RequestMapping("/home")
public class ApiCostController extends BaseController {
    @Autowired
    private IAccUserAccountService accUserAccountService;

    @Autowired
    private IMerCostService merCostService;

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IMerDeviceService merDeviceService;

    /**
     * 根据主键查询
     *
     * @return
     */
    @GetMapping("/index")
    @ResponseBody
    PosJsonResult index(HttpServletRequest request, @RequestParam("mode") String mode, String machine_Number, String sign) {
        PosJsonResult posJsonResult = new PosJsonResult();
        if("CompanyMember".equalsIgnoreCase(mode)){//获取人员信息
            int pageSize = Integer.parseInt(request.getParameter("pageSize"));
            int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
            try {
                List<PosUser> userList = merCostService.findCompanyMemberList(pageIndex,pageSize);

                Map map = new HashMap();
                map.put("Results",userList);
                map.put("totalCount",userList.size());
                map.put("totalPage",1);
                map.put("pageIndex",pageIndex);
                map.put("pageSize",pageSize);

                posJsonResult.setCode(10000);
                posJsonResult.setMessage("成功");
                posJsonResult.setData(map);

                //设置设备不再去更新人员
                for(PosUser posUser : userList){
                    long userId = posUser.getUniqueNumber();
                    SysUser sysUser = sysUserService.selectUserById(userId);
                    sysUser.setInPos(1);
                    this.sysUserService.updateInPos(sysUser);
                }

            }catch (PosException e){
                posJsonResult.setCode(e.getCode());
                posJsonResult.setMessage(e.getMessage());
            }
        }
        if("ReportDeviceStatus".equalsIgnoreCase(mode)){//获取心跳（同步设备在线离线状态）
            try {
                PosInfo posInfo = merCostService.reportDeviceStatus(machine_Number);
                posJsonResult.setCode(10000);
                posJsonResult.setMessage("成功");
                posJsonResult.setData(posInfo);
            }catch (PosException e){
                posJsonResult.setCode(e.getCode());
                posJsonResult.setMessage(e.getMessage());
            }
        }

        if("ModifyBalance".equalsIgnoreCase(mode)){//消费
            String uploadTime = request.getParameter("uploadTime");
            String outTradeNo = request.getParameter("out_trade_no");
            String cardNumber = request.getParameter("cardNumber");
            int consumptionType = Integer.parseInt(request.getParameter("consumption_type"));
            String money = request.getParameter("money");
            int deductionType =  Integer.parseInt(request.getParameter("deduction_Type"));
            String terminalParams = request.getParameter("terminal_params");

            try {
                //验证设备
                MerDevice merDevice = merDeviceService.selectMerDeviceByNo(machine_Number);
                if(merDevice==null){
                    throw new PosException(10029,"设备信息不存在");
                }
                if(merDevice.getStatus()==0){
                    throw new PosException(10029,"设备未启用");
                }

                PosCost posCost = merCostService.modifyBalance( machine_Number,  cardNumber,  deductionType,  money,  consumptionType,  outTradeNo);
                posJsonResult.setCode(10000);
                posJsonResult.setMessage("消费成功");
                posJsonResult.setData(posCost);
            }catch (PosException e){
                posJsonResult.setCode(e.getCode());
                posJsonResult.setMessage(e.getMessage());
            }
        }
        if("company_setup".equalsIgnoreCase(mode)){//获取公司名称
            posJsonResult.setCode(10000);
            posJsonResult.setMessage("成功");
            posJsonResult.setCompany("兰州纳百");
        }
        if("OfflineSet".equalsIgnoreCase(mode)){//获取脱机设置

            PosOfflineSet posOfflineSet = new PosOfflineSet();
            posOfflineSet.setfCompanyId(null);
            posOfflineSet.setfCreatorTime(null);
            posOfflineSet.setfId(null);
            posOfflineSet.setLimitMoney(10000.00);
            posOfflineSet.setLimitCount(255);
            posOfflineSet.setMachineNumber(machine_Number);

            posJsonResult.setCode(10000);
            posJsonResult.setMessage("成功");
            posJsonResult.setData(posOfflineSet);
        }
        /*if("GetFullRyxxByCardNo".equalsIgnoreCase(mode)){//余额查询
            try {
                String cardNumber = request.getParameter("cardNumber");
                PosUser posUser = posService.getFullRyxxByCardNo(cardNumber);
                posJsonResult.setCode(10000);
                posJsonResult.setMessage("成功");
                posJsonResult.setData(posUser);
            }catch (PosException e){
                posJsonResult.setCode(e.getCode());
                posJsonResult.setMessage(e.getMessage());
            }

        }
        if("ConsumeRecordList".equalsIgnoreCase(mode)){//查询消费记录
            try {
                String cardNumber = request.getParameter("cardNumber");
                int pageSize = Integer.parseInt(request.getParameter("pageSize"));
                int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));

                Map consumeRecordList = posService.findConsumeRecordList(cardNumber,pageIndex,pageSize);

                Map map = new HashMap();
                map.put("Results",consumeRecordList.get("data"));
                map.put("totalCount",consumeRecordList.get("totalCount"));
                map.put("totalPage",Integer.parseInt(consumeRecordList.get("totalCount").toString())/pageSize==0?Integer.parseInt(consumeRecordList.get("totalCount").toString())/pageSize:Integer.parseInt(consumeRecordList.get("totalCount").toString())/pageSize+1);
                map.put("pageIndex",pageIndex);
                map.put("pageSize",pageSize);

                posJsonResult.setCode(10000);
                posJsonResult.setMessage("成功");
                posJsonResult.setData(map);
            }catch (PosException e){
                posJsonResult.setCode(e.getCode());
                posJsonResult.setMessage(e.getMessage());
            }

        }

        if("GetConsumptionStatistics3".equalsIgnoreCase(mode)){//消费或充值统计
            try {
                String bdate = request.getParameter("bdate");
                String edate = request.getParameter("edate");
                int type = Integer.parseInt(request.getParameter("type"));//10-消费 20-充值

                PosConsumptionStatistics posConsumptionStatistics = posService.getConsumptionStatistics(machine_Number);

                posJsonResult.setCode(10000);
                posJsonResult.setMessage("成功");
                posJsonResult.setData(posConsumptionStatistics);
            }catch (PosException e){
                posJsonResult.setCode(e.getCode());
                posJsonResult.setMessage(e.getMessage());
            }
        }
*/
        return posJsonResult;
    }

    /**
     * 查询商户消费列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:merchant:list')")
    @GetMapping("/list")
    public TableDataInfo list(MerCost merCost)
    {
        startPage();
        List<MerCost> list = merCostService.selectMerCostList(merCost);
        return getDataTable(list);
    }

    /**
     * 导出商户消费列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:merchant:export')")
    @Log(title = "商户消费", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MerCost merCost)
    {
        List<MerCost> list = merCostService.selectMerCostList(merCost);
        ExcelUtil<MerCost> util = new ExcelUtil<MerCost>(MerCost.class);
        return util.exportExcel(list, "merchant");
    }

}
