export default [
    {
        path: '/dso/cluster',
        component: () => import('@/views/dso/cluster/index.vue'),
        name: 'ClusterManagement',
        meta: {
            title: '集群管理',
            permissions: ['dso:cluster:view'] // 权限标识符
        }
    },
    {
        path: '/dso/alerts',
        component: () => import('@/views/dso/alerts/index.vue'),
        name: 'AlertManagement',
        meta: {
            title: '告警中心',
            permissions: ['dso:alert:view'] // 权限标识符
        }
    }
];
