 /**
 * v-hasPermi 操作权限处理
 * Copyright (c) 2019 qdata
 */

 import useUserStore from '@/store/system/user'

 export default {
   mounted(el, binding, vnode) {
     const { value } = binding
     const all_permission = "*:*:*";
     const permissions = useUserStore().permissions

     // ✅ 支持字符串或数组传参
     let permissionFlag = []
     if (typeof value === 'string') {
       permissionFlag = [value]
     } else if (value instanceof Array) {
       permissionFlag = value
     } else {
       throw new Error(`指令参数必须是字符串或数组，当前类型为 ${typeof value}`)
     }

     const hasPermissions = permissions.some(permission => {
       return all_permission === permission || permissionFlag.includes(permission)
     })

     if (!hasPermissions) {
       el.parentNode && el.parentNode.removeChild(el)
     }
   }
 }
