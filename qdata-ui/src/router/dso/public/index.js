export default [
    {
        path: '/dso',
        component: () => import('@/layout/index.vue'),
        redirect: '/dso/index',
        meta: { title: '运维管理', icon: 'monitor' },
        children: [
            {
                path: 'index',
                component: () => import('@/views/dso/index.vue'),
                name: 'DsoIndex',
                meta: { title: '运维平台入口' }
            }
        ]
    }
];
