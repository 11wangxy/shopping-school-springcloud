(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-71efb54c"],{4790:function(t,e,a){"use strict";a("99af");var n=a("b775"),c="/admin/user/leader";e["a"]={getPageCheckList:function(t,e){return Object(n["a"])({url:"".concat(c,"/checkList/").concat(t,"/").concat(e),method:"get"})},getPageList:function(t,e){return Object(n["a"])({url:"".concat(c,"/list/").concat(t,"/").concat(e),method:"get"})},getById:function(t){return Object(n["a"])({url:"".concat(c,"/get/").concat(t),method:"get"})},save:function(t){return Object(n["a"])({url:"".concat(c,"/save"),method:"post",data:t})},updateById:function(t){return Object(n["a"])({url:"".concat(c,"/update"),method:"put",data:t})},removeById:function(t){return Object(n["a"])({url:"".concat(c,"/remove/").concat(t),method:"delete"})},removeRows:function(t){return Object(n["a"])({url:"".concat(c,"/batchRemove"),method:"delete",data:t})},check:function(t,e){return Object(n["a"])({url:"".concat(c,"/check/").concat(t,"/").concat(e),method:"post"})}}},9264:function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("el-card",{staticClass:"operate-container",attrs:{shadow:"never"}},[a("el-form",{attrs:{inline:!0}},[a("el-form-item",{attrs:{label:"输入搜索："}},[a("el-input",{staticStyle:{width:"203px"},attrs:{placeholder:"关键字"},model:{value:t.searchObj.keyword,callback:function(e){t.$set(t.searchObj,"keyword",e)},expression:"searchObj.keyword"}})],1),a("el-button",{attrs:{type:"primary",icon:"el-icon-search"},on:{click:function(e){return t.fetchData()}}},[t._v("查询")]),a("el-button",{attrs:{type:"default"},on:{click:function(e){return t.resetData()}}},[t._v("清空")])],1)],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],staticStyle:{width:"100%","margin-top":"10px"},attrs:{data:t.list,stripe:"",border:""}},[a("el-table-column",{attrs:{label:"序号",width:"70",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s((t.page-1)*t.limit+e.$index+1)+" ")]}}])}),a("el-table-column",{attrs:{prop:"name",label:"姓名",width:"100px"}}),a("el-table-column",{attrs:{prop:"phone",label:"手机号码",width:"120px"}}),a("el-table-column",{attrs:{prop:"takeName",label:"提货点名称"}}),a("el-table-column",{attrs:{prop:"param.regionName",label:"所属区域",width:"80px"}}),a("el-table-column",{attrs:{label:"状态",width:"100"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("p",[t._v(t._s(0===e.row.checkStatus?"待审核":1===e.row.checkStatus?"通过":"未通过"))])]}}])}),a("el-table-column",{attrs:{prop:"checkTime",label:"审核时间",width:"150px"}}),a("el-table-column",{attrs:{prop:"createTime",label:"申请时间",width:"150px"}}),a("el-table-column",{attrs:{label:"操作",width:"200",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{type:"text",size:"mini",disabled:!1===t.$hasBP("btn.all")},on:{click:function(a){return t.check(e.row.id,0)}}},[t._v("审核不通过")])]}}])})],1),a("el-pagination",{staticStyle:{padding:"30px 0","text-align":"center"},attrs:{"current-page":t.page,total:t.total,"page-size":t.limit,"page-sizes":[5,10,20,30,40,50,100],layout:"sizes, prev, pager, next, jumper, ->, total, slot"},on:{"current-change":t.fetchData,"size-change":t.changeSize}})],1)},c=[],o=a("4790"),l={data:function(){return{listLoading:!0,list:null,total:0,page:1,limit:10,searchObj:{},multipleSelection:[]}},created:function(){console.log("list created......"),this.fetchData()},mounted:function(){console.log("list mounted......")},methods:{changeSize:function(t){console.log(t),this.limit=t,this.fetchData(1)},fetchData:function(){var t=this,e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:1;console.log("翻页。。。"+e),this.page=e,o["a"].getPageList(this.page,this.limit,this.searchObj).then((function(e){t.list=e.data.records,t.total=e.data.total,t.listLoading=!1}))},resetData:function(){console.log("重置查询表单"),this.searchObj={},this.fetchData()},check:function(t,e){var a=this;o["a"].check(t,e).then((function(t){a.$message({type:"success",message:"操作成功!"}),a.fetchData()}))}}},i=l,r=a("2877"),s=Object(r["a"])(i,n,c,!1,null,null,null);e["default"]=s.exports}}]);