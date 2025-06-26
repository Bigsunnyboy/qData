<template>
  <div class="dso-container">
    <h2>运维管理中心</h2>

    <!-- 运维平台访问权限 -->
    <el-button v-hasPermi="'dso:view'" type="primary" @click="goToDataSophon">
      进入 DataSophon 运维平台
    </el-button>

    <div class="metrics">
      <!-- 集群指标查看权限 -->
      <p v-hasPermi="'dso:metric:view'">当前集群数：<b>{{ clusterCount }}</b></p>

      <!-- 告警指标查看权限 -->
      <p v-hasPermi="'dso:alert:view'">未读告警数：<b style="color:red">{{ alertCount }}</b></p>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from '@/utils/request';

const clusterCount = ref(0);
const alertCount = ref(0);

const fetchMetrics = async () => {
  try {
    const [clusterRes, alertRes] = await Promise.all([
      axios.get('/dso/metrics/clusters'),
      axios.get('/dso/metrics/alerts')
    ]);
    clusterCount.value = clusterRes.data || 0;
    alertCount.value = alertRes.data || 0;
  } catch (error) {
    console.error('获取指标失败:', error);
  }
};

onMounted(fetchMetrics);

const goToDataSophon = () => {
  const token = localStorage.getItem('token');
  window.open(`${datasophonUrl}?auth_token=${token}`);
};
</script>

<style scoped>
.dso-container {
  padding: 20px;
}
.metrics {
  margin-top: 20px;
}
</style>
