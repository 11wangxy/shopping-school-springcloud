import request from '@/utils/request';
import { api_name } from './user';

/*
给某个用户分配角色
roleId的结构: 字符串, 'rId1,rId2,rId3'
*/

export function assignRoles(adminId, roleId) {
    return request({
        url: `${api_name}/doAssign`,
        method: 'post',
        params: {
            adminId,
            roleId
        }
    });
}
