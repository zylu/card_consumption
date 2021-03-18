<template>
  <div class="app-container home align-center">
    <el-row :gutter="40" class="panel-group">
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('messages')">
          <div class="card-panel-icon-wrapper icon-message">
            <svg-icon icon-class="form" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              账户余额
            </div>
            <count-to :start-val="0" :end-val="countData.totalUserBalance" :duration="3000" :decimals="2" class="card-panel-num" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('newVisitis')">
          <div class="card-panel-icon-wrapper icon-people">
            <svg-icon icon-class="peoples" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              当日新增人数
            </div>
            <count-to :start-val="0" :end-val="countData.addUserCount" :duration="2600" class="card-panel-num" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('shoppings')">
          <div class="card-panel-icon-wrapper icon-shopping">
            <i class="el-icon-postcard card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              当日补卡人数
            </div>
            <count-to :start-val="0" :end-val="countData.replaceUserCount" :duration="3600" class="card-panel-num" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('messages')">
          <div class="card-panel-icon-wrapper icon-message">
            <i class="el-icon-s-custom card-panel-icon"/>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              当日注销人数
            </div>
            <count-to :start-val="0" :end-val="countData.cancelUserCount" :duration="3000" class="card-panel-num" />
          </div>
        </div>
      </el-col>

    </el-row>

    <el-row :gutter="40" class="panel-group">
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('purchases')">
          <div class="card-panel-icon-wrapper icon-money">
            <svg-icon icon-class="money" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              当日营业额
            </div>
            <count-to :start-val="0" :end-val="countData.totalOrderAmount" :duration="3200" :decimals="2" class="card-panel-num" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('newVisitis')">
          <div class="card-panel-icon-wrapper icon-people">
            <svg-icon icon-class="people" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              当日消费人数
            </div>
            <count-to :start-val="0" :end-val="countData.userPayCount" :duration="2600" class="card-panel-num" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('purchases')">
          <div class="card-panel-icon-wrapper icon-money">
            <i class="el-icon-folder-add card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              当日增款
            </div>
            <count-to :start-val="0" :end-val="countData.addOrderAmount" :duration="3200" :decimals="2" class="card-panel-num" />
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('shoppings')">
          <div class="card-panel-icon-wrapper icon-shopping">
            <i class="el-icon-folder-remove card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              当日减款
            </div>
            <count-to :start-val="0" :end-val="countData.reduceOrderAmount" :duration="3600" :decimals="2" class="card-panel-num" />
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <line-chart :chart-data="lineChartData" />
    </el-row>
  </div>


</template>

<script>
  //import PanelGroup from './components/PanelGroup'


  import { init ,getChart} from "@/api/dashboard/dashboard";
  import CountTo from 'vue-count-to'

  import LineChart from './dashboard/LineChart'
  import {getGoods} from "@/api/merchant/goods";
  const lineChartData = {
    newVisitis: {
      expectedData: [100, 120, 161, 134, 105, 160, 165],
      actualData: [120, 82, 91, 154, 162, 140, 145]
    }
  }
export default {
  components: {
    CountTo,
    LineChart
  },
  name: "index",
  data() {
    return {
      countData:{
        addUserCount:0,
        cancelUserCount:0,
        replaceUserCount:0,
        addOrderAmount:0,
        reduceOrderAmount:0,
        totalOrderAmount:0,
        userPayCount:0,
        totalUserBalance:0,
      },
      // 版本号
      version: "3.2.0",
      chart: null,
      lineChartData: {
        expectedData:[100, 120, 161, 134, 105, 160, 165],
        actualData:[120, 82, 91, 154, 162, 140, 145]
      }
    };
  },
  created() {
    init().then(response => {
      console.info( response);
      this.countData = response;
    });

    getChart().then(response => {
      console.info( response);
      this.lineChartData.expectedData = response.addOrderList;
      this.lineChartData.actualData = response.reduceOrderList;
    });
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
    handleSetLineChartData(type) {
      this.$emit('handleSetLineChartData', type)
    }
  },
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}


.panel-group {
  margin-top: 18px;

  .card-panel-col {
    margin-bottom: 32px;
  }

  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }

      .icon-people {
        background: #40c9c6;
      }

      .icon-message {
        background: #36a3f7;
      }

      .icon-money {
        background: #f4516c;
      }

      .icon-shopping {
        background: #34bfa3
      }


    }

    .icon-people {
      color: #40c9c6;
    }

    .icon-message {
      color: #36a3f7;
    }

    .icon-money {
      color: #f4516c;
    }

    .icon-shopping {
      color: #34bfa3
    }

    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: left;
      font-size: 48px;
    }

    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }

      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}

@media (max-width:550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;

    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}
</style>

