package com.huohuzhihui.merchant.service.impl;

import com.huohuzhihui.account.service.IAccCardService;
import com.huohuzhihui.account.service.IAccUserAccountService;
import com.huohuzhihui.common.core.domain.entity.Account;
import com.huohuzhihui.common.core.domain.entity.SysUser;
import com.huohuzhihui.common.utils.DateUtils;
import com.huohuzhihui.merchant.domain.MerCost;
import com.huohuzhihui.merchant.domain.MerCostReport;
import com.huohuzhihui.merchant.domain.MerDevice;
import com.huohuzhihui.merchant.exception.PosException;
import com.huohuzhihui.merchant.mapper.MerCostMapper;
import com.huohuzhihui.merchant.service.IMerCostService;
import com.huohuzhihui.merchant.service.IMerDeviceService;
import com.huohuzhihui.merchant.vo.PosCost;
import com.huohuzhihui.merchant.vo.PosInfo;
import com.huohuzhihui.merchant.vo.PosUser;
import com.huohuzhihui.system.service.ISysUserService;
import com.huohuzhihui.trade.domain.TradeOrder;
import com.huohuzhihui.trade.domain.TradeOrderReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MerCostServiceImpl implements IMerCostService {

    @Autowired
    private IAccUserAccountService accUserAccountService;
    @Autowired
    private IMerDeviceService merDeviceService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private MerCostMapper merCostMapper;


    @Override
    public List<PosUser> findCompanyMemberList(Integer pageIndex, Integer pageSize) {
        SysUser query = new SysUser();
        query.setInPos(0);
        List<SysUser> userList = this.sysUserService.selectUserList(query);
        if(userList ==null || userList.size()==0){
            throw new PosException(10028,"当前页不存在");
        }
        List<PosUser> posUserList = new ArrayList<PosUser>();
        for(int i = 0 ; i < userList.size(); i++){
            SysUser sysUser = userList.get(i);

            PosUser user = new PosUser();

            int cardStatus = sysUser.getCardStatus();//0，正常，1挂失，2注销
            if(cardStatus==0){
                user.setCardState(32);//32:正常卡 33:解挂未完全卡  64:注销卡  96:挂失未完全卡 160:回收卡
            }else if(cardStatus==1){
                user.setCardState(33);
            }else if(cardStatus==2){
                user.setCardState(64);
            }
            user.setUniqueNumber(sysUser.getUserId());
            user.setUid(sysUser.getUserId()+"");
            user.setCardNumber(sysUser.getCardNo());//学号作为卡号
            user.setFullName(sysUser.getUserName());
            user.setBalance(sysUser.getBalance());
            user.setImgData(sysUser.getAvatar());
            user.setLimitTimes1(255);
            user.setLimitTimes2(255);
            user.setLimitTimes3( 255);
            user.setLimitTimes4(255);
            user.setConsumptionQuota(10000.0000);

            posUserList.add(user);
        }
        return posUserList;
    }

    @Override
    public PosCost modifyBalance(String machineNumber, String cardNumber, int deductionType, String money, int consumptionType, String orderNumber) {
        SysUser sysUser = this.sysUserService.selectUserByCardNo(cardNumber);
        if(sysUser==null){
            throw new PosException(10008 ,"无效卡");
        }
        if(sysUser.getAccountStatus()!=0||!"0".equals(sysUser.getStatus())||sysUser.getCardStatus()!=0){
            throw new PosException(10010  ,"卡状态为非正常卡");
        }
        if(sysUser.getBalance().compareTo(new BigDecimal(money)) == -1){
            //余额不足
            throw new PosException(10019,"余额不足,无法消费");
        }

        //查询设备
        MerDevice merDevice = this.merDeviceService.selectMerDeviceByNo(machineNumber);

        //插入消费记录
        MerCost cost = new MerCost();
        cost.setAmount(new BigDecimal(money));
        cost.setCardNo(cardNumber);
        cost.setCreateTime(new Date());
        cost.setPosNo(machineNumber);
        cost.setUserId(sysUser.getUserId());
        cost.setOrderNo(orderNumber);
        cost.setMerchantId(merDevice.getMerchantId());
        merCostMapper.insertMerCost(cost);

        //更新账户余额
        Account accout = new Account();
        accout.setUserId(sysUser.getUserId());
        accout.setBalance(sysUser.getBalance().subtract(new BigDecimal(money)));
        this.accUserAccountService.updateAccUserAccountByUserId(accout);

        PosCost posCost = new PosCost();
        posCost.setBalance(accout.getBalance());
        posCost.setFullName(sysUser.getUserName());
        posCost.setConsumptionMone(new BigDecimal(money));
        return posCost;
    }

    @Override
    public PosInfo reportDeviceStatus(String machineNumber) {
        MerDevice merDevice = merDeviceService.selectMerDeviceByNo(machineNumber);
        if(merDevice==null){
            throw new PosException(10029,"设备信息不存在");
        }
        PosInfo posInfo = new PosInfo();
        if(merDevice.getStatus()==1){//设备已启用
            posInfo.setIsOpen(1);
        }else{
            posInfo.setIsOpen(0);
        }

        posInfo.setUpdateConfig("0");
        posInfo.setUpdateUserInfo("1");//自动更新人员信息
        return posInfo;
    }

   /* @Override
    public PosUser getFullRyxxByCardNo(String cardNumber) {
        Tcommaccount tcommaccount = accountFeignClient.getTcommaccountByCardNo(cardNumber);
        if(tcommaccount==null){
            throw new PosException(10008,"无效卡号");
        }
        PosUser user = new PosUser();
        user.setUniqueNumber(tcommaccount.getAccId());
        user.setCardClass("A");

        if("1".equals(tcommaccount.getCardStatus())){
            user.setCardState(32);//32:正常卡 33:解挂未完全卡  64:注销卡  96:挂失未完全卡 160:回收卡
        } else if("0".equals(tcommaccount.getCardStatus())){
            user.setCardState(96);//32:正常卡 33:解挂未完全卡  64:注销卡  96:挂失未完全卡 160:回收卡
        }else if("9".equals(tcommaccount.getCardStatus())){
            user.setCardState(64);//32:正常卡 33:解挂未完全卡  64:注销卡  96:挂失未完全卡 160:回收卡
        }
        user.setUid(tcommaccount.getAccId()+"");
        user.setCardNumber(cardNumber);
        user.setFullName(tcommaccount.getAccCname());
        user.setBalance(tcommaccount.getBalance());
        user.setOpeningDate(DateUtil.formatDate(tcommaccount.getAccCrdate(),"yyyy-MM-dd HH:mm:ss"));
        user.setLimitTimes1(255);
        user.setLimitTimes2(255);
        user.setLimitTimes3(255);
        user.setLimitTimes4(255);
        user.setConsumptionQuota(255.0000);
        user.setTermOfValidity(tcommaccount.getAccAvdate());

        return user;
    }

    @Override
    public Map findConsumeRecordList(String cardNumber, int pageIndex, int pageSize) {
        Ttnsmflwing ttnsmflwing = new Ttnsmflwing();
        ttnsmflwing.setFlwCrdid(cardNumber);
        Map map = this.merchantFeignClient.findByPage(pageIndex,pageSize,null,ttnsmflwing);
        List<LinkedHashMap<String,Object>> ttnsmflwingList = (List<LinkedHashMap<String,Object>>)map.get("data");
        if(ttnsmflwingList ==null || ttnsmflwingList.size()==0){
            throw new PosException(10028,"当前页不存在");
        }

        List<PosConsumeRecord> posConsumeRecordList = new ArrayList<PosConsumeRecord>();
        for(LinkedHashMap<String,Object> linkedHashMap : ttnsmflwingList){
            PosConsumeRecord posConsumeRecord = new PosConsumeRecord();
            posConsumeRecord.setStatus(0);
            for(Map.Entry<String, Object> entry : linkedHashMap.entrySet()) {
                if("flwContime".equalsIgnoreCase(entry.getKey())){
                    if(entry.getValue()!=null){
                        posConsumeRecord.setBizDate(entry.getValue().toString());
                    }
                }
                if("flwAmount".equalsIgnoreCase(entry.getKey())){
                    if(entry.getValue()!=null){
                        posConsumeRecord.setBizAmount((Double) entry.getValue());
                    }
                }
                if("accCname".equalsIgnoreCase(entry.getKey())){
                    if(entry.getValue()!=null){
                        posConsumeRecord.setFullName(entry.getValue().toString());
                    }
                }
                if("flwId".equalsIgnoreCase(entry.getKey())){
                    if(entry.getValue()!=null){
                        posConsumeRecord.setId( entry.getValue().toString());
                    }
                }
                if("flwCrdid".equalsIgnoreCase(entry.getKey())){
                    if(entry.getValue()!=null){
                        posConsumeRecord.setCardNumber( entry.getValue().toString());
                    }
                }
            }
            posConsumeRecordList.add(posConsumeRecord);
        }

        Map returnMap = new HashMap();
        returnMap.put("totalCount",map.get("count"));
        returnMap.put("data",posConsumeRecordList);
        return returnMap;
    }

    @Override
    public PosConsumptionStatistics getConsumptionStatistics(String mechineNumber) {
        Map map = this.merchantFeignClient.getCostSumAndCount(mechineNumber);
        PosConsumptionStatistics posConsumptionStatistics = new PosConsumptionStatistics();
        posConsumptionStatistics.setConsumptionMone(Double.parseDouble(map.get("sum").toString()));
        posConsumptionStatistics.setCounts(Integer.parseInt(map.get("count").toString()));
        posConsumptionStatistics.setMachineNumber(mechineNumber);
        return posConsumptionStatistics;
    }*/

    /**
     * 查询商户消费列表
     *
     * @param merCost 商户消费
     * @return 商户消费
     */
    @Override
    public List<MerCost> selectMerCostList(MerCost merCost)
    {
        return merCostMapper.selectMerCostList(merCost);
    }

    /**
     * 新增商户消费
     *
     * @param merCost 商户消费
     * @return 结果
     */
    @Override
    public int insertMerCost(MerCost merCost)
    {
        merCost.setCreateTime(DateUtils.getNowDate());
        return merCostMapper.insertMerCost(merCost);
    }

    @Override
    public List<MerCostReport> getOrderStatisticsByMerchantId(Long merchantId, String beginDate, String endDate) {
        MerCost merCost = new MerCost();
        merCost.setBeginTime(beginDate);
        merCost.setEndTime(endDate);
        merCost.setMerchantId(merchantId);
        return merCostMapper.getMerCostStatistics(merCost);
    }


}
